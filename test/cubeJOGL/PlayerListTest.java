/* James Donner
 * PlayerListTest.java
 * Last modified: 10/16/11
 * Tests adding and removing players from a list of players
 */
package cubeJOGL;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Creates and maintains an ArrayList of game players.
 */
public class PlayerListTest {

    private CubeBuilder cubeF;
    private PlayerList pList;
    private Player p1, p2, p3, p4, p5;
    private char[] defaultPasswd = {'0', '0', '0', '0'};

    @Before
    public void setUp() {
        defaultPasswd = Arrays.copyOf(defaultPasswd, 20);
        pList = new PlayerList();
        cubeF = new CubeMainFake();
        p1 = new Player(1, "Default User", cubeF, defaultPasswd);
        p2 = new Player(2, "James", cubeF, defaultPasswd);
        p3 = new Player(3, "Devin", cubeF, defaultPasswd);
        p4 = new Player(4, "Tucker", cubeF, defaultPasswd);
        p5 = new Player(5, "Michele", cubeF, defaultPasswd);
    }

    /**
     * Test of addPlayer method, of class PlayerList.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("testing...addPlayer");

        assertEquals(0, pList.getPlayerListSize());
        pList.addPlayer(p1);
        assertEquals(1, pList.getPlayerListSize());
        pList.addPlayer(p2);
        pList.addPlayer(p3);
        pList.addPlayer(p4);
        pList.addPlayer(p5);
        assertEquals(5, pList.getPlayerListSize());
    }

    /**
     * Test of deletePlayer method, of class PlayerList.
     * Must have 2 players to delete one (computer retains a "Default User")
     */
    @Test
    public void testDeletePlayer() {
        System.out.println("testing...deletePlayer");

        // Try to delete from an empty list
        pList.deletePlayer(0);
        assertEquals(0, pList.getPlayerListSize());

        pList.addPlayer(p1);
        assertEquals(1, pList.getPlayerListSize());
        // Try to delete "Default User" playerNum-1
        pList.deletePlayer(0);
        assertEquals(1, pList.getPlayerListSize());

        pList.addPlayer(p2); // index = 0 and index = 1
        pList.deletePlayer(2); // try to delete beyond upper index
        assertEquals(2, pList.getPlayerListSize());

        pList.addPlayer(p3);
        pList.deletePlayer(2); // delete player 3 at index 2
        assertEquals(2, pList.getPlayerListSize());

        //  Try to delete player that does not exist
        pList.deletePlayer(12);
        assertEquals(2, pList.getPlayerListSize());
    }

    /**
     * Test of updatePlayerPass method, of class PlayerList.
     */
    @Test
    public void testUpdatePlayerPass() {
        System.out.println("testing...updatePlayerPass");
        char[] passwd = {'J', 'A', 'M'};
        passwd = Arrays.copyOf(passwd, 20);

        // Try on an empty list
        pList.updatePlayerPass(0, passwd);

        pList.addPlayer(p1);
        // "Default User" retains password
        pList.updatePlayerPass(0, passwd);
        assertArrayEquals(defaultPasswd, pList.getPlayerNoCred(0).getPass());

        pList.addPlayer(p2);
        pList.addPlayer(p3);
        // update password p3
        pList.updatePlayerPass(2, passwd);
        assertArrayEquals(passwd, pList.getPlayerNoCred(2).getPass());

        // out of bounds -- only index 0,1,2 valid
        pList.updatePlayerPass(3, passwd);
    }

    /** testAddPlayer and testDeletePlayer use this extensively
     *  Test of getPlayerListSize method, of class PlayerList.
     */
    @Test
    public void testGetPlayerListSize() {
    }

    /**
     * Test of getPlayer method, of class PlayerList.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("testing...getPlayer");
        char[] passwd = {'D', 'E', 'V'};
        passwd = Arrays.copyOf(passwd, 20);

        //Player doesn't exist
        assertNull(pList.getPlayer(0, defaultPasswd));
        pList.addPlayer(p1);
        pList.addPlayer(p2);
        pList.addPlayer(p3);
        pList.addPlayer(p4);
        // Out of range
        assertNull(pList.getPlayer(4, defaultPasswd));
        //Player password not a match
        assertNull(pList.getPlayer(3, passwd));
        //Player OK and password is a match
        assertNotNull(pList.getPlayer(3, defaultPasswd));
    }

    /** testUpdatePlayerPass is sufficient
     * Test of getPlayerNoCred method, of class PlayerList.
     */
    @Test
    public void testGetPlayerNoCred() {
        System.out.println("testing...getPlayerNoCred()");
        // Test empty list
        assertNull(pList.getPlayerNoCred(12));
        // Test for "Default User"
        pList.addPlayer(p1);
        assertNotNull(pList.getPlayerNoCred(0));
    }
}
