package com.develoberke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private final GameLogic gameLogic;
    private final int cellSize;

    public GamePanel(int cellSize, GameLogic gameLogic) {
        this.cellSize = cellSize;
        this.gameLogic = gameLogic;

        setPreferredSize(new Dimension(
                gameLogic.getBoardWidth() * cellSize,
                gameLogic.getBoardHeight() * cellSize
        ));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;

                if (row >= 0 && row < gameLogic.getBoardHeight() && col >= 0 && col < gameLogic.getBoardWidth()) {
                    gameLogic.setCell(row, col);

                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        setPreferredSize(new Dimension(
                gameLogic.getBoardWidth() * cellSize,
                gameLogic.getBoardHeight() * cellSize
        ));

        super.paintComponent(g);

        for (int i = 0; i < gameLogic.getBoardHeight(); i++) {
            for (int j = 0; j < gameLogic.getBoardWidth(); j++) {
                if (gameLogic.getCell(i, j)) {
                    g.setColor(new Color(i * 10 % 256, j * 10 % 256, (i + j) * 5 % 256));
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}
