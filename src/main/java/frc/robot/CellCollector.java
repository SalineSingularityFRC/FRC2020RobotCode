package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// Same as pretty much every other mechanism class written, see comments there

/**
 * TODO: Add a pneumatic piston to this 
 */
public class CellCollector {

    Spark collectorMotor;
    DoubleSolenoid collectorSolenoid; 

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = 0.25;

    /**
     * Class to control a power cell collector using a single 550 motor and Spark controller
     * @param motorPort CAN ID of the Spark motor controller the collected is plugged into
     */
    public CellCollector(int motorPort, int solenoidForward, int solenoidReverse) {
        collectorMotor = new Spark(motorPort, true, 0.0);
        collectorSolenoid = new DoubleSolenoid(solenoidForward, solenoidReverse);
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

    public void collectorUp() {
        collectorSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void collectorDown() {
        collectorSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}