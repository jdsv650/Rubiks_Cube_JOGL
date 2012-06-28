/* James Donner
 * Cube.java
 * Last modified: 2/13/11
 * Class to keep track of cube face colors
 */
package cubeJOGL;

/**
 * Class keeps track of cube face colors.
 */
public class Cube {

    private int F;  //Front
    private int R;  //Right
    private int D;  //Down
    private int U;  //Up
    private int B;  //Back
    private int L;  //Left

    /**
     * Sets all cube face colors.
     * @param F front
     * @param R right
     * @param D down
     * @param U up
     * @param B back
     * @param L left
     * @precondition F,R,D,U,B,L in range 0..6
     */
    public void setAll(int F, int R, int D, int U, int B, int L) {
        if(F > 6 || R > 6 || D > 6 || U > 6 || B > 6 || L > 6)
            return;
        if(F < 0 || R < 0 || D < 0 || U < 0 || B < 0 || L < 0)
            return;
        this.F = F;
        this.R = R;
        this.D = D;
        this.U = U;
        this.B = B;
        this.L = L;
    }

    /**
     * Gets all cube face colors.
     * @return int[0] = F, [1]=R, [2]=D, [3]=U, [4]=B, [5]=L
     */
    public int[] getAll() {
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
     * get Back face color
     * @return color
     */
    public int getB() {
        return B;
    }

    /**
     * Set Back face color.
     * @param B the color index
     * @precondition 0 <= B <= 6
     */
    public void setB(int B) {
        if(B > 6 || B < 0)
            return;
        this.B = B;
    }

    /**
     * get Down face color
     * @return color
     */
    public int getD() {
        return D;
    }

    /**
     * Set Down face color.
     * @param D the color index
     * @precondition 0 <= D <= 6
     */
    public void setD(int D) {
         if(D > 6 || D < 0)
            return;
        this.D = D;
    }

    /**
     * get Front face color.
     * @return color
     */
    public int getF() {
        return F;
    }

    /**
     * Set Front face color.
     * @param F the color index
     * @precondition 0 <= F <= 6
     */
    public void setF(int F) {
        if(F > 6 || F < 0)
            return;
        this.F = F;
    }

    /**
     * get Left face color.
     * @return color
     */
    public int getL() {
        return L;
    }

    /**
     * Set Left face color.
     * @param L the color index.
     * @precondition 0 <= L <= 6
     */
    public void setL(int L) {
         if(L > 6 || L < 0)
            return;
        this.L = L;
    }

    /**
     * get Right face color.
     * @return color
     */
    public int getR() {
        return R;
    }

    /**
     * Set Right face color.
     * @param R the color index
     * @precondition 0 <= R <= 6
     */
    public void setR(int R) {
         if(R > 6 || R < 0)
            return;
        this.R = R;
    }

    /**
     * get Up face color.
     * @return color
     */
    public int getU() {
        return U;
    }

    /**
     * Set Up face color.
     * @param U the color index
     * @precondition 0 <= U <= 6
     */
    public void setU(int U) {
         if(U > 6 || U < 0)
            return;
        this.U = U;
    }
}
