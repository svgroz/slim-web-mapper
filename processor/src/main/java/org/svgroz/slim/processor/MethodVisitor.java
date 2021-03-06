package org.svgroz.slim.processor;

import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Post;
import org.svgroz.slim.processor.model.Method;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MethodVisitor extends BasicVisitor<Method, HttpMethodType> {

    @Override
    public Method visitExecutable(final ExecutableElement e, final HttpMethodType type) {
        var method = new Method();
        var methodName = e.getSimpleName().toString();
        method.setMethodName(methodName);

        String[] urls;
        switch (type) {
            case GET:
                urls = e.getAnnotation(Get.class).value();
                break;
            case POST:
                urls = e.getAnnotation(Post.class).value();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        for (String url : urls) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            if (!url.startsWith("/")) {
                url = "/" + url;
            }
            if (!url.endsWith("/")) {
                url = url + "/";
            }
            method.getUrls().add(url);
        }

        method.setVoid("void".equals(e.getReturnType().toString()));

        for (final Element enclosedElement : e.getParameters()) {
            method.getArgumentList().add(enclosedElement.accept(new MethodArgumentVisitor(), null));
        }

        return method;
    }
}
