import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Silence {
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
            {"I'm in need of a savior (Savior)", "0.10"},
            {"but I'm not asking for favors", "0.08"},
            {"My whole life, I've felt like a burden", "0.09"},
            {"I think too much, and I hate it", "0.07"},
            {"I'm so used to being in the wrong, I'm tired of caring", "0.09"},
            {"Loving never gave me a home", "0.10"},
            {"so I'll sit here in the silence", "0.11"}
        };

        double[] delays = {0.3, 3.0, 6.5, 10.0, 14.5, 18.5, 22.0};

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
