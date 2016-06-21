package zoo;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        AnimalFactory animalFactory = new AnimalFactory();
        for (int i=0;i<10;i++) {
            Animal randomAnimal = animalFactory.createRandomAnimal();
            randomAnimal.makeVoice();
        }
    }
}
