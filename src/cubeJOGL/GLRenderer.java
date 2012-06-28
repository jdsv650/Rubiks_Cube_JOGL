/* James Donner
 * GLRenderer.java
 * Last modified: 10/28/11
 * Contains OpenGL code to display a Rubik's cube
 */
package cubeJOGL;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.GLUT;

/**
 * Class to do OpenGL rendering of a Rubik's cube.
 */
public class GLRenderer implements GLEventListener {

    /**
     * The current cube is updated based on player moves.
     */
    protected CubeBuilder rubik;
    // private no getter/setter
    private CubeDefs defs = CubeDefs.getInstance(); //cube definitions
    private int ww = 500, wh = 500;  //window width, window heigth
    private int space = 2;           //space between cubes
    private double left, right, bottom, top;  //boundaries
    private double near = -2.0, far = 2.0, zoom = 10; //...
    private int cubeRot = 0, rot = 1; //rot of cube on XYZ
    private int speed = 3; //cube update freq -- user controlled
    private int lensAngle = 60;       //lens angle
    private static final int V1 = 1;  //viewports 1-4
    private static final int V2 = 2;
    private static final int V3 = 3;
    private static final int V4 = 4;
    private GL gl;     //gl context -better to pass the drawables
    private GLU glu;
    private GLUT glut;
    private int playerNumber = 1;  //current player number
    // private with getter and/or setter
    private int perspView = 1;   //1 Front,Top,Right 2 opp view
    private int updateOnly = 0;  //animate slice rotation?
    private float eyeX = 0, eyeY = 0, eyeZ = 20;  //looking from
    private int view = 1;  //view=1 then V2=Bottom V3=Left  V4=Back
    //view=2 then V2=Top    V3=Right V4=Front
    private int animateX = 0, animateY = 0, animateZ = 0; //animate on X,Y,Z
    // Animate slice rotation flags
    private int animateSlice1 = 0, animateSlice2 = 0, animateSlice3 = 0;
    private int animateSlice4 = 0, animateSlice5 = 0, animateSlice6 = 0;
    private int animateSlice7 = 0, animateSlice8 = 0, animateSlice9 = 0;

    /**
     * Check if a slice is being animated (allow one at a time)
     * @return true if any slice is being animated else false
     */
    public boolean checkSliceAnimated() {
        if (animateSlice1 != 0 || animateSlice2 != 0 || animateSlice3 != 0
                || animateSlice4 != 0 || animateSlice5 != 0 || animateSlice6 != 0
                || animateSlice7 != 0 || animateSlice8 != 0 || animateSlice9 != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the settings for the 9 animateSlice flags, [0] is unused.
     * @return int[10] with [1]..[9] = animateSlice1..animateSlice9
     */
    public int[] getAllAnimated() {
        int[] temp = new int[10];
        temp[0] = 0;
        temp[1] = animateSlice1;
        temp[2] = animateSlice2;
        temp[3] = animateSlice3;
        temp[4] = animateSlice4;
        temp[5] = animateSlice5;
        temp[6] = animateSlice6;
        temp[7] = animateSlice7;
        temp[8] = animateSlice8;
        temp[9] = animateSlice9;
        return temp;
    }

    /**
     * Reset all slice animations.
     */
    public void clearAllAnimatedSlices() {
        animateSlice1 = 0;
        animateSlice2 = 0;
        animateSlice3 = 0;
        animateSlice4 = 0;
        animateSlice5 = 0;
        animateSlice6 = 0;
        animateSlice7 = 0;
        animateSlice8 = 0;
        animateSlice9 = 0;
    }

    /**
     * Sets cube to players cube. And sets current player number.
     * @param p the player.
     */
    public GLRenderer(Player p) {
        rubik = p.getMyCube();
        playerNumber = p.getNumber();
    }

    /**
     * OpenGL init()
     * @param drawable the drawable context.
     */
    public void init(GLAutoDrawable drawable) {
        eyeX = defs.cubesize + 10;
        eyeY = defs.cubesize + 10;
        eyeZ = defs.cubesize + 10;

        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        // System.err.println("INIT GL IS: " + gl.getClass().getName());

        float light_position[] = {1.0f, 0.0f, 0.0f, 0.0f};
        float light_position1[] = {0.0f, 1.0f, 0.0f, 0.0f};

        /* Change the color of the light */
        float white_light[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float lmodel_ambient[] = {0.3f, 0.3f, 0.3f, 1.0f};

        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, light_position, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, light_position1, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_SPECULAR, white_light, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPECULAR, white_light, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, white_light, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, white_light, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, white_light, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, white_light, 0);
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_LIGHT0);
        gl.glEnable(gl.GL_LIGHT1);
        gl.glEnable(gl.GL_AUTO_NORMAL);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_BLEND);
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
        gl.glShadeModel(gl.GL_SMOOTH);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
    }

