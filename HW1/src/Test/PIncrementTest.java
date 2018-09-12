package Test;

import org.junit.Test;

import static org.junit.Assert.*;

public class PIncrementTest {
    @Test
    public void TestAtomicInteger() {
        for (int i = 1;i < 9;i++) {
            int res = q6.AtomicInteger.PIncrement.parallelIncrement(0, i);
            assertTrue("Result is " + res + ", expected result is 1200000.", res == 1200000);
        }
    }

    @Test
    public void TestReentrantLock() {
        for (int i = 1;i < 9;i++) {
            int res = q6.ReentrantLock.PIncrement.parallelIncrement(0, 8);
            assertTrue("Result is " + res + ", expected result is 1200000.", res == 1200000);
        }
    }

    @Test
    public void parallelIncrement() {
        for (int i = 1;i < 9;i++) {
            int res = q6.Synchronized.PIncrement.parallelIncrement(0, 8);
            assertTrue("Result is " + res + ", expected result is 1200000.", res == 1200000);
        }
    }

    @Test
    public void TestTournament() {
        int res = q6.Tournament.PIncrement.parallelIncrement(0, 8);
        assertTrue("Result is " + res + ", expected result is 1200000.", res == 1200000);
    }

}