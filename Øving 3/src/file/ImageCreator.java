package file;

import pathfinding.Node;
import pathfinding.Path;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageCreator {

    //Saves the image for the given solution
    public static final void saveImage(String name, Path path, Node[][] nodes) {
        try {
            BufferedImage image = new BufferedImage(nodes.length * Node.NODE_WIDTH, nodes[0].length * Node.NODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            draw(nodes, path, graphics2D);
            File f = new File("data/images/" + name + ".jpg");
            ImageIO.write(image,"jpeg", f);
        }
        catch(IOException e)  {
            e.printStackTrace();
        }
    }

    //Draws the path onto the image
    private static void draw(Node[][] nodes, Path path, Graphics g) {
        Node start = path.getPath()[0], end = path.getPath()[path.getPath().length - 1];
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {
                nodes[x][y].draw(g, x * Node.NODE_WIDTH, y * Node.NODE_WIDTH);
            }
        }
        g.setColor(Color.red);
        g.fillRect(start.getX() * Node.NODE_WIDTH, start.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(start.getX() * Node.NODE_WIDTH, start.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);


        g.setColor(Color.green);
        g.fillRect(end.getX() * Node.NODE_WIDTH, end.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(end.getX() * Node.NODE_WIDTH, end.getY() * Node.NODE_HEIGHT, Node.NODE_WIDTH, Node.NODE_HEIGHT);

        for (Node n : path.getPath()) {
            g.setColor(Color.BLACK);
            g.fillOval(n.getX() * Node.NODE_WIDTH + Node.NODE_WIDTH / 2 - 5, n.getY() * Node.NODE_HEIGHT + Node.NODE_HEIGHT / 2 - 5, 10, 10);
        }
        g.setFont(g.getFont().deriveFont(30f));
        for (Node n : path.getClosedNodes()) {
            g.drawString("X", n.getX() * Node.NODE_WIDTH + Node.NODE_WIDTH / 2 - 10, n.getY() * Node.NODE_HEIGHT + Node.NODE_HEIGHT / 2 + 13);
        }
        for (Node n : path.getOpenNodes()) {
            g.drawString("O", n.getX() * Node.NODE_WIDTH + Node.NODE_WIDTH / 2 - 10, n.getY() * Node.NODE_HEIGHT + Node.NODE_HEIGHT / 2 + 10);
        }
    }
}
