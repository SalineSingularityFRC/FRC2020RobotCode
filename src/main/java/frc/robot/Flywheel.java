package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Flywheel {
    
    MotorController flywheel1, flywheel2;

    final private double forwardSpeed = 0.25;
    final private double reverseSpeed = -0.25;

    //Constructer
    public Flywheel(int flywheel1Port, int flywheel2Port){
        flywheel1 = new Spark(flywheel1Port, true, 0);
        flywheel2 = new Spark(flywheel2Port, true, 0);
        flywheel2.follow(flywheel1, true);
    }
    //sets flywheels to spin forward
    public void flywheelForward() {
        flywheel1.setSpeed(forwardSpeed);
    }
    //sets flywheels to spin backward
    public void flywheelReverse() {
        flywheel1.setSpeed(reverseSpeed);
    }
    //stops flywheels from spinning
    public void flywheelOff() {
        flywheel1.setSpeed(0.0);
    }

}