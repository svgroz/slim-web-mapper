package org.svgroz.slim.processor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Context {
    private String packageName;
    private String className;
    private List<Method> getMethods = new ArrayList<>();
    private List<Method> postMethods = new ArrayList<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public List<Method> getGetMethods() {
        return getMethods;
    }

    public List<Method> getPostMethods() {
        return postMethods;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Context.class.getSimpleName() + "[", "]")
                .add("packageName='" + packageName + "'")
                .add("className='" + className + "'")
                .add("getMethods=" + getMethods)
                .add("postMethods=" + postMethods)
                .toString();
    }
}
