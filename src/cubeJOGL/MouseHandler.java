/* James Donner
 * MouseHandler.java
 * Last modified: 4/17/11
 * Processes mouse input
 */
package cubeJOGL;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * Class to process mouse input.  (Also handled in GameGUI).
 */
public class MouseHandler { //implements MouseListener {

    private GLRenderer cubeRend;  //the GLRenderer object
    private CubeDefs defs = CubeDefs.getInstance(); //cube definitions
    private Point clickedPoint = new Point();  // mouse clicked point
    private Point releasedPoint = new Point(); // mouse released point
    private GameGUI game;

    /**
     * Construct the mouse handler.
     * @param rend the rendering context.
     * @param game the main game gui.
     */
    public MouseHandler(GLRenderer rend, GameGUI game) {
        cubeRend = rend;
        this.game = game;
    }

    /**
     * Log the mouse clicked point for any F1-F9, R1-R9 button.
     * @param evt the mouse event.
     */
    public void jButtonMousePressed(MouseEvent evt) {
        clickedPoint = evt.getPoint();
    }

    /*************************
    public void jButtonF1MousePressed(MouseEvent evt) {
    clickedPoint = evt.getPoint();
    }
     *****************************/
    /**
     * Log the mouse released point and select slice to animate for F buttons.
     * Reduces redundant code in F1-F9 released methods.
     * @param evt the mouse event.
     */
    public void jButtonFMouseReleased(MouseEvent evt) {
        JButton jButtonR = (JButton) evt.getSource();
        if (cubeRend.checkSliceAnimated()) {
            return;
        }
        releasedPoint = evt.getPoint();

        if (releasedPoint.x > clickedPoint.x
                && releasedPoint.y > clickedPoint.y) { //Q4
            if (game.jButtonF1 == jButtonR
                    || game.jButtonF2 == jButtonR
                    || game.jButtonF3 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice4(1);
            } else if (game.jButtonF4 == jButtonR
                    || game.jButtonF5 == jButtonR
                    || game.jButtonF6 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice5(1);
            } else if (game.jButtonF7 == jButtonR
                    || game.jButtonF8 == jButtonR
                    || game.jButtonF9 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice6(1);
            }
        } else if (releasedPoint.x < clickedPoint.x
                && releasedPoint.y > clickedPoint.y) { //Q3
            if (game.jButtonF1 == jButtonR
                    || game.jButtonF4 == jButtonR
                    || game.jButtonF7 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice7(1);
            } else if (game.jButtonF2 == jButtonR
                    || game.jButtonF5 == jButtonR
                    || game.jButtonF8 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice8(1);
            } else if (game.jButtonF3 == jButtonR
                    || game.jButtonF6 == jButtonR
                    || game.jButtonF9 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice9(1);
            }
        } else if (releasedPoint.x < clickedPoint.x
                && releasedPoint.y < clickedPoint.y) { //Q2
            if (game.jButtonF1 == jButtonR
                    || game.jButtonF2 == jButtonR
                    || game.jButtonF3 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice4(1);
            } else if (game.jButtonF4 == jButtonR
                    || game.jButtonF5 == jButtonR
                    || game.jButtonF6 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice5(1);
            } else if (game.jButtonF7 == jButtonR
                    || game.jButtonF8 == jButtonR
                    || game.jButtonF9 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice6(1);
            }
        } else if (releasedPoint.x > clickedPoint.x
                && releasedPoint.y < clickedPoint.y) { //Q1
            if (game.jButtonF1 == jButtonR
                    || game.jButtonF4 == jButtonR
                    || game.jButtonF7 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice7(1);
            } else if (game.jButtonF2 == jButtonR
                    || game.jButtonF5 == jButtonR
                    || game.jButtonF8 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice8(1);
            } else if (game.jButtonF3 == jButtonR
                    || game.jButtonF6 == jButtonR
                    || game.jButtonF9 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice9(1);
            }
        } //end if
    } // end jButtonFMouseReleased()

