package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;

public class TestAuton extends AutonControlScheme{

    
    public TestAuton(SingDrive drive, LimeLight limeLight){
        super(drive, limeLight);
    }


    @Override
    public void moveAuton() {
        super.adjustToTarget();
        //super.vertical(10, 0.1);
        //super.vertical(10, -0.1);
        super.rotate(20, false);
    } 

}