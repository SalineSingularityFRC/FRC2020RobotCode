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
import frc.controller.autonomous.*;
//import frc.controller.controlSchemes.Test;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  int flywheelMotor1, flywheelMotor2;
  int conveyorMotor1, conveyorMotor2;
  int collectorMotor1;

  //Declaration of our driving scheme, which can be initialized to
  //any ControlScheme in robotInit()
  ControlScheme currentScheme;

  //Declaration of mechanisms
  SingDrive drive;
  //DrivePneumatics drivePneumatics;
  //Flywheel flywheel;
  //Conveyor conveyor;
  //CellCollector collector;

  //Creates an all-knowing limelight
  LimeLight limeLight;  // or CitrusSight?

  //Create a gyro
  AHRS gyro;
  boolean gyroResetAtTeleop;

  //Compressor compressor;

  //SendableChoosers
  SendableChooser goalChooser;
  SendableChooser positionChooser;
  SendableChooser secondaryChooser;
  
  //SendableChooser autoChooser;

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
    //TODO un-comment methods
    //initialize motor controller ports IDs
    setDefaultProperties();

    //initialize our driving scheme to a basic arcade drive
    currentScheme = new ArcadeDrive(XBOX_PORT, XBOX_PORT +1);
    
    //initialize all mechanisms on the robot
    drive = new BasicDrive(driveLeft1, driveLeft2, driveLeft3, driveRight1, driveRight2, driveRight3);
    //drivePneumatics = new DrivePneumatics(drivePneu1, drivePneu2);
    //flywheel = new Flywheel(flywheelMotor1, flywheelMotor2);
    //conveyor = new Conveyor(conveyorMotor1, conveyorMotor2);
    //collector = new CellCollector(collectorMotor1);
    
    limeLight = new LimeLight();
    //limeLight.setCamMode(limeLight, 0.0);
    //DO NOT REMOVE PLZ - starts collecting data from drive cameras
    //CameraServer.getInstance().startAutomaticCapture();

    //gyro = new AHRS(SPI.Port.kMXP);
    //gyroResetAtTeleop = true;

    //tutorial code for the sendableChooser in case it breaks
    /*autoChooser = new SendableChooser();
    autoChooser.addDefault("Default Auto", new TestAuton(drive, limeLight));
    autoChooser.addOption("SupremeAuto", new JustMove(drive, limeLight));
    SmartDashboard.putData("Auto mode", autoChooser);*/
    
    //compressor = new Compressor();

    goalChooser = new SendableChooser();
    positionChooser = new SendableChooser();
    secondaryChooser = new SendableChooser();

    goalChooser.addDefault("Nothing", -1);
    goalChooser.addOption("Target", 0);
    goalChooser.addOption("Trench", 1);

    positionChooser.addDefault("Position 1", 0);
    positionChooser.addOption("Position 2", 1);
    positionChooser.addOption("Position 3", 2);

    secondaryChooser.addDefault("Nothing", -1);
    secondaryChooser.addOption("Trench", 0);
    secondaryChooser.addOption("Move Away", 1);

    SmartDashboard.putData("Position", positionChooser);
    SmartDashboard.putData("Primary Goal", goalChooser);
    SmartDashboard.putData("Secondary Goal", secondaryChooser);
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
    //Positions 1,2,3
    //Goals
    //  0: Target
    //  1: Trench
    //secondaryGoals
    //  0: move to Trench
    //  1: move away
    /*AutonControlScheme[][] goals={{new Lightning1(drive, limeLight), new Trench1(drive, limeLight)},
                                {new Lightning2(drive, limeLight), new Trench2(drive, limeLight)},
                                {new Lightning3(drive, limeLight), new Trench3(drive, limeLight)}};
    
    AutonControlScheme[] secondaryGoals = {new MoveToTrench(drive,limeLight), new MoveAwayFromTarget(drive, limeLight)};*/
    
    SmartDashboard.putNumber("result of position", (int) positionChooser.getSelected());
    SmartDashboard.putNumber("result of goals", (int) goalChooser.getSelected());
    SmartDashboard.putNumber("result of secondary goals", (int) secondaryChooser.getSelected());

    /*if(goalChooser.getSelected() == -1)
      new JustMove(drive, limeLight).moveAuton();
    else 
      goals[positionChooser.getSelected()][goalChooser.getSelected()].moveAuton();

    if(secondaryChooser.getSelected() != -1)
      secondaryGoals[secondaryChooser.getSelected()].moveAuton();*/

    /*AutonControlScheme hodl = new TestAuton(drive, limeLight);
    hodl.moveAuton();*/
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    currentScheme.drive(drive/*, drivePneumatics*/);
    
    //TODO un-comment
    //currentScheme.ledMode(limeLight);
  }

  //Stuff to run when teleop is selected
  @Override
  public void teleopInit() {
    drive.setInitialPosition();
  }

  /**
   * Function that contains everything that will run in the teleop perio/option in DS
   */
  @Override
  public void teleopPeriodic() {

    // Allow driver control based on current scheme
    // (we shouldn't need to change this too often- other than commenting)
    currentScheme.drive(drive/*, drivePneumatics*/);
    SmartDashboard.putNumber("EncoderPosi", drive.getCurrentPosition());
    // partial autonomy via vision
    //currentScheme.ledMode(limeLight);
    //control other various mechanisms
    //currentScheme.flywheel(flywheel);
    //currentScheme.conveyor(conveyor);
    //currentScheme.collector(collector);
    
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
    driveRight1 = 1;
    driveRight2 = 1;
    driveRight3 = 1;

    // Flywheel motors
    flywheelMotor1 = 7;
    flywheelMotor2 = 8;

    // Conveyor motors
    conveyorMotor1 = 9;
    conveyorMotor2 = 10;

    // Cell Collector Motor
    collectorMotor1 = 11;

    //Pneumatics
    
    drivePneu1 = 0;
    drivePneu2 = 1;

    

  }



}