
package UserInterface;

import java.awt.event.KeyEvent;

/**
 *
 * @author ADMIN
 */
public class InputManger {

    //day la phan xu li cac nut bam hay con goi la thao tac tren man hinh
    public void processKeypressed(int keyCode) {
        switch(keyCode){
            
            case KeyEvent.VK_UP:
            System.out.println("UP");
            break;
            
            case KeyEvent.VK_DOWN:
            System.out.println("DOWN");
            break;

            case KeyEvent.VK_LEFT:
            System.out.println("LEFT");
            break;

            case KeyEvent.VK_RIGHT:
            System.out.println("RIGHT");
            break;

            case KeyEvent.VK_SPACE:
            break;

            case KeyEvent.VK_ENTER:
            break;

            case KeyEvent.VK_A:
            break;

        }
    }

    public void processKeyReleased(int keyCode){
        switch(keyCode){
            
            case KeyEvent.VK_UP:
            break;
            
            case KeyEvent.VK_DOWN:
            break;

            case KeyEvent.VK_LEFT:
            break;

            case KeyEvent.VK_RIGHT:
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

