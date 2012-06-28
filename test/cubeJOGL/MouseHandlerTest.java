/* James Donner
 * MouseHandlerTest.java
 * Last modified: 11/21/11
 * Tests handling of mouse input
 */
package cubeJOGL;

import java.awt.event.MouseEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Test mouse handler with simulated input.
 */
public class MouseHandlerTest {

    private MouseHandler mH;
    private GLRenderer rend;
    private Player p;
    private GameGUI gui;

    public MouseHandlerTest() {
        CubeMain theCube = new CubeMain();
        char[] newPass = new char[]{'0', '0', '0', '0'};
        p = new Player(1, "Default User", theCube, newPass);
        rend = new GLRenderer(p);
        gui = new GameGUI();
        mH = new MouseHandler(rend, gui);
    }

    // Check slice rotation mouse released Q1 F
    private void eventFQ1(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q1 (105, 95)
        evt.translatePoint(5, -5);
        mH.jButtonFMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
    }

    // Check slice rotation mouse released Q2 F
    private void eventFQ2(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q2 (95, 95)
        evt.translatePoint(-10, 0);
        mH.jButtonFMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
    }

    // Check slice rotation mouse released Q3 F
    private void eventFQ3(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q3 (95, 105)
        evt.translatePoint(0, +10);
        mH.jButtonFMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
    }

    // Check slice rotation mouse released Q4 F
    private void eventFQ4(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q4 (105, 105)
        evt.translatePoint(+10, 0);
        mH.jButtonFMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
        // RESET point to 100,100 for next test
        evt.translatePoint(-5, -5);
    }

    // Check slice rotation mouse released Q1 R
    private void eventRQ1(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q1 (105, 95)
        evt.translatePoint(5, -5);
        mH.jButtonRMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
    }

    // Check slice rotation mouse released Q2 R
    private void eventRQ2(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q2 (95, 95)
        evt.translatePoint(-10, 0);
        mH.jButtonRMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
    }

    // Check slice rotation mouse released Q3 R
    private void eventRQ3(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q3 (95, 105)
        evt.translatePoint(0, +10);
        mH.jButtonRMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
    }

    // Check slice rotation mouse released Q4 R
    private void eventRQ4(MouseEvent evt, int rotMult, int sliceNum) {
        // Set released point to Q4 (105, 105)
        evt.translatePoint(+10, 0);
        mH.jButtonRMouseReleased(evt);
        assertEquals(rotMult, rend.rubik.getRotMult());
        assertEquals(1, rend.getAllAnimated()[sliceNum]);
        rend.clearAllAnimatedSlices();
        // RESET point to 100,100 for next test
        evt.translatePoint(-5, -5);
    }

    /**
     * Test of jButtonFMouseReleased() method, of class MouseHandler.
     */
    @Test
    public void testJButtonFMouseReleased() {
        System.out.println("testing...jButtonFMouseReleased");
        MouseEvent evt = new MouseEvent(gui.jButtonF1, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), MouseEvent.ALT_DOWN_MASK, 100, 100, 1, false, MouseEvent.BUTTON1);

