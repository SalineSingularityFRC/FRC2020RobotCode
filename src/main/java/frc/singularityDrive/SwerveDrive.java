package frc.singularityDrive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;
import frc.controller.controlSchemes.*;
import frc.robot.LimeLight;
import frc.controller.ControlScheme;

//Referance SingDrive.java for comments about blocks of code that are confusing to you
//Most of this is coppied and pasted from that file

public class SwerveDrive  {

    protected Spark m_front_left_Wheel, m_front_right_Wheel, m_back_left_Wheel, m_back_right_Wheel;
    protected Spark m_front_left_Angle, m_front_right_Angle, m_back_left_Angle,  m_back_right_Angle;

    protected final static boolean DEFAULT_TO_BRUSHLESS = true;

    protected final static double DEFAULT_SLOW_SPEED_CONSTANT = 0.4;
	protected final static double DEFAULT_NORMAL_SPEED_CONSTANT = 0.8;
	protected final static double DEFAULT_FAST_SPEED_CONSTANT = 1.0;
    protected final static double smartMotionMaxRPM = 5700;
    
    protected double velocityMultiplier;

    protected Spark brushMotor1, brushMotor2, brushMotor3, brushMotor4;
    protected Spark brushlessMotor1, brushlessMotor2, brushlessMotor3, brushlessMotor4;

    public enum SpeedMode {
        FAST,
        NORMAL,
        SLOW
    }
    
    public final static double DEFAULT_RAMP_RATE = 0.5;
    public final static double MINIMUM_THRESHOLD = 0.05;
    public final static double DEFAULT_INPUT_POWER = 1.5;

    SpeedMode speedMode;

    boolean lowGear;
    boolean climberExtended;
    boolean climberDown;

    double tx, tv;
    
    //final double driveSpeedConstant = 0.3;
    final double txkP = 0.0022;
    final double angleDifferencekP = 0.011;
    final double endDistance = 2.0;

    //public abstract void arcadeDrive(double vertical, double rotation, double horizontal, boolean poweredInputs, SpeedMode speedMode);

    private double distance(double x1, double y1, double x2, double y2){
        return (Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))));
    }

    /**
     * 
     * @author Travis Crigger
     * 
     * @param vertical - How much we want to move forward & backward
     * @param horizontal -  How much we want to move Left and Right (Not previosly used by Singularity)
     * @param rotation - How much we want to rotate
     * @param (any)SpeedConstant - arbitrary speed multiplier constant for the speeds defined by which speed mode we are in
     * @param rotationSpeedConstant - arbitrary speed multiplier for how fast we rotate the wheels (MAY BE REMOVED)
     * 
     */

    public SwerveDrive(double vertical, double horizontal, double rotation, double slowSpeedConstant, double normalSpeedConstant, double fastSpeedCostant, double rotationSpeedConstant){
        double size = 1.0;

        double mFL_XPos_Curr = - Math.sqrt(2)/2.0;
        double mFL_YPos_Curr = Math.sqrt(2)/2.0;

        double mFR_XPos_Curr = Math.sqrt(2)/2.0;
        double mFR_YPos_Curr = Math.sqrt(2)/2.0;

        double mBL_XPos_Curr = -Math.sqrt(2)/2.0;
        double mBL_YPos_Curr = -Math.sqrt(2)/2.0;

        double mBR_XPos_Curr = Math.sqrt(2)/2.0;
        double mBR_YPos_Curr = -Math.sqrt(2)/2.0;

        double mFL_XPos_Next = horizontal + (Math.cos(-(rotation * rotationSpeedConstant) - (5.0 * Math.PI/4.0)));
        double mFL_YPos_Next = vertical + (Math.sin(-(rotation * rotationSpeedConstant) - (5.0 * Math.PI/4.0)));

        double mFR_XPos_Next = horizontal + (Math.cos(-(rotation * rotationSpeedConstant) - (7.0 * Math.PI/4.0)));
        double mFR_YPos_Next = vertical + (Math.sin(-(rotation * rotationSpeedConstant) - (7.0 * Math.PI/4.0)));
        
        double mBL_XPos_Next = horizontal + (Math.cos(-(rotation * rotationSpeedConstant) - (3.0 * Math.PI/4.0)));
        double mBL_YPos_Next = vertical + (Math.sin(-(rotation * rotationSpeedConstant) - (3.0 * Math.PI/4.0)));
        
        double mBR_XPos_Next = horizontal + (Math.cos(-(rotation * rotationSpeedConstant) - (Math.PI/4.0)));
        double mBR_YPos_Next = vertical + (Math.sin(-(rotation * rotationSpeedConstant) - (Math.PI/4.0)));

        //Angle adjusting motors will set the wheels to be pointed to the angle of these slopes:
        double mFL_Angle = Math.atan((mFL_YPos_Next - mFL_YPos_Curr)/(mFL_XPos_Next - mFL_XPos_Curr));
        double mFR_Angle = Math.atan((mFR_YPos_Next - mFR_YPos_Curr)/(mFR_XPos_Next - mFR_XPos_Curr));
        double mBL_Angle = Math.atan((mBL_YPos_Next - mBL_YPos_Curr)/(mBL_XPos_Next - mBL_XPos_Curr));
        double mBR_Angle = Math.atan((mBR_YPos_Next - mBR_YPos_Curr)/(mBR_XPos_Next - mBR_XPos_Curr));

        double mFR_Distance = distance(mFR_XPos_Curr, mFR_YPos_Curr, mFR_XPos_Next, mFR_YPos_Next);
        double mFL_Distance = distance(mFL_XPos_Curr, mFL_YPos_Curr, mFL_XPos_Next, mFL_YPos_Next);
        double mBL_Distance = distance(mBL_XPos_Curr, mBL_YPos_Curr, mBL_XPos_Next, mBL_YPos_Next);
        double mBR_Distance = distance(mBR_XPos_Curr, mBR_YPos_Curr, mBR_XPos_Next, mBR_YPos_Next);

        System.out.println(mFL_Angle);
        System.out.println(mFR_Angle);
        System.out.println(mBL_Angle);
        System.out.println(mBL_Angle + "\n");

        System.out.println(mFL_Distance);
        System.out.println(mFR_Distance);
        System.out.println(mBL_Distance);
        System.out.println(mBL_Distance + "\n");

        System.out.println(mFL_XPos_Next);
        System.out.println(mFL_YPos_Next);

        System.out.println(mFR_XPos_Next);
        System.out.println(mFR_YPos_Next);

        System.out.println(mBL_XPos_Next);
        System.out.println(mBL_YPos_Next);

        System.out.println(mBR_XPos_Next);
        System.out.println(mBR_YPos_Next);

    }
    public static void main(String[] args){
        SwerveDrive sDrive = new SwerveDrive(1, 1, 0.7853982, 1, 1, 1, 1);


    }

    private static double angleCalculator(double currentAngle, double futureAngle) {
        if (currentAngle < 0 || currentAngle > 360 || futureAngle < 0 || futureAngle > 360) {
            return -1;
        }

        double difference = futureAngle - currentAngle;

        if (difference <= 180 && difference >= -180) {
            return difference;
        }

        return (difference > 0) ? difference - 360 : difference + 360;
    }

}