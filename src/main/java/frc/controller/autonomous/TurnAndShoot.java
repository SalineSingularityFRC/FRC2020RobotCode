package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;

public class TurnAndShoot extends AutonControlScheme {

    public TurnAndShoot(SingDrive drive){
        super(drive, limeLight);
    }

    public void moveAuton(){
        super.vertical(120, 0.5);
    }

}