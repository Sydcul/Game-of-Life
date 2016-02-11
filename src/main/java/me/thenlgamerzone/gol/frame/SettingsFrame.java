package me.thenlgamerzone.gol.frame;

import me.thenlgamerzone.gol.GameOfLife;
import me.thenlgamerzone.gol.Settings;
import me.thenlgamerzone.gol.game.GamePhase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * Copyright (c) 2016 Tim
 * See LICENSE for license
 */
public class SettingsFrame {
    private JPanel cardPanel;
    private JFrame jFrame;

    private JTextField widthField;
    private JTextField heightField;
    private JTextField adressField;
    private JTextField usernameField;

    private JRadioButton slowButton;
    private JRadioButton normalButton;
    private JRadioButton fastButton;

    private String currentMenu;
    private String speedSetting;

    public SettingsFrame() {
        // Initialize components
        initComponents();
    }

    /**
     * Initializes all components of the setting screen
     */
    private void initComponents() {
        // Create JFrame, JPanels and layout
        jFrame = new JFrame();
        JPanel comboPane = new JPanel();
        JPanel startPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        cardPanel = new JPanel(new CardLayout());

        // Create and configure buttons
        String menuItems[] = {"Singleplayer", "Multiplayer"};

        JComboBox settingMenu = new JComboBox(menuItems);;
        JButton startButton = new JButton();

        settingMenu.setEditable(false);
        settingMenu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                changeMenu(event);
            }
        });

        startButton.setText("Start game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                startButtonClick(event);
            }
        });

        // Configure panels
        comboPane.add(settingMenu);
        comboPane.setBackground(Color.LIGHT_GRAY);

        // Initialize menu's
        JPanel singlePlayerSettings = initSinglePlayerSettings();
        JPanel multiPlayerSettings = initMultiPlayerSettings();

        // Add menu's to card menu
        cardPanel.add(singlePlayerSettings, "Singleplayer");
        cardPanel.add(multiPlayerSettings, "Multiplayer");

        // Start panel
        startPanel.add(startButton);
        startPanel.setBackground(Color.LIGHT_GRAY);

        // Configuring JFrame
        jFrame.setLayout(new BorderLayout());
        jFrame.add(comboPane, BorderLayout.PAGE_START);
        jFrame.add(cardPanel, BorderLayout.CENTER);
        jFrame.add(startPanel, BorderLayout.PAGE_END);

        jFrame.setSize(350, 190);
        jFrame.setTitle("Game of Life");

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        // Show the JFrame
        jFrame.setVisible(true);

        // Set default settings
        currentMenu = "Singleplayer";
        speedSetting = "normal";
    }

    /**
     * Initializes a new setting menu for single player
     * @return New menu
     */
    private JPanel initSinglePlayerSettings() {
        // Initialize JPanels
        JPanel singlePlayerSettings = new JPanel();
        JPanel widthPanel = new JPanel();
        JPanel heightPanel = new JPanel();
        JPanel speedPanel = new JPanel();

        // Initialize layout and backgrounds
        BoxLayout singleplayerLayout = new BoxLayout(singlePlayerSettings, BoxLayout.PAGE_AXIS);

        // Set layout and backgrounds
        singlePlayerSettings.setLayout(singleplayerLayout);
        singlePlayerSettings.setBackground(Color.LIGHT_GRAY);
        widthPanel.setBackground(Color.LIGHT_GRAY);
        heightPanel.setBackground(Color.LIGHT_GRAY);
        speedPanel.setBackground(Color.LIGHT_GRAY);

        // Initialize settings
        widthField = new JTextField();
        heightField = new JTextField();

        ButtonGroup speedGroup = new ButtonGroup();
        slowButton = new JRadioButton("Slow", false);
        normalButton = new JRadioButton("Normal", true);
        fastButton = new JRadioButton("Fast", false);

        JLabel widthLabel = new JLabel("Width: ");
        JLabel heightLabel = new JLabel("Height: ");
        JLabel speedLabel = new JLabel("Speed: ");

        // Set settings
        widthField.setText("75");
        widthField.setPreferredSize(new Dimension(50, 25));
        widthField.setMaximumSize(widthField.getPreferredSize());

        heightField.setText("75");
        heightField.setPreferredSize(new Dimension(50, 25));
        heightField.setMaximumSize(heightField.getPreferredSize());

        slowButton.setActionCommand("slow");
        slowButton.setBackground(Color.LIGHT_GRAY);
        slowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSpeedSetting(event);
            }
        });

        normalButton.setActionCommand("normal");
        normalButton.setBackground(Color.LIGHT_GRAY);
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSpeedSetting(event);
            }
        });

        fastButton.setActionCommand("fast");
        fastButton.setBackground(Color.LIGHT_GRAY);
        fastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSpeedSetting(event);
            }
        });

        speedGroup.add(slowButton);
        speedGroup.add(normalButton);
        speedGroup.add(fastButton);

        // Add everything to panels
        widthPanel.add(widthLabel);
        widthPanel.add(widthField);

        heightPanel.add(heightLabel);
        heightPanel.add(heightField);

        speedPanel.add(speedLabel);
        speedPanel.add(slowButton);
        speedPanel.add(normalButton);
        speedPanel.add(fastButton);

        singlePlayerSettings.add(widthPanel);
        singlePlayerSettings.add(Box.createHorizontalStrut(5));
        singlePlayerSettings.add(heightPanel);
        singlePlayerSettings.add(Box.createHorizontalStrut(5));
        singlePlayerSettings.add(speedPanel);

        return singlePlayerSettings;
    }

    /**
     * Initializes a new setting menu for multi player
     * @return New menu
     */
    private JPanel initMultiPlayerSettings() {
        // Initialize JPanels
        JPanel multiPlayerSettings = new JPanel();
        JPanel adressPanel = new JPanel();
        JPanel usernamePanel = new JPanel();

        // Initialize layout
        BoxLayout multiplayerLayout = new BoxLayout(multiPlayerSettings, BoxLayout.PAGE_AXIS);

        // Set layout and backgrounds
        multiPlayerSettings.setLayout(multiplayerLayout);
        multiPlayerSettings.setBackground(Color.LIGHT_GRAY);
        adressPanel.setBackground(Color.LIGHT_GRAY);
        usernamePanel.setBackground(Color.LIGHT_GRAY);

        // Initialize settings
        adressField = new JTextField();
        usernameField = new JTextField();

        JLabel adressLabel = new JLabel("IP / Hostname: ");
        JLabel usernameLabel = new JLabel("Username: ");

        // Set settings
        adressField.setText("localhost");
        adressField.setPreferredSize(new Dimension(100, 25));
        adressField.setMaximumSize(adressField.getPreferredSize());

        usernameField.setText("Your username");
        usernameField.setPreferredSize(new Dimension(100, 25));
        usernameField.setMaximumSize(usernameField.getPreferredSize());

        // Add everything to panels
        adressPanel.add(adressLabel);
        adressPanel.add(adressField);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        multiPlayerSettings.add(adressPanel);
        multiPlayerSettings.add(Box.createHorizontalStrut(5));
        multiPlayerSettings.add(usernamePanel);
        return multiPlayerSettings;
    }

    /**
     * Used to change menus
     * @param event The ItemEvent
     */
    private void changeMenu(ItemEvent event) {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

        // Set menu
        cardLayout.show(cardPanel, (String) event.getItem());

        // Change currentMenu var
        currentMenu = (String) event.getItem();
    }

    /**
     * Triggered when people clik on 'Start game'
     * Starts game with the current settings
     * @param event The ActionEvent
     */
    private void startButtonClick(ActionEvent event) {
        // Check which mode should be started
        if (currentMenu.equals("Singleplayer")) {
            int selectedWidth = 0;
            int selectedHeight = 0;
            int selectedSpeed = 0;

            // Try to parse the strings in the width/height textfields
            try {
                selectedWidth = Integer.parseInt(widthField.getText());
                selectedHeight = Integer.parseInt(heightField.getText());
            } catch (NumberFormatException exeption) {
                JOptionPane.showMessageDialog(jFrame, "Please make sure " + ((selectedWidth == 0) ? "'Width' and 'Height are integers!'" : "'Height' is an integer!"), "Game of Life - Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get the selected radiobutton
            switch (speedSetting) {
                case "slow":
                    selectedSpeed = 150;
                    break;
                case "fast":
                    selectedSpeed = 40;
                    break;
                default:
                    selectedSpeed = 80;
                    break;
            }

            // Change the settings in 'Settings' to the selected ones
            Settings.WIDTH.setSetting(selectedWidth);
            Settings.HEIGTH.setSetting(selectedHeight);
            Settings.SPEED.setSetting(selectedSpeed);

            // Change the current game phase
            Settings.GAME_PHASE.setSetting(GamePhase.STARTING);

            // Initialize cell manager
            GameOfLife.initCellManager();

            // Initialize timer
            new GameOfLife();

            // Initialize GOLFrame
            GameOfLife.initGOLFFrame();

            // Set setting frame invisible
            jFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(jFrame, "Multiplayer is not yet implemented!", "Game of Life - Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set the current speed setting to the one in the given event
     * @param event The ActionEvent
     */
    private void setSpeedSetting(ActionEvent event){
        speedSetting = event.getActionCommand();
    }
}
