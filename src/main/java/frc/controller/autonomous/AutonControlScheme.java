package frc.controller.autonomous;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

import com.kauailabs.navx.frc.AHRS;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.*;

public abstract class AutonControlScheme {

    protected static AHRS gyro;
    protected static SingDrive drive;

    //TODO find distance per revolution (circumference of the wheels)
    public static final double DistancePerRevolution = 1;
    
    public static final double encoderTicks = 4096;

    public AutonControlScheme(SingDrive drive){
        //define Limelight and all the sensors
        this.drive = drive;
        this.gyro = new AHRS(SPI.Port.kMXP);
    }

    //the main method of each auton programs
    public abstract void moveAuton();

    //TODO Reference the encoders to make the
    

    //private static double getAverage() { return (drive.getLeftPosition() + drive.getRightPosition()) / 2; }
    public void vertical(double distance, double verticalSpeed){

        while (drive.getCurrentPosition() > -distance*encoderTicks / DistancePerRevolution
                && drive.getCurrentPosition() < distance*encoderTicks / DistancePerRevolution) {
        
            SmartDashboard.putNumber("encoderPosition", drive.getCurrentPosition());
            drive.arcadeDrive(verticalSpeed, 0, 0.0, false, SpeedMode.NORMAL);
        
		}
    }

    public void rotate(double rotationSpeed, double angle, boolean counterClockwise){
        gyro.reset();
        if(counterClockwise) rotationSpeed*= -1;
		while(Math.abs(gyro.getAngle()) < angle) {
            
            SmartDashboard.putNumber("gyro Value", Math.abs(gyro.getAngle()));

			//TODO accelerate motors slowly
			//drive.rampVoltage();
			
			drive.arcadeDrive(0.0, rotationSpeed, 0.0, false, SpeedMode.NORMAL);
		}
		
		drive.arcadeDrive(0.0, 0.0, 0.0, false, SpeedMode.NORMAL);

    }

    public void detectTarget(){
        
    }

    public void shoot(){

    }



}