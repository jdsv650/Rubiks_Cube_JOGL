/* James Donner
 * CubeFilerTest.java
 * Last modified: 10/27/11
 * Tests CubeFiler class
 */
package cubeJOGL;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Tests writing and reading player (cube) info to a file.
 */
public class CubeFilerTest {

    private CubeFiler cFiler;
    private Player one;
    private PlayerList pList;

    public CubeFilerTest() {
        cFiler = new CubeFiler();
        pList = cFiler.inputAllPlayers();
    }

    @BeforeClass
    public static void oneTimeSetUp() {
        File myFile, tempFile;
        // Save a backup and clobber the file if it exists
        myFile = new File("cube_save_file.txt"); // file in CWD
        tempFile = new File("cube_save_file_BACKUP.txt");

        if (myFile.exists()) {
            if (myFile.renameTo(tempFile)) {
                myFile.delete();
            }
        }
    }

    @AfterClass
    public static void oneTimeTearDown() {
        File myFile, tempFile;
        //Restore the file
        myFile = new File("cube_save_file.txt");
        tempFile = new File("cube_save_file_BACKUP.txt");

        if (tempFile.exists()) {
            //Backup exists erase our temp cube save file and restore original
            myFile.delete();
            tempFile.renameTo(myFile);
        }
    }

    /**
     * Test of outputAllPlayers method, of class CubeFiler.
     */
    @Test
    public void testOutputAllPlayers() {
        System.out.println("testing...outputAllPlayers");
        char[] newPass = new char[]{'0', '0', '0', '0'};
        CubeMain theCube = new CubeMain();

        assertEquals(1, cFiler.inputAllPlayers().getPlayerListSize());

        Player p2 = new Player(2, "James", theCube, newPass);
        Player p3 = new Player(3, "Devin", theCube, newPass);
        pList.addPlayer(p2);
        pList.addPlayer(p3);
        cFiler.outputAllPlayers(pList);

        assertEquals(3, cFiler.inputAllPlayers().getPlayerListSize());

        Player p4 = new Player(4, "Tucker", theCube, newPass);
        pList.addPlayer(p4);
        cFiler.outputAllPlayers(pList);

        assertEquals(4, cFiler.inputAllPlayers().getPlayerListSize());
    }
}
