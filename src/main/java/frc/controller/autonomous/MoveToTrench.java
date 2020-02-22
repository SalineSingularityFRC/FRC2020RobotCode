package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;

public class MoveToTrench extends AutonControlScheme{

    public MoveToTrench(SingDrive drive, LimeLight limeLight){
        super(drive, limeLight);
    }

    public void moveAuton(){
        super.vertical(60, -0.5);
        super.rotate(90, true);
        super.vertical(66.91, -0.5);
        super.rotate(90, false);
        super.vertical(260, -0.5);
    }

}