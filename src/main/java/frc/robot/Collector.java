package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Collector{

    MotorController collector1;

    private final double forwardSpeed = 0.25;
    final private double reverseSpeed =-0.25;


    public Collector ( int controllerport1){
        collector1 = new Spark(controllerport1, true, 0.0);
    }
    public void collectorForward(){
        collector1.setSpeed(forwardSpeed);
    }

    public void collectorReverse(){
        collector1.setSpeed(reverseSpeed);

    }
    public void collectorOff(){
        collector1.setSpeed(0);
    }
}