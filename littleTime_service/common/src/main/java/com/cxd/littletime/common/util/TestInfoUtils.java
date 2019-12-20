package com.cxd.littletime.common.util;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author YiBuBuHuiTou
 */
public class TestInfoUtils extends TestWatcher {
    /**
     * 方法名
     */
    private String methodName;

    /**
     *类名
     */
    private String className;

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return super.apply(base, description);
    }

    @Override
    protected void succeeded(Description description) {
        super.succeeded(description);
    }

    @Override
    protected void starting(Description description) {
        // 类名
        String classTemp = description.getClassName();
        // 方法名
        String methodTemp = description.getMethodName().substring(4);
        String[] classes = classTemp.substring(0, classTemp.length()-4).split("\\.");
        // 处理后不带包名，不带test的类名
        this.className = classes[classes.length-1];
        // 处理后不带test的方法名
        this.methodName = methodTemp.substring(0,1).toLowerCase() + methodTemp.substring(1);
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
