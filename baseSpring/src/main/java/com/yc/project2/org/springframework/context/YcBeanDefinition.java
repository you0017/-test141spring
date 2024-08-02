package com.yc.project2.org.springframework.context;

/**
 * Bean的元信息类
 */
public class YcBeanDefinition {
    private boolean isLazy;
    private String scope = "singleton";
    private boolean isPrimary;
    private String classInfo;//这个是此类的全路径，通过它可以反射得到这个类

    @Override
    public String toString() {
        return "YcBeanDefinition{" +
                "isLazy=" + isLazy +
                ", scope='" + scope + '\'' +
                ", isPrimary=" + isPrimary +
                ", classInfo='" + classInfo + '\'' +
                '}';
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }
}
