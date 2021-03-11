package com.company;

import org.omg.CORBA.INTERNAL;

public enum Priority {

    PRIORITY_10(10),
    PRIORITY_9(9),
    PRIORITY_8(8),
    PRIORITY_7(7),
    PRIORITY_6(6),
    PRIORITY_5(5),
    PRIORITY_4(4),
    PRIORITY_3(3),
    PRIORITY_2(2),
    PRIORITY_1(1),
    ;

    private int value;

    Priority(int i) {
    }

    public int getValue(){
        return value;
    }

}
