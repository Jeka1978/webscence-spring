package mySpring;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class IRobot {
    @InjectByType
    private Speaker speaker;
    @InjectByType
    private Cleaner cleaner;

   /* public IRobot() throws Exception {
        speaker = ObjectFactory.getInstance().createObject(Speaker.class);
        cleaner = ObjectFactory.getInstance().createObject(Cleaner.class);
    }*/

    public void cleanRoom() {
        speaker.speak("I started my work");
        cleaner.clean();
        speaker.speak("I finished my work");
    }
}
