package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;

public class LightningtoTrench1 extends AutonControlScheme{

    public LightningtoTrench1(SingDrive drive, LimeLight limeLight){
    super(drive, limeLight);

    }
    public void moveAuton(){
        super.vertical(60, 0.5);
        super.rotate(90, true);
        super.vertical(60, 0.5);
        super.rotate(90, false);
        super.vertical()

        
    }
}