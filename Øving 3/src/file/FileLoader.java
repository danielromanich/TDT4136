package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileLoader {

    public static String[] loadFile(String path) {
        File f = new File("data/" + path);
        ArrayList<String> board = new ArrayList<>();
        try {
            Stream<String> stream = Files.lines(Paths.get(f.getAbsolutePath()));
            stream.forEach(board::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board.toArray(new String[board.size()]);
    }

}
