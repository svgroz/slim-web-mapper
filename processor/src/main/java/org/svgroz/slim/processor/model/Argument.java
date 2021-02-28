package org.svgroz.slim.processor.model;

import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Argument {
    private int argumentNumber;
    private ArgumentType argumentType;

    public int getArgumentNumber() {
        return argumentNumber;
    }

    public void setArgumentNumber(final int argumentNumber) {
        this.argumentNumber = argumentNumber;
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
                .add("argumentNumber=" + argumentNumber)
                .add("argumentType=" + argumentType)
                .toString();
    }
}
