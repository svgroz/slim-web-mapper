package org.svgroz.slim.processor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Method {
    private String methodName;
    private final SortedSet<String> urls = new TreeSet<>();
    private final List<Argument> argumentList = new ArrayList<>();
    private boolean isVoid = true;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public SortedSet<String> getUrls() {
        return urls;
    }

    public List<Argument> getArgumentList() {
        return argumentList;
    }

    public boolean getIsVoid() {
        return isVoid;
    }

    public void setVoid(final boolean aVoid) {
        isVoid = aVoid;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Method.class.getSimpleName() + "[", "]")
                .add("methodName='" + methodName + "'")
                .add("urls=" + urls)
                .add("argumentList=" + argumentList)
                .add("isVoid=" + isVoid)
                .toString();
    }
}