    /**
     * Log the mouse released point and select slice to animate for R buttons.
     * Reduces redundant code in R1-R9 released methods.
     * @param evt the mouse event.
     */
    public void jButtonRMouseReleased(MouseEvent evt) {
        JButton jButtonR = (JButton) evt.getSource();
        if (cubeRend.checkSliceAnimated()) {
            return;
        }
        releasedPoint = evt.getPoint();

        if (releasedPoint.x > clickedPoint.x
                && releasedPoint.y > clickedPoint.y) { //Q4
            if (game.jButtonR1 == jButtonR
                    || game.jButtonR4 == jButtonR
                    || game.jButtonR7 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice3(1);
            } else if (game.jButtonR2 == jButtonR
                    || game.jButtonR5 == jButtonR
                    || game.jButtonR8 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice2(1);
            } else if (game.jButtonR3 == jButtonR
                    || game.jButtonR6 == jButtonR
                    || game.jButtonR9 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice1(1);
            }
        } else if (releasedPoint.x < clickedPoint.x
                && releasedPoint.y > clickedPoint.y) { //Q3
            if (game.jButtonR1 == jButtonR
                    || game.jButtonR2 == jButtonR
                    || game.jButtonR3 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice4(1);
            } else if (game.jButtonR4 == jButtonR
                    || game.jButtonR5 == jButtonR
                    || game.jButtonR6 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice5(1);
            } else if (game.jButtonR7 == jButtonR
                    || game.jButtonR8 == jButtonR
                    || game.jButtonR9 == jButtonR) {
                cubeRend.rubik.setRotMult(-1);
                cubeRend.setAnimateSlice6(1);
            }
        } else if (releasedPoint.x < clickedPoint.x
                && releasedPoint.y < clickedPoint.y) { //Q2
            if (game.jButtonR1 == jButtonR
                    || game.jButtonR4 == jButtonR
                    || game.jButtonR7 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice3(1);
            } else if (game.jButtonR2 == jButtonR
                    || game.jButtonR5 == jButtonR
                    || game.jButtonR8 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice2(1);
            } else if (game.jButtonR3 == jButtonR
                    || game.jButtonR6 == jButtonR
                    || game.jButtonR9 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice1(1);
            }
        } else if (releasedPoint.x > clickedPoint.x
                && releasedPoint.y < clickedPoint.y) { //Q1
            if (game.jButtonR1 == jButtonR
                    || game.jButtonR2 == jButtonR
                    || game.jButtonR3 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice4(1);
            } else if (game.jButtonR4 == jButtonR
                    || game.jButtonR5 == jButtonR
                    || game.jButtonR6 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice5(1);
            } else if (game.jButtonR7 == jButtonR
                    || game.jButtonR8 == jButtonR
                    || game.jButtonR9 == jButtonR) {
                cubeRend.rubik.setRotMult(1);
                cubeRend.setAnimateSlice6(1);
            }
        } //end if
    } // end jButtonFMouseReleased(

    /**
     * Set T,F,R perspective view.
     * @param evt the mouse event.
     */
    public void jRadioButtonMenuItemPerspViewTFRActionPerformed(ActionEvent evt) {
        cubeRend.setEyeX(defs.cubesize + 10);
        cubeRend.setEyeY(defs.cubesize + 10);
        cubeRend.setEyeZ(defs.cubesize + 10);
        cubeRend.setPerspView(1);
    }

    /**
     * Set D,B,L perspective view.
     * @param evt the mouse event.
     */
    public void jRadioButtonMenuItemPerspViewBBLActionPerformed(ActionEvent evt) {
        cubeRend.setEyeX(defs.cubesize - 10);
        cubeRend.setEyeY(defs.cubesize - 10);
        cubeRend.setEyeZ(defs.cubesize - 10);
        cubeRend.setPerspView(2);
    }

    /**
     * Menu item Reset (build)) cube.
     * @param evt mouse event.
     */
    public void jMenuItemNewGameActionPerformed(ActionEvent evt) {
        cubeRend.rubik.buildCube(); // set up initial cube -- colors...
    }

    /**
     * Set three secondary viewports.
     * @param evt mouse event
     */
    public void jRadioButtonMenuItemSideViewTFRActionPerformed(ActionEvent evt) {
        cubeRend.setView(2);
    }

    /**
     * Set three secondary viewports.
     * @param evt mouse event.
     */
    public void jRadioButtonMenuItemSideViewBBLActionPerformed(ActionEvent evt) {
        cubeRend.setView(1);
    }

    /**
     * Turn animate slice rotation on.
     * @param evt the mouse event.
     */
    public void jRadioButtonMenuItemAnimRotOnActionPerformed(ActionEvent evt) {
        cubeRend.setUpdateOnly(0);
    }

    /**
     * Turn animate slice rotation off.
     * @param evt the mouse event.
     */
    public void jRadioButtonMenuItemAnimRotOffActionPerformed(ActionEvent evt) {
        cubeRend.setUpdateOnly(1);
    }

    /**
     * No rotation main cube.
     * @param evt mouse event.
     */
    public void jRadioButtonMenuItemRotateNoActionPerformed(ActionEvent evt) {
        cubeRend.setAnimateX(0);
        cubeRend.setAnimateY(0);
        cubeRend.setAnimateZ(0);
    }

    /**
     * Rotate main cube on X.
     * @param evt mouse event.
     */
    public void jRadioButtonMenuItemRotateXActionPerformed(ActionEvent evt) {
        cubeRend.setAnimateX(1);
        cubeRend.setAnimateY(0);
        cubeRend.setAnimateZ(0);
    }

    /**
     * Rotate main cube on Y.
     * @param evt mouse event.
     */
    public void jRadioButtonMenuItemRotateYActionPerformed(ActionEvent evt) {
        cubeRend.setAnimateX(0);
        cubeRend.setAnimateY(1);
        cubeRend.setAnimateZ(0);
    }

    /**
     * Rotate main cube on Z.
     * @param evt mouse event.
     */
    public void jRadioButtonMenuItemRotateZActionPerformed(ActionEvent evt) {
        cubeRend.setAnimateX(0);
        cubeRend.setAnimateY(0);
        cubeRend.setAnimateZ(1);
    }
}
