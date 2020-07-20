import java.util.*;

public class Maze {
    private Direction[][] path;

    private int m, n;

    private Snake snake;
    private Apple apple;

    public Maze(int m, int n) throws Exception {
        if (m < 2 || n < 2 || m % 2 != 0 || n % 2 != 0)
            throw new Exception("wrong input. requires m,n > 1 AND m or n even.");

        this.m = m;
        this.n = n;
        path = new Direction[m][n];
        snake = new Snake();
        apple = new Apple(1, 1);

        GeneratePath();
        print();
    }

    private void GeneratePath() {
        Snake s = new Snake();

        if (m % 2 == 0) {
            updatePath(s, Direction.UP);

            while (s.getHead().getX() < m - 2) {
                while (s.getHead().getY() < n - 1)
                    updatePath(s, Direction.UP);

                updatePath(s, Direction.RIGHT);
                while (s.getHead().getY() > 1)
                    updatePath(s, Direction.DOWN);

                updatePath(s, Direction.RIGHT);
            }

            while (s.getHead().getY() < n - 1)
                updatePath(s, Direction.UP);

            updatePath(s, Direction.RIGHT);

            while (s.getHead().getY() > 0)
                updatePath(s, Direction.DOWN);

            while (s.getHead().getX() > 0)
                updatePath(s, Direction.LEFT);

        }
    }

    private void updatePath(Snake s, Direction d) {
        path[s.getHead().getX()][s.getHead().getY()] = d;
        s.move(d);
    }

    public void print() {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();

        int appleX = apple.getPosition().getX();
        int appleY = apple.getPosition().getY();

        List<Entity> t = snake.getTail();

        for (int j = n - 1; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                boolean hasPrinted = false;

                if (headX == i && headY == j) {
                    System.out.print("H");
                    hasPrinted = true;
                }
                if (appleX == i && appleY == j && !hasPrinted) {
                    System.out.print("A");
                    hasPrinted = true;
                }
                for (Entity e : t) {
                    int tailX = e.getX();
                    int tailY = e.getY();

                    if (hasPrinted) {
                        break;
                    }
                    if (tailX == i && tailY == j) {
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
        System.out.println();
    }

    public void update(Direction d) {
        snake.move(d);
        print();
    }

    public Direction[][] getPath() {
        return path;
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }
}