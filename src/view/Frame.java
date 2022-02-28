package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class Frame extends JFrame{
    //JFrame f;
    JPanel MidPannel;
    JPanel SouthPannel;
    JMenuBar menu;
    JMenu options;
    JButton registrationButton;
    JButton StartGame;
    int registeredPlayers = 1;
    ArrayList <String> ColorChoices = new ArrayList<>(Arrays.asList("Red", "Blue", "Green", "Pink", "Black"));
    JComboBox<String> color = new JComboBox<String>(ColorChoices.toArray(new String[0]));
    final String[] SizeChoices = {"6","8","10","12" , "14" , "16","18","20","22","24"};
    final JComboBox<String> size = new JComboBox<String>(SizeChoices);
    JMenuItem exitMenuItem;
    JMenuItem restartMenuItem;
    JLabel RegistrationText;
    JTextField name;
    Board board;
    Level l;
    Game game;
    ArrayList<Player>players;
    public Frame() {
        try {
            players=new ArrayList<Player>();
            board = new Board();
            Registration();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }  /**
     * This method is To start the registration of the players
     * @author OmarAshour
     *
     * */
    public void Registration(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MidPannel = new JPanel();
        RegistrationText = new JLabel("Enter Player "  + registeredPlayers +"  Name and Light Colour");
        MidPannel.add(RegistrationText);
        SouthPannel = new JPanel();
        SouthPannel.setLayout(new BoxLayout(SouthPannel , BoxLayout.X_AXIS));
        name = new JTextField();
        SouthPannel.add(name);
        SouthPannel.add(color);
        registrationButton = new JButton("Register Player");
        SouthPannel.add(registrationButton);
        registrationButton.addActionListener(e ->{
            String ChoosenName = ColorChoices.get(color.getSelectedIndex());
            this.players.add(new Player( name.getText() , ChoosenName ));
            ColorChoices.remove(color.getSelectedIndex());
            name.setText("");
            if(registeredPlayers < 2) {
                registeredPlayers++;
                RegistrationText.setText("Enter Player " + registeredPlayers + "  Name and Light Colour");
                color.setModel(new DefaultComboBoxModel<String>(ColorChoices.toArray(new String[0])));
            }else{
                SetBoardSize();
            }


        });
        menu= new JMenuBar();
        setJMenuBar(menu);
        options = new JMenu("Options");
        menu.add(options);
        restartMenuItem = new JMenuItem("Restart");
        exitMenuItem = new JMenuItem("Exit");
        options.add(restartMenuItem);
        restartMenuItem.setMnemonic('R');
        restartMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_R , KeyEvent.ALT_MASK)
        );
        restartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Restart();
            }
        });
        options.add(exitMenuItem);
        exitMenuItem.setMnemonic('X');
        exitMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_X , KeyEvent.ALT_MASK)
        );

        exitMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        getContentPane().add(BorderLayout.NORTH , MidPannel);
        getContentPane().add(BorderLayout.SOUTH , SouthPannel);
        setSize(500,500);//500 width and 500 height
        setVisible(true);//making the frame visible
        color.setVisible(true);
    }
    /**
     * This method start the game
     * @author OmarAshour
     *
     * */
    public void StartGame(){
        try {
            this.MidPannel.removeAll();
            this.SouthPannel.removeAll();
            setVisible(false);
            int boardSize = Integer.parseInt(SizeChoices[size.getSelectedIndex()]);
            l = new Level(boardSize);
            l.FetchFile();
            game = new Game(  l , board);
            for(int i=0;i< players.size();i++){
                game.getPlayers().add(players.get(i));
            }
            game.SetBoard();
            add(BorderLayout.CENTER , board);

             setSize((new Dimension(41*boardSize , 51*boardSize)));
        //    pack();


           // board.repaint();
           // setResizable(false);
            new Thread(()->{
                addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent ke) {
                        try {
                            super.keyPressed(ke);
                            int kk = ke.getKeyCode();
                            switch (kk) {
                                case KeyEvent.VK_LEFT:
                                    game.getPlayers().get(0).setDirection(Direction.LEFT);
                                    game.getPlayers().get(0).setPlayerDirection(270 - game.getPlayers().get(0).getCurrentDegree());
                                    game.getPlayers().get(0).setCurrentDegree(270);

                                    break;
                                case KeyEvent.VK_RIGHT:
                                    game.getPlayers().get(0).setDirection(Direction.RIGHT);
                                    game.getPlayers().get(0).setPlayerDirection(90 - game.getPlayers().get(0).getCurrentDegree());
                                    game.getPlayers().get(0).setCurrentDegree(90);
                                    break;
                                case KeyEvent.VK_UP:
                                    game.getPlayers().get(0).setDirection(Direction.UP);
                                    game.getPlayers().get(0).setPlayerDirection(0 - game.getPlayers().get(0).getCurrentDegree());
                                    game.getPlayers().get(0).setCurrentDegree(0);
                                    break;
                                case KeyEvent.VK_DOWN:

                                    game.getPlayers().get(0).setDirection(Direction.DOWN);
                                    game.getPlayers().get(0).setPlayerDirection(180 - game.getPlayers().get(0).getCurrentDegree());
                                    game.getPlayers().get(0).setCurrentDegree(180);

                                    break;
                                case KeyEvent.VK_W:
                                    game.getPlayers().get(1).setDirection(Direction.UP);
                                    game.getPlayers().get(1).setPlayerDirection(0 - game.getPlayers().get(1).getCurrentDegree());
                                    game.getPlayers().get(1).setCurrentDegree(0);

                                    break;
                                case KeyEvent.VK_D:
                                    game.getPlayers().get(1).setDirection(Direction.RIGHT);
                                    game.getPlayers().get(1).setPlayerDirection(90 - game.getPlayers().get(1).getCurrentDegree());
                                    game.getPlayers().get(1).setCurrentDegree(90);
                                    break;
                                case KeyEvent.VK_S:
                                    game.getPlayers().get(1).setDirection(Direction.DOWN);
                                    game.getPlayers().get(1).setPlayerDirection(180 - game.getPlayers().get(1).getCurrentDegree());
                                    game.getPlayers().get(1).setCurrentDegree(180);
                                    break;
                                case KeyEvent.VK_A:
                                    game.getPlayers().get(1).setDirection(Direction.LEFT);
                                    game.getPlayers().get(1).setPlayerDirection(270 - game.getPlayers().get(1).getCurrentDegree());
                                    game.getPlayers().get(1).setCurrentDegree(270);
                                    break;
                                default:
                                    break;
                            }
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                });
               Player Loser =  game.StartMovment();
                JOptionPane.showMessageDialog(this , Loser.getName() + " " +  " Have Lost");

            }).start();

            setVisible(true);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
    /**
     * This method is to Set the BoardSize
     * @author OmarAshour
     *
     * */
    public void SetBoardSize(){
        this.SouthPannel.removeAll();
        this.MidPannel.removeAll();
        RegistrationText.setText("Set Board Size");
        this.StartGame = new JButton("Start Game");
        this.MidPannel.add(RegistrationText);
        this.SouthPannel.add(size);
        this.SouthPannel.add(StartGame);
            this.StartGame.addActionListener(

                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            StartGame();
                        }
                    }

            );

        setVisible(true);

    }
    /**
     * This method is to restart the program
     * @author OmarAshour
     *
     * */
    public void Restart(){
        registeredPlayers = 1;
        this.SouthPannel.removeAll();
        this.MidPannel.removeAll();
        remove(this.board);
        remove(this.SouthPannel);
        remove(this.MidPannel);
        this.board = null;
        ColorChoices = new ArrayList<>(Arrays.asList("Red", "Yellow", "Green", "Pink", "Black"));
        color = new JComboBox<String>(ColorChoices.toArray(new String[0]));
        dispose();
        l = null;
        players=new ArrayList<Player>();
        try{
            board = new Board();
        }
    catch(Exception e){}
        game = null;
        Registration();

    }


}
