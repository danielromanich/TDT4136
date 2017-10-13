package ui;

import board.Board;
import pathfinding.Node;
import pathfinding.Path;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrawPane extends JPanel {

    private Board board;
    private String name;
    private boolean running;
    private List<Node> path = new ArrayList<>(), open = new ArrayList<>(), closed = new ArrayList<>();
    public DrawPane(Board board, String name) {
        this.board = board;
        this.name = name;
    }

    public int getBoardWidth() {
        return (this.board.getBoard().length + 1) * Node.NODE_WIDTH;
    }

    public int getBoardHeight() {
        return (this.board.getBoard()[0].length + 1) * Node.NODE_HEIGHT;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
        g.setFont(g.getFont().deriveFont(30f));
        for (Node n : this.closed) {
            g.drawString("X", n.getX() * Node.NODE_WIDTH + Node.NODE_WIDTH / 2 - 10, n.getY() * Node.NODE_HEIGHT + Node.NODE_HEIGHT / 2 + 13);
        }
        for (Node n : this.open) {
            g.drawString("*", n.getX() * Node.NODE_WIDTH + Node.NODE_WIDTH / 2 - 5, n.getY() * Node.NODE_HEIGHT + Node.NODE_HEIGHT / 2 + 20);
        }
    }

    public void animate() {
        this.path.clear();
        this.open.clear();
        this.closed.clear();
        if (!running) {
            new Thread(() -> {
                running = true;
                Path path = board.getPath();
                for (int i = 0; i < path.getPath().length; i++) {
                    this.path.add(path.getPath()[i]);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.repaint();
                }
                saveImage();
                Stream.of(path.getOpenNodes()).forEach(open::add);
                Stream.of(path.getClosedNodes()).forEach(closed::add);
                running = false;
                this.repaint();
            }).start();
        }
    }

    private final void saveImage() {
        try {
            BufferedImage image = new BufferedImage(getBoardWidth(), getBoardHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            this.paint(graphics2D);
            ImageIO.write(image,"jpeg", new File("./data/" + this.name.substring(0, this.name.length() - 4) + ".jpg"));
        }
        catch(IOException e)  {
            e.printStackTrace();
        }
    }

}
