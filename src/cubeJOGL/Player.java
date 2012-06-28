/* James Donner
 * Player.java
 * Last modified: 10/06/11
 * Holds player information and his/her cube
 */
package cubeJOGL;

import java.util.Arrays;

/**
 * Class to hold player name, number, password, and cube.
 */
public class Player {

    private int number;  //player number "Default User" is always #1
    private char[] pass = new char[20];  // users password
    private String name;                 // username
    private CubeBuilder myMainCube;         // users cube

    /**
     * Construct a new player.
     * @param number the player number.
     * @param name player name.
     * @param cubeM players cube
     * @param passwd players password
     * @precondition passwd <= 20 chars
     */
    Player(int number, String name, CubeBuilder cubeM, char[] passwd) {
        this.number = number;
        this.name = name;
        myMainCube = cubeM;
        pass = Arrays.copyOf(passwd, 20);
    }

    /**
     * Setter for pass.
     * @param passwd the password.
     * @precondition passwd <= 20 chars
     * @precondition player number > 1
     */
    public void setPass(char[] passwd) {
        if (this.number <= 1) {
            return;
        }
        pass = Arrays.copyOf(passwd, 20);
    }

    /**
     * Getter for pass.
     * @return the password.
     */
    public char[] getPass() {
        return pass;
    }

    /**
     * Getter for Cube.
     * @return the cube.
     */
    public CubeBuilder getMyCube() {
        return myMainCube;
    }

    /**
     * Getter for username.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for user number.
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for user number.
     * @param number the number.
     * @precondition player number > 1
     */
    public void setNumber(int number) {
        if (this.number <= 1) {
            return;
        }
        this.number = number;
    }
}
