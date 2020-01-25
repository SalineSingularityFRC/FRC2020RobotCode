package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * Creates a class called CellCollector to make the cell collector go forward, reverse, and off
 * 
 * @param no param
 * @author Brian Liu
 */
public class CellCollector {

    MotorController motorControl; 

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = 0.25;

    /**
     * Creates the constructor for CellCollector
     * 
     * @param motorPort1
     * @author Brian Liu
     */
    public CellCollector(int motorPort1) {
        motorControl = new Spark(motorPort1, true, 0);
    }

    /**
     * Set the speed of the cell collector to forwardSpeed
     * 
     * @param no param
     * @author Brian Liu
     */
    public void cellCollectorForward() {
        motorControl.setSpeed(forwardSpeed);
    }

    /**
     * Set the speed of the cell collector to reverseSpeed
     * 
     * @param no param
     * @author Brian Liu
     */

    public void cellCollectorReverse() {
        motorControl.setSpeed(reverseSpeed);
    }
        /**
         * Set the speed of the cell collector to 0 
         * 
         * @param no param
         * @author Brian Liu 
         */
    public void cellCollectorOff() {
        motorControl.setSpeed(0);
    }
}