/* James Donner
 * CubeStartTest.java
 * Last modified: 11/25/11
 * Tests CubeStart class
 */
package cubeJOGL;

import org.junit.Test;

/**
 * Just calls main.
 */
public class CubeStartTest {

    CubeStart cStart;

    public CubeStartTest() {
        cStart = new CubeStart();
    }

    /**
     * Test of main method, of class CubeStart.
     */
    @Test
    public void testMain() {
        System.out.println("testing...main");
        CubeStart.main(null);
        assert (true);
    }
}
