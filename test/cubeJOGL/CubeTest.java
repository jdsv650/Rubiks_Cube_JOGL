/* James Donner
 * CubeTest.java
 * Last modified: 11/21/11
 * Tests Cube class
 */
package cubeJOGL;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class tests if we are recording cube colors in the range 0..6.
 */
public class CubeTest {

    private Cube c;

    @Before
    public void setUp() {
        c = new Cube();
    }

    /**
     * Test of setAll/getAll methods, of class Cube.
     * F,     R,     D,    U,  B,    L in range 0..6
     * Front, Right, Down, Up, Back, Left
     */
    @Test
    public void testSetAll() {
        System.out.println("testing...setAll");
        int[] temp = new int[6];

        int F = 0;
        int R = 0;
        int D = 0;
        int U = 0;
        int B = 0;
        int L = 0;

        temp[0] = F;
        temp[1] = R;
        temp[2] = D;
        temp[3] = U;
        temp[4] = B;
        temp[5] = L;
        c.setAll(F, R, D, U, B, L);
        assertArrayEquals(temp, c.getAll());

        // Check no change ... input high
        c.setAll(0, 1, 2, 3, 4, 7);
        assertArrayEquals(temp, c.getAll());

        // Check no change ... input low
        c.setAll(-1, 2, 3, 4, 5, 6);
        assertArrayEquals(temp, c.getAll());

        // OK should set all
        c.setAll(3, 3, 3, 3, 3, 3);
        for (int i = 0; i < 6; i++) {
            temp[i] = 3;
        }
        assertArrayEquals(temp, c.getAll());

    }

    /**
     * Test of setB method, of class Cube.
     */
    @Test
    public void testSetB() {
        c.setB(0);
        assertEquals(0, c.getB());
        c.setB(23);
        assertEquals(0, c.getB());

    }

    /**
     * Test of setD method, of class Cube.
     */
    @Test
    public void testSetD() {
        c.setD(6);
        assertEquals(6, c.getD());
        c.setD(7);
        assertEquals(6, c.getD());
    }

    /**
     * Test of setF method, of class Cube.
     */
    @Test
    public void testSetF() {
        c.setF(1);
        assertEquals(1, c.getF());
        c.setF(-1);
        assertEquals(1, c.getF());
    }

    /**
     * Test of setL method, of class Cube.
     */
    @Test
    public void testSetL() {
        c.setL(3);
        assertEquals(3, c.getL());
        c.setL(-25);
        assertEquals(3, c.getL());
    }

    /**
     * Test of setR method, of class Cube.
     */
    @Test
    public void testSetR() {
        c.setR(0);
        assertEquals(0, c.getR());
        c.setR(5);
        assertEquals(5, c.getR());
        c.setR(7);
        assertEquals(5, c.getR());
    }

    /**
     * Test of setU method, of class Cube.
     */
    @Test
    public void testSetU() {
        c.setU(3);
        assertEquals(3, c.getU());
        c.setU(10);
        assertEquals(3, c.getU());
    }
}
