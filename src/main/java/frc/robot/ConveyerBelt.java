package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * Creates ConveyerBelt class to make the conveyerbelt to go forward, reverse, and stop
 * 
 * @param no param
 * @author Brian Liu
 */
public class ConveyerBelt {
    
    MotorController motorControl1, motorControl2;

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    /**
     * Creates the constructor for ConveyerBelt
     * 
     * @param conveyerMotor1
     * @param conveyerMotor2
     * @author Brian Liu
     */
    public ConveyerBelt(int conveyerMotor1, int conveyerMotor2) {
        motorControl1 = new Spark(conveyerMotor1, true, 0);
        motorControl2 = new Spark(conveyerMotor2, true, 0);
        motorControl2.follow(motorControl1, false);
    }

    /**
     * Sets speed of ConveyerBelt to fowardSpeed
     * 
     * @param no param
     * @author Brian Liu 
     */
    public void forwardSpeed() {
        motorControl1.setSpeed(forwardSpeed);
        
    }

    /**
     * Sets speed of ConveyerBelt to reverseSpeed
     * 
     * @param no param
     * @author Brian Liu
     */
    public void reverseSpeed() {
        motorControl1.setSpeed(reverseSpeed);
    }
    
    /**
     * Sets speed of ConveyerBelt to 0
     * 
     * @param no param
     * @author Brian Liu
     */
    public void offSpeed() {
        motorControl1.setSpeed(0);
    }
}