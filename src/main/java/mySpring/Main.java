package mySpring;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        ObjectFactory factory = ObjectFactory.getInstance();
        IRobot iRobot = factory.createObject(IRobot.class);
        iRobot.cleanRoom();

      /*  Elf elf = factory.createObject(Elf.class);
        Elf elf2 = factory.createObject(Elf.class);
        System.out.println(elf);
        System.out.println(elf2);*/
    }
}
