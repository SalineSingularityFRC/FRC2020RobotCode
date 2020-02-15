package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;

public class Trench1 extends AutonControlScheme {

    public Trench1(SingDrive drive, LimeLight limeLight) {
        super(drive, limeLight);
    }

    //The back of the robot should be facing in the direction of the trench//
    public void moveAuton() {
        super.vertical(42.91, -0.5);
        super.rotate(90, false);
        super.vertical(122.63, -0.5);
        //move right up to the first ball in the trench//
    }
}