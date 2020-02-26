package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;
import frc.robot.Flywheel;
import frc.robot.Conveyor;
import frc.robot.CellCollector;

public class MoveToTrench extends AutonControlScheme{

    public MoveToTrench(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor, CellCollector cellCollector) {
        super(drive, limeLight, flywheel, conveyor, cellCollector);
    }

    public void moveAuton(){
        super.vertical(60, -0.5);
        super.rotate(90, true);
        super.vertical(66.91, -0.5);
        super.rotate(90, false);
        super.vertical(140, -0.5);
    }

}