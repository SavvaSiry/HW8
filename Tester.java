package com.company;

import com.company.Anotations.AfterSuite;
import com.company.Anotations.BeforeSuite;
import com.company.Anotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Tester {

    private static String strTestConst = "HELLO WORLD";
    private static int intTestConst = 0;
    private static boolean boolTestConst = false;

    public static void start(Class object) throws IllegalAccessException, InstantiationException {
        checkBeforeAfterSuitAnnotations(object);
        test(object.newInstance());
    }

    public static void start(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        start(Class.forName(className));
    }

    private static void checkBeforeAfterSuitAnnotations(Class object) {
        Method[] methodsArray = object.getMethods();
        ArrayList<Method> beforeSuitList = new ArrayList<>();
        ArrayList<Method> afterSuitList = new ArrayList<>();
        for (Method m : methodsArray) {
            if (m.isAnnotationPresent(BeforeSuite.class)) beforeSuitList.add(m);
            else if (m.isAnnotationPresent(AfterSuite.class)) afterSuitList.add(m);
        }
        if (beforeSuitList.size() > 1 || afterSuitList.size() > 1) throw new RuntimeException();
    }

    private static void test(Object object) {
        HashMap<Method, Integer> annotationsMap = new HashMap<>();
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(Test.class)) annotationsMap.put(method, method.getAnnotation(Test.class).priority().getValue());
            else if (method.isAnnotationPresent(BeforeSuite.class)) annotationsMap.put(method, 11);
            else if (method.isAnnotationPresent(AfterSuite.class)) annotationsMap.put(method, -1);
        }
        annotationsMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(x -> {
                    Class[] arrayClass = x.getKey().getParameterTypes();
                    try {
                        if (arrayClass.length == 1) {
                            if (arrayClass[0].equals(int.class)) x.getKey().invoke(object,intTestConst);
                            if (arrayClass[0].equals(boolean.class)) x.getKey().invoke(object,boolTestConst);
                            if (arrayClass[0].equals(String.class)) x.getKey().invoke(object,strTestConst);
                        }
                        if (arrayClass.length == 0) {
                            x.getKey().invoke(object);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }
}