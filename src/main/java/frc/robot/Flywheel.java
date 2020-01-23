package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Flywheel {

    MotorController flywheel1, flywheel2;

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    public Flywheel(int flywheel1Port, int flywheel2Port) {
        flywheel1 = new Spark(flywheel1Port, true, 0.00);
        flywheel2 = new Spark(flywheel2Port, true, 0.00);
        flywheel2.follow(flywheel1, true);
    }

    public void flywheelForward() {
        flywheel1.setSpeed(forwardSpeed);
    }

    public void flywheelReverse() {
        flywheel1.setSpeed(reverseSpeed);
    }

    public void flywheelOff() {
        flywheel1.setSpeed(0.0);
    }

}