public class HanggangSaHuli {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        singSong();
    }

    public static void animateText(String text, long delay) {
        synchronized (lock) {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    public static void singLyric(String lyric, long charSpeed, long lineDelay) throws InterruptedException {
        animateText(lyric, charSpeed);
        Thread.sleep(lineDelay);
    }

    public static void singSong() throws InterruptedException {
        String[][] lyrics = {
            // Format: { "lyric", "charSpeed(ms)", "postLineDelay(ms)" }
            {"\nHawakan mo aking kamay bago tayo maghiwalay", "80", "1800"},
            {"Lahat-lahat ibibigay, lahat-lahat", "85", "1600"},
            {"Paalam sa 'ting huling sayaw", "90", "1500"},
            {"May dulo pala ang langit", "100", "1400"},
            {"Kaya't sabay tayong bibitaw", "90", "1600"},
            {"Sa ating huling sayaw", "85", "2500"}
        };

        for (String[] line : lyrics) {
            String lyric = line[0];
            long charSpeed = Long.parseLong(line[1]);
            long lineDelay = Long.parseLong(line[2]);

            singLyric(lyric, charSpeed, lineDelay);
        }
    }
}
