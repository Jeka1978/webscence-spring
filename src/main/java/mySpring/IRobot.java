package mySpring;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class IRobot {
    private Speaker speaker;
    private Cleaner cleaner;

    public IRobot() {
        speaker =
        cleaner =
    }

    public void cleanRoom() {
        speaker.speak("I started my work");
        cleaner.clean();
        speaker.speak("I finished my work");
    }
}
