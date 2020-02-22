/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import frc.controller.*;
import frc.singularityDrive.*;
import frc.controller.controlSchemes.ArcadeDrive;
import frc.controller.controlSchemes.SmartArcadeDrive;
import frc.controller.autonomous.*;
//import frc.controller.controlSchemes.Test;
import frc.robot.Canifier;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  int colorSol1, colorSol2;
  int colorSpinner;
  
  int flywheelMotor1, flywheelMotor2, flywheelMotor3;
  int conveyorMotor1, conveyorMotor2;
  int collectorMotor1;
  int collectorSol1, collectorSol2;
  int downMotorPort;

  //Declaration of our driving scheme, which can be initialized to
  //any ControlScheme in robotInit()
  ControlScheme currentScheme;

  //Declaration of mechanisms
  SingDrive drive;
  SmartSingDrive smartDrive; //if we want to use smart motion, change this to SmartSingDrive
  DrivePneumatics drivePneumatics;
  Flywheel flywheel;
  Conveyor conveyor;
  CellCollector collector;
  Climber climber;

  //Creates an all-knowing limelight
  LimeLight limeLight;  // or CitrusSight?

  //Create a CANifier
  Canifier canifier;

  //Create a ColorSensor
  ColorSensor colorSensor;

  //Create a gyro
  AHRS gyro;
  boolean gyroResetAtTeleop;

  //Compressor compressor;
  Compressor compressor;

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
    //Uncomment to initialize motor controllers aswell - commented for texting purposes
    setDefaultProperties();

    //initialize our driving scheme to a basic arcade drive
    currentScheme = new SmartArcadeDrive(XBOX_PORT, XBOX_PORT +1);
    
    gyro = new AHRS(SPI.Port.kMXP);
    gyroResetAtTeleop = true;

    colorSensor = new ColorSensor(colorSpinner, colorSol1, colorSol2);
    
    //initialize all mechanisms on the robot
    smartDrive = new SmartBasicDrive(driveLeft1, driveLeft2, driveLeft3, driveRight1, driveRight2, driveRight3);
    drive = new BasicDrive(driveLeft1, driveLeft2, driveLeft3, driveRight1, driveRight2, driveRight3);
    // ^^^^^^^ change this to SmartBasicDrive if using SmartDrive
    drivePneumatics = new DrivePneumatics(drivePneu1, drivePneu2);
    flywheel = new Flywheel(flywheelMotor1, flywheelMotor2, flywheelMotor3);
    conveyor = new Conveyor(conveyorMotor1);
    collector = new CellCollector(collectorMotor1, collectorSol1, collectorSol2);
    climber = new Climber(downMotorPort);
    
    limeLight = new LimeLight();
    //limeLight.setCamMode(limeLight, 0.0);
    //DO NOT REMOVE PLZ - starts collecting data from drive cameras
    //start collecting data from drive cameras
    // This is not used if the raspberry pi is being used for image compression
    //CameraServer.getInstance().startAutomaticCapture();

    //gyro = new AHRS(SPI.Port.kMXP);
    //gyroResetAtTeleop = true;

    //tutorial code for the sendableChooser in case it breaks
    /*autoChooser = new SendableChooser();
    autoChooser.addDefault("Default Auto", new TestAuton(drive, limeLight));
    autoChooser.addOption("SupremeAuto", new JustMove(drive, limeLight));
    SmartDashboard.putData("Auto mode", autoChooser);*/
    
    compressor = new Compressor();
    goalChooser = new SendableChooser<String>();
    positionChooser = new SendableChooser<String>();
    secondaryChooser = new SendableChooser<String>();

    goalChooser.addDefault("Nothing", "-1");
    goalChooser.addOption("Target", "0");
    goalChooser.addOption("Trench", "1");

    positionChooser.addDefault("Position 1", "0");
    positionChooser.addOption("Position 2", "1");
    positionChooser.addOption("Position 3", "2");

    secondaryChooser.addDefault("Nothing", "-1");
    secondaryChooser.addOption("Trench", "0");
    secondaryChooser.addOption("Move Away", "1");

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

    compressor.start();

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
   AutonControlScheme[][] goals={{new Lightning1(drive, limeLight), new Trench1(drive, limeLight)},
                                {new Lightning2(drive, limeLight), new Trench2(drive, limeLight)},
                                {new Lightning3(drive, limeLight), new Trench3(drive, limeLight)}};
    
    AutonControlScheme[] secondaryGoals = {new MoveToTrench(drive,limeLight), new MoveAwayFromTarget(drive, limeLight)};
    
    SmartDashboard.putNumber("result of position", Integer.parseInt((String)positionChooser.getSelected()));
    SmartDashboard.putNumber("result of goals", Integer.parseInt((String)goalChooser.getSelected()));
    SmartDashboard.putNumber("result of secondary goals", Integer.parseInt((String)secondaryChooser.getSelected()));

    /*if(goalChooser.getSelected().equals("-1")){
      //SmartDashboard.putString("autoprogram", "JustMove");
      new JustMove(drive, limeLight).moveAuton();
    }
    else{
      //SmartDashboard.putString("autoprogram", "PrimaryGoals");
      goals[Integer.parseInt((String)positionChooser.getSelected())][Integer.parseInt((String)goalChooser.getSelected())].moveAuton();
    }
    if(!secondaryChooser.getSelected().equals("-1") && !goalChooser.getSelected().equals("-1")){
      //SmartDashboard.putString("autonprogram", "SecondaryGoals");
      secondaryGoals[Integer.parseInt((String)secondaryChooser.getSelected())].moveAuton();
    }*/
    
    AutonControlScheme hodl = new TestAuton(drive, limeLight);
    hodl.moveAuton();
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
    drive.setInitialPosition();
  }

  /**
   * Function that contains everything that will run in the teleop perio/option in DS
   */
  @Override
  public void teleopPeriodic() {

    // Allow driver control based on current schem
/*
    boolean colorData[] = canifier.getPinData();
    int color = canifier.binToDecColor(colorData);
    int count = canifier.binToDecCount(colorData);
    String byteString = canifier.byteArrayToString(colorData);

    SmartDashboard.putNumber("Current Color: ", color);
    SmartDashboard.putNumber("Current Count: ", count);
    SmartDashboard.putString("Byte Transfered: ", byteString);
*/  
    //colorSensor.spinColorWheelColor(2);
    currentScheme.colorSensor(colorSensor);

    SmartDashboard.putNumber("EncoderPosition", drive.getCurrentPosition());
    currentScheme.smartDrive(smartDrive, drivePneumatics);
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
    compressor.start();
    currentScheme.climberReset(climber);
  }

  
  /**
   * Assigning port numbers to motors, solenoids, etc.
   */
  private void setDefaultProperties() {
    
    // Drive Motors
    driveLeft1 = 4;
    driveLeft2 = 5;
    driveLeft3 = 6;
    driveRight1 = 1;
    driveRight2 = 2;
    driveRight3 = 3;

    colorSpinner = 16;

    driveLeft2 = 5;
    driveLeft3 = 6;
    driveRight1 = 1;
    driveRight2 = 2;
    driveRight3 = 3;

    // Flywheel motors
    flywheelMotor1 = 11;
    flywheelMotor2 = 12;
    flywheelMotor3 = 8;

    // Conveyor motors
    conveyorMotor1 = 7;

    // Cell Collector Motor
    collectorMotor1 = 9;

    // Climber Motor Ports
    downMotorPort = 13;


    //Pneumatics
    
    drivePneu1 = 1;
    drivePneu2 = 6;

    collectorSol1 = 2;
    collectorSol2 = 5;

    colorSol1 = 0;
    colorSol2 = 7;

    

  }



}
