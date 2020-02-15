package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;

public class Lightning2 extends AutonControlScheme{

    public Lightning2(SingDrive drive, LimeLight limeLight){
    super(drive, limeLight);//references constructor of super class
}
    public void moveAuton(){
        super.vertical(60, 0.5);
        super.rotate(90, false);//clockwise is always false(counterclockwise is left)
        super.vertical(59, 0.5);
        super.rotate(90, true); 
        super.vertical(40, 0.5);
        super.adjustToTarget();
        super.vertical(30, 0.5);
        super.shoot();
    }
}