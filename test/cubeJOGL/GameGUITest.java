/* James Donner
 * GameGUITest.java
 * Last modified: 11/12/11
 * Tests GameGUI class
 */
package cubeJOGL;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests GUI (limited).
 */
public class GameGUITest {

    GameGUI game;

    public GameGUITest() {
        try {
            game = new GameGUI();
            game.setVisible(true);
            assertTrue(game.isShowing());
            assertEquals("Rubik's Cube", game.getTitle());
        } finally {
            game.dispose();
        }
    }

    /**
     * Test F1-F9 and R1-R9 buttons set invisible
     */
    @Test
    public void testContentAreaOfButtons() {
        assertTrue(game.jButtonF1.isVisible());
        assertFalse(game.jButtonF1.isContentAreaFilled());
        assertTrue(game.jButtonF2.isVisible());
        assertFalse(game.jButtonF2.isContentAreaFilled());
        assertTrue(game.jButtonF3.isVisible());
        assertFalse(game.jButtonF3.isContentAreaFilled());
        assertTrue(game.jButtonF4.isVisible());
        assertFalse(game.jButtonF4.isContentAreaFilled());
        assertTrue(game.jButtonF5.isVisible());
        assertFalse(game.jButtonF5.isContentAreaFilled());
        assertTrue(game.jButtonF6.isVisible());
        assertFalse(game.jButtonF6.isContentAreaFilled());
        assertTrue(game.jButtonF7.isVisible());
        assertFalse(game.jButtonF7.isContentAreaFilled());
        assertTrue(game.jButtonF8.isVisible());
        assertFalse(game.jButtonF8.isContentAreaFilled());
        assertTrue(game.jButtonF9.isVisible());
        assertFalse(game.jButtonF9.isContentAreaFilled());
        assertTrue(game.jButtonR1.isVisible());
        assertFalse(game.jButtonR1.isContentAreaFilled());
        assertTrue(game.jButtonR2.isVisible());
        assertFalse(game.jButtonR2.isContentAreaFilled());
        assertTrue(game.jButtonR3.isVisible());
        assertFalse(game.jButtonR3.isContentAreaFilled());
        assertTrue(game.jButtonR4.isVisible());
        assertFalse(game.jButtonR4.isContentAreaFilled());
        assertTrue(game.jButtonR5.isVisible());
        assertFalse(game.jButtonR5.isContentAreaFilled());
        assertTrue(game.jButtonR6.isVisible());
        assertFalse(game.jButtonR6.isContentAreaFilled());
        assertTrue(game.jButtonR7.isVisible());
        assertFalse(game.jButtonR7.isContentAreaFilled());
        assertTrue(game.jButtonR8.isVisible());
        assertFalse(game.jButtonR8.isContentAreaFilled());
        assertTrue(game.jButtonR9.isVisible());
        assertFalse(game.jButtonR9.isContentAreaFilled());
    }

    @Test
    public void testPlayers() {
        assertTrue(game.jComboBoxSelectAddPlayer.isVisible());
        // Check "Default User" is selected on startup
        String name = (String) game.jComboBoxSelectAddPlayer.getSelectedItem();
        assertTrue(name.equalsIgnoreCase("Default User"));
        game.jComboBoxSelectAddPlayer.removeItemAt(0);
        assertTrue(game.jPasswordField1.isVisible());
    }
}
