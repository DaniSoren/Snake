/**
 * TODO: 
 * Collision with head and tail
 * Command-line functionality
 * Play on your own without auto control
 * Prettify code :)
 * 
 * Optional: Add path for odd m and n
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new Game(3, 4);

        int ms = 1000;

        while (!game.gameWon()) {
            Thread.sleep(ms);

            Direction next = game.getNextMove();
            game.update(next);
        }
    }
}