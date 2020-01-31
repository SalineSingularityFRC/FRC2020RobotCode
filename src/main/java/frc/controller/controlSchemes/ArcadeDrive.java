package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.Collector;
import frc.robot.ConveyorBelt;
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

    XboxController driveController, armController;

    SpeedMode speedMode;//this comes from control scheme

    boolean lowGear;

    public ArcadeDrive(int driveControllerPort, int armControllerPort) {
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController (armControllerPort);

        lowGear = true;
        speedMode = SpeedMode.SLOW;




        
    }

    /**
     * Drives arcade drive
     * 
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
    else{
        pneumatics.setHigh();
    }

    drive.arcadeDrive(driveController.getLS_Y(), driveController.getRS_X(), 0.0, true, speedMode);

}
   
    public void Flywheel(Flywheel flywheel) {
        if(armController.getAButton()) {//armController because this is anything but driving 
            flywheel.flywheelForward();
            //the dot helps you access things 
        }
        else {
            flywheel.flyWheelOff();
        }
    }
    
    public void Conveyor(ConveyorBelt conveyor){
        if(armController.getBButton()){
            conveyor.conveyorBeltForward();
        }
        else if(armController.getXButton()){
            conveyor.conveyorBeltReverse();
        }
        
        else{
            conveyor.conveyorBeltOff();
        }
    }
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
    public void Collector(Collector collector){
        if(armController.getBButton()){
            collector.collectorForward();
        }
         else if(armController.getXButton()){
                collector.collectorReverse();
            }
        }

    
    }
    


    