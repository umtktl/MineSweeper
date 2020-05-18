/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author MSİ
 */
public class MineSweeper  {

    /**
     * @param args the command line arguments
     */
     JFrame jFrame;
     JButton buttons [][];

     int min;
     int sec;
    public void createButtons(int x, int y, double ratio){
        final  Sweeper s = new Sweeper(x,y,ratio);
        final JTextField tf = new JTextField("");
        tf.setBounds(0,0,50, 50);
        
      final boolean [] [] areas = s.areas;
        System.out.println(x+","+y);
        this.jFrame = new JFrame("Mayın Tarlası");
        this.jFrame.setSize(50+x*80,50+y*80);
        this.jFrame.setLayout(null);
        this.jFrame.setVisible(true);
        jFrame.add(tf);
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                sec++;
                if(sec%60==0){
                  min++;
                  sec = 0;
            }
            tf.setText("Tmr:"+min+":"+sec);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MineSweeper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
        
        this.buttons = new JButton[x][y];
        for(int i =0;i<x;i++){
            for(int j=0;j<y;j++){
                this.createOneButton(s.getMineBool(i, j), s.getNeighbor(i, j), i, j);
                
            }
        }
        while(true){
            if(jFrame.isVisible())
            t1.run();
            else 
                break;
        }
    }
    public void createOneButton(boolean hasMine,int nCount,int i,int j){
        final boolean hasM = hasMine;
        final int nC = nCount;
        buttons[i][j] = new JButton("?");
        buttons[i][j].setBounds(50+(i*60),50+(j*60),60, 60);  
                buttons[i][j].setText("?");
        final JButton button = buttons[i][j];
         
                
                button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(hasM){
                    button.setText("!");
                    JOptionPane.showMessageDialog(jFrame, "Oyun Bitti!");
                    jFrame.setVisible(false);
                    jFrame.dispose();
                }
                else{
                   button.setText(nC+"");
                }
            }
                    
                });
                buttons[i][j] = button;
                buttons[i][j].setVisible(true);
                
                jFrame.add(buttons[i][j]);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        String x_val = JOptionPane.showInputDialog("Matris satır sayısını giriniz:");
        int x = Integer.parseInt(x_val);
        String y_val = JOptionPane.showInputDialog("Matris sütun sayısını giriniz:");
        int y = Integer.parseInt(y_val);
        String r_val = JOptionPane.showInputDialog("Mayın oranını giriniz (0-1 aralığında):");
        
        double ratio = Double.parseDouble(r_val);
        MineSweeper ms = new MineSweeper();
        ms.createButtons(x,y,ratio);
        ms.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        
    }

  
}
