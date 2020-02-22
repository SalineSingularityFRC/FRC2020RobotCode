package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.Flywheel;
import frc.robot.LimeLight;
import frc.robot.Conveyor;

public class JustMove extends AutonControlScheme {

    public JustMove(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor) {
        super(drive, limeLight, flywheel, conveyor);
    }

    public void moveAuton() {
        super.vertical(50, 0.2);
    }
}