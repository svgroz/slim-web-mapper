package org.svgroz.slim.processor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Context {
    private String packageName;
    private String className;
    private String rootUrl;
    private final SortedSet<String> imports = new TreeSet<>();
    private final List<Method> getMethods = new ArrayList<>();
    private final List<Method> postMethods = new ArrayList<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(final String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public SortedSet<String> getImports() {
        return imports;
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
                .add("rootUrl='" + rootUrl + "'")
                .add("imports=" + imports)
                .add("getMethods=" + getMethods)
                .add("postMethods=" + postMethods)
                .toString();
    }
}
