package view;

import java.io.File;
import java.util.Scanner;

public class Level {
    public String level[][];
    private File filename;
    int levelID;
    public Level(int LevelID){
        this.levelID = LevelID;
        switch (LevelID){
            case 6:
                filename = new File("../res/level6.txt");
                level = new String[6][6];
                break;
            case 8:
                filename = new File("../res/level8.txt");
                level = new String[8][8];
                break;
            case 10:
                filename = new File("../res/level10.txt");
                level = new String[10][10];
                break;
            case 12:
                filename = new File("../res/level12.txt");;
                level = new String[12][12];
                break;
            case 14:
                filename = new File("../res/level14.txt");;
                level = new String[14][14];
                break;
            case 16:
                filename = new File("../res/level16.txt");
                level = new String[16][16];
                break;
                case 18:
                filename = new File("../res/level18.txt");
                level = new String[18][18];
                break;
            case 20:
                filename = new File("../res/level20.txt");;
                level = new String[20][20];
                break;
            case 22:
                filename = new File("../res/level22.txt");;
                level = new String[22][22];
                break;
            case 24:
                filename = new File("../res/level24.txt");
                level = new String[24][24];
                break;
        }
    }
    /**
     * This method Scan the level file and fetch it in the level Matrix
     * @author OmarAshour
     * @throws Exception If File is not found it throws an exception
     * */
    public void FetchFile() throws Exception{
        try {
            Scanner scan = new Scanner(filename);
            for(int i = 0 ; i < this.levelID ; i++){
                StringBuilder temp = new StringBuilder();
                temp.append(scan.nextLine());
                for(int j = 0 ; j < this.levelID ; j++){
                  level[i][j] = String.valueOf(temp.charAt(j));
                }
                temp = null;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String getItem(int x , int y){
        return level[x][y];
    }
    public void SetItem(int x , int y , String val){
        level[x][y] = val;
    }
    public int getLevelID() {
        return levelID;
    }
    /**
     * This method Check if the player is out of border
     * @author OmarAshour
     * @param x takes in the player x position coordinate
     * @param y takes in the player y position coordinate
     * @return True if the player is out of border
     * */
    public boolean IsOutTheBorder(int x , int y){
        return (x < 0 || y < 0 || x>= levelID || y >= levelID);
    }
}
