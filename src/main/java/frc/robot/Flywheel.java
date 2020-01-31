package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;
//this import is needed because you can't just have spark, the computer doesn't know what that is really

public class Flywheel {

    MotorController flywheel1, flywheel2;
    DoubleSolenoid flywheelSolenoid;

    private final double forwardSpeed = 0.25;
    final private double reverseSpeed = -0.25;

    public Flywheel(final int flywheel1Port, final int flywheel2Port) {
        flywheel1 = new Spark(flywheel1Port, true, 0.00);
        flywheel2 = new Spark(flywheel2Port, true, 0.00);
        flywheel2.follow(flywheel1, true);
    }

    public void flywheelForward() {
        flywheel1.setSpeed(forwardSpeed);
      
    }

    public void flywheelReverse() {
        flywheel1.setSpeed(reverseSpeed);
        //this allows the wheels to reverse if we need them to
       
    }

    public void flyWheelOff() {
        flywheel1.setSpeed(0.0);
    }



    public void solenoidReverse() {
       flywheelSolenoid.set(DoubleSolenoid.Value.kReverse);
    }


    public void solenoidForward() {
        flywheelSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

