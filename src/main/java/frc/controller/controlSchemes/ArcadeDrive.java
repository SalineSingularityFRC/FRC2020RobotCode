package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.ColorSensor;
import frc.robot.DrivePneumatics;
import frc.robot.LimeLight;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.SpeedMode;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.*;
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

    

    //Hatch Variables
    //final int grabClawAngle = 120;
    //final int releaseClawAngle = 60;
    final double clawSpeed = 0.5;
    
    double tx, tv;
    double ultraIn;


    //Need to be adjusted for our robot
    final double driveSpeedConstant = 0.3;
    final double txkP = 0.022;
    final double angleDifferencekP = 0.011;
    final double endDistance = 2.0;

    boolean bButtonNow, bButtonPrev, driveMulti;

    // Constructor for the ArcadeDrive class

    public ArcadeDrive(int driveControllerPort, int armControllerPort) {
        //Initiates a new Xbox driveController
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);

        lowGear = true;
        speedMode = SpeedMode.SLOW;

        usingVision = false;

        driveMulti = false;
        bButtonNow = false;
        bButtonPrev = false;


        
    }

    public void colorSensor(ColorSensor colorSensor){
        if(armController.getPOVUp()) {
            colorSensor.spinColorWheelColor(2);
            colorSensor.resetCount(false);
        } else if (armController.getPOVDown()) {
            colorSensor.spinColorWheelRotations(26);
            colorSensor.resetCount(false);
        } else if (armController.getPOVLeft()) {
            colorSensor.resetCount(true);
        } else if (armController.getPOVRight()) {
            colorSensor.spinSpeed(ColorSensor.lowspeed);
            colorSensor.resetCount(false);
        } else {
            colorSensor.stopSpinning();
            colorSensor.resetCount(false);
        }
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
    
    public void colorSensor(ColorSensor colorSensor){
        if(armController.getPOVUp()) {
            colorSensor.spinColorWheelColor(2);
        }
        if(armController.getPOVDown()) {
            colorSensor.spinColorWheelRotations(24);
        }
        if(armController.getPOVLeft()) {
            colorSensor.setSpeed(0.25);
        }
        if(armController.getPOVRight()) {
            colorSensor.setSpeed(-0.25);
        }
        if(armController.getStartButton()) {
            colorSensor.extend();
        }
        if(armController.getBackButton()) {
            colorSensor.retract();
        }
    }
    
    public void ledMode( LimeLight limeLight ){
        if(driveController.getXButton() || driveController.getYButton()){
            limeLight.ledOff(limeLight);
        }
        else{
            limeLight.ledOn(limeLight);;
        }
    }

}

/**
 * Pseudocode for Limelight targeting 
 * Use a p controller
 */