package frc.controller.autonomous;

import frc.singularityDrive.SingDrive;
import frc.robot.LimeLight;


public class Lightning1 extends AutonControlScheme{//defining a lot of methods in this shceme
    
public Lightning1(SingDrive drive, LimeLight limeLight){//goal of lightning is for us to shoot in low goal
    super(drive, limeLight);
}

public void moveAuton(){
    super.vertical(60, 0.5);//super because we are referencing the super class(speed is distance beteew 0 and 1)
    super.rotate(90, true);
    super.vertical( 24, 0.5);//look up value 
    super.rotate(90, false);
    super.vertical(40, 0.5);
    super.adjustToTarget();
    super.vertical(30, 0.5);
    super.shoot();//we correct ourselves based on the target
    }

}