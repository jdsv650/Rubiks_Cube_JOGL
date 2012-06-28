/* James Donner
 * PlayerTest.java
 * Last modified: 10/16/11
 * Tests Player info
 */
package cubeJOGL;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class to test a Rubik's Cube Player.
 */
public class PlayerTest {

    private Player p1, p2;
    private CubeBuilder cubeF;
    private char[] defaultPasswd = {'0', '0', '0', '0'};

    @Before
    public void setUp() {
        cubeF = new CubeMainFake();
        defaultPasswd = Arrays.copyOf(defaultPasswd, 20);
        /* Player 1 will always exist in the system with these attributes */
        p1 = new Player(1, "Default User", cubeF, defaultPasswd);
        p2 = new Player(2, "James", cubeF, defaultPasswd);
    }

    /**
     * Test of setPass method, of class Player.
     */
    @Test
    public void testSetPass() {
        System.out.println("testing...setPass");

        assertArrayEquals(defaultPasswd, p1.getPass());
        assertArrayEquals(defaultPasswd, p2.getPass());

        char[] newpasswd = {'?'};
        newpasswd = Arrays.copyOf(newpasswd, 20);
        // p1 = default User (check can't change this password)
        p1.setPass(newpasswd);
        p2.setPass(newpasswd);

        assertArrayEquals(defaultPasswd, p1.getPass()); // p1 = default
        assertArrayEquals(newpasswd, p2.getPass());     // p2 gets set

        char[] newpasswd2 = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        newpasswd2 = Arrays.copyOf(newpasswd2, 20);
        p1.setPass(newpasswd2);
        p2.setPass(newpasswd2);

        assertArrayEquals(defaultPasswd, p1.getPass()); // p1 = default
        assertArrayEquals(newpasswd2, p2.getPass());
    }

    /**
     * Test of getMyCube method, of class Player.
     */
    @Test
    public void testGetMyCube() {
        System.out.println("testing...getMyCube");
        CubeBuilder result = p1.getMyCube();
        assertEquals(cubeF, result);
        result = p2.getMyCube();
        assertEquals(cubeF, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("testing...getName");
        String p1Name = "Default User";
        String result = p1.getName();
        assertEquals(p1Name, result);
    }

    /**
     * Test of setNumber method, of class Player.
     */
    @Test
    public void testSetNumber() {
        System.out.println("testing...setNumber");
        assertEquals(1, p1.getNumber());
        assertEquals(2, p2.getNumber());

        p1.setNumber(5);
        p2.setNumber(5);
        assertEquals(1, p1.getNumber());
        assertEquals(5, p2.getNumber());

        p1.setNumber(Integer.MAX_VALUE);
        p2.setNumber(Integer.MAX_VALUE);
        assertEquals(1, p1.getNumber());
        assertEquals(Integer.MAX_VALUE, p2.getNumber());
    }
}