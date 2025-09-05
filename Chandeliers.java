import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chandeliers {
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
            {"But I'm", "0.14"},
            {"holdin' on", "0.16"},
            {"for dear life", "0.18"},
            {"Won't look down", "0.15"},
            {"won't open my eyes", "0.16"},
            {"Keep my glass full", "0.14"},
            {"until mornin' light", "0.16"},
            {"'Cause I'm just", "0.13"},
            {"holdin' on", "0.16"},
            {"for tonight", "0.17"},
            {"Help me, I'm", "0.14"},
            {"holdin' on", "0.16"},
            {"for dear life", "0.18"},
            {"Won't look down", "0.15"},
            {"won't open my eyes", "0.16"},
            {"Keep my glass full", "0.14"},
            {"until mornin' light", "0.16"},
            {"'Cause I'm just", "0.13"},
            {"holdin' on", "0.16"},
            {"for tonight, on for tonight", "0.20"}
        };

        double[] delays = {0.5, 1.2, 2.1, 3.4, 4.6, 5.8, 7.2, 8.6, 9.9, 11.2, 12.8, 14.3, 15.9, 17.4, 18.9, 20.3, 21.9, 23.5, 25.0, 26.8};

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
