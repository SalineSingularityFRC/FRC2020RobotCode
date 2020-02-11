package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;

public class TurnAndShoot extends AutonControlScheme {

    public TurnAndShoot(SingDrive drive){
        super(drive, limeLight);
    }

    public void moveAuton(){
        super.vertical( 12, 0.5);
        //super.findTarget();
        //super.shoot();
        super.rotate(0.5, 180, false);
    }

}