/* James Donner
 * CubeStart.java
 * Last modified: 4/17/11
 * Starts the GameGUI
 */
package cubeJOGL;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 * Starts the GameGUI.
 */
public class CubeStart {

    /**
     * Sets the GUI frame to visible. Sets a custom security policy.
     * @param args the (unused) command line arguments
     */
    public static void main(String args[]) {

        /* 
        SecurityManager security = new SecurityManager();
        // custom policy
        System.setProperty("java.security.policy", "./file_perms.policy");
        System.setSecurityManager(security);
        System.out.println("SecurityManager: " + System.getSecurityManager());
        */
        
        // Run this in the AWT event thread to prevent deadlocks and race conditions
        EventQueue.invokeLater(new Runnable() {

            public void run() {

                // switch to system l&f for native font rendering etc.
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "can not enable system look and feel", ex);
                }
                GameGUI frame = new GameGUI();
                frame.setVisible(true);
                
            }
        });
    }
}
