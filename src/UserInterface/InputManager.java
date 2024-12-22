
package UserInterface;

import GameObject.GameWorld;
import GameObject.Megaman;
import GameObject.State;
import java.awt.event.KeyEvent;

/**
 *
 * @author ADMIN
 */
public class InputManager {
    
    private State gameState;
    
    public InputManager(State state){
        this.gameState = state;
    }
    
    public void setState(State state) {
        gameState = state;
    }
    
    public void setPressedButton(int code){
        gameState.setPressedButton(code);
    }
    
    public void setReleasedButton(int code){
        gameState.setReleasedButton(code);
    }
    
}


