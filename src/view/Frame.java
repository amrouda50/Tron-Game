package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.function.Function;


public class Frame extends JFrame {
    JPanel MidPanel;
    JPanel SouthPanel;
    JMenuBar menu;
    JMenu options;
    JButton registrationButton;
    JButton StartGame;
    int registeredPlayers = 1;
    ArrayList<String> ColorChoices = new ArrayList<>(Arrays.asList("Red", "Blue", "Green", "Pink", "Black"));
    JComboBox<String> color = new JComboBox<>(ColorChoices.toArray(new String[0]));
    final String[] SizeChoices = {"6", "8", "10", "12", "14", "16", "18", "20", "22", "24"};
    final JComboBox<String> size = new JComboBox<>(SizeChoices);
    JMenuItem exitMenuItem;
    JMenuItem restartMenuItem;
    JLabel RegistrationText;
    JTextField name;
    Board board;
    Level l;
    Game game;
    ArrayList<Player> players;

    public Frame() {
        try {
            players = new ArrayList<>();
            board = new Board();
            Registration();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is To start the registration of the players
     *
     * @author OmarAshour
     */
    public void Registration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MidPanel = new JPanel();
        RegistrationText = new JLabel("Enter Player " + registeredPlayers + "  Name and Light Colour");
        MidPanel.add(RegistrationText);
        SouthPanel = new JPanel();
        SouthPanel.setLayout(new BoxLayout(SouthPanel, BoxLayout.X_AXIS));
        name = new JTextField();
        SouthPanel.add(name);
        SouthPanel.add(color);
        registrationButton = new JButton("Register Player");
        SouthPanel.add(registrationButton);
        registrationButton.addActionListener(e -> {
            String ChoosenName = ColorChoices.get(color.getSelectedIndex());
            this.players.add(new Player(name.getText(), ChoosenName));
            ColorChoices.remove(color.getSelectedIndex());
            name.setText("");
            if (registeredPlayers < 2) {
                registeredPlayers++;
                RegistrationText.setText("Enter Player " + registeredPlayers + "  Name and Light Colour");
                color.setModel(new DefaultComboBoxModel<>(ColorChoices.toArray(new String[0])));
            } else {
                SetBoardSize();
            }


        });
        menu = new JMenuBar();
        setJMenuBar(menu);
        options = new JMenu("Options");
        menu.add(options);
        restartMenuItem = new JMenuItem("Restart");
        exitMenuItem = new JMenuItem("Exit");
        options.add(restartMenuItem);
        restartMenuItem.setMnemonic('R');
        restartMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.ALT_MASK)
        );
        restartMenuItem.addActionListener(e -> Restart());
        options.add(exitMenuItem);
        exitMenuItem.setMnemonic('X');
        exitMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_MASK)
        );

        exitMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        getContentPane().add(BorderLayout.NORTH, MidPanel);
        getContentPane().add(BorderLayout.SOUTH, SouthPanel);
        setSize(500, 500);//500 width and 500 height
        setVisible(true);//making the frame visible
        color.setVisible(true);
    }


    private void changePlayerPosition(int index, Direction direction, int degree) {
        game.getPlayers().get(index).setDirection(direction);
        game.getPlayers().get(index).setPlayerDirection(degree - game.getPlayers().get(index).getCurrentDegree());
        game.getPlayers().get(index).setCurrentDegree(degree);
    }

    final Map<Integer, Runnable> keyBindings = new HashMap<>()
    {{
        put(KeyEvent.VK_LEFT, () -> changePlayerPosition(0, Direction.LEFT, 270));
        put(KeyEvent.VK_RIGHT, () -> changePlayerPosition(0, Direction.RIGHT, 90));
        put(KeyEvent.VK_UP, () -> changePlayerPosition(0, Direction.UP, 0));
        put(KeyEvent.VK_DOWN, () -> changePlayerPosition(0, Direction.DOWN, 180));
        put(KeyEvent.VK_W, () -> changePlayerPosition(1, Direction.UP, 0));
        put(KeyEvent.VK_D, () ->changePlayerPosition(1, Direction.RIGHT, 90));
        put(KeyEvent.VK_S, () ->changePlayerPosition(1, Direction.DOWN, 180));
        put(KeyEvent.VK_A, () -> changePlayerPosition(1, Direction.LEFT, 270));
    }};;

    /**
     * This method start the game
     *
     * @author OmarAshour
     */
    public void StartGame() {
        try {
            this.MidPanel.removeAll();
            this.SouthPanel.removeAll();
            setVisible(false);
            int boardSize = Integer.parseInt(SizeChoices[size.getSelectedIndex()]);
            l = new Level(boardSize);
            l.FetchFile();
            game = new Game(l, board);
            for (Player player : players) {
                game.getPlayers().add(player);
            }
            game.SetBoard();
            add(BorderLayout.CENTER, board);

            setSize((new Dimension(41 * boardSize, 51 * boardSize)));
            new Thread(() -> {
                addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent ke) {
                        try {
                            super.keyPressed(ke);
                            int kk = ke.getKeyCode();
                            keyBindings.get(kk).run();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                Player Loser = game.StartMovement();
                JOptionPane.showMessageDialog(this, Loser.getName() + " " + " Have Lost");

            }).start();

            setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * This method is to Set the BoardSize
     *
     * @author OmarAshour
     */
    public void SetBoardSize() {
        this.SouthPanel.removeAll();
        this.MidPanel.removeAll();
        RegistrationText.setText("Set Board Size");
        this.StartGame = new JButton("Start Game");
        this.MidPanel.add(RegistrationText);
        this.SouthPanel.add(size);
        this.SouthPanel.add(StartGame);
        this.StartGame.addActionListener(
                e -> StartGame()
        );
        setVisible(true);

    }

    /**
     * This method is to restart the program
     *
     * @author OmarAshour
     */
    public void Restart() {
        registeredPlayers = 1;
        this.SouthPanel.removeAll();
        this.MidPanel.removeAll();
        remove(this.board);
        remove(this.SouthPanel);
        remove(this.MidPanel);
        this.board = null;
        ColorChoices = new ArrayList<>(Arrays.asList("Red", "Yellow", "Green", "Pink", "Black"));
        color = new JComboBox<>(ColorChoices.toArray(new String[0]));
        dispose();
        l = null;
        players = new ArrayList<>();
        try {
            board = new Board();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        game = null;
        Registration();
    }


}
