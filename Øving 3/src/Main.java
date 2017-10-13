import board.Board;
import board.BoardParser;
import board.PathRunnable;
import file.FileLoader;
import file.ImageCreator;
import pathfinding.PathFinding;
import ui.BoardUI;

public class Main {

    private static boolean useUI = true;

    public static void main(String... args) {
        if (useUI)
            new BoardUI();
        else
            saveImages();
    }

    private static void saveImages() {
        for (PathRunnable p : PathFinding.PATH_RUNNABLES) {
            int count = 0;
            for (String boardName : BoardUI.BOARDS) {
                Board b = count > 3 ? BoardParser.parseAdvanced(FileLoader.loadFile(boardName)) : BoardParser.parseSimple(FileLoader.loadFile(boardName));
                b.setPathRunnable(p);
                b.generatePath();
                ImageCreator.saveImage(boardName.substring(0, boardName.length() - 4) + "_" + p, b.getPath(), b.getBoard());
                count++;
            }
        }
    }


}
