package frc.controller.controlSchemes;

import frc.controller.*;
import frc.robot.*;
import frc.singularityDrive.*;
import com.kauailabs.navx.frc.AHRS;

// Alternative ControlScheme to ArcadeDrive.java (control scheme) to test different mechaninsms without breaking everything
// ControlSchemes can be changed in Robot.java

//Uncomment to enable Smart Dashboard
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Uncomment to enable gyro
//import com.kauailabs.navx.frc.AHRS;

public class Test extends ControlScheme{
    XboxController driveController;
    XboxController armController;

    public Test(int driveControllerPort, int armControllerPort){
        driveController = new XboxController(driveControllerPort);
        armController = new XboxController(armControllerPort);
    }

    public void drive(SingDrive drive/*, DrivePneumatics pneumatics*/) {
        
    }

    public void flywheel(Flywheel flywheel) {

    }

    public void conveyor(Conveyor conveyor) {
        
    }

    public void collector(CellCollector collector) {
        
    }

    public void climber(Climber climber) {
        
    }

    public void ledMode(LimeLight limeLight){
        
    }

    public void limeLightDrive(LimeLight limeLight, SingDrive drive, AHRS gryo){

    }
}