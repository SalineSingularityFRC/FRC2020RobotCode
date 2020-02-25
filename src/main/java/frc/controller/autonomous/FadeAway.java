package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.Flywheel;
import frc.robot.LimeLight;
import frc.robot.Conveyor;

public class FadeAway extends AutonControlScheme {

    public FadeAway(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor) {
        super(drive, limeLight, flywheel, conveyor);
    }

    public void moveAuton() {
        super.shoot();
        super.vertical(60, -0.3);
    }
}