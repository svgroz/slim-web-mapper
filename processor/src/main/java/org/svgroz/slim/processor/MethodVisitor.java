package org.svgroz.slim.processor;

import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Post;
import org.svgroz.slim.processor.model.Method;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.util.ArrayList;
import java.util.List;

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
                method.getGetUrls().addAll(formatUrls(urls));
                break;
            case POST:
                urls = e.getAnnotation(Post.class).value();
                method.getPostUrls().addAll(formatUrls(urls));
                break;
            default:
                throw new UnsupportedOperationException();
        }

        method.setVoid("void".equals(e.getReturnType().toString()));

        for (final Element enclosedElement : e.getParameters()) {
            method.getArgumentList().add(enclosedElement.accept(new MethodArgumentVisitor(), null));
        }

        return method;
    }

    private List<String> formatUrls(String[] urls) {
        ArrayList<String> strings = new ArrayList<>();
        for (String url : urls) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }
            if (!url.startsWith("/")) {
                url = "/" + url;
            }
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }
            strings.add(url);
        }
        return strings;
    }
}
