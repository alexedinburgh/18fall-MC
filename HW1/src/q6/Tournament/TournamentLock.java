package q6.Tournament;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**Reference: https://weblog.cs.uiowa.edu/cs5620f15/N-Process%20Mutual%20Exclusion
 * N = num of leaves which is power of 2
 *
 * node := i;
 * for level := 0 to log(N)-1 do
 *   id := node mod 2;
 *   node := node / 2;
 *   b[level,2*node+id] := true;
 *   turn[level,node] := id;
 *   await( b[level,2*node+1-id] = false \/ turn[level,node] = 1-id );
 * real critical section;
 * for level := log(N)-1 downto 0 do
 *   node := i / (2**level - 1);
 *   b[level,node] := false;
 */
public class TournamentLock implements Lock {
    private static volatile AtomicBoolean[][] wantCS;
    private static volatile AtomicInteger[][] turn;
    private static volatile int level;

    public TournamentLock(int numThreads){
        // your implementation goes here.
        numThreads = numThreads % 2 == 0 ? numThreads : numThreads + 1;
        level = (int) Math.ceil(Math.log(numThreads) / Math.log(2));
        wantCS = new AtomicBoolean[level][numThreads];
        turn = new AtomicInteger[level][numThreads];

        for (int i = 0;i < level;i++) {
            for (int j = 0;j < numThreads;j++) {
                wantCS[i][j] = new AtomicBoolean(false);
                turn[i][j] = new AtomicInteger(0);
            }
        }
    }

    /**
     * request
     * @param pid
     */
    @Override
    public void lock(int pid) {
        int node = pid;
        for (int k = 0;k < level;k++) {
            int id = node % 2;
            node /= 2;
            wantCS[k][2 * node + id].set(true);
            turn[k][node].set(id);
            while (wantCS[k][2 * node + 1 - id].get() &&
                    turn[k][node].get() == id);
        }

    }

    /**
     * release
     * @param pid
     */
    @Override
    public void unlock(int pid) {
        for (int k = level - 1;k >= 0;k--) {
            int node = (int) (pid / Math.pow(2, k));
            wantCS[k][node].set(false);
        }
    }
}