/* James Donner
 * CubeDefs.java
 * Last modified: 2/13/11
 * Class to create and initialize cube vertices and colors
 */
package cubeJOGL;

/**
 * Class creates and initializes cube vertices and colors.
 * It is implemented as a Singleton.
 */
public class CubeDefs {

    private static CubeDefs cubeDefs = new CubeDefs(); // create the new instance

    /**
     * Get the single instance.
     * @return the cubeDefs object.
     */
    public static CubeDefs getInstance() {
        return cubeDefs;
    }

    //private constructor to limit number of objs
    private CubeDefs() {
        initCubeVerts();  //initialize cube vertices
    }
    final int cubesize = 1;  //cube size
    final float[][] cubeVerts = new float[8][3]; //cube vertices
    //[0] Black [1] Blue [2] Green [3] Orange [4] Red [5] Yellow [6] White
    final float[][] colors = new float[][]{{0.0f, 0.0f, 0.0f, 1.0f}, {0.0f, 0.0f, 1.0f, 1.0f}, {0.0f, 1.0f, 0.0f, 1.0f},
        {1.0f, 0.5f, 0.0f, 1.0f}, {1.0f, 0.0f, 0.0f, 1.0f}, {1.0f, 1.0f, 0.0f, 1.0f},
        {1.0f, 1.0f, 1.0f, 1.0f}};
    // Front[0] Right[1]   Down[2]   Up[3]    Back[4]    Left[5]
    final int[][] faceVertsZ = new int[][]{{0, 3, 2, 1}, {2, 3, 7, 6}, {3, 0, 4, 7}, {1, 2, 6, 5}, {4, 5, 6, 7}, {5, 4, 0, 1}};
    // Front[0]   Right[1]  Down[2]   Up[3]    Back[4]   Left[5]
    final int[][] faceVertsY = new int[][]{{1, 2, 6, 5}, {2, 3, 7, 6}, {0, 3, 2, 1}, {4, 5, 6, 7}, {3, 0, 4, 7}, {5, 4, 0, 1}};
    // Front[0]   Right[1]  Down[2]   Up[3]    Back[4]   Left[5]
    final int[][] faceVertsX = new int[][]{{5, 4, 0, 1}, {0, 3, 2, 1}, {3, 0, 4, 7}, {1, 2, 6, 5}, {2, 3, 7, 6}, {4, 5, 6, 7}};

    //initialize cube vertices
    private void initCubeVerts() {
        // {{-cubesize,-cubesize,cubesize},{-cubesize,cubesize,cubesize},  {cubesize,cubesize,cubesize},
        //  {cubesize,-cubesize,cubesize} ,{-cubesize,-cubesize,-cubesize},{-cubesize,cubesize,-cubesize},
        //  {cubesize,cubesize,-cubesize},{cubesize,-cubesize,-cubesize} };

        cubeVerts[0][0] = -cubesize;
        cubeVerts[0][1] = -cubesize;
        cubeVerts[0][2] = cubesize;
        cubeVerts[1][0] = -cubesize;
        cubeVerts[1][1] = cubesize;
        cubeVerts[1][2] = cubesize;
        cubeVerts[2][0] = cubesize;
        cubeVerts[2][1] = cubesize;
        cubeVerts[2][2] = cubesize;
        cubeVerts[3][0] = cubesize;
        cubeVerts[3][1] = -cubesize;
        cubeVerts[3][2] = cubesize;
        cubeVerts[4][0] = -cubesize;
        cubeVerts[4][1] = -cubesize;
        cubeVerts[4][2] = -cubesize;
        cubeVerts[5][0] = -cubesize;
        cubeVerts[5][1] = cubesize;
        cubeVerts[5][2] = -cubesize;
        cubeVerts[6][0] = cubesize;
        cubeVerts[6][1] = cubesize;
        cubeVerts[6][2] = -cubesize;
        cubeVerts[7][0] = cubesize;
        cubeVerts[7][1] = -cubesize;
        cubeVerts[7][2] = -cubesize;
    }   //end initCubeVerts()
}
