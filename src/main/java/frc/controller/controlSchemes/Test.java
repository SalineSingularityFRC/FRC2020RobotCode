package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.*;
import frc.singularityDrive.*;

// Alternative ControlScheme to ArcadeDrive.java (control scheme) to test different mechaninsms without breaking everything
// ControlSchemes can be changed in Robot.java

//Uncomment to enable Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Uncomment to enable gyro
//import com.kauailabs.navx.frc.AHRS;

public class Test extends ControlScheme{
    XboxController driveController;
    XboxController armController;

    public Test(final int driveControllerPort, final int armControllerPort){
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);
    }

    public void drive(final SingDrive drive, final DrivePneumatics pneumatics) {
        
    }

    public void Conveyor(final ConveyorBelt conveyor){
        if(armController.getBButton()){
        conveyor.conveyorBeltForward();
        }
        else if(armController.getXButton()){
        conveyor.conveyorBeltReverse();
        }
    } 
 
    public void ledMode(final LimeLight limeLight){
    }

    public void Flywheel(final Flywheel flywheel){
        if(armController.getBButton()){
            flywheel.flywheelForward();

        }
        else if(armController.getXButton()){
            flywheel.flywheelReverse();
        }
    }

    public void Collector(final Collector collector){
        if(armController.getBButton()){
            collector.collectorForward();
        }
        else if(armController.getXButton()){
            collector.collectorReverse();
        }
    }

   

   
}