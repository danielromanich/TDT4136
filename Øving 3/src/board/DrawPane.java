package board;

import pathfinding.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPane extends JPanel {

    private Board board;
    private boolean running;
    private ArrayList<Node> path = new ArrayList<>();
    public DrawPane(Board board) {
        this.board = board;
    }

    public int getBoardWidth() {
        return (this.board.getBoard().length + 1) * Node.NODE_WIDTH;
    }

    public int getBoardHeight() {
        return (this.board.getBoard()[0].length + 1) * Node.NODE_HEIGHT;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < this.board.getBoard().length; x++) {
            for (int y = 0; y < this.board.getBoard()[x].length; y++) {
                this.board.getBoard()[x][y].draw(g, x * Node.NODE_WIDTH, y * Node.NODE_WIDTH);
            }
        }
        this.board.drawStart(g);
        this.board.drawEnd(g);
        for (Node n : this.path) {
            g.setColor(Color.BLACK);
            g.fillOval(n.getX() * Node.NODE_WIDTH + Node.NODE_WIDTH / 2 - 5, n.getY() * Node.NODE_HEIGHT + Node.NODE_HEIGHT / 2 - 5, 10, 10);
        }
    }

    public void animate() {
        this.path.clear();
        if (!running) {
            new Thread(() -> {
                running = true;
                for (int i = 0; i < board.getPath().length; i++) {
                    this.path.add(board.getPath()[i]);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.repaint();
                }
                running = false;
            }).start();
        }
    }

}
