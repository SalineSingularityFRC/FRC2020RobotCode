package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Climber {

    MotorController motorControl1, motorControl2;

    private final double forwardSpeed = 0.50;
    private final double hangSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    public Climber(int climberMotor1, int climberMotor2) {
        motorControl1 = new Spark(climberMotor1, true, 0);
        motorControl2 = new Spark(climberMotor2, true, 0);
    }

    public void forwardSpeed() {
        motorControl2.setSpeed(0);
        motorControl1.setSpeed(forwardSpeed);
    }

    public void hangSpeed() {
        motorControl2.setSpeed(0);
        motorControl1.setSpeed(hangSpeed);
    }

    public void reverseSpeed() {
        motorControl1.setSpeed(0);
        motorControl2.setSpeed(reverseSpeed);
    }

    public void stopSpeed() {
        motorControl1.setSpeed(0);
        motorControl2.setSpeed(0);
        
    }
}