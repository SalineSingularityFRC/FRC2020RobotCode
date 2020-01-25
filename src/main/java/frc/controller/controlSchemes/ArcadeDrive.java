package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.DrivePneumatics;
import frc.robot.LimeLight;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.SpeedMode;
import edu.wpi.first.wpilibj.Ultrasonic;


import com.kauailabs.navx.frc.AHRS;

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

    boolean usingLimeLight;
    double tx, tv;

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

        driveMulti = false;
        bButtonNow = false;
        bButtonPrev = false;

        usingLimeLight = false;


        
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

        else if (!usingLimeLight) {
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
    
    public void LimeLightDrive(LimeLight limeLight, SingDrive drive, DrivePneumatics dPneumatics, AHRS gyro, Ultrasonic ultra){
        tx = limeLight.tx.getDouble(0.0);
        tv = limeLight.tv.getDouble(0.0);

        boolean squareButton = driveController.getXButton();
        boolean offSetButton = driveController.getYButton();

        double currentAngle = super.smooshGyroAngle(gyro.getAngle());
        SmartDashboard.putNumber("current angle:", currentAngle);

        if (driveController.getAButton()){
            gyro.setAngleAdjustment(0);
            gyro.setAngleAdjustment(-super.smooshGyroAngle(gyro.getAngle()));
        }

        if ((squareButton == true || offSetButton == true) && tv == 1.0){
            double left_command = driveSpeedConstant;
            double right_command = driveSpeedConstant;

            double steering_adjust = 0.0;
            steering_adjust += txkP * tx; 

            double targetAngle;
            if(squareButton) {
                targetAngle = super.getSquareAngleForPort(currentAngle);
            }
            else{
                targetAngle = super.getOffsetHatchAngle(currentAngle);
            }

            double angleDifference = currentAngle - targetAngle;
            double secondAngleDifference = targetAngle - 360 + currentAngle;

            if (Math.abs(secondAngleDifference)<Math.abs(angleDifference)){
                angleDifference = secondAngleDifference;
            }
            steering_adjust += angleDifferencekP * angleDifference;

            left_command += steering_adjust;

            right_command -= steering_adjust;

            drive.tankDrive(left_command, right_command, 0.0, false, SpeedMode.FAST);

            usingLimeLight = true;
        }
        else{
            usingLimeLight = false;

        }
            

        
    }



}
    