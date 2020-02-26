package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.Flywheel;
import frc.robot.LimeLight;
import frc.robot.CellCollector;
import frc.robot.Conveyor;

public class AndCollectAndShoot extends AutonControlScheme {

    public AndCollectAndShoot(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor, CellCollector cellCollector) {
        super(drive, limeLight, flywheel, conveyor, cellCollector);
    }

    public void moveAuton() {
        super.rotate(17.94, false);
        super.cellCollector.collectorDown();
        super.verticalWithCollector(-108);
        super.verticalWithCollector(108);
        super.rotate(17.94, true);
        super.adjustToTarget();
        super.shoot();
    }
}