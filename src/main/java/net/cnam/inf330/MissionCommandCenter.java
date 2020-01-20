package net.cnam.inf330;

import java.util.*;

/**
 * Class for managing the rovers that are deployed on the Mars exploration grid.
 */
public class MissionCommandCenter {
    private int gridWidth;
    private int gridHeight;
    private List<Rover> rovers;

    // TODO 1) Make MCC a singleton class

    /**
     * Create a MCC without a predefined grid size.
     */
    public MissionCommandCenter() {
        this.gridWidth = -1;
        this.gridHeight = -1;
        this.rovers = new ArrayList<>();
    }

    /**
     * Create a MCC with a predefined grid size.
     *
     * @param gridWidth  The width (X axis) of the exploration grid
     * @param gridHeight The height (Y axis) of the exploration grid
     */
    public MissionCommandCenter(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.rovers = new ArrayList<>();
    }

    /**
     * Process the rover data line by line.
     *
     * @param lines The lines representing the rover data to process
     * @return
     */
    public List<String> processRoverData(List<String> lines) {
        System.out.println("Processing rover data...");

        String[] splitFirstLine = lines.remove(0).split(" ");
        int gridWidth = Integer.parseInt(splitFirstLine[0]);
        int gridHeight = Integer.parseInt(splitFirstLine[1]);
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        System.out.println("* Size of the grid : (" + this.gridWidth + "," + this.gridHeight + ")");

        List<String> outputLines = new ArrayList<>();
        int currentRoverId = 0;
        Iterator<String> it = lines.iterator();
        while (it.hasNext()) {
            currentRoverId++;
            String roverInitialStateData = it.next();
            String roverInstructionsData = it.next();

            Rover rover = deployAndMoveRover(currentRoverId, roverInitialStateData, roverInstructionsData);
            rovers.add(rover);
            System.out.println("Rover " + currentRoverId + "'s final state : " + rover);

            double roverCoveragePercent = computeRoverCoveragePercent(rover);
            System.out.println("Rover " + rover.getId() + "'s grid coverage : " + roverCoveragePercent + "");

            outputLines.add(String.join(" ", Arrays.asList(Integer.toString(rover.getX()),
                    Integer.toString(rover.getY()), rover.getO().toString())));
        }

        System.out.println("Finished processing rover data.");
        return outputLines;
    }

    /**
     * Deploy a new rover on the grid at its initial position, and move it until it reaches its final position.
     *
     * @param roverId              The ID of the rover to move
     * @param roverInitialPosition The rover's initial position data
     * @param roverInstructions    The data containing set of instructions to move the rover
     * @return The Rover
     */
    public Rover deployAndMoveRover(int roverId, String roverInitialPosition,
                                    String roverInstructions) {
        System.out.println("* Established communication signal with rover " + roverId + ".");

        String[] splitRoverInitialPositionData = roverInitialPosition.split(" ");
        int roverX = Integer.parseInt(splitRoverInitialPositionData[0]);
        int roverY = Integer.parseInt(splitRoverInitialPositionData[1]);
        Orientation roverOrientation = Orientation.valueOf(splitRoverInitialPositionData[2]);

        Rover rover = new Rover(roverId, roverX, roverY, roverOrientation);
        System.out.println("Rover " + roverId + "'s initial state : " + rover);

        try {
            checkRoverPosition(rover);
        } catch (InvalidRoverPositionException e) {
            // TODO 4) b) Don't deploy the rover if its initial position is invalid
            System.out.println("### WARNING : " + e.getMessage());
        }

        System.out.println("Controlling rover " + roverId + "...");
        for (Character c : roverInstructions.toCharArray()) {
            rover.processCommand(RoverCommand.valueOf(String.valueOf(c)));
            // TODO 4) a) Make the rover pull back if the move is invalid
        }

        System.out.println("Terminated communication with rover " + roverId + ".");
        return rover;
    }

    /**
     * Check whether the rover's current position is valid.
     *
     * @param rover The rover to validate
     * @throws InvalidRoverPositionException
     */
    public void checkRoverPosition(Rover rover) throws InvalidRoverPositionException {
        if (rover.getX() > this.gridWidth || rover.getY() > this.gridHeight)
            throw new InvalidRoverPositionException(rover,
                    "Position out of grid ! Communication signal weak.");

        // TODO 2) Throw an InvalidRoverPositionException if there is another rover on the rover's current position.
    }

    /**
     * Compute the rover's coverage percent, which is defined by the ratio between the number of distinct positions
     * visited by the rover and the total number of positions on the exploration grid.
     *
     * @param rover The rover to compute the coverage percent for
     * @return
     */
    public double computeRoverCoveragePercent(Rover rover) {
        // TODO 6) Compute the rover's grid coverage percentage
        return 0d;
    }

    public void clearRovers() {
        this.rovers.clear();
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public void addRover(Rover rover) {
        this.rovers.add(rover);
    }
}
