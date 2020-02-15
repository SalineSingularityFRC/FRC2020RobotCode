/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.controller.*;
import frc.singularityDrive.*;
import frc.singularityDrive.SingDrive;
import frc.controller.controlSchemes.ArcadeDrive;
//import frc.controller.controlSchemes.Test;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //stores the motor controller IDs
  int driveLeft1, driveLeft2, driveLeft3, driveRight1, driveRight2, driveRight3;
  int drivePneu1, drivePneu2;
  int flywheelMotor1, flywheelMotor2, flywheelMotor3;
  int conveyorMotor1, conveyorMotor2;
  int collectorMotor1;
  int collectorSol1, collectorSol2;

  //Declaration of our driving scheme, which can be initialized to
  //any ControlScheme in robotInit()
  ControlScheme currentScheme;

  //Declaration of mechanisms
  SingDrive drive;
  DrivePneumatics drivePneumatics;
  Flywheel flywheel;
  Conveyor conveyor;
  CellCollector collector;
  Climber climber;

  //Creates an all-knowing limelight
  LimeLight limeLight;  // or CitrusSight?

  //Create a gyro
  AHRS gyro;
  boolean gyroResetAtTeleop;

  //Compressor compressor;

  //default ports of certain joysticks in DriverStation
  final int XBOX_PORT = 0;
	final int BIG_JOYSTICK_PORT = 1;
  final int SMALL_JOYSTICK_PORT = 2;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    
    //initialize motor controller ports IDs
    setDefaultProperties();

    //initialize our driving scheme to a basic arcade drive
    currentScheme = new ArcadeDrive(XBOX_PORT, XBOX_PORT +1);
    
    //initialize all mechanisms on the robot
    drive = new BasicDrive(driveLeft1, driveLeft2, driveLeft3, driveRight1, driveRight2, driveRight3);
    drivePneumatics = new DrivePneumatics(drivePneu1, drivePneu2);
    flywheel = new Flywheel(flywheelMotor1, flywheelMotor2, flywheelMotor3);
    conveyor = new Conveyor(conveyorMotor1);
    collector = new CellCollector(collectorMotor1, collectorSol1, collectorSol2);
    
    //limeLight = new LimeLight();
    //start collecting data from drive cameras
    // This is not used if the raspberry pi is being used for image compression
    //CameraServer.getInstance().startAutomaticCapture();

    gyro = new AHRS(SPI.Port.kMXP);
    gyroResetAtTeleop = true;
    
    
    //compressor = new Compressor();
    

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    //compressor.start();

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //currentScheme.drive(drive, drivePneumatics);
    //currentScheme.ledMode(limeLight);
  }

  //Stuff to run when teleop is selected
  @Override
  public void teleopInit() {
  }

  /**
   * Function that contains everything that will run in the teleop perio/option in DS
   */
  @Override
  public void teleopPeriodic() {

    // Allow driver control based on current scheme
    // (we shouldn't need to change this too often- other than commenting)
    currentScheme.drive(drive, drivePneumatics);
    // partial autonomy via vision
    //currentScheme.ledMode(limeLight);
    //control other various mechanisms
    currentScheme.collectorConveyorFlywheel(conveyor, collector, flywheel);
    currentScheme.climber(climber);
    
  }

  /**
   * Use test mode in drivers station to charge compressor.
   */
  @Override
  public void testPeriodic() {

    //compressor.start();
  }

  
  /**
   * Assigning port numbers to motors, solenoids, etc.
   */
  private void setDefaultProperties() {
    
    // Drive Motors
    driveLeft1 = 4;
    driveLeft2 = 4;
    driveLeft3 = 4;
    driveRight1 = 11;
    driveRight2 = 11;
    driveRight3 = 11;

    // Flywheel motors
    flywheelMotor1 = 7;
    flywheelMotor2 = 8;
    flywheelMotor3 = 12;

    // Conveyor motors
    conveyorMotor1 = 9;

    // Cell Collector Motor
    collectorMotor1 = 11;

    //Pneumatics
    
    drivePneu1 = 0;
    drivePneu2 = 1;

    collectorSol1 = 2;
    collectorSol2 = 3;

    

  }



}





