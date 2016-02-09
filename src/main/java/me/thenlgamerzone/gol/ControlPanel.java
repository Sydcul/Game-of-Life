package me.thenlgamerzone.gol;

import me.thenlgamerzone.gol.game.GamePhase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class ControlPanel extends JPanel {
    private JButton startButton;
    private JButton resetButton;

    public ControlPanel() {
        // Initialize layout
        BorderLayout borderLayout = new BorderLayout();

        // Set the layout
        setLayout(borderLayout);
        setBackground(Color.GRAY);

        // Create an empty border so the buttons don't hug the side of the screen
        setBorder(new EmptyBorder(8, 13, 8, 13));


        // Create and add the buttons needed
        startButton = new JButton("Start game");
        resetButton = new JButton("Restart game");

        add(startButton, BorderLayout.LINE_START);
        add(resetButton, BorderLayout.LINE_END);

        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                startClick(event);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                restartClick(event);
            }
        });

        // Toggle state of restartButton
        toggleButton(resetButton);
    }

    /**
     * Will get fired when the user clicks the 'Start game' button
     */
    private void startClick(ActionEvent event) {
        // Check to see if the game is already running
        if (Settings.GAME_PHASE.getGamePhase() == GamePhase.PLAYING)
            return;

        // Change game phase
        Settings.GAME_PHASE.setSetting(GamePhase.PLAYING);

        // Toggle the buttons
        toggleButton(startButton);
        toggleButton(resetButton);
    }

    /**
     * Will get fired when the user clicks the 'Restart game' button
     */
    private void restartClick(ActionEvent event) {
        // Check to see if the game is already running or stable
        if (Settings.GAME_PHASE.getGamePhase() == GamePhase.PLAYING || Settings.GAME_PHASE.getGamePhase() == GamePhase.STABLE) {
            // Kill all the cells
            GameOfLife.getCellManager().killAllCells();

            // Change the game phase to starting
            Settings.GAME_PHASE.setSetting(GamePhase.STARTING);

            // Toggle the buttons
            toggleButton(startButton);
            toggleButton(resetButton);
        }
    }

    /**
     * Toggles a button
     * @param button The button to be toggled
     */
    public void toggleButton(JButton button) {
        // Change button settings
        button.setEnabled(!button.isEnabled());
    }
}
