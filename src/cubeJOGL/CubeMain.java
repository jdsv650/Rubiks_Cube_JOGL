/* James Donner
 * CubeMain.java
 * Last modified: 3/23/11
 * Contains and manipulates the 27 cubes of a standard Rubik's cube
 */
package cubeJOGL;

/**
 * Class creates and manipulates 27 cubes of a Rubik's cube.
 */
public class CubeMain implements CubeBuilder {

    private int rotMult = 1;  // + rotation multiplier
    private Cube[] cubes = new Cube[27]; //the cubes

    //initial implementation was array based. creating cube objects
    //may not be as efficient.
    //Front[][0] Right[][1] Down[][2]  Up[][3]    Back[][4]  Left[][5]
    //int[][] cubes = new int[27][6]; //27 cubes (0..26) with 6 face colors (0..5)
    /**
     * Constructor creates 27 new cubes.
     */
    CubeMain() {
        for (int i = 0; i < 27; i++) {
            cubes[i] = new Cube();
        }
        // buildCube();
    }

    /**
     * Getter rotation multiplier
     * @return the multiplier
     */
    public int getRotMult() {
        return rotMult;
    }

    /**
     * Setter rotation multiplier.
     * @param rotMult the new value != 0.
     */
    public void setRotMult(int rotMult) {
        if(rotMult == 0)
            return;
        this.rotMult = rotMult;
    }

    /**
     * Getter for the the cubes
     * @return an array containing the cubes
     */
    public Cube[] getCube() {
        return cubes;
    }

    //   public void setCube(Cube c, int index) {
    //      cubes[index] = c;
    //  }
    /**
     * Set cube colors to solved state.
     */
    public void buildCube() //color cube at start/completed state
    {
        //cube 1
        cubes[0].setF(0);  //F is black
        cubes[0].setR(0);  //R is black
        cubes[0].setD(0);  //D is black
        cubes[0].setU(4);  //U is red
        cubes[0].setB(5);  //B is yellow
        cubes[0].setL(1);  //L is blue
        //cube 2
        cubes[1].setF(0);  //F is black
        cubes[1].setR(0);  //R is black
        cubes[1].setD(0);  //D is black
        cubes[1].setU(4);  //U is red
        cubes[1].setB(5);  //B is yellow
        cubes[1].setL(0);  //L is black
        //cube 3
        cubes[2].setF(0);  //F is black
        cubes[2].setR(2);  //R is green
        cubes[2].setD(0);  //D is black
        cubes[2].setU(4);  //U is red
        cubes[2].setB(5);  //B is yellow
        cubes[2].setL(0);  //L is black
        //cube 4
        cubes[3].setF(0);  //F is black
        cubes[3].setR(0);  //R is black
        cubes[3].setD(0);  //D is black
        cubes[3].setU(0);  //U is black
        cubes[3].setB(5);  //B is yellow
        cubes[3].setL(1);  //L is blue
        //cube 5
        cubes[4].setF(0);  //F is black
        cubes[4].setR(0);  //R is black
        cubes[4].setD(0);  //D is black
        cubes[4].setU(0);  //U is black
        cubes[4].setB(5);  //B is yellow
        cubes[4].setL(0);  //L is black
        //cube 6
        cubes[5].setF(0);  //F is black
        cubes[5].setR(2);  //R is green
        cubes[5].setD(0);  //D is black
        cubes[5].setU(0);  //U is black
        cubes[5].setB(5);  //B is yellow
        cubes[5].setL(0);  //L is black
        //cube 7
        cubes[6].setF(0);  //F is black
        cubes[6].setR(0);  //R is black
        cubes[6].setD(3);  //D is orange
        cubes[6].setU(0);  //U is black
        cubes[6].setB(5);  //B is yellow
        cubes[6].setL(1);  //L is blue
        //cube 8
        cubes[7].setF(0);  //F is black
        cubes[7].setR(0);  //R is black
        cubes[7].setD(3);  //D is orange
        cubes[7].setU(0);  //U is black
        cubes[7].setB(5);  //B is yellow
        cubes[7].setL(0);  //L is black
        //cube 9
        cubes[8].setF(0);  //F is black
        cubes[8].setR(2);  //R is green
        cubes[8].setD(3);  //D is orange
        cubes[8].setU(0);  //U is black
        cubes[8].setB(5);  //B is yellow
        cubes[8].setL(0);  //L is black
        //cube 10
        cubes[9].setF(0);  //F is black
        cubes[9].setR(0);  //R is black
        cubes[9].setD(0);  //D is black
        cubes[9].setU(4);  //U is red
        cubes[9].setB(0);  //B is black
        cubes[9].setL(1);  //L is blue
        //cube 11
        cubes[10].setAll(0, 0, 0, 4, 0, 0);
        //F is black R is black D is black U is red B is black L is black
        //cube 12
        cubes[11].setAll(0, 2, 0, 4, 0, 0);
        //cube 13
        cubes[12].setAll(0, 0, 0, 0, 0, 1);
        //cube 14
        cubes[13].setAll(0, 0, 0, 0, 0, 0);
        //cube 15
        cubes[14].setAll(0, 2, 0, 0, 0, 0);
        //cube 16
        cubes[15].setAll(0, 0, 3, 0, 0, 1);
        //cube 17
        cubes[16].setAll(0, 0, 3, 0, 0, 0);
        //cube 18
        cubes[17].setAll(0, 2, 3, 0, 0, 0);
        //cube 19
        cubes[18].setAll(6, 0, 0, 4, 0, 1);
        //cube 20
        cubes[19].setAll(6, 0, 0, 4, 0, 0);
        //cube 21
        cubes[20].setAll(6, 2, 0, 4, 0, 0);
        //cube 22
        cubes[21].setAll(6, 0, 0, 0, 0, 1);
        //cube 23
        cubes[22].setAll(6, 0, 0, 0, 0, 0);
        //cube 24
        cubes[23].setAll(6, 2, 0, 0, 0, 0);
        //cube 25
        cubes[24].setAll(6, 0, 3, 0, 0, 1);
        //cube 26
        cubes[25].setAll(6, 0, 3, 0, 0, 0);
        //cube 27
        cubes[26].setAll(6, 2, 3, 0, 0, 0);
    } // end buildCube()

