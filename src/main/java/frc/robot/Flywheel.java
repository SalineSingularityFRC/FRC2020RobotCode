package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * Creates a class for flywheel so it has the capability to spin forward, reverse, and turn it off
 * @param no param
 * 
 * @author Brian Liu
 */
public class Flywheel {

    MotorController flywheelMotor1, flywheelMotor2;

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    public Flywheel (int motor1Port, int motor2Port) {
        flywheelMotor1 = new Spark(motor1Port, true, 0);
        flywheelMotor2 = new Spark(motor2Port, true, 0);
        flywheelMotor2.follow(flywheelMotor1, true);
    }

    /**
     * Sets the speed of flywheel to forward
     * @param no param
     * 
     * @author Brian Liu
     */

    public void flywheelForard() {
        flywheelMotor1.setSpeed(forwardSpeed);
    }

    /**
     * Sets the speed of flywheel to reverse
     * @param no param
     * 
     * @author Brian Liu
     */

    public void flywheelReverse() {
        flywheelMotor1.setSpeed(reverseSpeed);
    }

    /**
     * Sets the speed of flywheel to 0
     * @param no param
     * 
     * @author Brian Liu
     */

    public void flywheelOff() {
        flywheelMotor1.setSpeed(0);
    }

}