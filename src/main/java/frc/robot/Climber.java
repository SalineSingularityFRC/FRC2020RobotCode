package frc.robot;

import frc.controller.motorControllers.Spark;

public class Climber{

    Spark upMotor, downMotor;

    //private final double upVoltage = 11.5;
    private final double upSpeed = -0.25;
    private final double downSpeed = 0.25;
    //private final double constantSpeed = 0.1;
   // private final int upPosition = 5000;

   double kP = 6e-5; 
   double kI = 0;
   double kD = 0; 
   double kIz = 0; 
   double kFF = 0.000015; 
   double kMaxOutput = 1; 
   double kMinOutput = -1;
   double maxRPM = 5700;

    public Climber(int downMotorPort) {
        downMotor = new Spark(downMotorPort, true, 0.0, "Climber", false, false, kP, kI, kD, kIz, kFF, kMinOutput, kMaxOutput);
        downMotor.setCoastMode(false);
    }

    /*
        Old code based on using two motors, were only using one now
    public void climberToPosition(double joystickControl) {
        //11.5 volts, probably need to change that
        upMotor.setToPosition(joystickControl, upPosition, upVoltage);
    }

    public void climberUp() {
        upMotor.setSpeed(upSpeed);
    }

    public void climberHoldPosition() {
        upMotor.setSpeed(constantSpeed);
    }

    public void climberDown() {
        upMotor.setSpeed(0);
        upMotor.setCoastMode(true);
        downMotor.setSpeed(downSpeed);
    }

    public void downStop() {
        if(downMotor.isLowerLimitPressed(true)) {
            downMotor.setSpeed(0);
        }

        else {
            downMotor.setSpeed(downSpeed);
        }
    }
    */

    public void rachetDown() {
        downMotor.setVelocity(maxRPM);
    }

    public void rachetReset() {
        downMotor.setSpeed(upSpeed);
    }

    public void rachetOffVel() {
        downMotor.setVelocity(0);
    }

    public void rachetOffSpeed() {
        downMotor.setSpeed(0);
    }
}