package view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.*;
public class Player {

    private Color LightColor;
    private String name;
    private Direction CurrentDirection;
    private Position position;
    private  int CurrentDegree;
    private Image SelectedBeam;
    private Image SelectedPlayer;
    public Player(String name ,String LightColor){
        try {

            final Image GreenBeam = ResourceLoader.loadImage("res/GreenBeam.png");
            final Image BlueBeam = ResourceLoader.loadImage("res/BlueBeam.png");
            final Image BlackBeam = ResourceLoader.loadImage("res/BlackBeam.png");
            final Image PinkBeam = ResourceLoader.loadImage("res/PinkBeam.png");
            this.CurrentDirection = Direction.UP;
            this.CurrentDegree = 0;
            this.name = name;
            final Image RedBeam = ResourceLoader.loadImage("res/RedBeam.png");

            this.SelectedPlayer = ResourceLoader.loadImage("res/player.png");
            switch (LightColor) {
                case "Red":
                    this.LightColor = Color.red;
                    this.SelectedBeam = RedBeam;
                    break;
                case "Black":
                    this.LightColor = Color.BLACK;
                    this.SelectedBeam = BlackBeam;
                    break;
                case "Green":
                    this.LightColor = Color.green;
                    this.SelectedBeam = GreenBeam;
                    break;
                case "Pink":
                    this.LightColor = Color.pink;
                    this.SelectedBeam = PinkBeam;
                    break;
                case "Blue":
                    this.LightColor = Color.BLUE;
                    this.SelectedBeam = BlueBeam;
                    break;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void setLightColor(Color lightColor) {
        LightColor = lightColor;
    }

    public Direction getDirection() {
        return this.CurrentDirection;
    }

    public void setDirection(Direction direction) {
        this.CurrentDirection = direction;
    }

    public void setPosition(Position p ){
        this.position = p;
    }
    public Position  getPosition( ){
        return this.position;
    }

    public void setCurrentDegree(int currentDegree) {
        this.CurrentDegree = currentDegree;
    }

    public int getCurrentDegree() {
        return this.CurrentDegree;
    }

    public Image getLightBeam(){
        return this.SelectedBeam;
    }
    public void setLightBeamDirection(int degree){
        this.SelectedBeam =  ResourceLoader.rotate((BufferedImage) this.SelectedBeam , degree);
    }
    public void setPlayerDirection(int degree){
        this.SelectedPlayer =  ResourceLoader.rotate((BufferedImage) this.SelectedPlayer , degree);
    }

    public Image getSelectedPlayer() {
        return SelectedPlayer;
    }
    public String getName(){
        return name;
    }

}
