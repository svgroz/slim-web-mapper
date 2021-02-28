package org.svgroz.slim.processor;

import org.svgroz.slim.processor.model.Method;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MethodVisitor extends BasicVisitor<Method, Void> {
    @Override
    public Method visitExecutable(final ExecutableElement e, final Void unused) {
        var method = new Method();
        var methodName = e.getSimpleName().toString();
        method.setMethodName(methodName);

        for (final Element enclosedElement : e.getParameters()) {
            method.getArgumentList().add(enclosedElement.accept(new MethodArgumentVisitor(), null));
        }

        return method;
    }
}
