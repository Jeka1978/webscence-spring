package mySpring;

import javax.annotation.PostConstruct;

/**
 * Created by Evegeny on 21/06/2016.
 */
@Benchmark
public class IRobot {
    @InjectByType
    private Speaker speaker;
    @InjectByType
    private Cleaner cleaner;


    @PostConstruct
    public void init(){
        System.out.println(cleaner.getClass().getSimpleName());
    }



    /*public IRobot() throws Exception {
        speaker = ObjectFactory.getInstance().createObject(Speaker.class);
        cleaner = ObjectFactory.getInstance().createObject(Cleaner.class);
    }*/

    public void cleanRoom() {
        speaker.speak("I started my work");
        cleaner.clean();
        speaker.speak("I finished my work");
    }
}
