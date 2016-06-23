package mySpring;

/**
 * Created by Evegeny on 21/06/2016.
 */
@Benchmark
public class ConsoleSpeaker implements Speaker {
    public void speak(String message) {
        System.out.println(message);
    }
}
