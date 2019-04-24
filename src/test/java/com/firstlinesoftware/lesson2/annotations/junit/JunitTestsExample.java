package com.firstlinesoftware.lesson2.annotations.junit;

import com.firstlinesoftware.lesson2.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class JunitTestsExample {

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

    @Test
    public void assertEqualsTrue() {
        Assert.assertEquals(firstUser, firstUser);
    }

    @Test
    public void assertEqualsFalse() {
        Assert.assertEquals(firstUser, secondUser);
    }

    @Test
    public void assertTrue() {
        Assert.assertTrue(firstUser.isConfirmed());
    }
}
