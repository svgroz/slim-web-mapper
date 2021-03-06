package org.svgroz.slim.processor;

import org.svgroz.slim.api.Controller;
import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Post;
import org.svgroz.slim.processor.model.Context;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.Objects;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RootVisitor extends BasicVisitor<Context, Void> {
    private final Messager messager;

    public RootVisitor(final Messager messager) {
        this.messager = Objects.requireNonNull(messager);
    }

    @Override
    public Context visitType(final TypeElement e, final Void unused) {
        final Controller controller = e.getAnnotation(Controller.class);
        if (!e.getKind().isInterface()) {
            throw new IllegalArgumentException("Only interfaces supported for @" + Controller.class.getName());
        }

        var ctx = new Context();

        var rootUrls = controller.value();
        for (String rootUrl : rootUrls) {
            rootUrl = rootUrl.isEmpty() ? "/" : rootUrl;
            rootUrl = rootUrl.startsWith("/") ? rootUrl : "/" + rootUrl;
            rootUrl = rootUrl.length() != 1  && rootUrl.endsWith("/") ? rootUrl.substring(0, rootUrl.length() - 1) : rootUrl;
            ctx.getRootUrls().add(rootUrl);
        }

        var className = e.getSimpleName().toString();
        ctx.setClassName(className);
        var packageName = e.getEnclosingElement().accept(new PackageVisitor(), null).orElseThrow(() -> new IllegalArgumentException("Could not find package of: " + e));
        ctx.setPackageName(packageName);

        for (final Element enclosedElement : e.getEnclosedElements()) {
            if (enclosedElement.getKind() != ElementKind.METHOD) {
                continue;
            }

            if (enclosedElement.getAnnotation(Get.class) != null) {
                ctx.addMethod(enclosedElement.accept(new MethodVisitor(), HttpMethodType.GET));
            }

            if (enclosedElement.getAnnotation(Post.class) != null) {
                ctx.addMethod(enclosedElement.accept(new MethodVisitor(), HttpMethodType.POST));
            }
        }

        return ctx;
    }
}
