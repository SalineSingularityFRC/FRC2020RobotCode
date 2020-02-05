package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;

public class Conveyor {

    Spark motor1, motor2;

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = -0.25;

    public Conveyor(int port1, int port2) {
        motor1 = new Spark(port1, true, 0.0);
        motor2 = new Spark(port2, true, 0.0);
        motor2.follow(motor1, false);
    }

    public void conveyorForward() {
        motor1.setSpeed(forwardSpeed);
    }

    public void conveyorReverse() {
        motor1.setSpeed(reverseSpeed);
    }

    public void converyorOff() {
        motor1.setSpeed(0.0);
    }


}