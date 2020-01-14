package frc.robot;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.controller.motorControllers.Spark;

public class Elevator {

    //Declaring new motor using a spark motor controller and our Spark class
    Spark m_motor;
    Spark m_motor2;

    //PID values used by the encoder on the Spark motor controller, these still need to be adjusted for our robot.
    //These for the moment are just placeholder values, they need to be adjusted for our robot.
    public final double kP = 0.01, kI = 1e-6, kD = 1e-4, kIZ = 0, kFF = 0, kMaxOut = 1, kMinOut = -1;
    //Rate that the motor speeds up at
    public final double rampRate = 0.4;

    //The ElevatorPosition enum lets us set the elevator to each of the desired positions to deliver hatch panels & cargo or to pick things up.
    public enum ElevatorPosition {
        BOTTOM, 
        HATCH1, 
        HATCH2, 
        HATCH3, 
        CARGO1,
        CARGO2,
        CARGO3,
        CARGOSHIP,
    }

    //Constants for the encoder position for each of our different desired positions.
    //These ATM are just placholder values, need to be tested for our robot
    final double bottomPos = 0.0;
    final double hatch1Pos = 0.0;
    final double hatch2Pos = -30.0;
    final double hatch3Pos = -60.0;
    final double cargo1Pos = -15.0;
    final double cargo2Pos = -45.0;
    final double cargo3Pos = -70.0;
    final double cargoShipPos = -22.0;

    //Constructor for Elevator Class, takes in the port the motor is plugged in to and whether the motor is brushless or not, along with the PID values
    //This also sets coast mode to false (therefor to brake), so the elevator stays in place when not being moved
    public Elevator(int motorPort, boolean brushlessMotor, int motorPort2, boolean brushlessMotor2) {
        m_motor = new Spark(motorPort, brushlessMotor, this.rampRate, "Elevator Motor 1", true, false, kP, kI, kD, kIZ, kFF, kMinOut, kMaxOut);
        m_motor.setCoastMode(false);
        

        m_motor2 = new Spark(motorPort2, brushlessMotor2, this.rampRate, "Elevator Motor 2", true, false, kP, kI, kD, kIZ, kFF, kMinOut, kMaxOut);
        m_motor2.setCoastMode(false);

    }


    //Basic function using the setToPosition function in the Spark class to move the encoder to a specified point (position).
    //This also takes in a joystick value, and as defined in Spark if the motor hasn't moved yet it will use joystick control until it hits the bottom endstop
    public void setPosition(double position, double joystickControl) {
        m_motor.setToPosition(joystickControl, position, 0.0);
    }

    /*
    Using the ElevatorPosition enum & constants, we can set the elevator to one of the desired positions
    for doing our different tasks using the setPositionWithEnum method. Works using the constants defined
    above and setToPosition from Spark, see above setPosition for more info. 3 Position for placing the
    hatches & 3 for placing the cargo on the rocket ship, one for cargo on the cargo ship, and one at the
    bottom.
    */
    public void setPositionWithEnum (ElevatorPosition elevatorPosition, double joystickControl) {

        double position = 0.0;

        switch (elevatorPosition) {
            case BOTTOM:
                position = bottomPos;
                break;
            
            case HATCH1:
                position = hatch1Pos;
                break;

            case HATCH2:
                position = hatch2Pos;
                break;

            case HATCH3:
                position = hatch3Pos;
                break;

            case CARGO1:
                position = cargo1Pos;
                break;

            case CARGO2:
                position = cargo2Pos;
                break;

            case CARGO3:
                position = cargo3Pos;
                break;

            case CARGOSHIP:
                position = cargoShipPos;
                break;
        }

        this.m_motor.setToPosition(0 - joystickControl, position, 0.0);

    }

    /*
    For testing purposes to figure out the correct encoder values, or as a backup, the elevator can manually
    be controlled using the setSpeed function. It also adds the current encoder position to the smart
    dashboard using the printEncoderPosition() function from the Spark class.
    */
    public void setSpeed(double speed) {
        m_motor.setSpeed(speed);
        m_motor.printEncoderPosition();
    }

    //For using the elevator with two motors, one up and one down 
    public void setSpeedWithTwoMotorsLowSpeed(double speed) {
        if (m_motor.isLowerLimitPressed(true) && speed < 0) {
            m_motor.setSpeed(0);
            m_motor2.setSpeed(0);
        }

        else {
            if(speed > 0) {
                m_motor.setSpeed(speed);
                m_motor2.setSpeed(0.01);
            
            }
            if(speed < 0) {
                m_motor2.setSpeed(speed);
                m_motor.setSpeed(0.01);
                //m_motor.setSpeed(Math.abs(speed))
            }
            else {
                m_motor.setSpeed(0);
                m_motor2.setSpeed(0);        
            }
        }
    }

    public void setSpeedWithTwoMotorsPercent(double speed) {
        //if (m_motor2.isLowerLimitPressed(true) && speed < 0){
        //    m_motor.setSpeed(0);
        //    m_motor2.setSpeed(0);
        //}
        //else {
            if(speed > 0.1) {
                m_motor.setSpeed(speed);
                m_motor2.setSpeed(speed / 4);
                SmartDashboard.putNumber("Elevator Speed", speed);
            }
            else if(speed < -0.1) {
                m_motor2.setSpeed(speed);
                m_motor.setSpeed(speed / 4);
                SmartDashboard.putNumber("Elevator Speed", speed);
            }
            else {
                m_motor.setSpeed(0.01);
                m_motor2.setSpeed(-0.01);
                SmartDashboard.putNumber("Elevator Speed", speed);
            }
        //}
        
    }

}