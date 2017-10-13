package board;

import pathfinding.Node;

public class BoardParser {

    public static Board parseSimple(String[] board) {
        Node[][] nodes = new Node[board[0].length()][board.length];
        Node start = null, end = null;
        for (int x = 0; x < board[0].length(); x++) {
            for (int y = 0; y < board.length; y++) {
                char c = board[y].charAt(x);
                nodes[x][y] = new Node(x, y, c == '#' ? 0 : 1, c);
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
        for (int x = 0; x < board[0].length(); x++) {
            for (int y = 0; y < board.length; y++) {
                char c = board[y].charAt(x);
                switch (c) {
                    case 'w':
                        nodes[x][y] = new Node(x, y, 100, c);
                        break;
                    case 'm':
                        nodes[x][y] = new Node(x, y, 50, c);
                        break;
                    case 'f':
                        nodes[x][y] = new Node(x, y, 10, c);
                        break;
                    case 'g':
                        nodes[x][y] = new Node(x, y, 5, c);
                        break;
                    case 'r':
                        nodes[x][y] = new Node(x, y, 1, c);
                        break;
                    default:
                        nodes[x][y] = new Node(x, y, 1, c);
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
