package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * Creates class to make the flywheel spin forward, reverse, and stop
 * 
 * @param no param
 * @author Brian Liu
 */
public class Flywheel {

    MotorController motorControl1, motorControl2;

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    /**
     * Creates constructor for the motors
     * 
     * @param flywheelMotor1
     * @param flywheelMotor2
     * @author Brian Liu
     */
    public Flywheel(int flywheelMotor1, int flywheelMotor2) {
        motorControl1 = new Spark(flywheelMotor1, true, 0);
        motorControl2 = new Spark(flywheelMotor2, true, 0);
        motorControl2.follow(motorControl1, true);
    }

    /**
     * Sets flywheel speed to forward
     * 
     * @param no param
     * @author Brian Liu
     */
    public void forwardSpeed() {
        motorControl1.setSpeed(forwardSpeed);
    }

    /**
     * Sets flywheel speed to reverse
     * 
     * @param no param
     * @author Brian Liu
     */
    public void reverseSpeed() {
        motorControl1.setSpeed(reverseSpeed);
    }

    /**
     * Sets flywheel speed to 0
     * 
     * @param no param
     * @author Brian Liu
     */
    public void offSpeed() {
        motorControl1.setSpeed(0);
    }

}