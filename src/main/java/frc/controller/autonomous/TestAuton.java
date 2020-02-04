package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;

public class TestAuton extends AutonControlScheme{

    
    public TestAuton(SingDrive drive){
        super(drive);
    }


    @Override
    public void moveAuton() {
        super.vertical(10, 0.1);
    } 

}