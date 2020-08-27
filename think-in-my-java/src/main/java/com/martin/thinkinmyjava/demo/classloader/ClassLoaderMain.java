package com.martin.thinkinmyjava.demo.classloader;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Martin
 * @description
 * @date 2020/5/6
 */
public class ClassLoaderMain {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        //这个类class的路径
        String classPath = "D:\\_Develop/think-in-my-spring/think-in-my-java/src/main/java/com/martin/thinkinmyjava/demo/classloader/Log.class";

        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        //类的全称
        String packageNamePath = "com.martin.thinkinmyjava.demo.classloader.Log";

        //加载Log这个class文件
        Class<?> Log = myClassLoader.loadClass(packageNamePath);

        System.out.println("类加载器是:" + Log.getClassLoader());

        //利用反射获取main方法
        java.lang.reflect.Method method = Log.getDeclaredMethod("main", String[].class);
        Object object = Log.newInstance();
        String[] arg = {"ad"};
        method.invoke(object, (Object) arg);
    }

}
