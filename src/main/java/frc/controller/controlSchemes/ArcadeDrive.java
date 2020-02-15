package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.CellCollector;
import frc.robot.Climber;
import frc.robot.Conveyor;
import frc.robot.DrivePneumatics;
import frc.robot.Flywheel;
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

    //Create all objects & a speedMode object
    XboxController driveController, armController;

    SpeedMode speedMode;

    boolean lowGear;
    boolean climberExtended;
    boolean climberDown;

    /**
     * 
     * @param driveControllerPort Controller port the drive controller is connected to, probably 0
     * @param armControllerPort Controller port the arm controller is connect to, problably 1
     */
    public ArcadeDrive(int driveControllerPort, int armControllerPort) {
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);

        lowGear = true;
        climberExtended = false;
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


    public void collectorConveyorFlywheel(Conveyor conveyor, CellCollector collector, Flywheel flywheel) {
        if(armController.getTriggerLeft() > .5) {
            flywheel.flywheelForward();
        }

        else {
            flywheel.flywheelOff();
        }

        if(armController.getLB()) {
            collector.collectorForward();
            conveyor.conveyorForward();
        }

        else {
            collector.collectorOff();
            conveyor.conveyorOff();
        }

        if(armController.getTriggerRight() > .5) {
            flywheel.flywheelFeedOn();
        }

        else {
            flywheel.flywheelFeedOff();
        }

        if(armController.getYButton()) {
            collector.collectorUp();
        }

        else if(armController.getAButton()) {
            collector.collectorDown();
        }
    }

    public void climber(Climber climber) {
        if(driveController.getBButton()) {
            //check this idk joystick
            climber.climberToPosition(armController.getRS_X());
            climberExtended = true;
        }

        if(!driveController.getBButton() && !driveController.getYButton() && climberExtended) {
            climber.climberHoldPosition();
        }

        else if(!driveController.getBButton() && driveController.getYButton() && climberExtended){
            climber.climberUp();
        }

        else if(driveController.getAButton() && climberExtended) {
            climber.climberDown();
            climberDown = true;
        }

        if(!driveController.getAButton() && climberDown) {
            climber.downStop();
        }

        
    }

    /**
     * Only turns on the painfully bright Limelight LEDs when they're being used
     * @param limelight takes in Limelight object
     */
    public void ledMode(LimeLight limeLight ){

        /*
        if(driveController.getXButton() || driveController.getYButton()){
            limeLight.ledOff(limeLight);
        }
        else{
            limeLight.ledOn(limeLight);;
        }
        */
    }

}
    