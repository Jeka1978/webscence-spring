package zoo;

import org.fluttercode.datafactory.impl.DataFactory;
import org.reflections.Reflections;

import java.lang.reflect.*;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class AnimalFactory {
    private final List<Class<? extends Animal>> aninalTypes;
    private Reflections reflections = new Reflections("zoo");


    public AnimalFactory() {
        Set<Class<? extends Animal>> classes = reflections.getSubTypesOf(Animal.class);
        aninalTypes = classes.stream().filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .collect(Collectors.toList());
    }

    public Animal createRandomAnimal() throws IllegalAccessException, InstantiationException {
        Random random = new Random();
        int i = random.nextInt(aninalTypes.size());
        Class<? extends Animal> animalClass = aninalTypes.get(i);

      /*  DataFactory dataFactory = new DataFactory();
        dataFactory.getName();*/

        return animalClass.newInstance();
    }




   /* public static void main(String[] obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {


        Class clazz = obj.getClass();

        Object o1 = clazz.newInstance();

        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes() == null) {
                Object o = constructor.newInstance();
            }
        }

        Method[] methods = clazz.getMethods();
        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {

            if (field.getName().startsWith("p")&&field.getClass().equals(String.class)) {
                field.setAccessible(true);
                field.set(obj,"bla");
            }
        }
        for (Field field : fields) {

        }


    }*/
}




