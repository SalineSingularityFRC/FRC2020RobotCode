package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.CellCollector;
import frc.robot.DrivePneumatics;
import frc.robot.Flywheel;
import frc.robot.Vision;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.SpeedMode;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.*;
import com.kauailabs.navx.frc.AHRS;

/**
 * 
 * Main class to control the robot
 * 
 */
public class ArcadeDrive extends ControlScheme {

    XboxController driveController, armController;

    SpeedMode speedMode;

    boolean lowGear;

     /**
      * Sets ports for the drive controller and the arm controller 
      * Sets the variables for lowGear and speedMode
      * @param driveControllerPort
      * @param armControllerPort
      */
    public ArcadeDrive(int driveControllerPort, int armControllerPort) {
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);

        lowGear = true;
        speedMode = SpeedMode.SLOW;
    }

    /**
     * Sets low gear and high gear when you press a button
     * Sets speed mode to slow or fast if you press a button
     */
    public void drive(SingDrive drive, DrivePneumatics pneumatics) {
        if(driveController.getLB()) {
            speedMode = SpeedMode.SLOW;
        }

        else if(driveController.getRB()) {
            speedMode = SpeedMode.FAST;
        }
        SmartDashboard.putString("Speed Mode", "" + speedMode);

        if(driveController.getStartButton()) {
            lowGear = false;
        }

        else if(driveController.getBackButton()) {
            lowGear = true;
        }
        
        if(lowGear) {
            pneumatics.setLow();
        }

        else {
            pneumatics.setHigh();
        }

        drive.arcadeDrive(driveController.getLS_Y(), driveController.getRS_X(), 0.0, true, speedMode);
    }
    /**
     * Sets flywheel to forward speed and turn off when you press a button
     * 
     * @param flywheel
     */
    public void Flywheel(Flywheel flywheel) {

        if(armController.getAButton()) {
            flywheel.forwardSpeed();
        }

        else {
            flywheel.offSpeed();
        }

    }

    public void CellCollector(CellCollector cellCollector) {

        if(armController.getLB()) {
            cellCollector.cellCollectorForward();
        }

        else {
            cellCollector.cellCollectorOff();
        }

        if(armController.getYButton()) {
            cellCollector.setHigh();
        }

        else if(armController.getAButton()) {
            cellCollector.setLow();
        }
    }

    

    public void visionDrive(Vision vision, SingDrive drive, DrivePneumatics dPneumatics, AHRS gyro, Ultrasonic ultra) {
        // Defining tx and tv
        // tx = X coordinate between -27 and 27
        // tv = 0 if no target found, 1 is target found
        tx = vision.tx.getDouble(0.0);
        tv = vision.tv.getDouble(0.0);

        //SmartDashboard.putNumber("Inches", ultraIn);

        // Declaring and instantiating buttons used for enabling vision drive
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

        
        //Starts driving based on vision if the button is pushed and we have a target
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
            
            // Setting vision drive for tank drive
            left_command += steering_adjust;
            right_command -= steering_adjust;
            drive.tankDrive(left_command, right_command, 0.0, false, SpeedMode.FAST);
            

            usingVision = true;
        } // end of if statement

        else {
            usingVision = false;
        }
    }
    public void ledMode( Vision vision ){
        if(driveController.getXButton() || driveController.getYButton()){
            vision.ledMode.setDouble(3.0);
        }
        else{
            vision.ledMode.setDouble(1.0);
        }
    }

}
    