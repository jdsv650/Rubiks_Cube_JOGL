/* James Donner
 * PlayerList.java
 * Last modified: 10/6/11
 * Add and remove players from a list of players
 */
package cubeJOGL;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates and maintains an ArrayList of game players.
 */
public class PlayerList {

    private ArrayList<Player> players;  // collection of players

    /**
     * Creates new array list to populate with players.
     */
    public PlayerList() {
        players = new ArrayList<Player>();  // create the collection
    }

    /**
     * Add a player to the list of players.
     * @param p the player to add.
     */
    public void addPlayer(Player p) {
        players.add(p);
    }

    /**
     * Delete player from collection at given index.
     * @param index index of player to delete.
     */
    public void deletePlayer(int index) {
        if (players.isEmpty()) {
            return;
        } else if (players.size() == 1) {
            System.out.println("Default user can not be deleted");
        } else if (index >= players.size()) {
            return;
        } else {
            players.remove(index);
        }
    }

    /**
     * Update player password from collection at given index.
     * @param index index of player to update.
     * @param pass the new password.
     * @precondition pass <= 20 chars
     */
    public void updatePlayerPass(int index, char[] pass) {
        Player p;
        if (players.isEmpty()) {
            System.out.println("No players to update");
        } else if (players.size() == 1) {
            System.out.println("Default user password can not be updated");
        } else if (index >= players.size()) {
            return;
        } else {
            p = players.get(index);
            p.setPass(pass);
        }
    }

    /**
     * Returns number of players in collection.
     * @return number of players.
     */
    public int getPlayerListSize() {
        return (players.size());
    }

    /**
     * Returns a player from collection at specified index (checks password).
     * @param index index of player.
     * @param pass the players password.
     * @return the player or null if pass is not a match.
     * @precondition pass <= 20 chars
     */
    public Player getPlayer(int index, char[] pass) {
        Player p;
        if (players.isEmpty() || index >= players.size()) {
            return null;
        }

        p = players.get(index);
        if (checkPlayerCred(p, pass)) {
            return p;
        }
        return null;
    }

    /**
     * Returns a player from collection at specified index.
     * @param index index of player.
     * @return the player.
     */
    public Player getPlayerNoCred(int index) {
        Player p;
        if (players.isEmpty() || index >= players.size()) {
            return null;
        }
        p = players.get(index);
        return p;
    }

    //Compare players password with pass. Returns true/false match.
    private boolean checkPlayerCred(Player p, char[] pass) {
        if (Arrays.equals(p.getPass(), pass)) {
            return true;
        }
        return false;
    }
}
