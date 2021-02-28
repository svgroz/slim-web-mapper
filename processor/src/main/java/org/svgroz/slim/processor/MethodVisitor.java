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
        var methodName = e.getSimpleName().toString();

        for (final Element enclosedElement : e.getEnclosedElements()) {
            var arg = enclosedElement.accept(new MethodArgumentVisitor(), null);

        }

        var method = new Method();
        method.setMethodName(methodName);

        return method;
    }
}
