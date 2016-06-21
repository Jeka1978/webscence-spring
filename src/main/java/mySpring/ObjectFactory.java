package mySpring;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config = new JavaConfig();

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    private ObjectFactory() {
    }

    public <T> T createObject(Class<T> type) throws Exception {
        if (type.isInterface()) {
            type = config.getImpl(type);
        }
        T t = type.newInstance();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomInt.class)) {
                InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int i = min + random.nextInt(max - min);
                field.setAccessible(true);
                field.set(t,i);
            }
        }


        return t;
    }

}



















