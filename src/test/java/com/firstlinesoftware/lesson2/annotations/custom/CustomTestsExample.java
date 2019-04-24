package com.firstlinesoftware.lesson2.annotations.custom;

import com.firstlinesoftware.lesson2.domain.User;

public final class CustomTestsExample {

    private final User firstUser = User.builder()
            .id(1)
            .confirmed(true)
            .name("Sergey Kunzetsov")
            .email("sergey.kuznetsov@firstlinesoftware.com")
            .phone("88000000000")
            .build();

    private final User secondUser = User.builder()
            .id(1)
            .confirmed(false)
            .name("Alex")
            .email("alex@mail.ru")
            .build();

    @Test("This method claims that first user equal to first user")
    public void assertEqualsTrue() {
        Assertions.assertEquals(firstUser, firstUser);
    }

    @Test
    public void assertEqualsFalse() {
        Assertions.assertEquals(firstUser, secondUser);
    }

    @Test(needCheckTime = true)
    public void assertTrue() {
        Assertions.assertTrue(firstUser.isConfirmed());
    }

    public static void main(String[] args) {
        new CustomTestsRunnerImpl().runAllTests();
    }
}
