package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Hood{
    MotorController motorControl1;

    public Hood(int motorPort1){
        motorControl1 = new Spark(motorPort1, true, 0);
    }

    public void setSpeed(double motorSpeed){
        motorControl1.setSpeed(motorSpeed);
    }

    public void motorStop(){
        motorControl1.setSpeed(0);
    }
}
