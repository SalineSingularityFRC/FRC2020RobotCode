package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

<<<<<<< HEAD
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
=======

// Class to control the flywheel cell shooting mechanism on the Infinite Recharge robot

public class Flywheel {

    // Creates two generic motor controller objects to control the two motors (normal NEOs) on the flywheel
   Spark flywheel1, flywheel2;

    // Create two constant speed variables that run the motors forwards and backwars
    // Make these both final, so they can't be changed later, and private, so they're not influenced other places in the code
    // Set a constant speed here so it can be changed in one place when being adjusted
    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    // Init the flywheel object, taking int he two motors and ports and setting the flywheels to follow each other, reverse
    // Settings rampRate here to 0 so we get maximum firepower as fast as possible
    public Flywheel(int flywheel1Port, int flywheel2Port) {
        flywheel1 = new Spark(flywheel1Port, true, 0.00);
        flywheel2 = new Spark(flywheel2Port, true, 0.00);
        flywheel2.follow(flywheel1, true);
    }

    // Set the flywheels to shoot a cell forward
    public void flywheelForward() {
        flywheel1.setSpeed(forwardSpeed);
    }

    // Set the flywheels to go revserse - not sure if needed, but have it in-case
    public void flywheelReverse() {
        flywheel1.setSpeed(reverseSpeed);
    }

    // Turn the flywheels off
>>>>>>> 7b48ffabbec67757efc00a249cbbe0fc3ca19b6a
    public void flywheelOff() {
        flywheel1.setSpeed(0.0);
    }

}