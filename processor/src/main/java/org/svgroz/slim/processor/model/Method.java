package org.svgroz.slim.processor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Method {
    private String methodName;
    private List<Argument> argumentList = new ArrayList<>();

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public List<Argument> getArgumentList() {
        return argumentList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Method.class.getSimpleName() + "[", "]")
                .add("methodName='" + methodName + "'")
                .add("argumentList=" + argumentList)
                .toString();
    }
}