    // for resize
    private void adjustOrtho() {
        if (ww <= wh) {
            left = -zoom;
            right = zoom;
            bottom = (-zoom * (float) wh / (float) ww);
            top = (zoom * (float) wh / (float) ww);
            near = -zoom;
            far = 2 * zoom;
        } else {
            left = (-zoom * (float) ww / (float) wh);
            right = (zoom * (float) ww / (float) wh);
            bottom = -zoom;
            top = zoom;
            near = -2 * zoom;
            far = 2 * zoom;
        }
    }

    /**
     * OpenGL reshape()
     * @param drawable the drawable.
     * @param x unused
     * @param y unused
     * @param width the window width
     * @param height the window height
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL();
        ww = width;
        wh = height;
        // gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        adjustOrtho();
        gl.glMatrixMode(gl.GL_MODELVIEW);
        drawable.repaint();
    }

    // Set gluLookAt for view
    private void setLookAt() {
        if (perspView == 1) { //forward and above
            if (view == 1) // toggle bottom view
            {
                glu.gluLookAt(0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
            } else // toggle top view
            {
                glu.gluLookAt(0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0);
            }
        }

        if (perspView == 2) { // opposite view
            if (view == 1) //toggle bottom view
            {
                glu.gluLookAt(0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0);
            } else // toggle top view
            {
                glu.gluLookAt(0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
            }
        }
    }

    // Update a cube slice without the animation
    private void updateWithoutAnimate() {
        if (animateSlice1 == 1) {
            rubik.updateZSlice(1);
            animateSlice1 = 0;
        } else if (animateSlice2 == 1) {
            rubik.updateZSlice(2);
            animateSlice2 = 0;
        } else if (animateSlice3 == 1) {
            rubik.updateZSlice(3);
            animateSlice3 = 0;
        } else if (animateSlice4 == 1) {
            rubik.updateYSlice(4);
            animateSlice4 = 0;
        } else if (animateSlice5 == 1) {
            rubik.updateYSlice(5);
            animateSlice5 = 0;
        } else if (animateSlice6 == 1) {
            rubik.updateYSlice(6);
            animateSlice6 = 0;
        } else if (animateSlice7 == 1) {
            rubik.updateXSlice(7);
            animateSlice7 = 0;
        } else if (animateSlice8 == 1) {
            rubik.updateXSlice(8);
            animateSlice8 = 0;
        } else if (animateSlice9 == 1) {
            rubik.updateXSlice(9);
            animateSlice9 = 0;
        }
    }

    // Update a cube slice with the animation
    private void updateWithAnimate(GLAutoDrawable drawable) {
        gl = drawable.getGL();

        if (animateSlice1 == 1) {
            //rot = ++rot %91;
            //speed is now user adjustable for faster running time
            rot = ++rot % (90 / speed + 1);
            animateZSlice(1, drawable);  // 1st z slice CCW use 'r' for CW
            if (rot == 0) {
                animateSlice1 = 0;
            }
        } else if (animateSlice2 == 1) {
            //rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateZSlice(2, drawable);  // 2nd z slice (middle z slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice2 = 0;
            }
        } else if (animateSlice3 == 1) {
            // rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateZSlice(3, drawable);  // 3rd z slice (front z slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice3 = 0;
            }
        } else if (animateSlice4 == 1) {
            // rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateYSlice(4, drawable);  // 4th slice on y (top y slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice4 = 0;
            }
        } else if (animateSlice5 == 1) {
            // rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateYSlice(5, drawable);  // 5th slice (middle y slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice5 = 0;
            }
        } else if (animateSlice6 == 1) {
            //  rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateYSlice(6, drawable);  // 6th slice (bottom y slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice6 = 0;
            }
        } else if (animateSlice7 == 1) {
            // rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateXSlice(7, drawable);  // 7th slice on x (left x slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice7 = 0;
            }
        } else if (animateSlice8 == 1) {
            // rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateXSlice(8, drawable);  // 8th slice (middle x slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice8 = 0;
            }
        } else if (animateSlice9 == 1) {
            //  rot = ++rot % 91;
            rot = ++rot % (90 / speed + 1);
            animateXSlice(9, drawable);  // 9th slice (right x slice) CCW use 'r' for C
            if (rot == 0) {
                animateSlice9 = 0;
            }
        }
    }

    /**
     * OpenGL display()
     * @param drawable the drawable.
     */
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glPushMatrix();
        //(lower-left)
        gl.glViewport(0, 0, ww / 2, wh / 2);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(left, right, bottom, top, near, far);
        setLookAt();

        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        drawCube(drawable, V2);
        // (upper-right)
        gl.glViewport(ww / 2, wh / 2, ww / 2, wh / 2);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(left, right, bottom, top, near, far);
        if (view == 1) {
            glu.gluLookAt(0.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        } else {
            glu.gluLookAt(0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        }
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        drawCube(drawable, V4);
        // (upper-left)
        gl.glViewport(0, wh / 2, ww / 2, wh / 2);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(left, right, bottom, top, near, far);
        if (view == 1) {
            glu.gluLookAt(-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        } else {
            glu.gluLookAt(1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        }
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        drawCube(drawable, V3);
        // Perspective viewport (lower-right)
        gl.glViewport(ww / 2, 0, ww / 2, wh / 2);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(lensAngle, (double) ww / (double) wh, 0.5, 1000);
        glu.gluLookAt(eyeX, eyeY, eyeZ, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        if (animateX == 1) {
            animateXCube(drawable);
        } else if (animateY == 1) {
            animateYCube(drawable);
        } else if (animateZ == 1) {
            animateZCube(drawable);
        }

        if (updateOnly == 1) {
            updateWithoutAnimate();

        } else {
            updateWithAnimate(drawable);
        }

        if (animateSlice1 != 1 && animateSlice2 != 1 && animateSlice3 != 1
                && animateSlice4 != 1 && animateSlice5 != 1 && animateSlice6 != 1
                && animateSlice7 != 1 && animateSlice8 != 1 && animateSlice9 != 1) {
            drawCube(drawable, V1);
        }
        gl.glPopMatrix();
        drawable.swapBuffers();
        drawable.repaint();
    }

    /**
     * OpenGL displayChanged() Unused.
     * @param drawable
     * @param modeChanged
     * @param deviceChanged
     */
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    //draw the cube
    private void drawCube(GLAutoDrawable drawable, int viewportNum) //draw entire cube
    {
        gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glDisable(gl.GL_LIGHTING);
        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
        gl.glShadeModel(gl.GL_SMOOTH);
        gl.glLineWidth(2);  // for line through axis
        if (view == 1) {
            if (viewportNum == V4) {   // V1,V2,V3,V4 CW starting at persp view lower right
                gl.glColor3fv(defs.colors[5], 0);  //Yellow
                gl.glRasterPos3f(1, 5, 0);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'B');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'A');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'C');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'K');
            } else if (viewportNum == V3) {   // V1,V2,V3,V4 CW starting at persp view lower right
                gl.glColor3fv(defs.colors[1], 0);  //Blue
                gl.glRasterPos3f(0, 5, -1);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'L');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'E');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'F');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
            } else if (viewportNum == V2) {   // V1,V2,V3,V4 CW starting at persp view lower right
                gl.glColor3fv(defs.colors[3], 0);  //Orange
                gl.glRasterPos3f(-1, 0, 5);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'B');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'M');
            }
        }  // end if(view==1)

        if (view == 2) {
            if (viewportNum == V4) {   // V1,V2,V3,V4 CW starting at persp view lower right
                gl.glColor3fv(defs.colors[6], 0);  //White
                gl.glRasterPos3f(1, 5, 0);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'F');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'R');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'N');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
            } else if (viewportNum == V3) {   // V1,V2,V3,V4 CW starting at persp view lower right
                gl.glColor3fv(defs.colors[2], 0);  //Green
                gl.glRasterPos3f(0, 5, -1);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'R');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'I');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'G');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'H');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
            } else if (viewportNum == V2) {   // V1,V2,V3,V4 CW starting at persp view lower right
                gl.glColor3fv(defs.colors[4], 0);  //Red
                gl.glRasterPos3f(-1, 0, 5);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'P');
            }
        }  // end if(view==2)
        if (viewportNum == V1) {   // V1 persp view lower right
            if (perspView == 1) {
                gl.glColor3fv(defs.colors[6], 0);  //White
                gl.glRasterPos3f(0, 0, 9);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'F');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'R');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'N');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, '(');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'Z');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, ')');
                gl.glBegin(gl.GL_LINES);
                gl.glVertex3f(0, 0, -5 * (defs.cubesize + space));
                gl.glVertex3f(0, 0, 5 * (defs.cubesize + space));
                gl.glEnd();
                gl.glColor3fv(defs.colors[2], 0);  //Green
                gl.glRasterPos3f(7, 0, 0);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'R');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'I');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'G');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'H');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, '(');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'X');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, ')');
                gl.glBegin(gl.GL_LINES);
                gl.glVertex3f(-5 * (defs.cubesize + space), 0, 0);
                gl.glVertex3f(5 * (defs.cubesize + space), 0, 0);
                gl.glEnd();
                gl.glColor3fv(defs.colors[4], 0);  //Red
                gl.glRasterPos3f(0, 7, 0);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'P');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, '(');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'Y');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, ')');
                gl.glBegin(gl.GL_LINES);
                gl.glVertex3f(0, -5 * (defs.cubesize + space), 0);
                gl.glVertex3f(0, 5 * (defs.cubesize + space), 0);
                gl.glEnd();
            } else if (perspView == 2) {
                gl.glColor3fv(defs.colors[5], 0);  //Yellow
                gl.glRasterPos3f(0, 0, -9);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'B');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'A');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'C');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'K');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, '(');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'Z');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, ')');
                gl.glColor3fv(defs.colors[6], 0);  //White
                gl.glBegin(gl.GL_LINES);
                gl.glVertex3f(0, 0, -5 * (defs.cubesize + space));
                gl.glVertex3f(0, 0, 5 * (defs.cubesize + space));
                gl.glEnd();
                gl.glColor3fv(defs.colors[1], 0);  //Blue
                gl.glRasterPos3f(-7, 0, 0);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'L');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'E');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'F');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, '(');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'X');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, ')');
                gl.glColor3fv(defs.colors[2], 0);  //Green
                gl.glBegin(gl.GL_LINES);
                gl.glVertex3f(-5 * (defs.cubesize + space), 0, 0);
                gl.glVertex3f(5 * (defs.cubesize + space), 0, 0);
                gl.glEnd();
                gl.glColor3fv(defs.colors[3], 0);  //Orange
                gl.glRasterPos3f(0, -7, 0);
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'B');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'T');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'O');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'M');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, '(');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, 'Y');
                glut.glutBitmapCharacter(glut.BITMAP_9_BY_15, ')');
                gl.glColor3fv(defs.colors[4], 0);  //Red
                gl.glBegin(gl.GL_LINES);
                gl.glVertex3f(0, -5 * (defs.cubesize + space), 0);
                gl.glVertex3f(0, 5 * (defs.cubesize + space), 0);
                gl.glEnd();
            }
        }
        gl.glLineWidth(1);  //Reset line width
        //slice one
        gl.glTranslatef(0, 0, -(defs.cubesize + space));
        drawZSlice(1);
        //2nd slice
        gl.glTranslatef(0, 0, +(defs.cubesize + space));
        drawZSlice(2);
        //3rd slice
        gl.glTranslatef(0, 0, +(defs.cubesize + space));
        drawZSlice(3);
        gl.glPopMatrix();
    }

    // draw one of the X slices 7,8, or 9
    private void drawXSlice(int num) {
        int cubeArray[] = new int[9];
        if (num == 7) {		 //x slice7  1,4,7,10,13,16,19,22,25
            cubeArray[0] = 19;
            cubeArray[1] = 10;
            cubeArray[2] = 1;
            cubeArray[3] = 22;
            cubeArray[4] = 13;
            cubeArray[5] = 4;
            cubeArray[6] = 25;
            cubeArray[7] = 16;
            cubeArray[8] = 7;
        } else if (num == 8) { //x slice8  2,5,8,11,14,17,20,23,26
            cubeArray[0] = 20;
            cubeArray[1] = 11;
            cubeArray[2] = 2;
            cubeArray[3] = 23;
            cubeArray[4] = 14;
            cubeArray[5] = 5;
            cubeArray[6] = 26;
            cubeArray[7] = 17;
            cubeArray[8] = 8;
        } else if (num == 9) { //x slice9  3,6,9,12,15,18,21,24,27
            cubeArray[0] = 21;  //21,3,12
            cubeArray[1] = 12;
            cubeArray[2] = 3;
            cubeArray[3] = 24;  //24,6,15
            cubeArray[4] = 15;
            cubeArray[5] = 6;
            cubeArray[6] = 27;  //27,9,18
            cubeArray[7] = 18;
            cubeArray[8] = 9;
        }
        gl.glPushMatrix();
        gl.glTranslatef(-(defs.cubesize + space), defs.cubesize + space, 0); //row1 col1 cube
        drawMiniCubeX(cubeArray[0]);           // slice num 7 8 or 9
        gl.glTranslatef(defs.cubesize + space, 0, 0);  //row1 col2 cube
        drawMiniCubeX(cubeArray[1]);
        gl.glTranslatef(defs.cubesize + space, 0, 0);  //row1 col3 cube
        drawMiniCubeX(cubeArray[2]);
        gl.glTranslatef(-2 * (defs.cubesize + space), -(defs.cubesize + space), 0);  //row2 col1 cube
        drawMiniCubeX(cubeArray[3]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row2 col2 cube  -- center cube
        drawMiniCubeX(cubeArray[4]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row2 col3 cube
        drawMiniCubeX(cubeArray[5]);
        gl.glTranslatef(-2 * (defs.cubesize + space), -(defs.cubesize + space), 0);  //row3 col1 cube
        drawMiniCubeX(cubeArray[6]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row3 col2 cube
        drawMiniCubeX(cubeArray[7]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row3 col3 cube
        drawMiniCubeX(cubeArray[8]);
        gl.glPopMatrix();
    } // end drawXSlice()

    //draw one of the Y slices (stacks) 4,5,6
    private void drawYSlice(int num) {
        int cubeArray[] = new int[9];

        if (num == 4) {		 //slice 4(or stack 1)
            cubeArray[0] = 19;
            cubeArray[1] = 20;
            cubeArray[2] = 21;
            cubeArray[3] = 10;
            cubeArray[4] = 11;
            cubeArray[5] = 12;
            cubeArray[6] = 1;
            cubeArray[7] = 2;
            cubeArray[8] = 3;
        } else if (num == 5) { //slice 5 (or stack 2)
            cubeArray[0] = 22;
            cubeArray[1] = 23;
            cubeArray[2] = 24;
            cubeArray[3] = 13;
            cubeArray[4] = 14;
            cubeArray[5] = 15;
            cubeArray[6] = 4;
            cubeArray[7] = 5;
            cubeArray[8] = 6;
        } else if (num == 6) { //slice 6 (or stack 3)
            cubeArray[0] = 25;
            cubeArray[1] = 26;
            cubeArray[2] = 27;
            cubeArray[3] = 16;
            cubeArray[4] = 17;
            cubeArray[5] = 18;
            cubeArray[6] = 7;
            cubeArray[7] = 8;
            cubeArray[8] = 9;
        }

        gl.glPushMatrix();
        gl.glTranslatef(-(defs.cubesize + space), defs.cubesize + space, 0); //row1 col1 cube
        drawMiniCubeY(cubeArray[0]);           // slice num 4 5 or 6
        gl.glTranslatef(defs.cubesize + space, 0, 0);  //row1 col2 cube
        drawMiniCubeY(cubeArray[1]);
        gl.glTranslatef(defs.cubesize + space, 0, 0);  //row1 col3 cube
        drawMiniCubeY(cubeArray[2]);
        gl.glTranslatef(-2 * (defs.cubesize + space), -(defs.cubesize + space), 0);  //row2 col1 cube
        drawMiniCubeY(cubeArray[3]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row2 col2 cube  -- center cube
        drawMiniCubeY(cubeArray[4]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row2 col3 cube
        drawMiniCubeY(cubeArray[5]);
        gl.glTranslatef(-2 * (defs.cubesize + space), -(defs.cubesize + space), 0);  //row3 col1 cube
        drawMiniCubeY(cubeArray[6]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row3 col2 cube
        drawMiniCubeY(cubeArray[7]);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row3 col3 cube
        drawMiniCubeY(cubeArray[8]);
        gl.glPopMatrix();
    }

    //draw 1 of the Z slices 1,2,3
    private void drawZSlice(int num) {
        int cubeNum = 1;

        if (num == 1) {
            cubeNum = 1;
        } else if (num == 2) //slice 2 10-18
        {
            cubeNum = 10;
        } else if (num == 3) //slice 3 19-27
        {
            cubeNum = 19;
        }
        gl.glPushMatrix();
        gl.glTranslatef(-(defs.cubesize + space), defs.cubesize + space, 0); //row1 col1 cube
        drawMiniCubeZ(cubeNum);           // slice num 1 2 or 3  -- slice num *
        gl.glTranslatef(defs.cubesize + space, 0, 0);  //row1 col2 cube
        drawMiniCubeZ(cubeNum + 1);
        gl.glTranslatef(defs.cubesize + space, 0, 0);  //row1 col3 cube
        drawMiniCubeZ(cubeNum + 2);
        gl.glTranslatef(-2 * (defs.cubesize + space), -(defs.cubesize + space), 0);  //row2 col1 cube
        drawMiniCubeZ(cubeNum + 3);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row2 col2 cube  -- center cube
        drawMiniCubeZ(cubeNum + 4);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row2 col3 cube
        drawMiniCubeZ(cubeNum + 5);
        gl.glTranslatef(-2 * (defs.cubesize + space), -(defs.cubesize + space), 0);  //row3 col1 cube
        drawMiniCubeZ(cubeNum + 6);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row3 col2 cube
        drawMiniCubeZ(cubeNum + 7);
        gl.glTranslatef(defs.cubesize + space, 0, 0); //row3 col3 cube
        drawMiniCubeZ(cubeNum + 8);
        gl.glPopMatrix();
    }

    //draw cube looking from X
    private void drawMiniCubeX(int num) {
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getF()], 0);
        drawFace(defs.faceVertsX[0][0], defs.faceVertsX[0][1], defs.faceVertsX[0][2], defs.faceVertsX[0][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getR()], 0);
        drawFace(defs.faceVertsX[1][0], defs.faceVertsX[1][1], defs.faceVertsX[1][2], defs.faceVertsX[1][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getD()], 0);
        drawFace(defs.faceVertsX[2][0], defs.faceVertsX[2][1], defs.faceVertsX[2][2], defs.faceVertsX[2][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getU()], 0);
        drawFace(defs.faceVertsX[3][0], defs.faceVertsX[3][1], defs.faceVertsX[3][2], defs.faceVertsX[3][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getB()], 0);
        drawFace(defs.faceVertsX[4][0], defs.faceVertsX[4][1], defs.faceVertsX[4][2], defs.faceVertsX[4][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getL()], 0);
        drawFace(defs.faceVertsX[5][0], defs.faceVertsX[5][1], defs.faceVertsX[5][2], defs.faceVertsX[5][3]);
    }

    private void drawMiniCubeY(int num) { //draw cube looking from Y
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getF()], 0);
        drawFace(defs.faceVertsY[0][0], defs.faceVertsY[0][1], defs.faceVertsY[0][2], defs.faceVertsY[0][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getR()], 0);
        drawFace(defs.faceVertsY[1][0], defs.faceVertsY[1][1], defs.faceVertsY[1][2], defs.faceVertsY[1][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getD()], 0);
        drawFace(defs.faceVertsY[2][0], defs.faceVertsY[2][1], defs.faceVertsY[2][2], defs.faceVertsY[2][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getU()], 0);
        drawFace(defs.faceVertsY[3][0], defs.faceVertsY[3][1], defs.faceVertsY[3][2], defs.faceVertsY[3][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getB()], 0);
        drawFace(defs.faceVertsY[4][0], defs.faceVertsY[4][1], defs.faceVertsY[4][2], defs.faceVertsY[4][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getL()], 0);
        drawFace(defs.faceVertsY[5][0], defs.faceVertsY[5][1], defs.faceVertsY[5][2], defs.faceVertsY[5][3]);
    }

    private void drawMiniCubeZ(int num) {   //draw cube looking from Z
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getF()], 0);
        drawFace(defs.faceVertsZ[0][0], defs.faceVertsZ[0][1], defs.faceVertsZ[0][2], defs.faceVertsZ[0][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getR()], 0);
        drawFace(defs.faceVertsZ[1][0], defs.faceVertsZ[1][1], defs.faceVertsZ[1][2], defs.faceVertsZ[1][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getD()], 0);
        drawFace(defs.faceVertsZ[2][0], defs.faceVertsZ[2][1], defs.faceVertsZ[2][2], defs.faceVertsZ[2][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getU()], 0);
        drawFace(defs.faceVertsZ[3][0], defs.faceVertsZ[3][1], defs.faceVertsZ[3][2], defs.faceVertsZ[3][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getB()], 0);
        drawFace(defs.faceVertsZ[4][0], defs.faceVertsZ[4][1], defs.faceVertsZ[4][2], defs.faceVertsZ[4][3]);
        gl.glColor3fv(defs.colors[rubik.getCube()[num - 1].getL()], 0);
        drawFace(defs.faceVertsZ[5][0], defs.faceVertsZ[5][1], defs.faceVertsZ[5][2], defs.faceVertsZ[5][3]);
    }

    // draw one side of one cube
    private void drawFace(
            int vert1, int vert2, int vert3, int vert4) {
        gl.glBegin(gl.GL_POLYGON);
        gl.glVertex3fv(defs.cubeVerts[vert1], 0);
        gl.glVertex3fv(defs.cubeVerts[vert2], 0);
        gl.glVertex3fv(defs.cubeVerts[vert3], 0);
        gl.glVertex3fv(defs.cubeVerts[vert4], 0);
        gl.glEnd();
    } // end drawFace()

    // animate slice 7,8 or 9 on X axis
    // 7 is left slice, 8 is middle slice, 9 is right slice
    private void animateXSlice(int sliceNum, GLAutoDrawable drawable) {
        gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        if (sliceNum == 7) //x slice 7
        {
            gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
            gl.glTranslatef(0, 0, 0);
            drawXSlice(8);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            drawXSlice(9);
            gl.glTranslatef(0, 0, -2 * (defs.cubesize + space));
            gl.glRotatef(rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawXSlice(7);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateXSlice(7);
            }
        } else if (sliceNum == 8) // x slice 8
        {
            gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
            gl.glTranslatef(0, 0, +defs.cubesize + space);
            drawXSlice(9);
            gl.glTranslatef(0, 0, -2 * (defs.cubesize + space));
            drawXSlice(7);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            gl.glRotatef(rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawXSlice(8);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateXSlice(8);
            }
        } else if (sliceNum == 9) // x slice 9
        {
            gl.glRotatef(90, 0.0f, 1.0f, 0.0f);
            gl.glTranslatef(0, 0, -(defs.cubesize + space));
            drawXSlice(7);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            drawXSlice(8);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            gl.glRotatef(rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawXSlice(9);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateXSlice(9);
            }
        }
        // drawable.swapBuffers();
        // drawable.repaint();
        gl.glPopMatrix();
    }  // end animateXSlice()

    // animate slice 4,5 or 6 on Y axis
    // 4 is top slice, 5 is middle slice, 6 is bottom slice
    // OR 1st stack, 2nd stack, 3rd stack
    private void animateYSlice(int sliceNum, GLAutoDrawable drawable) {
        gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        if (sliceNum == 4) //y slice 4(or stack) on top
        {
            gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
            gl.glTranslatef(0, 0, 0);
            drawYSlice(5);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            drawYSlice(6);
            gl.glTranslatef(0, 0, -2 * (defs.cubesize + space));
            gl.glRotatef(-rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawYSlice(4);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateYSlice(4);
            }
        } else if (sliceNum == 5) // y slice 5 (or 2nd stack) middle
        {
            gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
            gl.glTranslatef(0, 0, +defs.cubesize + space);
            drawYSlice(6);
            gl.glTranslatef(0, 0, -2 * (defs.cubesize + space));
            drawYSlice(4);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            gl.glRotatef(-rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawYSlice(5);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateYSlice(5);
            }
        } else if (sliceNum == 6) // y slice 6 (or 3rd stack) bottom
        {
            gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
            gl.glTranslatef(0, 0, -(defs.cubesize + space));
            drawYSlice(4);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            drawYSlice(5);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            gl.glRotatef(-rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawYSlice(6);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateYSlice(6);
            }
        }
        //  drawable.swapBuffers();
        gl.glPopMatrix();
    }  // end animateYSlice()

// animate slice 1,2 or 3 on Z axis
// 1 is back slice, 2 is middle slice, 3 is front slice
    private void animateZSlice(int sliceNum, GLAutoDrawable drawable) {
        gl = drawable.getGL();
        gl.glPushMatrix();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        if (sliceNum == 1) //z slice 1 back
        {
            gl.glTranslatef(0, 0, 0);
            drawZSlice(2);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            drawZSlice(3);
            gl.glTranslatef(0, 0, +-2 * (defs.cubesize + space));
            gl.glRotatef(rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawZSlice(1);
            if (rot >= 90 / speed) {  //update cube rot complete
                rubik.updateZSlice(1);
            }
        } else if (sliceNum == 2) // z slice 2 in middle
        {
            gl.glTranslatef(0, 0, -(defs.cubesize + space));
            drawZSlice(1);
            gl.glTranslatef(0, 0, (2 * (defs.cubesize + space)));
            drawZSlice(3);
            gl.glTranslatef(0, 0, -(defs.cubesize + space));  //back to center
            gl.glRotatef(rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawZSlice(2);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateZSlice(2);
            }
        } else if (sliceNum == 3) // 3rd z slice in front
        {
            gl.glTranslatef(0, 0, -(defs.cubesize + space));
            drawZSlice(1);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            drawZSlice(2);
            gl.glTranslatef(0, 0, +(defs.cubesize + space));
            gl.glRotatef((float) rot * speed * rubik.getRotMult(), 0.0f, 0.0f, 1.0f);
            drawZSlice(3);
            if (rot >= 90 / speed) // update cube
            {
                rubik.updateZSlice(3);
            }
        }
        //  drawable.swapBuffers();
        gl.glPopMatrix();
    }

    private void animateXCube(GLAutoDrawable drawable) // Animate whole cube on X
    {
        if (animateX == 0) {
            return;
        }
        gl = drawable.getGL();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        cubeRot = ++cubeRot % 360;
        gl.glRotatef(cubeRot, 1.0f, 0.0f, 0.0f);
    }

    private void animateYCube(GLAutoDrawable drawable) // Animate whole cube on Y
    {
        if (animateY == 0) {
            return;
        }
        gl = drawable.getGL();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        cubeRot = ++cubeRot % 360;
        gl.glRotatef(cubeRot, 0.0f, 1.0f, 0.0f);
    }

    private void animateZCube(GLAutoDrawable drawable) // Animate whole cube on Z
    {
        if (animateZ == 0) {
            return;
        }
        gl = drawable.getGL();
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        cubeRot = ++cubeRot % 360;
        gl.glRotatef(cubeRot, 0.0f, 0.0f, 1.0f);
    }

    /**
     * Setter animateSlice1.
     * @param animateSlice1
     */
    public void setAnimateSlice1(int animateSlice1) {
        this.animateSlice1 = animateSlice1;
    }

    /**
     * Setter animateSlice2.
     * @param animateSlice2
     */
    public void setAnimateSlice2(int animateSlice2) {
        this.animateSlice2 = animateSlice2;
    }

    /**
     * Setter animateSlice3
     * @param animateSlice3
     */
    public void setAnimateSlice3(int animateSlice3) {
        this.animateSlice3 = animateSlice3;
    }

    /**
     * Setter animate slice4.
     * @param animateSlice4
     */
    public void setAnimateSlice4(int animateSlice4) {
        this.animateSlice4 = animateSlice4;
    }

    /**
     * Setter animateSlice5.
     * @param animateSlice5
     */
    public void setAnimateSlice5(int animateSlice5) {
        this.animateSlice5 = animateSlice5;
    }

    /**
     * Setter animateSlice6.
     * @param animateSlice6
     */
    public void setAnimateSlice6(int animateSlice6) {
        this.animateSlice6 = animateSlice6;
    }

    /**
     * Setter animateslice7.
     * @param animateSlice7
     */
    public void setAnimateSlice7(int animateSlice7) {
        this.animateSlice7 = animateSlice7;
    }

    /**
     * Setter animateSlice8.
     * @param animateSlice8
     */
    public void setAnimateSlice8(int animateSlice8) {
        this.animateSlice8 = animateSlice8;
    }

    /**
     * Setter animateSlice9.
     * @param animateSlice9
     */
    public void setAnimateSlice9(int animateSlice9) {
        this.animateSlice9 = animateSlice9;
    }

    /**
     * Setter animate on X.
     * @param animateX
     */
    public void setAnimateX(int animateX) {
        this.animateX = animateX;
    }

    /**
     * Setter animate on Y.
     * @param animateY
     */
    public void setAnimateY(int animateY) {
        this.animateY = animateY;
    }

    /**
     * Setter animate on Z.
     * @param animateZ
     */
    public void setAnimateZ(int animateZ) {
        this.animateZ = animateZ;
    }

    /**
     * Setter view.
     * @param view
     */
    public void setView(int view) {
        this.view = view;
    }

    /**
     * Getter view.
     * @return view
     */
    public int getView() {
        return view;
    }

    /**
     * Setter perspective view.
     * @param perspView
     */
    public void setPerspView(int perspView) {
        this.perspView = perspView;
    }

    /**
     * Setter updateOnly no animation.
     * @param updateOnly
     */
    public void setUpdateOnly(int updateOnly) {
        this.updateOnly = updateOnly;
    }

    /**
     * Getter eyeX.
     * @return eyeX
     */
    public float getEyeX() {
        return eyeX;
    }

    /**
     * Getter eyeY.
     * @return eyeY.
     */
    public float getEyeY() {
        return eyeY;
    }

    /**
     * Getter eyeZ.
     * @return eyeZ.
     */
    public float getEyeZ() {
        return eyeZ;
    }

    /**
     * Setter EyeX.
     * @param eyeX
     */
    public void setEyeX(float eyeX) {
        this.eyeX = eyeX;
    }

    /**
     * Setter eyeY.
     * @param eyeY
     */
    public void setEyeY(float eyeY) {
        this.eyeY = eyeY;
    }

    /**
     * Setter eyeZ.
     * @param eyeZ
     */
    public void setEyeZ(float eyeZ) {
        this.eyeZ = eyeZ;
    }

    /**
     * Setter speed.
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Set current player number and get the cube.
     * @param player
     */
    public void setPlayer(Player player) {
        playerNumber = player.getNumber();
        rubik = player.getMyCube();
    }

    /**
     * Get current player number.
     * @return player num.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
} // end CubeMain()

