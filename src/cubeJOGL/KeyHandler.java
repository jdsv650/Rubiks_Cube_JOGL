/* James Donner
 * KeyHandler.java
 * Last modified: 11/21/11
 * Processes keyboard input
 */
package cubeJOGL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class to handle input from the keyboard.
 */
public class KeyHandler implements KeyListener {

    private GLRenderer rend;  //the GLRenderer object
    private CubeDefs defs = CubeDefs.getInstance();  //cube definitions

    /**
     * Construct the key handler.
     * @param rend the rendering context.
     */
    KeyHandler(GLRenderer rend) {
        this.rend = rend;
    }

    /**
     * Unused (implements KeyListener).
     * @param e
     */
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Unused (implements KeyListener).
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Get the key pressed.
     * @param e the key event.
     */
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_1:
                rend.setAnimateSlice1(1);
                break;
            case KeyEvent.VK_2:
                rend.setAnimateSlice2(1);
                break;
            case KeyEvent.VK_3:
                rend.setAnimateSlice3(1);
                break;
            case KeyEvent.VK_4:
                rend.setAnimateSlice4(1);
                break;
            case KeyEvent.VK_5:
                rend.setAnimateSlice5(1);
                break;
            case KeyEvent.VK_6:
                rend.setAnimateSlice6(1);
                break;
            case KeyEvent.VK_7:
                rend.setAnimateSlice7(1);
                break;
            case KeyEvent.VK_8:
                rend.setAnimateSlice8(1);
                break;
            case KeyEvent.VK_9:
                rend.setAnimateSlice9(1);
                break;
            case KeyEvent.VK_L:
            case '-':
            case '_':
                rend.setEyeX(rend.getEyeX() + 1);
                rend.setEyeY(rend.getEyeY() + 1);
                rend.setEyeZ(rend.getEyeZ() + 1);
                break;
            case '+':
            case '=':
                rend.setEyeX(rend.getEyeX() - 1);
                rend.setEyeY(rend.getEyeY() - 1);
                rend.setEyeZ(rend.getEyeZ() - 1);
                break;
           // Exit via GUI
           // case 'Q':
           // case 'q':
           //     System.exit(0);
           //     break;
            case 'r':
            case 'R':
                if (rend.rubik.getRotMult() == 1) {
                    rend.rubik.setRotMult(-1);
                } else {
                    rend.rubik.setRotMult(1);
                }
                break;
            case '<':
            case ',':
                rend.setEyeX(defs.cubesize - 10);
                rend.setEyeY(defs.cubesize - 10);
                rend.setEyeZ(defs.cubesize - 10);
                rend.setPerspView(2);
                break;
            case '>':
            case '.':
                rend.setEyeX(defs.cubesize + 10);
                rend.setEyeY(defs.cubesize + 10);
                rend.setEyeZ(defs.cubesize + 10);
                rend.setPerspView(1);
                break;
            case 'V':
            case 'v':
                if (rend.getView() == 1) {
                    rend.setView(2);
                } else //view==2
                {
                    rend.setView(1);
                }
                break;
        }
    } // end keyTyped()
}
