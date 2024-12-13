
package UserInterface;

import GameObjects.GameWorld;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    Thread gameThread;
    
    InputManger inputManger;

    GameWorld gameWorld;
    public  boolean isRunning=true;

    public GamePanel(){
        gameWorld = new GameWorld();
        inputManger = new InputManger(gameWorld);
    }
    public void startGame(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    int a = 0;
    @Override
    public void run() {
        long previousTime=System.nanoTime();
        long currentTime;
        long sleepTime;

        long period= 100000000/80;
        while (isRunning) {
            gameWorld.Update();
            gameWorld.Render();
            repaint();
            currentTime= System.nanoTime();
            sleepTime= period-(currentTime-previousTime);
            try{
                if(sleepTime>0)
                Thread.sleep(sleepTime/1000000);
                else Thread.sleep(period/2000000);
            }catch(Exception e){}
            previousTime=System.nanoTime();
            
        }
    }
        public void paint(Graphics g){
            //tao man hinh do
    //        g.setColor(java.awt.Color.WHITE);
    //        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            g.drawImage(gameWorld.getBufferedImage(), 0, 0, this);
        }
        @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
       
        inputManger.setPressedButton(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
        inputManger.setReleasedButton(e.getKeyCode());
   
    }
}


    


        // int FPS =60 ;
        // long period = (1000*1000000)/FPS;
        // long beginTime;
        // long sleepTime;
        // int n = 1;

        // beginTime = System.nanoTime();
        // while(isRunning){
            
        // UpdateGame();
        //    //Render game
        //    RenderGame();
        //    repaint();
         
           
        //    long deltaTime = System.nanoTime() - beginTime;
        //    sleepTime = period - deltaTime;


        //     // System.out.println("n = " +(n++));
        //     try {
        //         if(sleepTime > 0){
        //             Thread.sleep(sleepTime/1000000);
        //         }
        //         else Thread.sleep(period/2000000);
        //     }catch(InterruptedException ex){
        //     }

        //     beginTime = System.nanoTime();
        // }
    

//     @Override
//     public void paint(Graphics g){
//         //tao man hinh do
// //        g.setColor(java.awt.Color.WHITE);
// //        g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
//         g.drawImage(bufImage, 0, 0, this);
        
//     }
//     public void UpdateGame(){
//     gameWorld.Update();
//     }
//     public void RenderGame(){
//         if(bufImage == null){
//             bufImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
//         }
        
//         if(bufImage != null){
//             buf2D = (Graphics2D) bufImage.getGraphics();
            
//         }
        
//         if(buf2D != null){
//             // vẽ ở trong đây:
//         buf2D.setColor(java.awt.Color.WHITE);
//         //buf2D.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
//         gameWorld.Render(buf2D);      
// //        buf2D.setColor(Color.red);
// //        buf2D.fillRect(40, 50, 200, 50);

//         }
//     }
    
    

    
    

