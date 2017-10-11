package ui;

import board.BoardParser;
import board.DrawPane;
import file.FileLoader;

import javax.swing.*;

public class BoardUI extends JFrame {

    public static final String[] BOARDS = {"board-1-1.txt", "board-1-2.txt", "board-1-3.txt", "board-1-4.txt",
            "board-2-1.txt", "board-2-2.txt", "board-2-3.txt", "board-2-4.txt",};

    private DrawPane drawPane;
    public BoardUI() {
        this.init();
    }

    private void init() {
        JComboBox<String> comboBox = new JComboBox<>(BOARDS);
        comboBox.setBounds(5, 5, 150, 20);
        this.add(comboBox);

        comboBox.addActionListener((event) -> {
            if (comboBox.getSelectedIndex() >= 0)
                this.update(comboBox.getSelectedIndex());
        });

        JButton select = new JButton("Find path!");
        select.setBounds(160, 5, 120, 20);
        this.add(select);

        select.addActionListener((event) -> {
            if (this.drawPane != null)
                this.drawPane.animate();
        });

        this.setSize(300, 70);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        comboBox.setSelectedIndex(0);
    }

    private void update(int index) {
        if (this.drawPane != null)
            this.remove(this.drawPane);
        this.setSize(300, 70);
        if (index <= 3)
            this.drawPane = new DrawPane(BoardParser.parseSimple(FileLoader.loadFile(BOARDS[index])));
        else
            this.drawPane = new DrawPane(BoardParser.parseAdvanced(FileLoader.loadFile(BOARDS[index])));
        this.setSize(this.drawPane.getBoardWidth() - 23, this.drawPane.getBoardHeight() + 30);
        this.drawPane.setBounds(0, 30, this.drawPane.getBoardWidth(), this.drawPane.getBoardHeight());
        this.add(drawPane);
    }



}
