import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AngelBabySnippet {
    private static final Lock lock = new ReentrantLock();

    public static void animateText(String text, double delay) {
        lock.lock();
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep((long) (delay * 1000));
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void singLyric(String lyric, double delay, double speed) {
        try {
            Thread.sleep((long) (delay * 1000));
            animateText(lyric, speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void singSong() {
        String[][] lyrics = {
            {"You came out the blue on a rainy night, no lie", "0.07"},
            {"I'll tell you how I almost died", "0.07"},
            {"While you're bringing me back to life", "0.07"},
            {"I just wanna live in this moment forever", "0.08"},
            {"'Cause I'm afraid that living couldn't get any better", "0.08"},
        };

        double[] delays = {0.0, 3.0, 6.0, 9.0, 12.0};

        Thread[] threads = new Thread[lyrics.length];

        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            double speed = Double.parseDouble(lyrics[i][1]);
            double delay = delays[i];

            threads[i] = new Thread(() -> singLyric(lyric, delay, speed));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        singSong();
    }
}
