package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.CellCollector;
import frc.robot.Conveyor;
import frc.robot.DrivePneumatics;
import frc.robot.Flywheel;
import frc.robot.LimeLight;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.SpeedMode;

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

    //Create all objects & a speedMode object
    XboxController driveController, armController;

    SpeedMode speedMode;

    boolean lowGear;

    //vision variables
    double ty, tx, tv;
    final double driveSpeedConstant = 0.3;
    final double txkP = 0.022;
    final double angleDifferencekP = 0.011;
    final double endDistance = 2.0;

    /**
     * 
     * @param driveControllerPort Controller port the drive controller is connected to, probably 0
     * @param armControllerPort Controller port the arm controller is connect to, problably 1
     */
    public ArcadeDrive(int driveControllerPort, int armControllerPort) {
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);

        lowGear = true;
        speedMode = SpeedMode.SLOW;

    }

    /**
     * Drives arcade drive
     * 
     */
    public void drive(SingDrive drive/*, DrivePneumatics pneumatics*/) {
        //Switch speed mode object, set to low with left bumber and high with right bumper
        if(driveController.getLB()) {
            speedMode = SpeedMode.SLOW;
        }

        else if(driveController.getRB()) {
            speedMode = SpeedMode.FAST;
        }
        //Put current speedMode on SmartDashboard
        SmartDashboard.putString("Speed Mode", "" + speedMode);


        //Change physical pneumatic gearing with the start button (high gear) and back button (low gear).
        //This sets a boolean lowGear
        if(driveController.getStartButton()) {
            lowGear = false;
        }

        else if(driveController.getBackButton()) {
            lowGear = true;
        }

        // lowGear is used to actually set the drive pneumatics to intended value.
        if(lowGear) {
            //pneumatics.setLow();
        }

        else {
            //pneumatics.setHigh();
        }

        //
        //IMPORTANT
        //
        //The only line actually needed to drive - takes in control sticks, speed mode, and drives based on BasicDrive.
        drive.arcadeDrive(driveController.getLS_Y(), driveController.getRS_X(), 0.0, true, speedMode);

        // Use the d-pad/POV hat on the gamepad to drive the robot slowly in any direction for precise adjustments.
        if(driveController.getPOVLeft()) {
            drive.arcadeDrive(0, -0.1, 0.0, false, SpeedMode.FAST);
        }
        else if (driveController.getPOVRight()) {
            drive.arcadeDrive(0.0, 0.1, 0.0, false, SpeedMode.FAST);
        }
        else if (driveController.getPOVDown()) {
            drive.arcadeDrive(-0.1, 0.0, 0.0, false, SpeedMode.FAST);
        }
        else if (driveController.getPOVUp()) {
            drive.arcadeDrive(0.1, 0, 0.0, false, SpeedMode.FAST);
        }

    }
    
    /**
     * Controls flywheel - turns only on (forward) and off
     * @param flywheel Takes in flywheel object to control.
     */
    public void flywheel(Flywheel flywheel) {
        // When A is pressed and held, turn on the flywheel to the constant speed defined in the class.
        if(armController.getRB()) {
            flywheel.flywheelForward();
        }

        else {
            flywheel.flywheelOff();
        }
    }

    /**
     * Controls the conveyor on the bot using two buttons to move it
     * forward or backward
     * 
     * @param converyor Takes in a conveyor object to control
     */
    public void conveyor(Conveyor conveyor){
        //Conveyor will be moved forward with the B button
        if(armController.getBButton()) {
            conveyor.conveyorForward();
        }
        //And reversed with the x button - opposite each other on gamepad
        else if(armController.getXButton()) {
            conveyor.conveyorReverse();
        }
        //turns conveyor off if nothing is happening
        else {
            conveyor.converyorOff();
        }
    }

    public void collector(CellCollector collector) {
        if(armController.getLB()) {
            collector.collectorForward();
        }

        else {
            collector.collectorOff();
        }
    }

    /**
     * Only turns on the painfully bright Limelight LEDs when they're being used
     * @param limelight takes in Limelight object
     */
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

        SmartDashboard.putNumber("tx", tx);
        SmartDashboard.putNumber("tv", ty);

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
        if ((squareButton == true || offSetButton == true) && tv == 1.0) {
            
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
        }
    }//end of limeLightDrive

}
    