import java.util.*;

/**
 * So far, I have created the following:
 * 
 * Function to print the map based on the map's situation (snake head, snake tail, apple and empty spaces).
 * Snake class that stores a position and is able to move up, down, left or right and update the tail correspondingly.
 * Apple class.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Maze m = new Maze(2, 2);
        Direction[][] p = m.getPath();
        Snake s = m.getSnake();

        for(int i = 0; i < 4; i++) {
            Direction next = p[s.getHead().getX()][s.getHead().getY()];
            m.update(next);
        }

        //Direction nextDir = p.get(s.getHead());
        //m.update(nextDir);
    }
}