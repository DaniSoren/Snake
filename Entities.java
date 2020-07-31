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

    public static Entity random(int m, int n) {
        Random r = new Random();
        
        int x = r.nextInt(m);
        int y = r.nextInt(n);

        return new Entity(x, y);
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

class Snake {

    private Entity head;
    private List<Entity> tail;

    public Snake() {
        head = new Entity(0, 0);
        tail = new ArrayList<Entity>();
    }

    public void move(Direction dir, boolean appleEat) {
        int prevX = head.getX();
        int prevY = head.getY();

        Entity prevPos = new Entity(prevX, prevY);

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

        if (appleEat)
            addTail(prevPos);
        else
            moveTail(prevPos);
    }

    private void addTail(Entity prev) {
        tail.add(0, prev);
    }

    private void moveTail(Entity prev) {
        int length = tail.size();

        if (length > 0) {
            tail.remove(length - 1);
            addTail(prev);
        }
    }

    public void reset() {
        head.setX(0);
        head.setY(0);
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