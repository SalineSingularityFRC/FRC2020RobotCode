package frc.robot;

import frc.controller.motorControllers.Spark;

public class Climber{

    Spark upMotor, downMotor;

    //private final double upVoltage = 11.5;
    private final double upSpeed = -0.25;
    private final double downSpeed = 0.25;
    //private final double constantSpeed = 0.1;
   // private final int upPosition = 5000;

    public Climber(int downMotorPort) {
        downMotor = new Spark(downMotorPort, true, 0.0);
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
        downMotor.setPower(downSpeed);
    }

    public void rachetReset() {
        downMotor.setPower(upSpeed);
    }

    public void rachetOff() {
        downMotor.setPower(0);
    }
}