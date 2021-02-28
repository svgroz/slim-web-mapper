package org.svgroz.slim.processor.model;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Argument {
    private String argumentName;

    private ArgumentType argumentType;

    public String getArgumentName() {
        return argumentName;
    }

    public void setArgumentName(final String argumentName) {
        this.argumentName = argumentName;
    }

    public ArgumentType getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(final ArgumentType argumentType) {
        this.argumentType = argumentType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Argument.class.getSimpleName() + "[", "]")
                .add("argumentName=" + argumentName)
                .add("argumentType=" + argumentType)
                .toString();
    }
}
