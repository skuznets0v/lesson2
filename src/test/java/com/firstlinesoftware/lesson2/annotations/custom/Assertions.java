package com.firstlinesoftware.lesson2.annotations.custom;

public class Assertions {

    private Assertions() {}

    public static void assertTrue(boolean condition) {
        assert condition;
    }

    public static <T> void assertEquals(T o1, T o2) {
        assert o1.equals(o2);
    }
}
