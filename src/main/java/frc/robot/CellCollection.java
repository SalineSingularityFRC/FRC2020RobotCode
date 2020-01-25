package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * Creates class that makes collector motor run forward, backward, or turn off based on a set speed.
 * @author Neel Moudgal
 */

public class CellCollection{
    
    /**
     * Declares motorControl1, the collector motor
     * @author Neel Moudgal
     */
    MotorController motorControl1;

    /**
     * Sets forward and backward speed for motorControl1
     * @author Neel Moudgal
     */
    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    /**
     * Default constructor for CellCollection
     * @param motorPort1
     * @author Neel Moudgal
     */
    public CellCollection(int motorPort1){
        motorControl1 = new Spark(motorPort1, true, 0);
        
    }

    /**
     * Method to make collector motor forward at the preset speed
     * @param No param
     * @author Neel Moudgal
     */
    public void CollectorForward(){
        motorControl1.setSpeed(forwardSpeed);
    }
   
    /**
     * Method to make collector motor move backward at the preset speed
     * @param No param
     * @author Neel Moudgal
     */
    public void CollectorReverse(){
        motorControl1.setSpeed(reverseSpeed);
    }

    /**
     * Method to turn collector motor off
     * @param No param
     * @author Neel Moudgal
     */
    public void CollectorOff(){
        motorControl1.setSpeed(0);
    }





}
