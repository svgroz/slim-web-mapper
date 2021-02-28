package org.svgroz.slim.processor;

import org.svgroz.slim.api.Parameter;
import org.svgroz.slim.processor.model.Argument;

import javax.lang.model.element.VariableElement;
import java.util.Optional;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MethodArgumentVisitor extends BasicVisitor<Argument, Void> {
    @Override
    public Argument visitVariable(final VariableElement e, final Void unused) {
        if (e.getAnnotation(Parameter.class) == null) {
            throw new IllegalArgumentException("All method arguments has to have @" + Parameter.class.getName());
        }

        var arg = new Argument();
        return arg;
    }
}
