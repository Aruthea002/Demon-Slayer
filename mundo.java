public class    mundo {
    public static void main(String[] args) {
        String[] lyrics = {
            "SUNOD SA BAWAT GALAW",
            "HINDI NA MALILIGAW",
            "HINDI NA MALILIGAW",
            "HINDI NA MALILIGAW",
            "HINDI NA MALILIGAW",
            "HINDI NA MALILIGAW",
            "HINDI NA MALILIGAW",
            "AKING SINTA IKAW ANG TAHANAN AT MUNDO (MUNDO'Y MAGIGING IKAW)",
            "SA PAG BALIK MANANATILI NA SA PILING MO (MUNDO'Y MAGIGING IKAW)",
            "AKING SINTA (LIMUTIN NA ANG MUNDO)",
            "IKAW ANG TAHANAN AT MUNDO (NANG MAGKASAMA TAYO MUNDO'Y MAGIGING IKAW)",
            "SA PAG BALIK (SUNOD SA BAWAT GALAW)",
            "MANANATILI NA SA PILING MO (HINDI NA MALILIGAW MUNDO'Y MAGIGING IKAW)"
        };

        int delay = 4000; 

        try {
            for (String line : lyrics) {
                System.out.println(line);
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.err.println("An error occurred: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
