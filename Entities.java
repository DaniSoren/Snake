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

    public void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public static Entity random(int m, int n) {
        Random r = new Random();

        int x = r.nextInt(m);
        int y = r.nextInt(n);

        return new Entity(x, y);
    }

    public void reset() {
        x = 0;
        y = 0;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity) {
            Entity obj1 = (Entity) obj;

            if (x == obj1.x && y == obj1.y)
                return true;
            else
                return false;

        } else {
            return false;
        }
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

    public void move(Direction dir, boolean appleEat) {
        Entity prevPos = new Entity(head.getX(), head.getY());
        moveHead(dir);
        moveTail(prevPos, appleEat);
    }

    private void moveHead(Direction dir) {
        switch (dir) {
            case RIGHT:
                head.translate(1, 0);
                break;
            case DOWN:
                head.translate(0, -1);
                break;
            case LEFT:
                head.translate(-1, 0);
                break;
            case UP:
                head.translate(0, 1);
                break;
            default:
                break;
        }
    }

    private void addTail(Entity prev) {
        tail.add(0, prev);
    }

    private void moveTail(Entity prev, boolean appleEat) {
        if(!appleEat) {
            int length = tail.size();

            if(length > 0)
                tail.remove(length - 1);
        }
        
        addTail(prev);
    }

    public void reset() {
        head.reset();
        tail.clear();
    }

    public Entity getHead() {
        return head;
    }

    public List<Entity> getTail() {
        return tail;
    }

    @Override
    public String toString() {
        return "Head" + head.toString() + "  -  Tail" + tail.toString();
    }
}