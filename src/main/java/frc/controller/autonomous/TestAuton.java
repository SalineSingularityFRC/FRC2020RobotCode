package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;
import frc.robot.Flywheel;
import frc.robot.Conveyor;

public class TestAuton extends AutonControlScheme{

    
    public TestAuton(SingDrive drive, LimeLight limeLight, Flywheel flywheel, Conveyor conveyor) {
        super(drive, limeLight, flywheel, conveyor);
    }


    @Override
    public void moveAuton() {
        //super.adjustToTarget();
        super.vertical(10, 0.1);
        //super.vertical(10, -0.1);
        //super.rotate(20, false);
    } 

}