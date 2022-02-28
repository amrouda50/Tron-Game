package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {
    private Level GameLevel;
    private Board Board;
    private ArrayList<Player> Players ;
    private boolean GameHasEnded = false;
    Game(Level GameLevel , Board board){
        this.GameLevel = GameLevel;
        this.Board = board;
        this.Players = new ArrayList<>();
        board.setGame(this);
    }
    /**
     * This method Start movement of players
     * @author OmarAshour
     * @return The loser player after the infinite movment is returned
     * */
    public Player StartMovment(){
        int i = 0;
        while(true) {
            try {
                Player p1 = Players.get(0);
                Player p2 = Players.get(1);
                Position current1 = p1.getPosition();
                Position next1 = p1.getPosition().translate(p1.getDirection());
                if(  GameLevel.IsOutTheBorder(next1.x , next1.y) || GameLevel.getItem(next1.x , next1.y) == "^" || GameLevel.getItem(next1.x , next1.y) == ")")
                {
                   return p1;
                }
                Position current2 = p2.getPosition();
                Position next2 = p2.getPosition().translate(p2.getDirection());
                if( GameLevel.IsOutTheBorder(next2.x , next2.y) || GameLevel.getItem(next2.x , next2.y) == "@" || GameLevel.getItem(next2.x , next2.y) == "("){
                   return p2;
                }
                GameLevel.SetItem(current1.x, current1.y, "(");
                GameLevel.SetItem(next1.x, next1.y, "@");
                GameLevel.SetItem(current2.x, current2.y, ")");
                GameLevel.SetItem(next2.x, next2.y, "^");
                Thread.sleep(300);
                Board.repaint();
            }
            catch(Exception e){

            }
        }
     }
    /**
     * This method Fetch the File with the map into the level matrix
     * @author OmarAshour
     *
     * */
    private void FetchFile(){
        try {
            GameLevel.FetchFile();
        }
        catch (Exception e){

        }
    }
    /**
     * This method Set the size of the Board and its rows and columns
     * @author OmarAshour
     *
     * */
    public void SetBoard(){
        Board.setColms(GameLevel.getLevelID());
        Board.setRows(GameLevel.getLevelID());
        Board.setPreferredSize(new Dimension(48*GameLevel.getLevelID() , 48*GameLevel.getLevelID()));
    }
    /**
     * This method get the level item at a certain point
     * @author OmarAshour
     * @param i takes the x Coordinate
     * @param j takes the y Coordinate
     * @return Level item
     * */
    public String GetLevelItem(int i , int j){
       return GameLevel.getItem(i , j);
    }
    /**
     * This method Set player 1 position
     * @author OmarAshour
     * @param x takes the x position
     * */
    public void SetPlayerOnePosition(Position x){
        Players.get(0).setPosition(x);
    }
    /**
     * This method set player 2 position
     * @author OmarAshour
     * @param x takes x position
     * */
    public void SetPlayerTwoPosition(Position x){
        Players.get(1).setPosition(x);
    }
    /**
     * This method return the players list
     * @author OmarAshour
     * @return Arraylist
     * */
    public ArrayList<Player> getPlayers() {
        return Players;
    }
    /**
     * This method return the Image for the lightBeam for player one
     * @author OmarAshour
     * @return Image of the LightBeam for player one
     * */
    public Image GetPlayerOneLightBeam(){
        return Players.get(0).getLightBeam();
    }
    /**
     * This method return the Image for the lightBeam for player two
     * @author OmarAshour
     * @return Image of light beam for player two
     * */
    public Image GetPlayerTwoLightBeam(){
        return Players.get(1).getLightBeam();
    }
    /**
     * This method return the Image for player one
     * @author OmarAshour
     * @return Image for player one
     * */
    public Image  GetPlayerOneImage(){
       return  Players.get(0).getSelectedPlayer();
    }
    /**
     * This method return the Image for Player two
     * @author OmarAshour
     * @return Image for player two
     * */
    public Image GetPlayerTwoImage(){
       return  Players.get(1).getSelectedPlayer();
    }


}
