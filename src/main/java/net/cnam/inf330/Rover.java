package net.cnam.inf330;

public class Rover {

    private int id;
    private int x;
    private int y;
    private Orientation o;

    public Rover(int id, int x, int y, Orientation o) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.o = o;
    }

    @Override
    public String toString() {
        return this.id + ":(" + this.x + "," + this.y + "," + this.o + ")";
    }

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
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getO() {
        return o;
    }
}
