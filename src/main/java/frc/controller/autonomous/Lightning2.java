package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;
import frc.robot.Flywheel;
import frc.robot.Conveyor;
import frc.robot.CellCollector;

public class Lightning2 extends AutonControlScheme {

    public Lightning2(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor, CellCollector cellCollector) {
        super(drive, limeLight, flywheel, conveyor, cellCollector);
    }

    public void moveAuton() {
        super.vertical(60, 0.5);
        super.rotate(90, false);
        super.vertical(59, 0.5);
        super.rotate(90, true);
        super.vertical(40, 0.5);
        super.adjustToTarget();
        super.vertical(30, 0.5);
        super.shoot();
    }

}