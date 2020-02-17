package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// Same as pretty much every other mechanism class written, see comments there

/**
 * TODO: Add a pneumatic piston to this 
 */
public class CellCollector {

    Spark collectorMotor1, collectorMotor2;
    DoubleSolenoid collectorSolenoid; 

    private final double forwardSpeed = 1.00;
    private final double reverseSpeed = -1.00;

    /**
     * Class to control a power cell collector using a single 550 motor and Spark controller
     * @param motorPort CAN ID of the Spark motor controller the collected is plugged into
     */
    public CellCollector(int motorPort1, int motorPort2, int solenoidForward, int solenoidReverse) {
        collectorMotor1 = new Spark(motorPort1, true, 0.0);
        collectorMotor2 = new Spark(motorPort2, true, 0.0);
        collectorMotor2.follow(collectorMotor1, true);
        //collectorSolenoid = new DoubleSolenoid(solenoidForward, solenoidReverse);
    }

    public void collectorForward() {
        collectorMotor1.setPower(forwardSpeed);
    }

    public void collectorReverse() {
        collectorMotor1.setPower(reverseSpeed);
    }

    public void collectorOff() {
        collectorMotor1.setPower(0.0);
    }

    public void collectorUp() {
        collectorSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void collectorDown() {
        collectorSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}