
package UserInterface;

import GameEffect.Animation;
import GameEffect.CacheDataLoader;
import GameEffect.FrameImage;
import GameObject.GameWorld;
import GameObject.Megaman;
import GameObject.PhysicalMap;
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
    
    Thread thread;
    private boolean isRunning = true;

    private InputManager inputManager;
    
    private BufferedImage bufImage;
    private Graphics2D buf2D;
    
    //Testing
    public GameWorld gameWorld;
    
    public GamePanel(){
        
        gameWorld = new GameWorld();
        
        inputManager = new InputManager(gameWorld);
        
//        bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);        
                
    }

    public void startGame(){
//        if(thread == null){
//            isRunning = true;
//            thread = new Thread(this);
//            thread.start();
//        }
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void paint(Graphics g){
        //tao man hinh do
//        g.setColor(java.awt.Color.WHITE);
//        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        g.drawImage(gameWorld.getBufferedImage(), 0, 0, this);
        
        
        
    }
    
//    public void UpdateGame(){
//        gameWorld.Update();
//        //gameWorld.Render();
//    }
    
//    public void RenderGame(){
//        if(bufImage == null){
//            bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
//        }
//        
//        if(bufImage != null){
//            buf2D = (Graphics2D) bufImage.getGraphics();
//            
//        }
//        
//        if(buf2D != null){
//            // vẽ ở trong đây:
//        buf2D.setColor(java.awt.Color.WHITE);
//
//        
//        gameWorld.Render();
//       
//
//        }
//    }
    

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
//           UpdateGame();
           //Render game
//           RenderGame();
        gameWorld.Update();
        gameWorld.Render();
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
       
        inputManager.processKeypressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        inputManager.processKeyReleased(e.getKeyCode());
        
   
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
}
