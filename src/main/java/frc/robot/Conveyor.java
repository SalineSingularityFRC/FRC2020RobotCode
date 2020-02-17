package frc.robot;

import frc.controller.motorControllers.Spark;

public class Conveyor {

    Spark motor1, motor2;

    private final double forwardSpeed = -0.25;
    private final double reverseSpeed = 0.25;

    public Conveyor(int port1) {
        motor1 = new Spark(port1, true, 0.0);
        //motor2 = new Spark(port2, true, 0.0);
        //motor2.follow(motor1, false);
    }

    public void conveyorForward() {
        motor1.setPower(forwardSpeed);
    }

    public void conveyorReverse() {
        motor1.setPower(reverseSpeed);
    }

    public void conveyorOff() {
        motor1.setPower(0.0);
    }


}