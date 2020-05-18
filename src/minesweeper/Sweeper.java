/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author MSİ
 */
public class Sweeper {
    public boolean areas [][];
    public int neighbor [][];
    public double ratio;
    public int x;
    public int y;
    
    public Sweeper(int x,int y,double ratio){
        this.x = x;
        this.y = y;
        this.ratio = ratio;
        this.areas = new boolean[x][y];
        this.neighbor = new int[x][y];
        fillMatrix();
        calculateNM();
    }
    public void fillMatrix(){
        for(int i =0;i<areas.length;i++){
            for(int j=0;j<areas.length;j++){
                double chance = Math.random();
                if(chance < ratio){
                    this.areas[i][j] = true;
                }
            }
        }
    }
    public void calculateNM(){
        for(int i =0;i<x;i++){
            for(int j=0;j<y;j++){
                 this.neighbor[i][j]=0;
                for(int k=-1;k<2;k++){
                    for(int l=-1;l<2;l++){
                        int index1 = i+k;
                        int index2 = j+l;
                        if(index1<0 || index1>(x-1)||index2<0||index2>(y-1)){
                            continue;
                        }
                        else if(this.areas[index1][index2]){
                            System.out.println(i+","+j);
                            this.neighbor[i][j]++;
                        }
                    }
                }
            }
        }
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                System.out.print(this.neighbor[i][j]+",");
            }
            System.out.println("");
        }
         for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                System.out.print(this.areas[i][j]+",");
            }
            System.out.println("");
        }
    }
    public boolean getMineBool(int i,int j){
        return areas[i][j];
        
    }
    public int getNeighbor(int i,int j){
        return neighbor[i][j];
    }
    
}
