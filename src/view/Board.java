package view;

import javax.swing.*;
import view.ResourceLoader;
import view.Level;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends JPanel {
    private final Image  player = ResourceLoader.loadImage("res/player.png");
    private final Image Ground = ResourceLoader.loadImage("res/ground.png");
    private final Image box = ResourceLoader.loadImage("res/box.png");
    private  Game Game;
    private boolean isSet = true;
    private final int scaledSize = 38;
    private  int rows;
    private  int colms;
    public Board() throws IOException {
    }
    public view.Game getGame() {
        return Game;
    }
    public void setGame(view.Game game) {
        Game = game;
    }
    /**
     * This method repaint the component of the board
     * @author OmarAshour
     *
     * */
    @Override
    public void repaint(){
        paintComponent(super.getGraphics());
    }
    /**
     * This method Paints the Component of the Board
     * @author OmarAshour
     * @param g Main method default param
     * */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D)g;
        for (int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < colms ; j++){
                Image current = null;
                String currBlock = Game.GetLevelItem(i, j);
                switch (currBlock) {
                    case "#" -> current = Ground;
                    case "&" -> current = box;
                    case "@" -> {
                        current = Game.GetPlayerOneImage();
                        Game.SetPlayerOnePosition(new Position(i, j));
                    }
                    case "^" -> {
                        current = Game.GetPlayerTwoImage();
                        Game.SetPlayerTwoPosition(new Position(i, j));
                    }
                    case "(" -> current = Game.GetPlayerOneLightBeam();
                    case ")" -> current = Game.GetPlayerTwoLightBeam();
                }
                gr.drawImage(current, j*scaledSize, i * scaledSize, scaledSize, scaledSize, null);
            }
        }



    }
    /**
     * This method set the rows
     * @author OmarAshour
     * @param rows rows as parameter
     * */
    public void setRows(int rows) {
        this.rows = rows;
    }
    /**
     * This method set the columns
     * @author OmarAshour
     * @param colms columns as a parameter
     * */
    public void setColms(int colms) {
        this.colms = colms;
    }
}
