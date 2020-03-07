package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;
import frc.robot.Flywheel;
import frc.robot.Conveyor;
import frc.robot.CellCollector;

public class ShootAndMove extends AutonControlScheme{

    public ShootAndMove(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor, CellCollector cellCollector) {
        super(drive, limeLight, flywheel, conveyor, cellCollector);
    }

    public void moveAuton(){
        super.shoot();
        super.vertical(50, -0.4);
        super.rotate(0.6, 4200, true);
    }
}