package mySpring;

import java.lang.reflect.Field;

/**
 * Created by Evegeny on 23/06/2016.
 */
public class InjectByTypeAnnotationObjectConfigurer implements ObjectConfigurer {
    @Override
    public void configure(Object o) throws Exception {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object fieldValue = ObjectFactory.getInstance().createObject(field.getType());
                field.set(o,fieldValue);
//                field.getType()
            }
        }
    }
}
