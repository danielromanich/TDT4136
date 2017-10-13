package pathfinding;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private int x, y, weight;
    private double g, h;
    private char type;
    private Node prevNode;
    private boolean blocking;
    public static int NODE_WIDTH = 40, NODE_HEIGHT = 40;
    private ArrayList<Node> neighbours = new ArrayList<>();

    public static final HashMap<Character, Color> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put('w', new Color(77, 77, 255));
        COLOR_MAP.put('m', new Color(166, 166, 166));
        COLOR_MAP.put('f', new Color(0, 128, 0));
        COLOR_MAP.put('g', new Color(128, 255, 128));
        COLOR_MAP.put('r', new Color(191, 128, 64));
        COLOR_MAP.put('#', new Color(0, 0, 0));
        COLOR_MAP.put('.', new Color(255, 255, 255));
    }

    public Node(int x, int y, int weight, char type) {
        this.x = x;
        this.y = y;
        this.g = Float.MAX_VALUE;
        this.weight = weight;
        this.type = type;
        if (this.weight == 0)
            this.blocking = true;
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

    public float distanceTo(Node node) {
        return (float) Math.sqrt(Math.pow(this.getX() - node.getX(), 2) + Math.pow(this.getY() - node.getY(), 2));
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

    public Node getPrevNode() {
        return this.prevNode;
    }

    public double getCost() {
        return this.h + this.g;
    }

    public double getG() {
        return this.g;
    }

    public Node[] getNeighbours() {
        return this.neighbours.toArray(new Node[this.neighbours.size()]);
    }

    public void setPrevNode(Node n) {
        this.prevNode = n;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = neighbours;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(COLOR_MAP.get(this.type));
        g.fillRect(x, y, NODE_WIDTH, NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, NODE_WIDTH, NODE_HEIGHT);
    }

}
