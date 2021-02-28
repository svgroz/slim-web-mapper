package org.svgroz.slim.processor.model;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Argument {
    private ArgumentType argumentType;

    public ArgumentType getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(final ArgumentType argumentType) {
        this.argumentType = argumentType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Argument.class.getSimpleName() + "[", "]")
                .add("argumentType=" + argumentType)
                .toString();
    }
}
