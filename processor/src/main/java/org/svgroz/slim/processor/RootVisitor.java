package org.svgroz.slim.processor;

import org.svgroz.slim.api.Controller;
import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Post;
import org.svgroz.slim.processor.model.Context;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
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
        if (e.getAnnotation(Controller.class) != null && !e.getKind().isInterface()) {
            throw new IllegalArgumentException("Only interfaces supported for @" + Controller.class.getName());
        }

        var ctx = new Context();
        var className = e.getSimpleName().toString();
        ctx.setClassName(className);
        var packageName = e.getEnclosingElement().accept(new PackageVisitor(), null).orElseThrow(() -> new IllegalArgumentException("Could not find package of: " + e));
        ctx.setPackageName(packageName);

        final List<Element> getMethods = new ArrayList<>();
        final List<Element> postMethods = new ArrayList<>();

        for (final Element enclosedElement : e.getEnclosedElements()) {
            if (enclosedElement.getKind() != ElementKind.METHOD) {
                continue;
            }

            if (enclosedElement.getAnnotation(Get.class) != null) {
                getMethods.add(enclosedElement);
            } else if (enclosedElement.getAnnotation(Post.class) != null) {
                postMethods.add(enclosedElement);
            }
        }



        messager.printMessage(Diagnostic.Kind.OTHER, "Class name is " + className);
        messager.printMessage(Diagnostic.Kind.OTHER, "Package name is " + packageName);

        return ctx;
    }
}