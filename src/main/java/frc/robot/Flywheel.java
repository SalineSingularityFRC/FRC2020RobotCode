package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

/**
 * creates class for Flywheel to give the capability to put in forward, reverse, and stop.
 * @author Neel Moudgal
 */
public class Flywheel {
    MotorController flywheelMotor1, flywheelMotor2;
    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    /**
     * Constructor for Flywheel, makes flywheel 2 follow flywheel 1
     * @param motor1Port
     * @param motor2Port
     * @author Neel Moudgal
     */


    public Flywheel (int motor1Port, int motor2Port) {
        flywheelMotor1 = new Spark(motor1Port, true, 0);
        flywheelMotor2 = new Spark(motor2Port, true, 0);
        flywheelMotor2.follow(flywheelMotor1, true);
    }

    /**
     * Sets the speed of flywheel to forward
     * @param No param
     * @author Neel Moudgal
     */

    public void flywheelForward(){
        flywheelMotor1.setSpeed(forwardSpeed);
    }

    /**
     * Sets flywheel to reverse speed
     * @param No param
     * @author Neel Moudgal
     */
    public void flywheelReverse(){
        flywheelMotor1.setSpeed(reverseSpeed);
    }

    /**
     * Turns off flywheel
     * @param No param
     * @author Neel Moudgal
     */
    public void flywheelOff(){
        flywheelMotor1.setSpeed(0);
    }
}