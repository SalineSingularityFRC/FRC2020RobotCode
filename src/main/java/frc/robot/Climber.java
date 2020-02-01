package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Climber{

    Spark upMotor, downMotor;

    private final double upSpeed = 0.25;
    private final double downSpeed = 0.25;
    private final double constantSpeed = 0.1;
    private final int upPosition = 5000;

    public Climber(int upMotorPort, int downMotorPort) {
        upMotor = new Spark(upMotorPort, true, 0.0);
        downMotor = new Spark(downMotorPort, true, 0.0);
        upMotor.setCoastMode(false);
        downMotor.setCoastMode(false);
    }

    public void climberToPosition() {
        //upMotor.setToPosition(joystickControl, position, feedForward);
    }
}