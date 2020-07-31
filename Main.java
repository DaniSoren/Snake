/**
 * TODO: 
 * Command-line functionality
 * Prettify code :)
 * Avoid recalculating too many objects...
 * 
 * Optional: Add path for odd m and n
 */

public class Main {

    public static void main(String[] args) throws Exception {
        
        GameMode gm = new GameMode(3, 4, 1000);
        gm.play(GameType.AUTO);
    }
}