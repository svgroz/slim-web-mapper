package org.svgroz.slim.processor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Argument {
    private List<String> argumentNames = new ArrayList<>();

    private ArgumentType argumentType;

    public List<String> getArgumentNames() {
        return argumentNames;
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
                .add("argumentNames=" + argumentNames)
                .add("argumentType=" + argumentType)
                .toString();
    }
}
