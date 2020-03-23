package net.cnam.inf330;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class for testing the Rover Mission Command Center application.
 */
public class RoverTest {
    /**
     * Initialize the MCC before the tests are run.
     */
    @BeforeClass // This method is run only once, before the test methods are run
    public static void initMissionCommandCenter() {
        // TODO 1) Initialize MCC singleton instance before the test methods are run
        // FIXME The idea was to store the instance in a member variable
        MissionCommandCenter instanceMCC = MissionCommandCenter.getInstance(1,1);
        System.out.println(instanceMCC);
    }

    /**
     * Application must catch an InvalidRoverPositionException if a rover has moved out of the grid.
     * Rover must pull back after moving out of the grid.
     */
    // TODO 5) Change this test to check that the rover pulls back after moving out of the grid
    @Test
    public void testRoverOutOfGridException() {
        MissionCommandCenter mcc = MissionCommandCenter.getInstance();
        Rover rover = new Rover(1, 0, 0, Orientation.N);
        mcc.addRover(rover);
        rover.moveForward();
        rover.moveForward();

        ThrowingRunnable tr = () -> mcc.checkRoverPosition(rover);
        assertThrows(InvalidRoverPositionException.class, tr);

        // Previous position TO DO 5 avec moveBackward
        rover.moveBackward(); // FIXME Should have called the moveRover method to test expected behaviour
        assertEquals(rover.getX(), 0);
        assertEquals(rover.getY(), 1);

        mcc.clearRovers();
    }

    // TODO 5) Change this test to check that the rover pulls back after moving out of the grid
    // Nouveau test avec deployAndMoveRover
    @Test
    public void testRoverOutOfGridExceptionBack() {
        MissionCommandCenter mcc = MissionCommandCenter.getInstance();
        Rover rover = mcc.deployAndMoveRover(1, "0 0 N", "MM");
        // MM : y incrémenté de 1. Donc après MM, y=2. Mais en dehors
        // de la grille. Donc y=1
        assertEquals(rover.getY(), 1);

        mcc.clearRovers();
    }

    /* TODO 3) 5) Write a new test for a scenario where 2 rovers collide at the same position on the grid
     *   and the second rover must pull back as a result
     */

    @Test
    public void testRoverPositionTaken() {
        // TO DO 3)
        MissionCommandCenter mcc = MissionCommandCenter.getInstance();
        Rover rover1 = new Rover(1, 0, 1, Orientation.N);
        Rover rover2 = new Rover(2, 0, 0, Orientation.N);
        mcc.addRover(rover1);
        mcc.addRover(rover2);
        rover2.moveForward();

        ThrowingRunnable tr = () -> mcc.checkRoverPosition(rover2);
        assertThrows(InvalidRoverPositionException.class, tr);

        // TO DO 5) avec moveBackward
        rover2.moveBackward(); // FIXME Should have called the moveRover method to test expected behaviour
        assertEquals(rover2.getX(), 0);
        assertEquals(rover2.getY(), 0);
        mcc.clearRovers();
    }

    /* TODO 5) Write a new test for a scenario where 2 rovers collide at the same position on the grid
     *   and the second rover must pull back as a result
     */
    // TO DO 5 avec  deployAndMoveRover
    @Test
    public void testRoverPositionTakenBack() {
        MissionCommandCenter mcc = MissionCommandCenter.getInstance();
        Rover rover1 = new Rover(1, 0, 1, Orientation.N);
        mcc.addRover(rover1);

        Rover rover = mcc.deployAndMoveRover(2, "0 0 N", "MM");

        assertEquals(rover.getY(), 0);
    }

    /* TODO 5) Write a new test for a scenario where a rover is created at an invalid position
     *   and is not deployed as a result
     */
    @Test
    public void testRoverNotDeployed() {
        MissionCommandCenter mcc = MissionCommandCenter.getInstance();
        Rover rover = new Rover(0, 0, 0, Orientation.N);
        mcc.addRover(rover);

        // Out of the grid
        Rover rover2 = mcc.deployAndMoveRover(1, "2 2 N", "M");
        assertNull(rover2);
    }

    /**
     * Application must produce output data that matches the expected output after processing the input rover data.
     */
    @Test
    public void outputDataTest() throws IOException, URISyntaxException {
        List<String> inputLines = Main.readResourceFile("rover_test_input.txt");
        List<String> expectedOutputLines = Main.readResourceFile("rover_test_output.txt");

        // TODO 7) Test that processing the input lines produces an output that matches the expected output lines
        MissionCommandCenter mcc = MissionCommandCenter.getInstance();
        List<String> outputLines = mcc.processRoverData(inputLines);

        // FIXME Do this with only 1 assert and without the for loop
        for (int i=0; i<outputLines.size(); i++){
            assertEquals(outputLines.get(i), expectedOutputLines.get(i));
        }
    }
}
