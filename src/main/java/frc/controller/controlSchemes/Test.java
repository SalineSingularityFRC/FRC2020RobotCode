package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.*;
import frc.singularityDrive.*;

import edu.wpi.first.wpilibj.Ultrasonic;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

public class Test extends ControlScheme{
    XboxController driveController;
    XboxController armController;

    int grabClawAngle = 160;
    final int releaseClawAngle = 90;

    public Test(int driveControllerPort, int armControllerPort){
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);
    }

    public void drive(SingDrive drive, DrivePneumatics pneumatics) {
        
    }

	public void visionDrive(Vision vision, SingDrive drive, DrivePneumatics dPneumatics, AHRS gyro, Ultrasonic ultra){

    }

    public void ledMode(LimeLight limeLight){
        
    }
}