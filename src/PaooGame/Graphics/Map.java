package PaooGame.Graphics;

import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Map{
    private int [][] m;
    private int rows;
    private int columns;
    public Map(String path)
    {

        File file = new File(path);

        try {
            Scanner scanner = new Scanner(file);
            rows=scanner.nextInt();
            columns=scanner.nextInt();
            m=new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    m[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void drawMap(Graphics g, int lvlOffset)
    {
        for(int i=0;i<rows;++i) {
            for(int j=0;j<columns;++j) {
                if (m[i][j] == 0 || m[i][j]==Tile.max.GetId() || m[i][j]==Tile.snake.GetId())
                    continue;
                else
                    Tile.tiles[m[i][j]].Draw(g, j * Tile.TILE_WIDTH - lvlOffset, i * Tile.TILE_WIDTH, m[i][j]);
            }
        }
    }
    public int getId(int i,int j){
        return m[i][j];
    }
    public void setId(int i,int j,int val){
         m[i][j]=val;
    }
    public int[][] getMap(){return m;}
}
