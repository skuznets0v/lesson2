package com.firstlinesoftware.lesson2.annotations.custom;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.util.logging.Level.SEVERE;

public final class CustomTestsRunnerImpl implements CustomTestsRunner {

    private static final Logger log = Logger.getLogger(CustomTestsRunner.class.getName());

    @Override
    public void runAllTests() {
        try {
            for (Method method : findAllTestsMethods()) {
                final Test annotation = method.getAnnotation(Test.class);
                final String testName = "".equals(annotation.value()) ? method.getName() : annotation.value();
                final long start = nanoTime();
                log.info(format("Start test '%s'", testName));
                try {
                    method.invoke(method.getDeclaringClass().newInstance());
                    log.info(format("Passed test '%s'", testName)
                            + (annotation.needCheckTime() ? format(". Duration = %s", nanoTime() - start) : ""));
                } catch (InvocationTargetException e) {
                    log.log(SEVERE, "Test failed " + testName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Method> findAllTestsMethods() throws NoSuchFieldException, IllegalAccessException {
        ClassLoader classLoader = getClass().getClassLoader();
        final List<Method> methods = new ArrayList<>();
        while (classLoader != null) {
            methods.addAll(getAllClassesByClassLoader(classLoader).stream()
                    .map(Class::getDeclaredMethods)
                    .flatMap(Arrays::stream)
                    .filter(m -> m.isAnnotationPresent(Test.class))
                    .collect(Collectors.toList()));
            classLoader = classLoader.getParent();
        }
        return methods;
    }

    private List<Class<?>> getAllClassesByClassLoader(ClassLoader classLoader)
            throws NoSuchFieldException, IllegalAccessException {
        Class<?> classLoaderClass = classLoader.getClass();
        while (classLoaderClass != java.lang.ClassLoader.class) {
            classLoaderClass = classLoaderClass.getSuperclass();
        }
        final Field classesField = classLoaderClass.getDeclaredField("classes");
        classesField.setAccessible(true);
        @SuppressWarnings("unchecked") final Vector<Class<?>> classes = (Vector<Class<?>>) classesField.get(classLoader);
        return new ArrayList<>(classes);
    }
}
