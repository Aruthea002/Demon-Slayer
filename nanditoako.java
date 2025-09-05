import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class nanditoako {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        singSong();
    }

    public static void animateText(String text, long delay) {
        lock.lock();
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(delay);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void singSong(String lyric, long delay, long speed) {
        try {
            Thread.sleep(delay);
            animateText(lyric, speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void singSong() {
        String[][] lyrics = {
                { "\nnandito ako", "120" },
                { "umiibig sa iyo", "120" },
                { "kahit naa nagdurugo ang puso", "140" },
                { "at kung sakaling iwanan ka niya", "160" },
                { "huwag kang mag-alala", "180" },
                { "may nagmamahal sa iyo", "200" },
                { "nandito ako", "210" }
        };

        long[] delays = { 300, 3900, 8000, 14000, 15500, 16000, 17000};

        ExecutorService executor = Executors.newFixedThreadPool(lyrics.length);
        for (int i = 0; i < lyrics.length; i++) {
            final int index = i;
            executor.submit(() -> singSong(lyrics[index][0], delays[index], Long.parseLong(lyrics[index][1])));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}