    /**
     * Update X slice base on direction of rotation multiplier.
     * @param snum the X slice num (7,8, or 9)
     */
    public void updateXSlice(int snum) {  // Finished animating a slice so update the cube
        int temp, temp2, temp3, temp4, temp5, temp6, temp7, temp8;
        //x slice7  0 ,3 ,6 ,9 ,12 ,15, 18 ,21 ,24   rotMult + CW, rotMult - CCW
        //x slice8  1 ,4 ,7 ,10 ,13 ,16 ,19 ,22 ,25
        //x slice9  2 ,5 ,8 ,11 ,14 ,17 ,20, 23 ,26
        // cube 1
        // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        if (snum == 7 && rotMult > 0) {   // rotate Slice7 CCW
            //cube 1
            temp = cubes[0].getB();  //B
            temp2 = cubes[0].getL();  //L
            temp3 = cubes[0].getU();  //U
            //cube 1
            cubes[0].setB(cubes[6].getD());  //B
            cubes[0].setL(cubes[6].getL());  //L
            cubes[0].setU(cubes[6].getB());  //U
            //cube 4
            temp6 = cubes[3].getB(); //B
            temp7 = cubes[3].getL(); //L
            cubes[3].setB(cubes[15].getD());  //B
            cubes[3].setL(cubes[15].getL());  //L
            //cube 7
            cubes[6].setL(cubes[24].getL());  //L
            cubes[6].setB(cubes[24].getD());  //B
            cubes[6].setD(cubes[24].getF());  //D
            //cube 10
            temp4 = cubes[9].getL();  //L
            temp5 = cubes[9].getU(); //U
            cubes[9].setL(temp7);  //L
            cubes[9].setU(temp6);  //U
            //cube 13
            //no change
            //cube 16
            cubes[15].setL(cubes[21].getL());  //L
            cubes[15].setD(cubes[21].getF());  //D
            //cube25
            cubes[24].setF(cubes[18].getU()); //F
            cubes[24].setL(cubes[18].getL()); //L
            cubes[24].setD(cubes[18].getF()); //D
            //cube 19
            cubes[18].setF(temp3);  //F
            cubes[18].setL(temp2);  //L
            cubes[18].setU(temp);   //U
            //cube 22
            cubes[21].setF(temp5); //F
            cubes[21].setL(temp4);  //L
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        else if (snum == 7 && rotMult < 0) {   // rotate Slice7 CW
            //cube 1
            temp = cubes[0].getB();  //B
            temp2 = cubes[0].getL(); //L
            temp3 = cubes[0].getU(); //U
            cubes[0].setB(cubes[18].getU());  //B
            cubes[0].setL(cubes[18].getL());  //L
            cubes[0].setU(cubes[18].getF());  //U
            //cube 4
            temp4 = cubes[3].getB();  //
            temp5 = cubes[3].getL();  //L
            cubes[3].setB(cubes[9].getU());  //B
            cubes[3].setL(cubes[9].getL());  //L
            //cube 10
            cubes[9].setL(cubes[21].getL());  //L
            cubes[9].setU(cubes[21].getF());  //U
            //cube 13
            //no change
            //cube 19
            cubes[18].setF(cubes[24].getD());  //F
            cubes[18].setL(cubes[24].getL());  //L
            cubes[18].setU(cubes[24].getF());  //U
            //cube 22
            cubes[21].setF(cubes[15].getD()); //F
            cubes[21].setL(cubes[15].getL()); //L
            //cube25
            cubes[24].setF(cubes[6].getD()); //F
            cubes[24].setL(cubes[6].getL()); //L
            cubes[24].setD(cubes[6].getB()); //D
            //cube 7
            cubes[6].setL(temp2);  //L
            cubes[6].setB(temp3);  //B
            cubes[6].setD(temp);  //D
            //cube 16
            cubes[15].setL(temp5);  //L
            cubes[15].setD(temp4);  //D
        }
        // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        if (snum == 8 && rotMult > 0) {  // rotate Slice8 CCW
            //x slice1  0 ,3 ,6 ,9 , 12 ,15, 18 ,21 ,24
            //x slice2  1 ,4 ,7 ,10 ,13 ,16 ,19 ,22 ,25
            //cube 2
            temp = cubes[1].getB();  //B
            temp3 = cubes[1].getU(); //U
            cubes[1].setB(cubes[7].getD());  //B
            cubes[1].setU(cubes[7].getB());  //U
            //cube 5
            temp6 = cubes[4].getB();
            cubes[4].setB(cubes[16].getD());  //B
            //cube 8
            cubes[7].setB(cubes[25].getD());  //B
            cubes[7].setD(cubes[25].getF());  //D
            //cube 11
            temp5 = cubes[10].getU();  //U
            cubes[10].setU(temp6);  //U
            //cube 14
            //no change
            //cube 17
            cubes[16].setD(cubes[22].getF());  //D
            //cube26
            cubes[25].setF(cubes[19].getU()); //F
            cubes[25].setD(cubes[19].getF()); //D
            //cube 20
            cubes[19].setF(temp3);  //F
            cubes[19].setU(temp);   //U
            //cube 23
            cubes[22].setF(temp5); //F
        } else if (snum == 8 && rotMult < 0) {  // rotate Slice8 CW
            //x slice1  0 ,3 ,6 ,9 , 12 ,15, 18 ,21 ,24
            //x slice2  1 ,4 ,7 ,10 ,13 ,16 ,19 ,22 ,25
            //Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
            //cube 2
            temp = cubes[1].getB(); //B
            temp3 = cubes[1].getU(); //U
            cubes[1].setB(cubes[19].getU());  //B
            cubes[1].setU(cubes[19].getF());  //U
            //cube 5
            temp4 = cubes[4].getB();
            cubes[4].setB(cubes[10].getU());  //B
            //cube 11
            cubes[10].setU(cubes[22].getF());  //U
            //cube 14
            //no change
            //cube 20
            cubes[19].setF(cubes[25].getD());  //F
            cubes[19].setU(cubes[25].getF());  //U
            //cube 23
            cubes[22].setF(cubes[16].getD()); //F
            //cube26
            cubes[25].setF(cubes[7].getD()); //F
            cubes[25].setD(cubes[7].getB()); //D
            //cube 8
            cubes[7].setB(temp3);  //B
            cubes[7].setD(temp);  //D
            //cube 17
            cubes[16].setD(temp4);  //D
        }
        // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        if (snum == 9 && rotMult > 0) {   // rotate Slice9 CCW
            //x slice1  0 ,3 ,6 ,9 ,12 ,15, 18 ,21 ,24   rotMult + CW, rotMult - CCW
            //x slice3  2 ,5 ,8 ,11 ,14 ,17 ,20, 23 ,26
            //cube 3
            temp = cubes[2].getB(); //B
            temp2 = cubes[2].getR(); //R
            temp3 = cubes[2].getU(); //U
            cubes[2].setB(cubes[8].getD());  //B
            cubes[2].setR(cubes[8].getR());  //R
            cubes[2].setU(cubes[8].getB());  //U
            //cube 6
            temp6 = cubes[5].getB(); //B
            temp7 = cubes[5].getR(); //R
            cubes[5].setB(cubes[17].getD());  //B
            cubes[5].setR(cubes[17].getR());  //R

