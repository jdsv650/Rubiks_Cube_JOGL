 /* James Donner
 * CubeMainFake.java
 * Last modified: 10/5/11
 * Contains and manipulates the 27 cubes of a standard Rubik's cube
 */
package cubeJOGL;

/**
 * Class creates and manipulates 27 cubes of a Rubik's cube.
 */
public class CubeMainFake implements CubeBuilder {

    private int rotMult = 1;  // + rotation multiplier
    private Cube[] cubes = new Cube[27]; //the cubes

    //Front[][0] Right[][1] Down[][2]  Up[][3]    Back[][4]  Left[][5]
    //int[][] cubes = new int[27][6]; //27 cubes (0..26) with 6 face colors (0..5)
    /**
     * Constructor creates 27 new cubes.
     */
    CubeMainFake() {
        for (int i = 0; i < 27; i++) {
            cubes[i] = new Cube();
        }
        // buildCube();
    }

    /**
     * Getter for the the cubes
     * @return an array containing the cubes
     */
    public Cube[] getCube() {
        return cubes;
    }

    /**
     * Getter rotation multiplier
     * @return 1
     */
    public int getRotMult() {
        return 1;
    }

    /**
     * Dummy.
     */
    public void setRotMult(int rotMult) {
    }

    /**
     * Dummy.
     */
    public void buildCube() {
    }

    /**
     *  Dummy.
     */
    public void updateXSlice(int snum) {
    }

    /**
     * Dummy.
     */
    public void updateYSlice(int snum) {
    }

    /**
     * Dummy.
     */
    public void updateZSlice(int snum) {
    }
}
