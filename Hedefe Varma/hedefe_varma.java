

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;




public class hedefe_varma {

    public static int zaman_txt =15;


    public static int hedef_x=310;
    public static int hedef_y=310;

    public static int oyuncu_x=150;
    public static int oyuncu_y=150;
    public static int score_txt;

    public static int best_score_txt;


    public static File yol = new File("yol.txt");
    public static void main(String[] args) throws FileNotFoundException{


        //pencere oluşturma
        JFrame pencere_1 =new JFrame("pencere_1");


        //pencere içine gelecek objeler
        JLabel lbl_1 = new JLabel(new ImageIcon("Assets/Image/10X10.png"));
        JLabel lbl_2 = new JLabel(new ImageIcon("Assets/Image/alan.png"));
        JLabel lbl_3 = new JLabel(new ImageIcon("Assets/Image/score.png"));
        JLabel lbl_4 = new JLabel(new ImageIcon("Assets/Image/hedef.png"));
        JLabel score =new JLabel("SKOR: 0");
        JLabel zaman =new JLabel("ZAMAN: "+zaman_txt);
        JButton btn_1 =new JButton();
        JButton btn_2 =new JButton();
        JButton btn_3 =new JButton();
        JButton btn_4 =new JButton();
        //pencere içine gelecek objelerin konum ve sizeları
        lbl_1.setBounds(oyuncu_x, oyuncu_y, 10, 10);
        lbl_2.setBounds(0, 0, 350, 350);
        lbl_3.setBounds(0,350,350,60);
        lbl_4.setBounds(hedef_x, hedef_y, 10, 10);
        score.setBounds(10, 360, 100, 30);
        zaman.setBounds(110, 360, 100, 30);
        btn_1.setBounds(100, 460, 50, 50);
        btn_2.setBounds(0, 460, 50, 50);
        btn_3.setBounds(50, 510, 50, 50);
        btn_4.setBounds(50, 410, 50, 50);

        //file işlemleri


 


        File best_score = new File("Data/Score/best_score.txt");
        Scanner oku = new Scanner(best_score);
        while(oku.hasNextLine())
        {
            best_score_txt=Integer.parseInt(oku.nextLine());
            System.out.println("Best:"+best_score_txt);
        }
        oku.close();



        //buton işlemleri
        btn_1.addActionListener((ActionListener) new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(oyuncu_x>=310){
                    oyuncu_x=310;
                }
                else{
                    oyuncu_x+=10;
                }
                lbl_1.setBounds(oyuncu_x, oyuncu_y, 10, 10);
            }            
        });

        btn_2.addActionListener((ActionListener) new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(oyuncu_x<=30){
                    oyuncu_x=30;
                }
                else{
                    oyuncu_x-=10;
                }
                lbl_1.setBounds(oyuncu_x, oyuncu_y, 10, 10);
                
            }            
        });
        //S
        btn_3.addActionListener((ActionListener) new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(oyuncu_y>=310){
                    oyuncu_y=310;
                }
                else{
                    oyuncu_y+=10;
                }
                lbl_1.setBounds(oyuncu_x, oyuncu_y, 10, 10);
                
            }            
        });

        btn_4.addActionListener((ActionListener) new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(oyuncu_y<=30){
                    oyuncu_y=30;
                }
                else{
                    oyuncu_y-=10;
                }
                lbl_1.setBounds(oyuncu_x, oyuncu_y, 10, 10);
                
            }            
        });

        //Zaman
        Timer timer_zaman=new Timer();
        TimerTask gorev_zaman=new TimerTask(){
        
            @Override
            public void run() {
                zaman_txt--;
                zaman.setText("ZAMAN: "+zaman_txt);
                if(zaman_txt<=0){
                    try {
                        if(best_score_txt<score_txt){
                            FileWriter best_score_write = new FileWriter("Data/Score/best_score.txt");
                            best_score_txt=score_txt;
                            best_score_write.write(Integer.toString(best_score_txt));
                            best_score_write.close();
                            System.out.println("Skor:"+score_txt);
                            System.out.println("Best:"+best_score_txt);
                        }
                    } 
                    catch (IOException e1) {
                        System.out.println("An error occurred.");
                        e1.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(pencere_1,"SKOR:"+score_txt+"\n"+"EN YÜKSEK SKOR:"+best_score_txt);
                    score_txt=0;
                    String score_txt_txt=Integer.toString(score_txt);
                    score.setText("SKOR:"+score_txt_txt);
                    zaman_txt=15;
                    zaman.setText("ZAMAN: "+zaman_txt);
                    hedef_x=0;
                    hedef_y=0;
                    Random random_1 = new Random();
                    Random random_2 = new Random();
                    int sayı_x=random_1.nextInt(32)*10;
                    int sayı_y=random_2.nextInt(32)*10;
                    if(sayı_x<=20){
                        sayı_x=30;
                        hedef_x=sayı_x;
                        lbl_4.setLocation(hedef_x, hedef_y);
                    }
                    else{
                        hedef_x=sayı_x;
                        lbl_4.setLocation(hedef_x, hedef_y);;
                    }
                    //
                    if(sayı_y<=20){
                        sayı_y=30;
                        hedef_y=sayı_y;
                        lbl_4.setLocation(hedef_x, hedef_y);
                    }
                    else{
                        hedef_y=sayı_y;
                        lbl_4.setLocation(hedef_x, hedef_y);
                    }

                }
            }
                    
        };
        timer_zaman.schedule(gorev_zaman, 0, 1000);

        //ÇARPIŞMA KONTROL
        Timer timer_çarpışma=new Timer();
        TimerTask gorev_çarpışma=new TimerTask(){

            @Override
            public void run() {
                if((oyuncu_x==hedef_x)&&(oyuncu_y==hedef_y)){
                    score_txt++;
                    score.setText("SKOR: "+score_txt);
                    zaman_txt=10;
                    zaman.setText("ZAMAN: "+zaman_txt);
                    hedef_x=0;
                    hedef_y=0;
                    Random random_1 = new Random();
                    Random random_2 = new Random();
                    int sayı_x=random_1.nextInt(32)*10;
                    int sayı_y=random_2.nextInt(32)*10;
                    if(sayı_x<=20){
                        sayı_x=30;
                        hedef_x=sayı_x;
                        lbl_4.setLocation(hedef_x, hedef_y);
                    }
                    else{
                        hedef_x=sayı_x;
                        lbl_4.setLocation(hedef_x, hedef_y);;
                    }
                    //
                    if(sayı_y<=20){
                        sayı_y=30;
                        hedef_y=sayı_y;
                        lbl_4.setLocation(hedef_x, hedef_y);
                    }
                    else{
                        hedef_y=sayı_y;
                        lbl_4.setLocation(hedef_x, hedef_y);
                    }
                    
                }
            }
            
        };
        timer_çarpışma.schedule(gorev_çarpışma, 0, 10); 

        //pencere içine gelecek objelerin pencereye eklenmesi
        pencere_1.add(lbl_1);
        pencere_1.add(lbl_2);
        pencere_1.add(lbl_3);
        pencere_1.add(lbl_4);
        pencere_1.add(score);
        pencere_1.add(zaman);
        pencere_1.add(btn_1);
        pencere_1.add(btn_2);
        pencere_1.add(btn_3);
        pencere_1.add(btn_4);
        
        //pencere özellikleri
        pencere_1.setBounds(0,0,366,600);       
        pencere_1.setResizable(false);
        pencere_1.setFocusable(false);
        pencere_1.setLayout(null);
        pencere_1.setVisible(true);                               
        pencere_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    
}