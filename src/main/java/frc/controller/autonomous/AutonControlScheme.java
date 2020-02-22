package frc.controller.autonomous;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.LimeLight;
import frc.singularityDrive.SingDrive;
import frc.singularityDrive.SingDrive.*;

public abstract class AutonControlScheme {

    protected static AHRS gyro;
    protected static SingDrive drive;
    protected static LimeLight limeLight;

    public static final double radius = 3;
    
    public static final double encoderTicks = 15;

    public AutonControlScheme(SingDrive drive, LimeLight limeLight){
        //define Limelight and all the sensors
        this.drive = drive;
        this.gyro = new AHRS(SPI.Port.kMXP);
        this.limeLight = limeLight;
    }

    //the main method of each auton programs
    public abstract void moveAuton();
    
    /**
     * How to make the robot move forward or backwards autonomously.
     * DISCLAIMER If you want the robot to go backwards set verticalSpeed number to negative
     * @param distance the absolute value of the distance in inches the robot will travel 
     * @param verticalSpeed The speed between -1 and 1 the robot will go. 
     */
    public void vertical(double distance, double verticalSpeed){

        drive.setInitialPosition();

        while ( drive.getCurrentPosition() / encoderTicks > -distance /( 2* Math.PI *radius)
                && drive.getCurrentPosition() / encoderTicks < distance / ( 2* Math.PI *radius)) {
        
            SmartDashboard.putNumber("encoderPo", drive.getCurrentPosition());
            SmartDashboard.putNumber("goal", distance / radius);

            drive.arcadeDrive(0.1, 0, 0.0, false, SpeedMode.NORMAL);
        
		}
    }

    public void rotate(double angle, boolean isCounterClockwise){
        rotate(0.2, angle, isCounterClockwise);
    }

    public void rotate(double rotationSpeed, double angle, boolean isCounterClockwise){
        gyro.reset();
        if(isCounterClockwise) rotationSpeed*= -1;
		while(gyro.getAngle() < angle) {
			
			//TODO accelerate motors slowly
			//drive.rampVoltage();
			
			drive.arcadeDrive(0.0, rotationSpeed, 0.0, false, SpeedMode.NORMAL);
		}
		
		drive.arcadeDrive(0.0, 0.0, 0.0, false, SpeedMode.NORMAL);

    }

    public void adjustToTarget(){
        while(drive.limeLightDrive(limeLight, drive, gyro, false, true, false));
    }

    public void shoot(){

    }



}