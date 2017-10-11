package board;

import pathfinding.Node;

public class BoardParser {

    public static Board parseSimple(String[] board) {
        Node[][] nodes = new Node[board[0].length()][board.length];
        Node start = null, end = null;
        for (int x = 0; x < board[0].length(); x++) {
            for (int y = 0; y < board.length; y++) {
                char c = board[y].charAt(x);
                nodes[x][y] = new Node(x, y, c == '#' ? true : false);
                if (c == 'A')
                    start = nodes[x][y];
                else if (c == 'B')
                    end = nodes[x][y];
            }
        }
        return new Board(start, end, nodes);
    }

    public static Board parseAdvanced(String[] board) {
        Node[][] nodes = new Node[board[0].length()][board.length];
        Node start = null, end = null;
        System.out.println(nodes.length + " " + nodes[0].length);
        for (int x = 0; x < board[0].length(); x++) {
            for (int y = 0; y < board.length; y++) {
                char c = board[y].charAt(x);
                switch (c) {
                    case 'w':
                        nodes[x][y] = new Node(x, y, 100);
                        break;
                    case 'm':
                        nodes[x][y] = new Node(x, y, 50);
                        break;
                    case 'f':
                        nodes[x][y] = new Node(x, y, 10);
                        break;
                    case 'g':
                        nodes[x][y] = new Node(x, y, 5);
                        break;
                    case 'r':
                        nodes[x][y] = new Node(x, y, 1);
                        break;
                    default:
                        nodes[x][y] = new Node(x, y, 0);
                        break;
                }
                if (c == 'A')
                    start = nodes[x][y];
                else if (c == 'B')
                    end = nodes[x][y];
            }
        }
        return new Board(start, end, nodes);
    }
}
