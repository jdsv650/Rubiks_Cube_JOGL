/* James Donner
 * CubeJOGLSuite.java
 * Last modified: 11/29/11
 * Lists classes to test
 */
package cubeJOGL;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Classes to test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({cubeJOGL.KeyHandlerTest.class, cubeJOGL.MouseHandlerTest.class,
    cubeJOGL.CubeTest.class, cubeJOGL.CubeFilerTest.class, cubeJOGL.PlayerListTest.class,
    cubeJOGL.PlayerTest.class, cubeJOGL.GameGUITest.class, cubeJOGL.CubeMainTest.class,
    cubeJOGL.GLRendererTest.class})
public class CubeJOGLSuite {
}
