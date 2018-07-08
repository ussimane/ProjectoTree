/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package splashe;

import Vista.Login;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MASSINGUE
 */
public class Splashe extends JWindow{
    JProgressBar barraDeProgresso;

    public Splashe() throws InterruptedException {
        
            int w = Toolkit.getDefaultToolkit().getScreenSize().width;
            int h = Toolkit.getDefaultToolkit().getScreenSize().height;
            int z = 2;
            int x = (w - 500) / z;
            int y = (h - 270) / z;

            String path = new File("Icone/splash.png").getAbsolutePath();
            JLabel img = new JLabel(new ImageIcon(path));
            img.setLocation(new Point(0, 0));
            img.setSize(490, 260);

            this.setLayout(null);
            this.add(img);
            this.setLocation(new Point(x, y));
            this.setSize(490, 280);
            this.setVisible(true);

            barraDeProgresso = new JProgressBar();
            barraDeProgresso.setBackground(new Color(0, 102, 52));
          // barraDeProgresso.setBackground(Color.BLUE);
            barraDeProgresso.setBounds(0, 260, 490, 20);
            barraDeProgresso.setStringPainted(true);
            this.add(barraDeProgresso);

            new Thread() {

                @Override
                public void run() {
                    for (int progress = 0; progress < 101; progress++) {
                        try {
                            barraDeProgresso.setValue(progress);
                            sleep(20);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }.start();
            Thread.sleep(2600);
            this.setVisible(false);
            new Login(null, true).setVisible(true);

       

    }
    
    public static void main(String[] args) throws InterruptedException {
        try {             
            // Splash splash = new Splash();
            //   splash.setVisible(true);
            String nimbu = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
            UIManager.setLookAndFeel(nimbu);
            new Splashe();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Splashe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Splashe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Splashe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Splashe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    
}

