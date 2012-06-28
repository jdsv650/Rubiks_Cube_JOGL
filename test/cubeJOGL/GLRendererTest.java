/* James Donner
 * GLRendererTest.java
 * Last modified: 11/12/11
 * Tests GLRenderer class
 */
package cubeJOGL;

import javax.media.opengl.GLAutoDrawable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests animate flags - no openGL code.
 */
public class GLRendererTest {

    private GLRenderer rend;
    private Player p;

    public GLRendererTest() {
        CubeMain theCube = new CubeMain();
        char[] newPass = new char[]{'0', '0', '0', '0'};
        p = new Player(1, "Default User", theCube, newPass);
        rend = new GLRenderer(p);
    }

    /**
     * Test of checkSliceAnimated method, of class GLRenderer.
     */
    @Test
    public void testCheckSliceAnimated() {
        System.out.println("testing...checkSliceAnimated");
        int[] animateFlags = new int[10];

        rend.clearAllAnimatedSlices();
        assertEquals(false, rend.checkSliceAnimated()); // no animated slice
        animateFlags = rend.getAllAnimated();
        for (int i = 1; i < 10; i++) {
            assertEquals(0, animateFlags[i]);
        }

        rend.setAnimateSlice5(1);
        assertEquals(true, rend.checkSliceAnimated());
        animateFlags = rend.getAllAnimated();
        for (int i = 1; i < 10; i++) {
            if (i == 5) {
                assertEquals(1, animateFlags[i]);
            } else {
                assertEquals(0, animateFlags[i]);
            }
        }

    }
}
