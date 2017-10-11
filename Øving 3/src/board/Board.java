package board;

import pathfinding.AStar;
import pathfinding.Node;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    private Node[][] board;
    private Node start, end;
    private Node[] path;
    public Board(Node start, Node end, Node[][] board) {
        this.start = start;
        this.end = end;
        this.board = board;
        this.init();
        this.path = AStar.getPath(start, end);
    }

    private void init() {
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                this.board[x][y].setH(this.board[x][y].getDistance(this.end));
                this.board[x][y].setNeighbours(this.getNeighbours(this.board[x][y]));
            }
        }
    }

    private ArrayList<Node> getNeighbours(Node node) {
        ArrayList<Node> neighbours = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (x + node.getX() >= 0 && y + node.getY() >= 0 && x + node.getX() < this.board.length && y + node.getY() < this.board[0].length && ((x == 0 && y != 0) || (x != 0 && y == 0))) {
                    Node neighbour = board[x + node.getX()][y + node.getY()];
                    if (!neighbour.isBlocking() && canReach(node, neighbour))
                        neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    private boolean canReach(Node current, Node neighbour) {
        int x = current.getX() + (neighbour.getX() - current.getX());
        int y = current.getY() + (neighbour.getY() - current.getY());
        return !this.board[x][current.getY()].isBlocking() && !this.board[current.getX()][y].isBlocking();
    }

    public Node[][] getBoard() {
        return this.board;
    }

    public Node[] getPath() {
        return this.path;
    }

    public void drawStart(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(start.getX() * Node.NODE_WIDTH, start.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(start.getX() * Node.NODE_WIDTH, start.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);
    }

    public void drawEnd(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(end.getX() * Node.NODE_WIDTH, end.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(end.getX() * Node.NODE_WIDTH, end.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);
    }

}
