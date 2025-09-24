package com.develoberke;

import javax.swing.*;
import java.awt.*;

public class GameUI {
    private static final double REFRESH_SPEED = 0.5;
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int CELL_SIZE = 20;
    private static final int START_BUTTON_SIZE = 100;

    public static Frame GetGameFrame() {
        JFrame frame = new JFrame();

        GameLogic gameLogic = new GameLogic(WIDTH / CELL_SIZE, (HEIGHT - START_BUTTON_SIZE) / CELL_SIZE);
        JPanel panel = new GamePanel(CELL_SIZE, gameLogic);

        frame.add(panel, BorderLayout.CENTER);

        JButton toggleButton = getStartButton(gameLogic, panel);

        frame.add(toggleButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(null);
        return frame;
    }

    private static JButton getStartButton(GameLogic gameLogic, JPanel panel) {
        Timer timer = new Timer((int) (REFRESH_SPEED * 1000), e -> {
            gameLogic.runGameLogic();
            panel.repaint();
        });

        JButton toggleButton = new JButton("Start");
        toggleButton.setVerticalAlignment(JButton.CENTER);
        toggleButton.setPreferredSize(new Dimension(panel.getWidth(), START_BUTTON_SIZE / 2));

        toggleButton.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                toggleButton.setText("Start");
            } else {
                timer.start();
                toggleButton.setText("Stop");
            }
        });
        return toggleButton;
    }
}
