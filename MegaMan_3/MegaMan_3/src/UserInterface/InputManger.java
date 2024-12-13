
package UserInterface;

import GameObjects.GameWorld;
import java.awt.event.KeyEvent;

/**
 *
 * @author ADMIN
 */
public class InputManger {
    private GameWorld gameWorld;
    public InputManger(GameWorld gameWorld){
    this.gameWorld=gameWorld;
    }

    //day la phan xu li cac nut bam hay con goi la thao tac tren man hinh
    public void setPressedButton(int code){
        switch(code){
            
                    case KeyEvent.VK_UP:
                    gameWorld.megaMan.dick();
                    break;
                    
                    case KeyEvent.VK_DOWN:
                    gameWorld.megaMan.dick();
                    break;
        
                    case KeyEvent.VK_LEFT:
                    gameWorld.megaMan.setDirection(gameWorld.megaMan.LEFT_DIR);
                    gameWorld.megaMan.run();
                    break;
        
                    case KeyEvent.VK_RIGHT:
                    gameWorld.megaMan.setDirection(gameWorld.megaMan.RIGHT_DIR);
                    gameWorld.megaMan.run();
                    break;
        
                    case KeyEvent.VK_SPACE:
                   gameWorld.megaMan.jump();
                    break;
        
                    case KeyEvent.VK_ENTER:
                    if(gameWorld.state==gameWorld.PAUSEGAME){
                        if(gameWorld.previousState==gameWorld.GAMEPLAY)
                        gameWorld.switchState(gameWorld.GAMEPLAY);
                        else gameWorld.switchState(gameWorld.TUTORIAL);
                        gameWorld.bgMusic.loop();
                        gameWorld.bgMusic.play();
                    }
                    if(gameWorld.state== gameWorld.TUTORIAL && gameWorld.storyTutorial>=1){
                        if(gameWorld.storyTutorial<=3){
                            gameWorld.storyTutorial ++;
                            gameWorld.currentSize=1;
                            gameWorld.textTutorial=gameWorld.texts1[gameWorld.storyTutorial-1];
                        }else{
                            gameWorld.switchState(gameWorld.GAMEPLAY);
                        }
                        if(gameWorld.tutorialState==gameWorld.MEETFINALBOSS){
                            gameWorld.switchState(gameWorld.GAMEPLAY);
                        }
                    }

                    break;
        
                    case KeyEvent.VK_A:
                    gameWorld.megaMan.attack();
                    break;
        
                }         
    }
    public void setReleasedButton(int code){
        switch(code){
            
                    case KeyEvent.VK_UP:
                    break;
                    
                    case KeyEvent.VK_DOWN:
                    gameWorld.megaMan.standUp();
                    break;
        
                    case KeyEvent.VK_LEFT:
                    if (gameWorld.megaMan.getSpeedX()<0)
                    gameWorld.megaMan.stopRun();
                    break;
        
                    case KeyEvent.VK_RIGHT:
                    if (gameWorld.megaMan.getSpeedX()>0)
                    gameWorld.megaMan.stopRun();
                    break;
        
                    case KeyEvent.VK_SPACE:
                    break;
        
                    case KeyEvent.VK_ENTER:
                    break;
        
                    case KeyEvent.VK_A:
                    break;
        }
    }
    // public void processKeypressed(int keyCode) {
    //     switch(keyCode){
            
    //         case KeyEvent.VK_UP:
            
    //         break;
            
    //         case KeyEvent.VK_DOWN:
            
    //         break;

    //         case KeyEvent.VK_LEFT:
    //         gameWorld.megaMan.setDirection(gameWorld.megaMan.LEFT_DIR);
    //         gameWorld.megaMan.run();
    //         break;

    //         case KeyEvent.VK_RIGHT:
    //         gameWorld.megaMan.setDirection(gameWorld.megaMan.RIGHT_DIR);
    //         gameWorld.megaMan.run();
    //         break;

    //         case KeyEvent.VK_SPACE:
    //        gameWorld.megaMan.jump();
    //         break;

    //         case KeyEvent.VK_ENTER:
    //         break;

    //         case KeyEvent.VK_A:
    //         gameWorld.megaMan.attack();
    //         break;

    //     }
    // }

    // public void processKeyReleased(int keyCode){
    //     switch(keyCode){
            
    //         case KeyEvent.VK_UP:
    //         break;
            
    //         case KeyEvent.VK_DOWN:
    //         break;

    //         case KeyEvent.VK_LEFT:
    //         if (gameWorld.megaMan.getSpeedX()<0)
    //         gameWorld.megaMan.stopRun();
    //         break;

    //         case KeyEvent.VK_RIGHT:
    //         if (gameWorld.megaMan.getSpeedX()>0)
    //         gameWorld.megaMan.stopRun();
    //         break;

    //         case KeyEvent.VK_SPACE:
    //         break;

    //         case KeyEvent.VK_ENTER:
    //         break;

    //         case KeyEvent.VK_A:
    //         break;

    //     }
    // }
}