            //cube 9
            cubes[8].setR(cubes[26].getR());  //R
            cubes[8].setB(cubes[26].getD());  //B
            cubes[8].setD(cubes[26].getF());  //D
            //cube 12
            temp4 = cubes[11].getR();  //R
            temp5 = cubes[11].getU();  //U
            cubes[11].setR(temp7);  //R
            cubes[11].setU(temp6);  //U
            //cube 15
            //no change
            //cube 27
            cubes[26].setR(cubes[20].getR()); //R
            cubes[26].setD(cubes[20].getF()); //D
            cubes[26].setF(cubes[20].getU()); //F
            //cube 21
            cubes[20].setF(temp3);  //F
            cubes[20].setR(temp2);  //R
            cubes[20].setU(temp);   //U
            //cube18
            cubes[17].setR(cubes[23].getR()); //R
            cubes[17].setD(cubes[23].getF()); //D
            //cube 24
            cubes[23].setF(temp5); //F
            cubes[23].setR(temp4); //R
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        else if (snum == 9 && rotMult < 0) {   // rotate Slice9 CW
            //x slice1  0 ,3 ,6 ,9  ,12 ,15, 18 ,21 ,24
            //x slice3  2 ,5 ,8 ,11 ,14 ,17 ,20, 23 ,26
            //cube 3
            temp = cubes[2].getB(); //B
            temp2 = cubes[2].getR(); //R
            temp3 = cubes[2].getU(); //U
            cubes[2].setB(cubes[20].getU());  //B
            cubes[2].setR(cubes[20].getR());  //R
            cubes[2].setU(cubes[20].getF());  //U
            //cube 6
            temp4 = cubes[5].getB(); //B
            temp5 = cubes[5].getR(); //R
            cubes[5].setB(cubes[11].getU());  //B
            cubes[5].setR(cubes[11].getR());  //R
            //cube 12
            cubes[11].setR(cubes[23].getR());  //R
            cubes[11].setU(cubes[23].getF());  //U
            //cube 15
            //no change
            //cube 21
            cubes[20].setF(cubes[26].getD());  //F
            cubes[20].setR(cubes[26].getR());  //R
            cubes[20].setU(cubes[26].getF());  //U
            //cube 24
            cubes[23].setF(cubes[17].getD()); //F
            cubes[23].setR(cubes[17].getR()); //R
            //cube27
            cubes[26].setF(cubes[8].getD()); //F
            cubes[26].setR(cubes[8].getR()); //R
            cubes[26].setD(cubes[8].getB()); //D
            //cube 9
            cubes[8].setR(temp2);  //R
            cubes[8].setB(temp3);  //B
            cubes[8].setD(temp);  //D
            //cube 18
            cubes[17].setR(temp5);  //R
            cubes[17].setD(temp4);  //D
        }
    }

    /**
     * Update Y slice base on direction of rotation multiplier.
     * @param snum the Y slice num (4,5, or 6)
     */
    public void updateYSlice(int snum) {  // Finished animating a slice so update the cube
        int temp, temp2, temp3, temp4, temp5, temp6, temp7, temp8;
        // stack1/snum4  = 0,1,2,9,10,11,18,19,20      rotMult + CW, rotMult - CCW
        // stack2/snum5  = 3,4,5,12,13,14,21,22,23
        // stack3/snum6  = 6,7,8,15,16,17,24,25,26
        // cube 1
        // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        if (snum == 4 && rotMult > 0) {   // rotate Slice4/Stack1 CCW
            //cube 1
            temp = cubes[0].getB();
            temp2 = cubes[0].getL();
            temp3 = cubes[0].getU();
            cubes[0].setB(cubes[2].getR());  //B
            cubes[0].setL(cubes[2].getB());  //L
            cubes[0].setU(cubes[2].getU());  //U
            //cube 2
            temp6 = cubes[1].getB();
            temp7 = cubes[1].getU();
            cubes[1].setB(cubes[11].getR());  //B
            cubes[1].setU(cubes[11].getU());  //U
            //cube 3
            cubes[2].setR(cubes[20].getF());  //R
            cubes[2].setB(cubes[20].getR());  //B
            cubes[2].setU(cubes[20].getU());  //U
            //cube 10
            temp4 = cubes[9].getL();
            temp5 = cubes[9].getU();
            cubes[9].setL(temp6);  //L
            cubes[9].setU(temp7);  //U
            //cube 11
            //no change
            //cube 12
            cubes[11].setR(cubes[19].getF());  //R
            cubes[11].setU(cubes[19].getU());  //U
            //cube 21
            cubes[20].setF(cubes[18].getL());  //F
            cubes[20].setR(cubes[18].getF());  //R
            cubes[20].setU(cubes[18].getU());  //U
            //cube 19
            cubes[18].setF(temp2); //F
            cubes[18].setL(temp);  //L
            cubes[18].setU(temp3); //U
            //cube 20
            cubes[19].setF(temp4); //F
            cubes[19].setU(temp5); //U
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        else if (snum == 4 && rotMult < 0) {   // rotate Slice4 CW
            //cube 1
            temp = cubes[0].getB();
            temp2 = cubes[0].getL();
            temp3 = cubes[0].getU();
            cubes[0].setB(cubes[18].getL());  //B
            cubes[0].setL(cubes[18].getF());  //L
            cubes[0].setU(cubes[18].getU());  //U
            //cube 2
            temp4 = cubes[1].getB();
            temp5 = cubes[1].getU();
            cubes[1].setB(cubes[9].getL());  //B
            cubes[1].setU(cubes[9].getU());  //U
            //cube 10
            cubes[9].setL(cubes[19].getF());  //L
            cubes[9].setU(cubes[19].getU());  //U
            //cube 11
            //no change
            //cube 19
            cubes[18].setF(cubes[20].getR());  //F
            cubes[18].setL(cubes[20].getF());  //L
            cubes[18].setU(cubes[20].getU());  //U
            //cube 20
            cubes[19].setF(cubes[11].getR());  //F
            cubes[19].setU(cubes[11].getU());  //U
            //cube 21
            cubes[20].setF(cubes[2].getR());  //F
            cubes[20].setR(cubes[2].getB());  //R
            cubes[20].setU(cubes[2].getU());  //U
            //cube 3
            cubes[2].setR(temp);   //R
            cubes[2].setB(temp2);  //B
            cubes[2].setU(temp3);  //U
            //cube 12
            cubes[11].setR(temp4);  //R
            cubes[11].setU(temp5);  //U
        }

        if (snum == 5 && rotMult > 0) {  // rotate Slice5/Stack2 CCW
            // stack1/snum4  = 0,1,2,9, 10,11,18,19,20      rotMult + CW, rotMult - CCW
            // stack2/snum5  = 3,4,5,12,13,14,21,22,23
            //cube 4
            temp = cubes[3].getB();
            temp2 = cubes[3].getL();
            cubes[3].setB(cubes[5].getR());  //B
            cubes[3].setL(cubes[5].getB());  //L
            //cube 5
            temp6 = cubes[4].getB();
            cubes[4].setB(cubes[14].getR());  //B
            //cube 6
            cubes[5].setR(cubes[23].getF());  //R
            cubes[5].setB(cubes[23].getR());  //B
            //cube 13
            temp4 = cubes[12].getL();
            cubes[12].setL(temp6);  //L
            //cube 14
            //no change
            //cube 15
            cubes[14].setR(cubes[22].getF());  //R
            //cube 24
            cubes[23].setF(cubes[21].getL());  //F
            cubes[23].setR(cubes[21].getF());  //R
            //cube 22
            cubes[21].setF(temp2); //F
            cubes[21].setL(temp);  //L
            //cube 23
            cubes[22].setF(temp4); //F
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        // stack1/snum4  = 0,1,2,9, 10,11,18,19,20
        // stack2/snum5  = 3,4,5,12,13,14,21,22,23
        else if (snum == 5 && rotMult < 0) {  // rotate Slice5/Stack2 CW
            //cube 1
            temp = cubes[3].getB();
            temp2 = cubes[3].getL();
            cubes[3].setB(cubes[21].getL());  //B
            cubes[3].setL(cubes[21].getF());  //L
            //cube 2
            temp4 = cubes[4].getB();
            cubes[4].setB(cubes[12].getL());  //B
            //cube 10
            cubes[12].setL(cubes[22].getF());  //L
            //cube 11
            //no change
            //cube 19
            cubes[21].setF(cubes[23].getR());  //F
            cubes[21].setL(cubes[23].getF());  //L
            //cube 20
            cubes[22].setF(cubes[14].getR());  //F
            //cube 21
            cubes[23].setF(cubes[5].getR());  //F
            cubes[23].setR(cubes[5].getB());  //R
            //cube 3
            cubes[5].setR(temp);   //R
            cubes[5].setB(temp2);  //B
            //cube 12
            cubes[14].setR(temp4);  //R
        }
        // stack1/snum4  = 0,1,2,9, 10,11,18,19,20
        // stack3/snum6  = 6,7,8,15,16,17,24,25,26
        // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        if (snum == 6 && rotMult > 0) {   // rotate Slice6/Stack3 CCW
            //cube 7
            temp = cubes[6].getB();
            temp2 = cubes[6].getL();
            temp3 = cubes[6].getD();
            cubes[6].setB(cubes[8].getR());  //B
            cubes[6].setL(cubes[8].getB());  //L
            cubes[6].setD(cubes[8].getD());  //D
            //cube 8
            temp4 = cubes[7].getB();
            temp5 = cubes[7].getD();
            cubes[7].setB(cubes[17].getR());  //B
            cubes[7].setD(cubes[17].getD());  //D
            //cube 9
            cubes[8].setR(cubes[26].getF());  //R
            cubes[8].setB(cubes[26].getR());  //B
            cubes[8].setD(cubes[26].getD());  //D
            //cube 16
            temp6 = cubes[15].getL();
            temp7 = cubes[15].getD();
            cubes[15].setL(temp4);  //L
            cubes[15].setD(temp5); //D
            //cube 17
            //no change
            //cube 18
            cubes[17].setR(cubes[25].getF());  //R
            cubes[17].setD(cubes[25].getD());  //D
            //cube 27
            cubes[26].setF(cubes[24].getL());  //F
            cubes[26].setR(cubes[24].getF());  //R
            cubes[26].setD(cubes[24].getD());  //D
            //cube 25
            cubes[24].setF(temp2); //F
            cubes[24].setL(temp);  //L
            cubes[24].setD(temp3); //D
            //cube 26
            cubes[25].setF(temp6);  //F
            cubes[25].setD(temp7);  //D
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        // stack1/snum4  = 0,1,2,9, 10,11,18,19,20
        // stack3/snum6  = 6,7,8,15,16,17,24,25,26
        else if (snum == 6 && rotMult < 0) {   // rotate Slice6/Stack3 CW
            //cube 1
            temp = cubes[6].getB();
            temp2 = cubes[6].getL();
            temp3 = cubes[6].getD();
            cubes[6].setB(cubes[24].getL());  //B
            cubes[6].setL(cubes[24].getF());  //L
            cubes[6].setD(cubes[24].getD());  //D
            //cube 2
            temp4 = cubes[7].getB();
            temp5 = cubes[7].getD();  //D
            cubes[7].setB(cubes[15].getL());  //B
            cubes[7].setD(cubes[15].getD());  //D
            //cube 10
            cubes[15].setL(cubes[25].getF());  //L
            cubes[15].setD(cubes[25].getD());  //D
            //cube 11
            //no change
            //cube 19
            cubes[24].setF(cubes[26].getR());  //F
            cubes[24].setL(cubes[26].getF());  //L
            cubes[24].setD(cubes[26].getD());  //D
            //cube 20
            cubes[25].setF(cubes[17].getR());  //F
            cubes[25].setD(cubes[17].getD());  //D
            //cube 21
            cubes[26].setF(cubes[8].getR());  //F
            cubes[26].setR(cubes[8].getB());  //R
            cubes[26].setD(cubes[8].getD());  //D
            //cube 3
            cubes[8].setR(temp);   //R
            cubes[8].setB(temp2);  //B
            cubes[8].setD(temp3);  //D
            //cube 12
            cubes[17].setR(temp4);  //R
            cubes[17].setD(temp5);  //D
        }
    }  // end update y slice

    /**
     * Update Z slice base on direction of rotation multiplier.
     * @param snum the Z slice num (1,2, or 3)
     */
    public void updateZSlice(int snum) {	// Finished animating a slice so update the cube
        int temp1_3, temp1_4, temp0_3, temp0_4, temp0_5, temp3_4, temp3_5, temp5_1, temp5_4;
        int temp, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8;
        // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        // slice1 0-8,   slice2 9-17,  slice3  18-26,  rotMult + CW, rotMult - CCW
        // cube 1
        if (snum == 1 && rotMult > 0) {   // rotate Slice1 CCW
            temp0_3 = cubes[0].getU();
            temp0_4 = cubes[0].getB();
            temp0_5 = cubes[0].getL();
            cubes[0].setU(cubes[2].getR());  //U
            cubes[0].setL(cubes[2].getU());  //L
            cubes[0].setB(cubes[2].getB());  //B
            //cube 2
            temp1_3 = cubes[1].getU();
            temp1_4 = cubes[1].getB();
            cubes[1].setU(cubes[5].getR());  //U
            cubes[1].setB(cubes[5].getB());  //B
            //cube 3
            cubes[2].setR(cubes[8].getD());  //R
            cubes[2].setU(cubes[8].getR());  //U
            cubes[2].setB(cubes[8].getB());  //B
            //cube 4
            temp3_5 = cubes[3].getL();
            temp3_4 = cubes[3].getB();
            cubes[3].setL(temp1_3);  //L
            cubes[3].setB(temp1_4);  //B
            //cube 5 no change
            //cube 6
            cubes[5].setR(cubes[7].getD());  //R
            cubes[5].setB(cubes[7].getB());  //B
            //cube 9
            cubes[8].setR(cubes[6].getD());  //R
            cubes[8].setD(cubes[6].getL());  //D
            cubes[8].setB(cubes[6].getB());  //B
            //cube 7
            cubes[6].setD(temp0_5);  //D is orange
            cubes[6].setL(temp0_3);  //L is blue
            cubes[6].setB(temp0_4);  //B
            // cube 8
            cubes[7].setD(temp3_5);  //D
            cubes[7].setB(temp3_4);  //B
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        else if (snum == 1 && rotMult < 0) {   // rotate Slice1 CW
            temp0_3 = cubes[0].getU();
            temp0_4 = cubes[0].getB();
            temp0_5 = cubes[0].getL();
            cubes[0].setU(cubes[6].getL());  //U
            cubes[0].setL(cubes[6].getD());  //L
            cubes[0].setB(cubes[6].getB());  //B
            temp1_3 = cubes[1].getU();
            temp1_4 = cubes[1].getB();
            //cube 2
            cubes[1].setU(cubes[3].getL());  //U
            cubes[1].setB(cubes[3].getB());  //B
            //cube 4
            cubes[3].setL(cubes[7].getD());  //L
            cubes[3].setB(cubes[7].getB());  //B
            //cube 5 no change
            //cube 6
            temp5_1 = cubes[5].getR();
            temp5_4 = cubes[5].getB();
            cubes[5].setR(temp1_3);  //R
            cubes[5].setB(temp1_4);  //B
            //cube 7
            cubes[6].setD(cubes[8].getR());  //D
            cubes[6].setL(cubes[8].getD());  //L
            cubes[6].setB(cubes[8].getB());  //B
            //cube 8
            cubes[7].setD(temp5_1);  //D
            cubes[7].setB(temp5_4);  //B
            //cube 9
            cubes[8].setR(cubes[2].getU());  //R
            cubes[8].setD(cubes[2].getR());  //D
            cubes[8].setB(cubes[2].getB());  //B
            //cube 3
            cubes[2].setR(temp0_3);  //R
            cubes[2].setU(temp0_5);  //U
            cubes[2].setB(temp0_4);  //B
        }
        if (snum == 2 && rotMult > 0) {   // rotate Slice2 CCW
            temp2 = cubes[9].getU();   //no front or back
            temp3 = cubes[9].getL();
            //cube 10
            cubes[9].setU(cubes[11].getR());  //U
            cubes[9].setL(cubes[11].getU());  //L
            //cube 11
            temp = cubes[10].getU();
            cubes[10].setU(cubes[14].getR());  //U
            //cube 12
            cubes[11].setR(cubes[17].getD());  //R
            cubes[11].setU(cubes[17].getR());  //U
            //cube 13
            temp4 = cubes[12].getL();
            cubes[12].setL(temp);  //L
            //cube 14 no change
            //cube 15
            cubes[14].setR(cubes[16].getD());  //R
            //cube 18
            cubes[17].setR(cubes[15].getD());  //R
            cubes[17].setD(cubes[15].getL());  //D
            //cube 16
            cubes[15].setD(temp3);  //D
            cubes[15].setL(temp2);  //L
            //cube 17
            cubes[16].setD(temp4);  //D
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        else if (snum == 2 && rotMult < 0) {   // rotate Slice2 CW
            temp2 = cubes[9].getU();
            temp3 = cubes[9].getL();
            cubes[9].setU(cubes[15].getL());  //U is red
            cubes[9].setL(cubes[15].getD());  //L is blue
            temp = cubes[10].getU();
            //cube 11
            cubes[10].setU(cubes[12].getL());  //U is red
            //cube 13
            cubes[12].setL(cubes[16].getD());  //L is blue
            //cube 14 no change
            //cube 15
            temp4 = cubes[14].getR();
            cubes[14].setR(temp);  //R is green
            //cube 16
            cubes[15].setD(cubes[17].getR());  //D is orange
            cubes[15].setL(cubes[17].getD());  //L is blue
            //cube 17
            cubes[16].setD(temp4);  //D is orange
            //cube 18
            cubes[17].setR(cubes[11].getU());  //R is green
            cubes[17].setD(cubes[11].getR());  //D is orange
            //cube 12
            cubes[11].setR(temp2);  //R is green
            cubes[11].setU(temp3);  //U is red
        }

        if (snum == 3 && rotMult > 0) {   // rotate Slice3 CCW
            temp2 = cubes[18].getU();
            temp3 = cubes[18].getL();
            temp4 = cubes[18].getF();
            //cube 19
            cubes[18].setU(cubes[20].getR());  //U
            cubes[18].setL(cubes[20].getU());  //L
            cubes[18].setF(cubes[20].getF());  //F
            //cube 20
            temp7 = cubes[19].getU();
            temp8 = cubes[19].getF();
            cubes[19].setU(cubes[23].getR());  //U
            cubes[19].setF(cubes[23].getF());  //F
            //cube 21
            cubes[20].setR(cubes[26].getD());  //R
            cubes[20].setU(cubes[26].getR());  //U
            cubes[20].setF(cubes[26].getF());  //F
            //cube 22
            temp5 = cubes[21].getL();
            temp6 = cubes[21].getF();
            cubes[21].setL(temp7); //L
            cubes[21].setF(temp8); //F
            //cube 23 no change
            //cube 24
            cubes[23].setR(cubes[25].getD());  //R
            cubes[23].setF(cubes[25].getF());  //F
            //cube 27
            cubes[26].setR(cubes[24].getD());  //R
            cubes[26].setD(cubes[24].getL());  //D
            cubes[26].setF(cubes[24].getF());  //F
            //cube 25
            cubes[24].setD(temp3);  //D
            cubes[24].setL(temp2);  //L
            cubes[24].setF(temp4);  //F
            //cube 26
            cubes[25].setD(temp5);  //D
            cubes[25].setF(temp6);  //F
        } // Front[0] Right[1] Down[2]  Up[3]    Back[4]  Left[5]
        else if (snum == 3 && rotMult < 0) {   // rotate Slice2 CW
            temp2 = cubes[18].getU();
            temp3 = cubes[18].getL();
            temp4 = cubes[18].getF();
            cubes[18].setU(cubes[24].getL()); //U
            cubes[18].setL(cubes[24].getD()); //L
            cubes[18].setF(cubes[24].getF()); //F
            temp7 = cubes[19].getU();
            temp8 = cubes[19].getF();
            //cube 20
            cubes[19].setU(cubes[21].getL());  //U
            cubes[19].setF(cubes[21].getF());  //F
            //cube 22
            cubes[21].setL(cubes[25].getD());  //L
            cubes[21].setF(cubes[25].getF());  //F
            //cube 23 no change
            //cube 24
            temp5 = cubes[23].getR();
            temp6 = cubes[23].getF();
            cubes[23].setR(temp7);  //R
            cubes[23].setF(temp8);  //F
            //cube 25
            cubes[24].setD(cubes[26].getR());  //D
            cubes[24].setL(cubes[26].getD());  //L
            cubes[24].setF(cubes[26].getF());  //F
            //cube 26
            cubes[25].setD(temp5); //D
            cubes[25].setF(temp6); //F
            //cube 27
            cubes[26].setR(cubes[20].getU());  //R
            cubes[26].setD(cubes[20].getR());  //D
            cubes[26].setF(cubes[20].getF());  //F
            //cube 21
            cubes[20].setR(temp2);  //R
            cubes[20].setU(temp3);  //U
            cubes[20].setF(temp4);  //F
        }
    }  // end updateZSlice()
}
