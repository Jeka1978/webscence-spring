package mySpring;

import java.util.Random;

/**
 * Created by Evegeny on 21/06/2016.
 */
public class Tank {
    private int maxSpeed;

    public Tank() {
        Random random = new Random();
        maxSpeed = 40 + random.nextInt(10);
    }
}
