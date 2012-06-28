/* James Donner
 * CubeFiler.java
 * Last modified: 2/13/11
 * Class to input and output player(cube) info to/from a file
 */
package cubeJOGL;

import java.io.*;

/**
 * Class inputs/outputs player info to a file.
 */
public class CubeFiler {

    private Player defaultUser; // the default user
    private CubeBuilder theCube;   // the cube to write out
    private File myFile;        // file to store cube info

    /**
     * Constructs a new CubeWriter to output cube info. 
     */
    CubeFiler() {
        char[] newPass = new char[]{'0', '0', '0', '0'};
        theCube = new CubeMain();
        myFile = new File("cube_save_file.txt"); // file in CWD
        try {
            if (!myFile.exists()) {
                if (myFile.createNewFile()) {
                    System.out.println("New save file created");
                    defaultUser = new Player(1, "Default User", theCube, newPass);
                    defaultUser.getMyCube().buildCube();
                    outputPlayer(defaultUser);
                    
                } else {
                    System.out.println("Error couldn't create save file");
                }
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("Unrecoverable error creating new save file");
            System.exit(0);
        }
    }

    /**
     * Writes all player info to a file.
     * @param pList The list of players to write out.
     */
    public void outputAllPlayers(PlayerList pList) {
        PrintWriter out = null;  // out stream
        CubeBuilder cube;
        Cube[] cubes; //= new Cube[27];
        // int[][] cubes = new int[27][6];

        try {
            out = new PrintWriter(
                    new BufferedWriter(
                    new FileWriter(myFile, false)));

            for (int num = 0; num < pList.getPlayerListSize(); num++) {
                out.println(pList.getPlayerNoCred(num).getName());   // current User Name
                out.println(pList.getPlayerNoCred(num).getPass());
                cube = pList.getPlayerNoCred(num).getMyCube();
                cubes = cube.getCube();
                for (int i = 0; i < 27; i++) {   //[27][6]
                    out.print(cubes[i].getF() + "\t");
                    out.print(cubes[i].getR() + "\t");
                    out.print(cubes[i].getD() + "\t");
                    out.print(cubes[i].getU() + "\t");
                    out.print(cubes[i].getB() + "\t");
                    out.print(cubes[i].getL() + "\t");
                    out.println();
                }
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("Error writing data to file");
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * Writes player info to a file.
     * @param p The player to write out.
     */
    private void outputPlayer(Player p) { // throws FileNotFoundException, IOException {
        //[27][6]
        char[] temp = new char[20];
        CubeBuilder cube;
        Cube[] cubes = new Cube[27];

        PrintWriter out = null;  // out stream
        try {
            out = new PrintWriter(
                    new BufferedWriter(
                    new FileWriter(myFile, false)));

            out.println(p.getName());   // current User Name
            temp = p.getPass();
            out.println(temp);
            cube = p.getMyCube();
            cubes = cube.getCube();
            for (int i = 0; i < 27; i++) {
                out.print(cubes[i].getF() + "\t");
                out.print(cubes[i].getR() + "\t");
                out.print(cubes[i].getD() + "\t");
                out.print(cubes[i].getU() + "\t");
                out.print(cubes[i].getB() + "\t");
                out.print(cubes[i].getL() + "\t");
                //   for (int j = 0; j < 6; j++) {
                //       out.print(p.getMyCube().getCube().getCubeArrray()[i][j] + "\t");
                //   }
                out.println();
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("Error writing data to file");
        } finally {
           if (out != null) {
                out.close();
            }
        }
    }  // outputPlayer

    /**
     * Reads in all players and builds a player list.
     * @return the player list.
     */
    public PlayerList inputAllPlayers() { // throws FileNotFoundException, IOException {
        BufferedReader in = null;  //in stream
        String line;  // the line to read in
        String[] splitLine;  // separate "\t" fields
        int number = 0, count = 0, passIndex = 0;
        // int[][] cubes = new int[27][6];
        String name = "", passStr = "";
        char[] pass = new char[20];
        char tempChar;
        Player p;
        CubeBuilder c;
        Cube[] cubes = new Cube[27];
        PlayerList pList = new PlayerList();

        try {
            in = new BufferedReader(
                    new FileReader(myFile));
            line = "";
            while (line != null) {
                c = new CubeMain();  // each player has a new cube
                line = in.readLine();
                if (line == null) {
                    break;
                }
                name = line;
                passIndex = 0;
                passStr = in.readLine();  // get password
                for (int j = 0; j < passStr.length(); j++) {
                    pass[passIndex++] = passStr.charAt(j);
                    if (passIndex == 20) {
                        break;
                    }
                }
                count++;

                for (int i = 0; i < 27; i++) {
                    line = in.readLine();
                    splitLine = line.split("\t");
                    c.getCube()[i].setF(Integer.valueOf(splitLine[0]));
                    c.getCube()[i].setR(Integer.valueOf(splitLine[1]));
                    c.getCube()[i].setD(Integer.valueOf(splitLine[2]));
                    c.getCube()[i].setU(Integer.valueOf(splitLine[3]));
                    c.getCube()[i].setB(Integer.valueOf(splitLine[4]));
                    c.getCube()[i].setL(Integer.valueOf(splitLine[5]));
                }
                p = new Player(count, name, c, pass);
                pList.addPlayer(p);
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            System.out.println("Error reading data from file");
        }
        return (pList);
    }  // inputPlayer
}
