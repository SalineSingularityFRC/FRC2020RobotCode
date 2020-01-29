package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.DrivePneumatics;
import frc.robot.LimeLight;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.SpeedMode;
import edu.wpi.first.wpilibj.smartdashboard.*;
import com.kauailabs.navx.frc.AHRS;

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
    

    double tx, ty, tv;

    final double txkP = 0.022;
    final double driveSpeedConstant = 0.3;
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

        //TODO uncomment this pneumatics
        /*if(lowGear) {
            pneumatics.setLow();
        }
        else {
            pneumatics.setHigh();
        }*/
    }
    
    public void ledMode(LimeLight limeLight ){
        if(driveController.getXButton() || driveController.getYButton()){
            limeLight.ledOff(limeLight);
        }
        else{
            limeLight.ledOn(limeLight);;
        }
    }

    public void limeLightDrive(LimeLight limeLight, SingDrive drive, AHRS gyro) {
        // Defining tx and tv
        // tx = X coordinate between -27 and 27
        // tv = 0 if no target found, 1 is target found
        tx = limeLight.tx.getDouble(0.0);
        tv = limeLight.tv.getDouble(0.0);

        //SmartDashboard.putNumber("Inches", ultraIn);

        // Declaring and instantiating buttons used for enabling LimeLight drive
        boolean squareButton = driveController.getXButton();
        boolean offSetButton = driveController.getYButton();
    
        
        // Defining and Declaring currentAngle as angle from gyro, between 0 and 360 degrees
        double currentAngle = super.smooshGyroAngle(gyro.getAngle());
        SmartDashboard.putNumber("current angle:", currentAngle);

        // Resets gyro value to 0
        if (driveController.getAButton()) {
            gyro.setAngleAdjustment(0);
            gyro.setAngleAdjustment(-super.smooshGyroAngle(gyro.getAngle()));
            
        }

        
        //Starts driving based on LimeLight if the button is pushed and we have a target
        if ((squareButton == true || offSetButton == true) && tv == 1.0/* && ultraIn > endDistance*/) {
            
            //Declaring the left and right command speeds and setting it equal to the driveSpeedConstant
            double left_command = driveSpeedConstant;
            double right_command = driveSpeedConstant;

            //Declares and instaniates steering_adjust, and sets it to txkP * tx
            double steering_adjust = 0.0;
            steering_adjust += txkP * tx;

            // Declare and adjust targetAngle based on currentAngle
            double targetAngle;
            if(squareButton) {
                targetAngle = super.getSquareAngleForPort(currentAngle);
            }
            else {
                targetAngle = super.getOffsetHatchAngle(currentAngle);
            }

            //Declaring the offset angle for turning
            double angleDifference = currentAngle - targetAngle;
            //This is an alternative angleDifference
            double secondAngleDifference = targetAngle - 360 + currentAngle; 
            //This is where we define which one we want to use. 
            //Takes which ever one is closer to 0
            if (Math.abs(secondAngleDifference) < Math.abs(angleDifference)) {
                angleDifference = secondAngleDifference;
            }
            //To remove gyro control, comment out this line:
            steering_adjust += angleDifferencekP * angleDifference;
            
            // Setting LimeLight drive for tank drive
            left_command += steering_adjust;
            right_command -= steering_adjust;
            drive.tankDrive(left_command, right_command, 0.0, false, SpeedMode.FAST);
            

            usingLimeLight = true;
        } // end of if statement

        else {
            usingLimeLight = false;
        }
    }

}
    