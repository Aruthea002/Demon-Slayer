import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kaytagalkitanghinintay {
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
            {"Hawakan mo ang aking kamay", "0.15"},
            {"At tayong dal'wa'y", "0.15"},
            {"Maghahasik ng kaligayahan", "0.15"},
            {"Bitawan mo'ng unang salita", "0.15"},
            {"Ako ay handa nang tumapak sa lupa", "0.15"},
            {"Tapos na ang paghihintay", "0.15"},
            {"Nandito ka na't", "0.15"},
            {"Oras ay naiinip, nagdahan-dahan", "0.15"},
            {"Sinasamsam bawat gunita", "0.15"},
            {"Na para bang tayo'y 'di na tatanda", "0.15"}
        };

        double[] delays = {0.0, 2.5, 5.0, 8.0, 11.0, 14.0, 17.0, 20.0, 25.0, 28.0};

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