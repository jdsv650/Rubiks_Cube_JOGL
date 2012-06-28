/* James Donner
 * CubeBuilder.java
 * Last modified: 10/5/11
 * Ops to manipulate the 27 cubes of a standard Rubik's cube
 */
package cubeJOGL;

/**
 *  Methods required to build/update cube.
 */
public interface CubeBuilder {

    /**
     * Set cube colors to solved state.
     */
    void buildCube();


    /**
     * Getter for the the cubes
     * @return an array containing the cubes
     */
    Cube[] getCube();


    /**
     * Getter rotation multiplier
     * @return the multiplier
     */
    int getRotMult();

    /**
     * Setter rotation multiplier.
     * @param rotMult the new value.
     */
    void setRotMult(int rotMult);

    /**
     * Update X slice base on direction of rotation multiplier.
     * @param snum the X slice num (7,8, or 9)
     */
    void updateXSlice(int snum);

    /**
     * Update Y slice base on direction of rotation multiplier.
     * @param snum the Y slice num (4,5, or 6)
     */
    void updateYSlice(int snum);

    /**
     * Update Z slice base on direction of rotation multiplier.
     * @param snum the Z slice num (1,2, or 3)
     */
    void updateZSlice(int snum);



}
