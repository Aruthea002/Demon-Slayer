
import java.util.concurrent.TimeUnit;

public class ENCHANTED {

    public static void main(String[] args) {
        String[] lines = {
            "This was the very first page",
            "Not where the storyline ends",
            "My thoughts will echo your name",
            "Until I see you again",
            "These are the words I held back",
            "As I was leaving too soon",
            "I was enchanted to meet you",
            "Please don't be in love with someone else",
            "Please don't have somebody waiting on you..."
        };

        double[] charDelays = {
            0.09,
            0.09,
            0.09,
            0.09,
            0.1,
            0.1,
            0.11,
            0.13, 
            0.15 
        };

       
        double[] lineDelays = {
            0.5, 
            0.5,
            0.6,
            0.6,
            0.5,
            0.4,
            1.0, // Big pause before chorus
            1.5,
            2.0 // Dramatic lingering end
        };

        for (int i = 0; i < lines.length; i++) {
            printWithDelay(lines[i], charDelays[i]);
            try {
                TimeUnit.SECONDS.sleep((long) lineDelays[i]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println();
        }
    }

    private static void printWithDelay(String line, double charDelay) {
        for (char c : line.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                TimeUnit.MILLISECONDS.sleep((long) (charDelay * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
