import java.util.ArrayList;

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

    public void printPos() {
        System.out.println("(" + x + "," + y + ")");
    }
}

enum Direction {
    RIGHT, DOWN, LEFT, UP
}

class Snake {

    private ArrayList<Entity> tail;
    private Entity head;

    public Entity getHead() {
        return head;
    }

    public ArrayList<Entity> getTail() {
        return tail;
    }

    public Snake() {
        head = new Entity(1, 1);
        tail = new ArrayList<Entity>();
    }

    public void move(Direction dir) {
        Entity prevPos = head;
        switch (dir) {
            case RIGHT:
                head.setX(head.getX() + 1);
                break;
            case DOWN:
                head.setY(head.getY() - 1);
                break;
            case LEFT:
                head.setX(head.getX() - 1);
                break;
            case UP:
                head.setY(head.getY() + 1);
                break;
            default:
                break;
        }
        if (tail.size() > 0) {
            tail.remove(tail.size() - 1); // + or - 1?
            tail.add(0, prevPos);
        }
    }
}

class Apple {
    private Entity pos;

    public Apple() {
        pos = new Entity(0, 0);
    }

    public Entity getPos() {
        return pos;
    }

    public void updatePos(Entity newPos) {
        pos = newPos;
    }
}