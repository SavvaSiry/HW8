package com.company;

import com.company.Anotations.AfterSuite;
import com.company.Anotations.BeforeSuite;
import com.company.Anotations.Test;

public class MyTest {

    @BeforeSuite
    public static void start(){
        System.out.println("=====START=====");
    }

    @AfterSuite
    public void finish(){
        System.out.println("======END======");
    }

    @Test(priority = Priority.PRIORITY_3)
    public void checkInt(int a){
        System.out.println("INT = " + a);
    }

    @Test(priority = Priority.PRIORITY_4)
    public void checkString(String str){
        System.out.println("STRING = " + str);
    }

    @Test(priority = Priority.PRIORITY_4)
    public void checkBoolean(boolean bool){
        System.out.println("BOOLEAN = " + bool);
    }

    @Test(priority = Priority.PRIORITY_10)
    public void checkNoParameter(){
        System.out.println("NO PARAMETER");
    }
}
