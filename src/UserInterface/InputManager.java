
package UserInterface;

import GameObject.GameWorld;
import GameObject.Megaman;
import java.awt.event.KeyEvent;

/**
 *
 * @author ADMIN
 */
public class InputManager {
    
    private GameWorld gameWorld;
    
    public InputManager(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }
    
    //day la phan xu li cac nut bam hay con goi la thao tac tren man hinh
    public void processKeypressed(int keyCode) {
        switch(keyCode){
            
            case KeyEvent.VK_UP:
            System.out.println("UP");
            break;
            
            case KeyEvent.VK_DOWN:
            gameWorld.megaman.dick();
            break;

            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setDirection(gameWorld.megaman.LEFT_DIR);
                gameWorld.megaman.run();
             break;

            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(gameWorld.megaman.RIGHT_DIR);
                gameWorld.megaman.run();
            break;

            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
            break;

            case KeyEvent.VK_ENTER:
                if(gameWorld.state == GameWorld.PAUSEGAME){
                    if(gameWorld.previousState == GameWorld.GAMEPLAY){
                        gameWorld.switchState(GameWorld.GAMEPLAY);
                    }else {
                        gameWorld.switchState(GameWorld.TUTORIAL);
                    }
                    gameWorld.bgMusic.loop();
                    gameWorld.bgMusic.play();
                    
                }
                
                if(gameWorld.state ==  GameWorld.TUTORIAL && gameWorld.storyTutorial >= 1){
                    if(gameWorld.storyTutorial <= 3){
                        gameWorld.storyTutorial ++;
                        gameWorld.currentSize = 1;
                        gameWorld.textTutorial = gameWorld.texts1[gameWorld.storyTutorial - 1];
                    }
                }
                    
            break;

            case KeyEvent.VK_A:
                gameWorld.megaman.attack();
            break;

        }
    }

    public void processKeyReleased(int keyCode){
        switch(keyCode){
            
            case KeyEvent.VK_UP:
            break;
            
            case KeyEvent.VK_DOWN:
                gameWorld.megaman.standUp();
            break;

            case KeyEvent.VK_LEFT:
                if(gameWorld.megaman.getSpeedX() < 0)
                    gameWorld.megaman.stopRun();
            break;

            case KeyEvent.VK_RIGHT:
                if(gameWorld.megaman.getSpeedX() > 0)
                    gameWorld.megaman.stopRun();
            break;

            case KeyEvent.VK_SPACE:                              
                
            break;

            case KeyEvent.VK_ENTER:
            break;

            case KeyEvent.VK_A:
            break;

        }
    }
}

