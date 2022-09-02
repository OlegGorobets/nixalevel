package com.nixalevel.lesson10.utility;

import com.nixalevel.lesson10.annotation.Autowired;
import com.nixalevel.lesson10.annotation.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;

public class ReflectionUtil {
    private static final Map<Class<?>, Object> CACHE = new HashMap<>();


    @SuppressWarnings("unchecked")
    public static <T> T getClass(Class<?> cl) throws IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException {
        checkClass(cl);
        if (CACHE.containsKey(cl)) {
            return (T) CACHE.get(cl);
        }
        List<Object> parameters = new ArrayList<>();
        for (Constructor<?> constructor : cl.getDeclaredConstructors()) {
            constructor.setAccessible(true);
            if (constructor.isAnnotationPresent(Autowired.class)) {
                for (Parameter parameter : constructor.getParameters()) {
                    parameters.add(CACHE.get(parameter.getType()));
                }

                Object object = constructor.newInstance(parameters.toArray());
                if (cl.isAnnotationPresent(Singleton.class)) {
                    CACHE.put(cl, object);
                }
                return (T) setInstance(object);
            }
        }
        return (T) cl.getDeclaredConstructor().newInstance();
    }

    private static Object setInstance(Object object) {
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getName().equals("instance"))
                .toList()
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(object, object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return object;
    }

    private static void checkClass(Class<?> cl) {
        if (Objects.isNull(cl)) {
            throw new IllegalArgumentException("The class is null");
        }
    }
}
