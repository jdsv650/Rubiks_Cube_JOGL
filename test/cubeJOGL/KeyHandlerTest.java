/* James Donner
 * KeyHandlerTest.java
 * Last modified: 11/21/11
 * Tests keyboard input
 */
package cubeJOGL;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Test key handler with simulated keyboard input.
 */
public class KeyHandlerTest {

    private KeyHandler kH;
    private GLRenderer rend;
    private Player p;
    private CubeDefs defs = CubeDefs.getInstance();

    public KeyHandlerTest() {
        CubeMain theCube = new CubeMain();
        char[] newPass = new char[]{'0', '0', '0', '0'};
        p = new Player(1, "Default User", theCube, newPass);
        rend = new GLRenderer(p);
        kH = new KeyHandler(rend);
    }

    /**
     * Test of keyTyped method, keyPressed and keyReleased not implemented.
     */
    @Test
    public void testKeyTyped() {
        float eyeX, eyeY, eyeZ;
        JTextField tF = new JTextField(20);  //Just need any component for KeyEvent const
        System.out.println("testing...keyTyped");

        eyeX = rend.getEyeX();
        eyeY = rend.getEyeY();
        eyeZ = rend.getEyeZ();

        //create and send in a '-' key press
        KeyEvent e = new KeyEvent(tF, KeyEvent.KEY_TYPED, System.currentTimeMillis(),
                KeyEvent.SHIFT_DOWN_MASK, KeyEvent.VK_UNDEFINED, '-',
                KeyEvent.KEY_LOCATION_UNKNOWN);
        kH.keyTyped(e);  // increments eyeX - eyeY and eyeZ

        assertEquals(eyeX + 1, rend.getEyeX(), 0.01);
        assertEquals(eyeY + 1, rend.getEyeY(), 0.01);
        assertEquals(eyeZ + 1, rend.getEyeZ(), 0.01);

        e.setKeyChar('='); // decrements eyeX - eyeY and eyeZ
        kH.keyTyped(e);
        kH.keyTyped(e);

        assertEquals(eyeX - 1, rend.getEyeX(), 0.01);
        assertEquals(eyeY - 1, rend.getEyeY(), 0.01);
        assertEquals(eyeZ - 1, rend.getEyeZ(), 0.01);

        rend.rubik.setRotMult(1);
        e.setKeyChar('R');

        // Check toggle -1,1
        kH.keyTyped(e);
        assertEquals(-1, rend.rubik.getRotMult());
        kH.keyTyped(e);
        assertEquals(1, rend.rubik.getRotMult());

        e.setKeyChar('v');
        rend.setView(1);
        // Check toggle  1,2
        kH.keyTyped(e);
        assertEquals(2, rend.getView());
        kH.keyTyped(e);
        assertEquals(1, rend.getView());

        // Check <,>
        e.setKeyChar('<');
        kH.keyTyped(e);
        // assertEquals(2, rend.getPerspView);  no getter
        assertEquals(defs.cubesize - 10, rend.getEyeX(), 0.01);
        assertEquals(defs.cubesize - 10, rend.getEyeY(), 0.01);
        assertEquals(defs.cubesize - 10, rend.getEyeZ(), 0.01);

        e.setKeyChar('>');
        kH.keyTyped(e);
        // assertEquals(1, rend.getPerspView);  no getter
        assertEquals(defs.cubesize + 10, rend.getEyeX(), 0.01);
        assertEquals(defs.cubesize + 10, rend.getEyeY(), 0.01);
        assertEquals(defs.cubesize + 10, rend.getEyeZ(), 0.01);

        // Check animateSlice flags
        rend.clearAllAnimatedSlices();
        e.setKeyChar('1');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[1]);
        assertEquals(0, rend.getAllAnimated()[2]);
        e.setKeyChar('2');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[2]);
        e.setKeyChar('3');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[3]);
        e.setKeyChar('4');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[4]);
        e.setKeyChar('5');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[5]);
        e.setKeyChar('6');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[6]);
        e.setKeyChar('7');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[7]);
        e.setKeyChar('8');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[8]);
        e.setKeyChar('9');
        kH.keyTyped(e);
        assertEquals(1, rend.getAllAnimated()[9]);
        rend.clearAllAnimatedSlices();
        for (int i = 1; i < 10; i++) {
            assertEquals(0, rend.getAllAnimated()[i]);
        }

        // unused -- call to show better coverage
        kH.keyPressed(e);
        kH.keyReleased(e);

    }
}
