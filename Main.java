import java.util.ArrayList;

/**
 * So far, I have created the following:
 * 
 * Function to print the map based on the map's situation (snake head, snake tail, apple and empty spaces).
 * Snake class that stores a position and is able to move up, down, left or right and update the tail correspondingly.
 * Apple class.
 */

public class Main {
    static int n = 10, m = 10;
    static int[][] map = new int[n][m];
    static Snake snake = new Snake();
    static Apple apple = new Apple();

    public static void main(String[] args) throws Exception {
        if (n < 2 || m < 2) {
            throw new Exception();
        }
        printMap();
    }

    private static void printMap() {
        Entity h = snake.getHead();
        Entity a = apple.getPos();
        ArrayList<Entity> t = snake.getTail();

        h.printPos();
        a.printPos();

        for (int j = n - 1; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                boolean hasPrinted = false;
                if (h.getX() == i && h.getY() == j) {
                    System.out.print("H");
                    hasPrinted = true;
                }
                if(i == a.getX() && j == a.getY()) {
                    System.out.print("A");
                    hasPrinted = true;
                }
                for (Entity e : t) {
                    if (i == e.getX() && j == e.getY()) {
                        System.out.println("T");
                        hasPrinted = true;
                        break;
                    }
                }
                if (!hasPrinted) {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}