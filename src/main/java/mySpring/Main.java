package mySpring;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
       /* IRobot iRobot = new IRobot();
        iRobot.cleanRoom();*/
        Elf elf = ObjectFactory.getInstance().createObject(Elf.class);
        System.out.println(elf);
    }
}
