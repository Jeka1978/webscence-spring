package mySpring;

/**
 * Created by Evegeny on 23/06/2016.
 */
public class BenchmarkCleaner implements Cleaner {
    @InjectByType
    private CleanerImpl cleaner;
    @Override
    public void clean() {
        long before = System.nanoTime();
        cleaner.clean();
        long after = System.nanoTime();
        System.out.println(after-before);
    }
}
