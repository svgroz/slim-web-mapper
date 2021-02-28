package org.svgroz.slim.processor;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Context {
    private String packageName;
    private String className;

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
}
