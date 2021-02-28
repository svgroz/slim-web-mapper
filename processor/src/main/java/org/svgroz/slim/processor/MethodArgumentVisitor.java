package org.svgroz.slim.processor;

import org.svgroz.slim.api.Parameter;
import org.svgroz.slim.processor.model.Argument;
import org.svgroz.slim.processor.model.ArgumentType;

import javax.lang.model.element.VariableElement;
import java.math.BigDecimal;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class MethodArgumentVisitor extends BasicVisitor<Argument, Void> {
    @Override
    public Argument visitVariable(final VariableElement e, final Void unused) {
        if (e.getAnnotation(Parameter.class) == null) {
            throw new IllegalArgumentException("All method arguments has to have @" + Parameter.class.getName());
        }

        var type = e.asType().toString();
        final ArgumentType argumentType;
        if (String.class.getName().equals(type)) {
            argumentType = ArgumentType.REQUIRED_STRING;
        } else if (BigDecimal.class.getName().equals(type)) {
            argumentType = ArgumentType.REQUIRED_NUMERIC;
        } else {
            throw new IllegalArgumentException("Unsupported arg type: " + type);
        }

        var arg = new Argument();
        arg.setArgumentType(argumentType);
        return arg;
    }
}
