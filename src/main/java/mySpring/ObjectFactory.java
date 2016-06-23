package mySpring;

import org.reflections.Reflections;
import org.springframework.cglib.proxy.*;

import javax.annotation.PostConstruct;
import java.lang.reflect.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config = new JavaConfig();
    private List<ObjectConfigurer> objectConfigurers = new ArrayList<>();
    private Reflections reflections = new Reflections("mySpring");

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    private ObjectFactory() {
        Set<Class<? extends ObjectConfigurer>> classes = reflections.getSubTypesOf(ObjectConfigurer.class);
        for (Class<? extends ObjectConfigurer> aClass : classes) {
            if (!Modifier.isAbstract(aClass.getModifiers())) {
                try {
                    objectConfigurers.add(aClass.newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public <T> T createObject(Class<T> type) throws Exception {
        type = resolveImpl(type);
        T t = type.newInstance();
        configure(t);
        invokeInitMethod(t);
        if (type.isAnnotationPresent(Benchmark.class)) {
            if (type.getInterfaces().length == 0) {
                return (T) Enhancer.create(type, new org.springframework.cglib.proxy.InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                        System.out.println("**********BENCHMARK*************");
                        System.out.println(method.getName()+" is working");
                        long before = System.nanoTime();
                        Object retVal = method.invoke(t, args);
                        long after = System.nanoTime();
                        System.out.println(after-before);
                        System.out.println("**********BENCHMARK*************");
                        return retVal;
                    }
                });
            }
            return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), type.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("**********BENCHMARK*************");
                    System.out.println(method.getName()+" is working");
                    long before = System.nanoTime();
                    Object retVal = method.invoke(t, args);
                    long after = System.nanoTime();
                    System.out.println(after-before);
                    System.out.println("**********BENCHMARK*************");
                    return retVal;
                }
            });
        }
        return t;
    }

    private <T> void invokeInitMethod(T t) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = t.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.setAccessible(true);
                method.invoke(t);
            }
        }
    }


    private <T> Class<T> resolveImpl(Class<T> type) {
        if (type.isInterface()) {
            type = config.getImpl(type);
        }
        return type;
    }

    private <T> void configure(T t) throws Exception {
        for (ObjectConfigurer objectConfigurer : objectConfigurers) {
            objectConfigurer.configure(t);
        }
    }


}



















