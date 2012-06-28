/* James Donner
 * CubeMainTest.java
 * Last modified: 10/16/11
 * Tests CubeMain class
 */
package cubeJOGL;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Check to see if cube remains in a valid state after rotation.
 */
public class CubeMainTest {

    private CubeMain cMain, cTest;
    private int rotMult;

    public CubeMainTest() {
        cMain = new CubeMain();
        cTest = new CubeMain();
        cMain.buildCube();
        cTest.buildCube();
        rotMult = 1;
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of setRotMult method -- rotMult!= 0.
     */
    @Test
    public void testSetRotMult() {
        System.out.println("testing...setRotMult");
        cMain.setRotMult(1);
        assertEquals(1, cMain.getRotMult());
        cMain.setRotMult(0); // no change
        assertEquals(1, cMain.getRotMult());
    }

    /**
     * Loads cube colors into an array.
     * @param F front
     * @param R right
     * @param D down
     * @param U up
     * @param B back
     * @param L left
     * @return colors in an array
     */
    private int[] setColors(int F, int R, int D, int U, int B, int L) {
        int[] temp = new int[6];
        temp[0] = F;
        temp[1] = R;
        temp[2] = D;
        temp[3] = U;
        temp[4] = B;
        temp[5] = L;
        return temp;
    }

    /**
     * Test of buildCube method, of class CubeMain.
     * face[0] = Front, [1]=Right, [2]=Down, [3]=Up, [4]=Back, [5]=Left
     * (0 Black)  1 Blue  (2 Green)  3 Orange (4 Red) 5 Yellow (6 White)
     */
    @Test
    public void testBuildCube() {
        CubeMain tempCube = new CubeMain();
        System.out.println("testing...buildCube");
        Cube[] cubes = new Cube[27];
        int[] face = new int[6];

        tempCube.buildCube();
        cubes = tempCube.getCube();
        face = setColors(0, 0, 0, 4, 5, 1);
        assertArrayEquals(face, cubes[0].getAll());
        face = setColors(0, 0, 0, 4, 5, 0);
        assertArrayEquals(face, cubes[1].getAll());
        face = setColors(0, 2, 0, 4, 5, 0);
        assertArrayEquals(face, cubes[2].getAll());
        face = setColors(0, 0, 0, 0, 5, 1);
        assertArrayEquals(face, cubes[3].getAll());
        face = setColors(0, 0, 0, 0, 5, 0);
        assertArrayEquals(face, cubes[4].getAll());
        face = setColors(0, 2, 0, 0, 5, 0);
        assertArrayEquals(face, cubes[5].getAll());
        face = setColors(0, 0, 3, 0, 5, 1);
        assertArrayEquals(face, cubes[6].getAll());
        face = setColors(0, 0, 3, 0, 5, 0);
        assertArrayEquals(face, cubes[7].getAll());
        face = setColors(0, 2, 3, 0, 5, 0);
        assertArrayEquals(face, cubes[8].getAll());
        face = setColors(0, 0, 0, 4, 0, 1);
        assertArrayEquals(face, cubes[9].getAll());
        face = setColors(0, 0, 0, 4, 0, 0);
        assertArrayEquals(face, cubes[10].getAll());
        face = setColors(0, 2, 0, 4, 0, 0);
        assertArrayEquals(face, cubes[11].getAll());
        face = setColors(0, 0, 0, 0, 0, 1);
        assertArrayEquals(face, cubes[12].getAll());
        face = setColors(0, 0, 0, 0, 0, 0);
        assertArrayEquals(face, cubes[13].getAll());
        face = setColors(0, 2, 0, 0, 0, 0);
        assertArrayEquals(face, cubes[14].getAll());
        face = setColors(0, 0, 3, 0, 0, 1);
        assertArrayEquals(face, cubes[15].getAll());
        face = setColors(0, 0, 3, 0, 0, 0);
        assertArrayEquals(face, cubes[16].getAll());
        face = setColors(0, 2, 3, 0, 0, 0);
        assertArrayEquals(face, cubes[17].getAll());
        face = setColors(6, 0, 0, 4, 0, 1);
        assertArrayEquals(face, cubes[18].getAll());
        face = setColors(6, 0, 0, 4, 0, 0);
        assertArrayEquals(face, cubes[19].getAll());
        face = setColors(6, 2, 0, 4, 0, 0);
        assertArrayEquals(face, cubes[20].getAll());
        face = setColors(6, 0, 0, 0, 0, 1);
        assertArrayEquals(face, cubes[21].getAll());
        face = setColors(6, 0, 0, 0, 0, 0);
        assertArrayEquals(face, cubes[22].getAll());
        face = setColors(6, 2, 0, 0, 0, 0);
        assertArrayEquals(face, cubes[23].getAll());
        face = setColors(6, 0, 3, 0, 0, 1);
        assertArrayEquals(face, cubes[24].getAll());
        face = setColors(6, 0, 3, 0, 0, 0);
        assertArrayEquals(face, cubes[25].getAll());
        face = setColors(6, 2, 3, 0, 0, 0);
        assertArrayEquals(face, cubes[26].getAll());
    }

    private void checkCubeSolved(CubeMain cM) {
        Cube[] cubes = new Cube[27];
        Cube[] cubesSolved = new Cube[27];
        cubes = cM.getCube();
        cubesSolved = cTest.getCube();

        assertArrayEquals(cubesSolved[0].getAll(), cubes[0].getAll());
        assertArrayEquals(cubesSolved[1].getAll(), cubes[1].getAll());
        assertArrayEquals(cubesSolved[2].getAll(), cubes[2].getAll());
        assertArrayEquals(cubesSolved[3].getAll(), cubes[3].getAll());
        assertArrayEquals(cubesSolved[4].getAll(), cubes[4].getAll());
        assertArrayEquals(cubesSolved[5].getAll(), cubes[5].getAll());
        assertArrayEquals(cubesSolved[6].getAll(), cubes[6].getAll());
        assertArrayEquals(cubesSolved[7].getAll(), cubes[7].getAll());
        assertArrayEquals(cubesSolved[8].getAll(), cubes[8].getAll());
        assertArrayEquals(cubesSolved[9].getAll(), cubes[9].getAll());
        assertArrayEquals(cubesSolved[10].getAll(), cubes[10].getAll());
        assertArrayEquals(cubesSolved[11].getAll(), cubes[11].getAll());
        assertArrayEquals(cubesSolved[12].getAll(), cubes[12].getAll());
        assertArrayEquals(cubesSolved[13].getAll(), cubes[13].getAll());
        assertArrayEquals(cubesSolved[14].getAll(), cubes[14].getAll());
        assertArrayEquals(cubesSolved[15].getAll(), cubes[15].getAll());
        assertArrayEquals(cubesSolved[16].getAll(), cubes[16].getAll());
        assertArrayEquals(cubesSolved[17].getAll(), cubes[17].getAll());
        assertArrayEquals(cubesSolved[18].getAll(), cubes[18].getAll());
        assertArrayEquals(cubesSolved[19].getAll(), cubes[19].getAll());
        assertArrayEquals(cubesSolved[20].getAll(), cubes[20].getAll());
        assertArrayEquals(cubesSolved[21].getAll(), cubes[21].getAll());
        assertArrayEquals(cubesSolved[22].getAll(), cubes[22].getAll());
        assertArrayEquals(cubesSolved[23].getAll(), cubes[23].getAll());
        assertArrayEquals(cubesSolved[24].getAll(), cubes[24].getAll());
        assertArrayEquals(cubesSolved[25].getAll(), cubes[25].getAll());
        assertArrayEquals(cubesSolved[26].getAll(), cubes[26].getAll());
    }

    /**
     * Test of updateXSlice method, snum = 7,8,9.
     */
    @Test
    public void testUpdateXSlice() {
        System.out.println("testing...updateXSlice");
        cMain.setRotMult(1);  //CCW
        cMain.updateXSlice(7);  // 1st slice looking from right side cube
        cMain.updateXSlice(7);
        cMain.updateXSlice(7);
        cMain.updateXSlice(7);
        checkCubeSolved(cMain); // Equals original cube?
        cMain.setRotMult(-1);  //CW
        cMain.updateXSlice(7);  // 1st slice looking from right side cube
        cMain.updateXSlice(7);
        cMain.updateXSlice(7);
        cMain.updateXSlice(7);
        checkCubeSolved(cMain);
        cMain.setRotMult(1);  //CCW
        cMain.updateXSlice(8);  // 2nd slice looking from right side cube
        cMain.updateXSlice(8);
        cMain.updateXSlice(8);
        cMain.updateXSlice(8);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateXSlice(8);  // 1st slice looking from right side cube
        cMain.updateXSlice(8);
        cMain.updateXSlice(8);
        cMain.updateXSlice(8);
        checkCubeSolved(cMain);
        cMain.setRotMult(1);  //CCW
        cMain.updateXSlice(9);  // 3rd slice looking from right side cube
        cMain.updateXSlice(9);
        cMain.updateXSlice(9);
        cMain.updateXSlice(9);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateXSlice(9);  // 1st slice looking from right side cube
        cMain.updateXSlice(9);
        cMain.updateXSlice(9);
        cMain.updateXSlice(9);
        checkCubeSolved(cMain);
    }

    /**
     * Test of updateYSlice method, snum = 4,5,6.
     */
    @Test
    public void testUpdateYSlice() {
        System.out.println("testing...updateYSlice");

        cMain.setRotMult(1);  //CCW
        cMain.updateYSlice(4);  // 1st slice looking down from above cube
        cMain.updateYSlice(4);
        cMain.updateYSlice(4);
        cMain.updateYSlice(4);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateYSlice(4);  // 1st slice looking down from above cube
        cMain.updateYSlice(4);
        cMain.updateYSlice(4);
        cMain.updateYSlice(4);
        checkCubeSolved(cMain);
        cMain.setRotMult(1);  //CCW
        cMain.updateYSlice(5);  // 2nd slice looking down from above cube
        cMain.updateYSlice(5);
        cMain.updateYSlice(5);
        cMain.updateYSlice(5);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateYSlice(5);  // 2nd slice looking down from above cube
        cMain.updateYSlice(5);
        cMain.updateYSlice(5);
        cMain.updateYSlice(5);
        checkCubeSolved(cMain);
        cMain.setRotMult(1);  //CCW
        cMain.updateYSlice(6);  // 3rd slice looking down from above cube
        cMain.updateYSlice(6);
        cMain.updateYSlice(6);
        cMain.updateYSlice(6);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateYSlice(6);  // 3rd slice looking down from above cube
        cMain.updateYSlice(6);
        cMain.updateYSlice(6);
        cMain.updateYSlice(6);
        checkCubeSolved(cMain);
    }

    /**
     * Test of updateZSlice method, snum = 1,2,3.
     */
    @Test
    public void testUpdateZSlice() {
        System.out.println("testing...updateZSlice");
        cMain.setRotMult(1);  //CCW
        cMain.updateZSlice(1);  // 1st slice looking front to back
        cMain.updateZSlice(1);
        cMain.updateZSlice(1);
        cMain.updateZSlice(1);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateZSlice(1);  // 1st slice looking front to back
        cMain.updateZSlice(1);
        cMain.updateZSlice(1);
        cMain.updateZSlice(1);
        checkCubeSolved(cMain);
        cMain.setRotMult(1);  //CCW
        cMain.updateZSlice(2);  // 2nd slice looking front to back
        cMain.updateZSlice(2);
        cMain.updateZSlice(2);
        cMain.updateZSlice(2);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateZSlice(2);  // 2nd slice looking front to back
        cMain.updateZSlice(2);
        cMain.updateZSlice(2);
        cMain.updateZSlice(2);
        checkCubeSolved(cMain);
        cMain.setRotMult(1);  //CCW
        cMain.updateZSlice(3);  // 3rd slice looking front to back
        cMain.updateZSlice(3);
        cMain.updateZSlice(3);
        cMain.updateZSlice(3);
        checkCubeSolved(cMain);
        cMain.setRotMult(-1);  //CW
        cMain.updateZSlice(3);  // 3rd slice looking front to back
        cMain.updateZSlice(3);
        cMain.updateZSlice(3);
        cMain.updateZSlice(3);
        checkCubeSolved(cMain);
    }
}
