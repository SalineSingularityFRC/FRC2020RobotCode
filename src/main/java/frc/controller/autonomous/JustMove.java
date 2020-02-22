package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;


public class JustMove extends AutonControlScheme {

    public JustMove(SingDrive drive, LimeLight limeLight) {
        super(drive, limeLight);
    }

    public void moveAuton() {
        super.vertical(50, 0.2);
    }
}