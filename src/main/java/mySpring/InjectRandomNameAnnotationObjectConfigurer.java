package mySpring;

import org.fluttercode.datafactory.impl.DataFactory;

import java.lang.reflect.Field;

/**
 * Created by Evegeny on 23/06/2016.
 */

public class InjectRandomNameAnnotationObjectConfigurer implements ObjectConfigurer {

    private DataFactory dataFactory;

    public InjectRandomNameAnnotationObjectConfigurer() {
        dataFactory = new DataFactory();
    }

    @Override
    public void configure(Object t) throws Exception {

        Class<?> type = t.getClass();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomName.class)) {
                field.setAccessible(true);
                field.set(t,dataFactory.getName());
            }
        }
    }
}
