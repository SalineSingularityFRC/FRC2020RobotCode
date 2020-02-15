package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;

public class Lightning2  extends AutonControlScheme{

    public Lightning2 (SingDrive drive, LimeLight limeLight){
        super(drive, limeLight);
    }

    public void moveAuton(){
        super.vertical(60, 0.5);//move forward
        super.rotate(90, false);//turn clockwise 90 degrees
        super.vertical(60, 0.5);//move forward
        super.rotate(90, true);//turn counterclockwise 90 degrees
        super.vertical( 40, 0.5);//move forward
        super.adjustToTarget();//adjust to the target using limelight
        super.vertical(30,0.5);//move forward
        super.shoot();//shoot power cell
    }

}