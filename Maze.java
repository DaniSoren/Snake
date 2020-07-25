import java.util.*;

public class Maze {
    private Direction[][] path;

    private int m, n;

    private Snake snake;
    private Entity apple;

    private boolean wasAppleJustEaten = false;

    public Maze(int m, int n) throws Exception {
        if (m < 2 || n < 2 || (!MathTools.isEven(m) && !MathTools.isEven(n)))
            throw new Exception("wrong input. requires m,n > 1 AND m or n even.");

        this.m = m;
        this.n = n;
        path = new Direction[m][n];
        snake = new Snake();
        apple = new Entity(1, 1);

        GeneratePath();

        print();
    }

    private void GeneratePath() {
        Snake s = new Snake();

        if (MathTools.isEven(m)) {
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

        } else if (MathTools.isEven(n)) {
            updatePath(s, Direction.RIGHT);

            while (s.getHead().getY() < n - 2) {
                while (s.getHead().getX() < m - 1)
                    updatePath(s, Direction.RIGHT);

                updatePath(s, Direction.UP);
                while (s.getHead().getX() > 1)
                    updatePath(s, Direction.LEFT);

                updatePath(s, Direction.UP);
            }

            while (s.getHead().getX() < m - 1)
                updatePath(s, Direction.RIGHT);
            updatePath(s, Direction.UP);

            while (s.getHead().getX() > 0)
                updatePath(s, Direction.LEFT);

            while (s.getHead().getY() > 0)
                updatePath(s, Direction.DOWN);
        } else {
            // neither n nor m is even and we cannot create a hamiltonian path
        }
    }

    private void updatePath(Snake s, Direction d) {
        path[s.getHead().getX()][s.getHead().getY()] = d;
        s.move(d, false);
    }

    public void print() {
        int headX = snake.getHead().getX();
        int headY = snake.getHead().getY();

        List<Entity> t = snake.getTail();

        int appleX = apple.getX();
        int appleY = apple.getY();

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
                    if (hasPrinted)
                        break;

                    int tailX = e.getX();
                    int tailY = e.getY();

                    if (tailX == i && tailY == j) {
                        System.out.print("T");
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
        snake.move(d, wasAppleJustEaten);
        updateApple();
        print();
    }

    private void updateApple() {
        wasAppleJustEaten = false;
        if (snake.getHead().equals(apple)) {
            Random r = new Random();

            Entity newPos = new Entity(r.nextInt(m), r.nextInt(n));

            while (!legalApplePos(newPos))
                newPos = new Entity(r.nextInt(m), r.nextInt(n));

            apple = newPos;
            wasAppleJustEaten = true;
        }
    }

    private boolean legalApplePos(Entity prop) {
        int x = prop.getX();
        int y = prop.getY();
        boolean isLegal = true;
        int snakeX = snake.getHead().getX();
        int snakeY = snake.getHead().getY();
        List<Entity> t = snake.getTail();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isLegal)
                    break;

                if (snakeX == x && snakeY == y)
                    isLegal = false;

                for (Entity e : t) {
                    int tailX = e.getX();
                    int tailY = e.getY();

                    if (tailX == x && tailY == y)
                        isLegal = false;
                }
            }
        }

        return isLegal;
    }

    public Direction[][] getPath() {
        return path;
    }

    public Snake getSnake() {
        return snake;
    }

    public Entity getApple() {
        return apple;
    }
}