package org.svgroz.slim.processor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Context {
    private String packageName;
    private String className;
    private SortedSet<String> rootUrls = new TreeSet<>();
    private final SortedSet<String> imports = new TreeSet<>();
    private final List<Method> methods = new ArrayList<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public Set<String> getRootUrls() {
        return rootUrls;
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

    public List<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        Optional<Method> first = methods.stream().filter(m -> m.getMethodName().equals(method.getMethodName()))
                .findFirst();
        if (first.isEmpty()) {
            methods.add(method);
            return;
        }

        first.get().getGetUrls().addAll(method.getGetUrls());
        first.get().getPostUrls().addAll(method.getPostUrls());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Context.class.getSimpleName() + "[", "]")
                .add("packageName='" + packageName + "'")
                .add("className='" + className + "'")
                .add("rootUrls='" + rootUrls + "'")
                .add("imports=" + imports)
                .toString();
    }
}
