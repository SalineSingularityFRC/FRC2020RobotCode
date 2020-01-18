package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.DrivePneumatics;
import frc.robot.LimeLight;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.SpeedMode;
import edu.wpi.first.wpilibj.smartdashboard.*;

//Uncomment to enable gyro stuff
//import com.kauailabs.navx.frc.AHRS;

/**
 * 
 * Main class to control the robot
 * 
 */
public class ArcadeDrive extends ControlScheme {

    // make new XBox driveController objects
    XboxController driveController;
    XboxController armController;

    boolean lowGear;
    SpeedMode speedMode;

    boolean usingVision;

    boolean bButtonNow, bButtonPrev, driveMulti;

    // Constructor for the ArcadeDrive class

    public ArcadeDrive(int driveControllerPort, int armControllerPort) {
        //Initiates a new Xbox driveController
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);

        lowGear = true;
        speedMode = SpeedMode.SLOW;

        driveMulti = false;
        bButtonNow = false;
        bButtonPrev = false;


        
    }

    /**
     * Drives arcade drive
     * 
     */
    public void drive(SingDrive drive, DrivePneumatics pneumatics) {

        //Set speed mode based on the dpad on the driveController
        if(driveController.getLB()){
            speedMode = SpeedMode.SLOW;

        }
        else if(driveController.getRB()){
            speedMode = SpeedMode.FAST;
        }
        SmartDashboard.putString("Speed Mode", ""+ speedMode);

        
        bButtonNow = driveController.getBButton();
        if(bButtonNow && !bButtonPrev) {
            driveMulti = !driveMulti;
        }
        bButtonPrev = bButtonNow;


        //driving arcade drive based on right joystick on driveController
        //changed boolean poweredInputs from false to true, change back if robot encounters issues
        //ADDED USINGVISION, SO IF THINGS ARE ACTING WEIRD COME BACK TO THIS
        if(driveController.getPOVLeft()) {
            drive.arcadeDrive(0, -0.1, 0.0, false, SpeedMode.FAST);
        }

        else if (driveController.getPOVUp()) {
            drive.arcadeDrive(0.1, 0, 0.0, false, SpeedMode.FAST);
        }

        else if (driveController.getPOVRight()) {
            drive.arcadeDrive(0.0, 0.1, 0.0, false, SpeedMode.FAST);
        }
        
        else if (driveController.getPOVDown()) {
            drive.arcadeDrive(-0.1, 0.0, 0.0, false, SpeedMode.FAST);
        }

        else if (!usingVision) {
            if (driveMulti) {
                drive.arcadeDrive((-1 * driveController.getLS_Y()), driveController.getRS_X(), 0.0, true, speedMode);
            }
            else {
                drive.arcadeDrive(driveController.getLS_Y(), driveController.getRS_X(), 0.0, true, speedMode);
            }
            
        }


        if(driveController.getBackButton()) {
           lowGear = true;
        }
        else if(driveController.getStartButton()) {
            lowGear = false;
        }

        if(lowGear) {
            pneumatics.setLow();
        }
        else {
            pneumatics.setHigh();
        }
    }
    
    public void ledMode(LimeLight limeLight ){
        if(driveController.getXButton() || driveController.getYButton()){
            limeLight.ledOff(limeLight);
        }
        else{
            limeLight.ledOn(limeLight);;
        }
    }

}
    