package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

// Same as pretty much every other mechanism class written, see comments there
public class CellCollector {

    Spark collectorMotor;

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = 0.25;

    /**
     * Class to control a power cell collector using a single 550 motor and Spark controller
     * @param motorPort CAN ID of the Spark motor controller the collected is plugged into
     */
    public CellCollector(int motorPort) {
        collectorMotor = new Spark(motorPort, true, 0.0);
    }

    public void collectorForward() {
        collectorMotor.setSpeed(forwardSpeed);
    }

    public void collectorReverse() {
        collectorMotor.setSpeed(reverseSpeed);
    }

    public void collectorOff() {
        collectorMotor.setSpeed(0.0);
    }
}