        // Set clicked point to 100,100
        mH.jButtonMousePressed(evt);
        // F1
        eventFQ1(evt, -1, 7);
        eventFQ2(evt, -1, 4);
        eventFQ3(evt, 1, 7);
        eventFQ4(evt, 1, 4);
        // F2
        evt.setSource(gui.jButtonF2);
        eventFQ1(evt, -1, 8);
        eventFQ2(evt, -1, 4);
        eventFQ3(evt, 1, 8);
        eventFQ4(evt, 1, 4);
        // F3
        evt.setSource(gui.jButtonF3);
        eventFQ1(evt, -1, 9);
        eventFQ2(evt, -1, 4);
        eventFQ3(evt, 1, 9);
        eventFQ4(evt, 1, 4);
        // F4
        evt.setSource(gui.jButtonF4);
        eventFQ1(evt, -1, 7);
        eventFQ2(evt, -1, 5);
        eventFQ3(evt, 1, 7);
        eventFQ4(evt, 1, 5);
        // F5
        evt.setSource(gui.jButtonF5);
        eventFQ1(evt, -1, 8);
        eventFQ2(evt, -1, 5);
        eventFQ3(evt, 1, 8);
        eventFQ4(evt, 1, 5);
        // F6
        evt.setSource(gui.jButtonF6);
        eventFQ1(evt, -1, 9);
        eventFQ2(evt, -1, 5);
        eventFQ3(evt, 1, 9);
        eventFQ4(evt, 1, 5);
        // F7
        evt.setSource(gui.jButtonF7);
        eventFQ1(evt, -1, 7);
        eventFQ2(evt, -1, 6);
        eventFQ3(evt, 1, 7);
        eventFQ4(evt, 1, 6);
        // F8
        evt.setSource(gui.jButtonF8);
        eventFQ1(evt, -1, 8);
        eventFQ2(evt, -1, 6);
        eventFQ3(evt, 1, 8);
        eventFQ4(evt, 1, 6);
        // F9
        evt.setSource(gui.jButtonF9);
        eventFQ1(evt, -1, 9);
        eventFQ2(evt, -1, 6);
        eventFQ3(evt, 1, 9);
        eventFQ4(evt, 1, 6);
    }

    /**
     * Test of jButtonRMouseReleased method, of class MouseHandler.
     */
    @Test
    public void testJButtonRMouseReleased() {
        System.out.println("testing...jButtonRMouseReleased");
        MouseEvent evt = new MouseEvent(gui.jButtonR1, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), MouseEvent.ALT_DOWN_MASK, 100, 100, 1, false, MouseEvent.BUTTON1);

        // Set clicked point to 100,100
        mH.jButtonMousePressed(evt);
        // F1
        eventRQ1(evt, 1, 4);
        eventRQ2(evt, 1, 3);
        eventRQ3(evt, -1, 4);
        eventRQ4(evt, -1, 3);
        // F2
        evt.setSource(gui.jButtonR2);
        eventRQ1(evt, 1, 4);
        eventRQ2(evt, 1, 2);
        eventRQ3(evt, -1, 4);
        eventRQ4(evt, -1, 2);
        // F3
        evt.setSource(gui.jButtonR3);
        eventRQ1(evt, 1, 4);
        eventRQ2(evt, 1, 1);
        eventRQ3(evt, -1, 4);
        eventRQ4(evt, -1, 1);
        // F4
        evt.setSource(gui.jButtonR4);
        eventRQ1(evt, 1, 5);
        eventRQ2(evt, 1, 3);
        eventRQ3(evt, -1, 5);
        eventRQ4(evt, -1, 3);
        // F5
        evt.setSource(gui.jButtonR5);
        eventRQ1(evt, 1, 5);
        eventRQ2(evt, 1, 2);
        eventRQ3(evt, -1, 5);
        eventRQ4(evt, -1, 2);
        // F6
        evt.setSource(gui.jButtonR6);
        eventRQ1(evt, 1, 5);
        eventRQ2(evt, 1, 1);
        eventRQ3(evt, -1, 5);
        eventRQ4(evt, -1, 1);
        // F7
        evt.setSource(gui.jButtonR7);
        eventRQ1(evt, 1, 6);
        eventRQ2(evt, 1, 3);
        eventRQ3(evt, -1, 6);
        eventRQ4(evt, -1, 3);
        // F8
        evt.setSource(gui.jButtonR8);
        eventRQ1(evt, 1, 6);
        eventRQ2(evt, 1, 2);
        eventRQ3(evt, -1, 6);
        eventRQ4(evt, -1, 2);
        // F9
        evt.setSource(gui.jButtonR9);
        eventRQ1(evt, 1, 6);
        eventRQ2(evt, 1, 1);
        eventRQ3(evt, -1, 6);
        eventRQ4(evt, -1, 1);
    }
}
