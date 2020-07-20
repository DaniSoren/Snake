import java.util.*;

class Entity {

    private int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

enum Direction {
    RIGHT, DOWN, LEFT, UP
}

class Snake {

    private Entity head;
    private List<Entity> tail;

    public Snake() {
        head = new Entity(0, 0);
        tail = new ArrayList<Entity>();
    }

    public void move(Direction dir) {
        int prevX = head.getX();
        int prevY = head.getY();

        switch (dir) {
            case RIGHT:
                head.setX(prevX + 1);
                break;
            case DOWN:
                head.setY(prevY - 1);
                break;
            case LEFT:
                head.setX(prevX - 1);
                break;
            case UP:
                head.setY(prevY + 1);
                break;
            default:
                break;
        }
        if (tail.size() > 0) {
            tail.remove(tail.size() - 1); // + or - 1?
            tail.add(0, new Entity(prevX, prevY));
        }
    }

    public Entity getHead() {
        return head;
    }

    public List<Entity> getTail() {
        return tail;
    }

    @Override
    public String toString() {
        return "H: " + head.toString() + " -- T: " + tail.toString();
    }
}

class Apple {
    private Entity pos;

    public Apple(int x, int y) {
        pos = new Entity(x, y);
    }

    public Entity getPosition() {
        return pos;
    }

    public void updatePos(Entity newPos) {
        pos = newPos;
    }

    @Override
    public String toString() {
        return "A: " + pos.toString();
    }
}