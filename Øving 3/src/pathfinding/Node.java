package pathfinding;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private int x, y, weight;
    private float g, h;
    private boolean blocking, fancy;
    public static int NODE_WIDTH = 40, NODE_HEIGHT = 40;
    private ArrayList<Node> neighbours = new ArrayList<>();

    public static final HashMap<Integer, Color> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put(100, new Color(77, 77, 255));
        COLOR_MAP.put(50, new Color(166, 166, 166));
        COLOR_MAP.put(10, new Color(0, 128, 0));
        COLOR_MAP.put(5, new Color(128, 255, 128));
        COLOR_MAP.put(1, new Color(191, 128, 64));
    }

    public Node(int x, int y, boolean blocking) {
        this.x = x;
        this.y = y;
        this.g = Float.MAX_VALUE;
        this.blocking = blocking;
        this.weight = 1;
    }

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.g = Float.MAX_VALUE;
        this.blocking = false;
        this.weight = weight;
        this.fancy = true;
    }

    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ") -> Blocking: " + this.isBlocking();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node n = (Node) o;
            return n.getX() == this.getX() && n.getY() == this.getY();
        }
        return false;
    }

    public float getDistance(Node node) {
        return (float) Math.sqrt(Math.pow(this.getX() - node.getX(), 2) + Math.pow(this.getY() - node.getY(), 2)) + node.getWeight();
    }

    public boolean isBlocking() {
        return this.blocking;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public float getCost() {
        return this.h + this.g;
    }

    public float getG() {
        return this.g;
    }

    public Node[] getNeighbours() {
        return this.neighbours.toArray(new Node[this.neighbours.size()]);
    }

    private Color getColor() {
        if (this.fancy)
            return COLOR_MAP.get(this.getWeight());
        return this.isBlocking() ? new Color(0, 0, 0) : new Color(255, 255, 255);
    }

    public void setH(float h) {
        this.h = h;
    }

    public void setG(float g) {
        this.g = g;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(this.getColor());
        g.fillRect(x, y, NODE_WIDTH, NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, NODE_WIDTH, NODE_HEIGHT);
    }

}
