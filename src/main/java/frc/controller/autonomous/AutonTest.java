package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;

public class AutonTest extends AutonControlScheme {

    public AutonTest(SingDrive drive){
        super(drive);
    }

    public void moveAuton(){

        super.rotate(90, false);

    }


}