
package UserInterface;

import GameEffect.Animation;
import GameEffect.CacheDataLoader;
import GameEffect.FrameImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    private Thread thread;
    private boolean isRunning;

    private InputManger inputManger;
    
    private BufferedImage bufImage;
    private Graphics2D buf2D;
    
    
    
    public GamePanel(){
        inputManger = new InputManger();
        
        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        
        
    }

    @Override
    public void paint(Graphics g){
        //tao man hinh do
//        g.setColor(java.awt.Color.WHITE);
//        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        g.drawImage(bufImage, 0, 0, this);
        
    }
    
    public void RenderGame(){
        if(bufImage == null){
            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }
        
        if(bufImage != null){
            buf2D = (Graphics2D) bufImage.getGraphics();
            
        }
        
        if(buf2D != null){
            // vẽ ở trong đây:
        buf2D.setColor(java.awt.Color.WHITE);
        buf2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        
//        buf2D.setColor(Color.red);
//        buf2D.fillRect(40, 50, 200, 50);
        }
    }
    
    public void startGame(){
        if(thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {

        int FPS =60 ;
        long period = (1000*1000000)/FPS;
        long beginTime;
        long sleepTime;
        int n = 1;

        beginTime = System.nanoTime();
        while(isRunning){
            
           //Update game
           //Render game
           RenderGame();
           repaint();
         
           
           long deltaTime = System.nanoTime() - beginTime;
           sleepTime = period - deltaTime;


            // System.out.println("n = " +(n++));
            try {
                if(sleepTime > 0){
                    Thread.sleep(sleepTime/1000000);
                }
                else Thread.sleep(period/2000000);
            }catch(InterruptedException ex){
            }

            beginTime = System.nanoTime();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
       
        inputManger.processKeypressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        inputManger.processKeyReleased(e.getKeyCode());
   
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
}
