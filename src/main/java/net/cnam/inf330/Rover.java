package net.cnam.inf330;

import java.util.HashSet;
import java.util.Set;

/**
 * Class which represents a Rover deployed on the Mars exploration grid.
 */
public class Rover {

    private int id;
    private int x;
    private int y;
    private Orientation o;
    private Set<Position> listePos = new HashSet<>();

    /**
     * Create a Rover at a specified location and orientation on the grid.
     *
     * @param id The ID of the Rover
     * @param x  The initial position on the X axis of the grid
     * @param y  The initial position on the Y axis of the grid
     * @param o  The initial orientation of the Rover on the grid
     */
    public Rover(int id, int x, int y, Orientation o) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.o = o;
        this.listePos.add(new Position(x, y));
        // listePos Set which contains all the visited positions of the rover (unique)
    }

    /**
     * Encode a Rover in a String.
     *
     * @return The String representation of the Rover
     */
    @Override
    public String toString() {
        return this.id + ":(" + this.x + "," + this.y + "," + this.o + ")";
    }

    /**
     * Process a command sent by the MCC and move on the grid accordingly.
     *
     * @param roverCommand The command sent by the MCC
     */
    public void processCommand(RoverCommand roverCommand) {
        switch (roverCommand) {
            case L:
                rotateLeft();
                break;
            case R:
                rotateRight();
                break;
            case M:
                moveForward();
                break;
            default:
        }
    }

    /**
     * Rotate the Rover 90 degrees to the left without changing the current position.
     */
    public void rotateLeft() {
        switch (o) {
            case N:
                this.o = Orientation.W;
                break;
            case E:
                this.o = Orientation.N;
                break;
            case S:
                this.o = Orientation.E;
                break;
            case W:
                this.o = Orientation.S;
                break;
            default:
        }
    }

    /**
     * Rotate the Rover 90 degrees to the right without changing the current position.
     */
    public void rotateRight() {
        switch (o) {
            case N:
                this.o = Orientation.E;
                break;
            case E:
                this.o = Orientation.S;
                break;
            case S:
                this.o = Orientation.W;
                break;
            case W:
                this.o = Orientation.N;
                break;
            default:
        }
    }

    /**
     * Move the Rover on the grid in a direction which depends on the current orientation of the Rover.
     */
    public void moveForward() {
        switch (o) {
            case N:
                this.y = this.y + 1;
                break;
            case E:
                this.x = this.x + 1;
                break;
            case S:
                this.y = this.y - 1;
                break;
            case W:
                this.x = this.x - 1;
                break;
            default:
        }
        this.listePos.add(new Position(this.getX(), this.getY()));
    }

    /**
     * Move the Rover on its  previous positions on the grid.
     */
    public void moveBackward() {
        // System.out.println("BEFORE " + this.listePos.size());
        this.listePos.remove(new Position(this.getX(), this.getY()));
        // System.out.println("AFTER " + this.listePos.size());
        switch (o) {
            case N:
                this.y = this.y - 1;
                break;
            case E:
                this.x = this.x - 1;
                break;
            case S:
                this.y = this.y + 1;
                break;
            case W:
                this.x = this.x + 1;
                break;
            default:
        }
    }

    /**
     * Get the Rover's ID.
     * @return The ID of the Rover as an integer
     */
    public int getId() {
        return id;
    }

    /**
     * Get the Rover's current position on the X axis.
     * @return The Rover's position on the X axis as an integer
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Rover's current position on the Y axis.
     * @return The Rover's position on the Y axis as an integer
     */
    public int getY() {
        return y;
    }

    /**
     * Get the Rover's current cardinal orientation.
     * @return The Rover's current Orientation
     */
    public Orientation getO() {
        return o;
    }

    /**
     * Get the Rover's unique positions
     * @return The Rover's list of unique positions
     */
    public Set<Position> getListePos(){
        return this.listePos;
    }

}
