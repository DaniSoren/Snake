public class Game {

    private Maze maze;
    private Snake snake;
    private Entity apple;

    private boolean wasAppleJustEaten;

    public Game(int m, int n) throws Exception {
        maze = new Maze(m, n);

        snake = new Snake();

        spawnApple();
        wasAppleJustEaten = false;

        print();
    }

    public void update(Direction d) {
        snake.move(d, wasAppleJustEaten);

        if (gameLost())
            return;
        
        updateApple();

        print();
    }

    public boolean gameWon() {
        int m = maze.getWidth();
        int n = maze.getLength();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean isOccupied = false;
                Entity curr = new Entity(i, j);

                if (curr.equals(snake.getHead()))
                    isOccupied = true;

                for (Entity tail : snake.getTail())
                    if (curr.equals(new Entity(tail.getX(), tail.getY())))
                        isOccupied = true;

                if (!isOccupied)
                    return false;
            }
        }

        return true;
    }

    public boolean gameLost() {
        if (gameWon())
            return false;
        else if (isSnakeCollided())
            return true;

        return false;
    }

    private void print() {
        int m = maze.getWidth();
        int n = maze.getLength();
        for (int j = n - 1; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                Entity curr = new Entity(i, j);

                if (curr.equals(snake.getHead()))
                    System.out.print("H");
                else if (curr.equals(apple))
                    System.out.print("A");
                else {
                    boolean hasPrinted = false;

                    for (Entity tail : snake.getTail())
                        if (curr.equals(new Entity(tail.getX(), tail.getY()))) {
                            System.out.print("T");
                            hasPrinted = true;
                        }

                    if (!hasPrinted)
                        System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void spawnApple() {
        int m = maze.getWidth();
        int n = maze.getLength();
        Entity newPos = null;

        do {
            newPos = Entity.random(m, n);
        } while (!legalApplePos(newPos));

        apple = newPos;
    }

    private void updateApple() {
        wasAppleJustEaten = false;
        if (snake.getHead().equals(apple) && !gameWon()) {
            spawnApple();

            wasAppleJustEaten = true;
        }
    }

    private boolean legalApplePos(Entity proposedSpot) {
        boolean isLegal = true;

        int m = maze.getWidth();
        int n = maze.getLength();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isLegal)
                    break;

                if (proposedSpot.equals(snake.getHead()))
                    isLegal = false;

                for (Entity e : snake.getTail())
                    if (proposedSpot.equals(e))
                        isLegal = false;
            }
        }

        return isLegal;
    }

    private boolean isSnakeCollided() {
        Entity head = snake.getHead();
        int x = head.getX();
        int y = head.getY();

        int m = maze.getWidth();
        int n = maze.getLength();

        if (x < 0 || y < 0 || x >= m || y >= n) {
            return true;
        }

        for (Entity t : snake.getTail()) {
            if (head.equals(t)) {
                return true;
            }
        }

        return false;
    }

    public void reset() {
        snake.reset();
        updateApple();
        wasAppleJustEaten = false;
    }

    public Direction getNextMove() {
        int snakeX = snake.getHead().getX();
        int snakeY = snake.getHead().getY();
        Direction[][] path = maze.getPath();

        return path[snakeX][snakeY];
    }

    public Snake getSnake() {
        return snake;
    }

    public Entity getApple() {
        return apple;
    }
}