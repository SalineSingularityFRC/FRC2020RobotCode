package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * Creates a class that puts the conveyer belt in forward, reverse, or turns it off based on a given forward and reverse speed
 * @author Neel Moudgal
 */

public class ConveyerBelt {
    
    /**
     * Declares motocControl1 and motorControl2 which control the two motors
     * @author Neel Moudgal
     */
    MotorController motorControl1, motorControl2;
    
    /**
     * sets forward and reverse speed, used later in program
     * @author Neel Moudgal
     */
    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    /**
     * Default constructer for ConveyerBelt, sets motorControl2 to follow motorControl1
     * @param motorPort1
     * @param motorPort2
     * @author Neel Moudgal
     */
    public ConveyerBelt(int motorPort1, int motorPort2){
        motorControl1 = new Spark(motorPort1, true, 0);
        motorControl2 = new Spark(motorPort2, true, 0);
        motorControl2.follow(motorControl1, false);
    }

    /**
     * Method to set conveyer belt to move forward using forwardSpeed set earlier
     * @param No param
     * @author Neel Moudgal
     */

    public void ConveyerForward(){
        motorControl1.setSpeed(forwardSpeed);
    }
     /**
     * Method to set conveyer belt to move in reverse using reverseSpeed set earlier
     * @param No param
     * @author Neel Moudgal
     */
    public void ConveyerReverse(){
        motorControl1.setSpeed(reverseSpeed);
    }


    /**
     * Method to turn conveyer belt off
     * @param No param
     * @author Neel Moudgal
     */
    public void ConveyerOff(){
        motorControl1.setSpeed(0);
    }

    





}