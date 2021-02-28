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
    private Long id = 0L;
    private String packageName;
    private String className;
    private SortedSet<String> imports = new TreeSet<>();
    private List<Method> getMethods = new ArrayList<>();
    private List<Method> postMethods = new ArrayList<>();

    public Long getId() {
        return id++;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public SortedSet<String> getImports() {
        return imports;
    }

    public String getClassName() {
        return className + "Impl";
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public List<Method> getGetMethods() {
        return getMethods;
    }

    public void setGetMethods(final List<Method> getMethods) {
        this.getMethods = getMethods;
    }

    public List<Method> getPostMethods() {
        return postMethods;
    }

    public void setPostMethods(final List<Method> postMethods) {
        this.postMethods = postMethods;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Context.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("packageName='" + packageName + "'")
                .add("className='" + className + "'")
                .add("imports=" + imports)
                .add("getMethods=" + getMethods)
                .add("postMethods=" + postMethods)
                .toString();
    }
}
