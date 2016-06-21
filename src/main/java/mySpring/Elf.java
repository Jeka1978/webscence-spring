package mySpring;

import lombok.Data;

import java.util.Random;

/**
 * Created by Evegeny on 21/06/2016.
 */

@Data
public class Elf {
    @InjectRandomInt(min = 10, max = 20)
    private int power;


}
