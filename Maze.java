public class Maze {

    private Direction[][] path;
    private int m, n;

    public Maze(int m, int n) throws Exception {
        if (m < 2 || n < 2)
            throw new Exception("Length and Width must both be greater than 1.");
        else if (!MathTools.isEven(m) && !MathTools.isEven(n))
            throw new Exception("Length or width must be even size.");

        this.m = m;
        this.n = n;
        path = new Direction[m][n];

        GeneratePath();
    }

    private void GeneratePath() {
        Snake s = new Snake();
        Entity head = s.getHead();

        if (MathTools.isEven(m)) {
            updatePath(s, Direction.UP);

            while (head.getX() < m - 2) {
                while (head.getY() < n - 1)
                    updatePath(s, Direction.UP);

                updatePath(s, Direction.RIGHT);
                while (head.getY() > 1)
                    updatePath(s, Direction.DOWN);

                updatePath(s, Direction.RIGHT);
            }

            while (head.getY() < n - 1)
                updatePath(s, Direction.UP);
            updatePath(s, Direction.RIGHT);

            while (head.getY() > 0)
                updatePath(s, Direction.DOWN);

            while (head.getX() > 0)
                updatePath(s, Direction.LEFT);

        } else if (MathTools.isEven(n)) {
            updatePath(s, Direction.RIGHT);

            while (head.getY() < n - 2) {
                while (head.getX() < m - 1)
                    updatePath(s, Direction.RIGHT);

                updatePath(s, Direction.UP);
                while (head.getX() > 1)
                    updatePath(s, Direction.LEFT);

                updatePath(s, Direction.UP);
            }

            while (head.getX() < m - 1)
                updatePath(s, Direction.RIGHT);
            updatePath(s, Direction.UP);

            while (head.getX() > 0)
                updatePath(s, Direction.LEFT);

            while (head.getY() > 0)
                updatePath(s, Direction.DOWN);
        } else {
            // Unreachable code. An exception will be thrown before we ever reach this code.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    path[i][j] = Direction.DOWN;
                }
            }
        }
    }

    private void updatePath(Snake s, Direction d) {
        int snakeX = s.getHead().getX();
        int snakeY = s.getHead().getY();

        path[snakeX][snakeY] = d;

        s.move(d, false);
    }

    public Direction[][] getPath() {
        return path;
    }

    public int getWidth() {
        return m;
    }

    public int getLength() {
        return n;
    }
}