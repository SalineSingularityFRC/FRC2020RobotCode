package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.controller.motorControllers.Spark;

public class ColorSensor{

    Spark colorSpinner;
    Canifier canifier;
    public static final double speed = SmartDashboard.getNumber("Current Color Motor Speed: ", 0.50); //subject to change
    public static final double lowspeed = SmartDashboard.getNumber("Current Color Motor Speed: ", 0.20); //subject to change
    DoubleSolenoid colorSolenoid;
    int pistonExtend;
    int pistonRetract;



    public ColorSensor(int colorSensorPort, int pistonExtend, int pistonRetract){
        colorSpinner = new Spark(colorSensorPort, true, 0.00);
        canifier = new Canifier();
        colorSolenoid = new DoubleSolenoid(pistonExtend, pistonRetract);
    }
    public void setSpeed(double speed) {
        colorSpinner.setSpeed(speed);
    }

    public void spinColorWheelRotations(int numRotations) { // @param numRotations is actually the number of colors we want to see
        boolean colorData[] = canifier.getPinData();
        int color = canifier.binToDecColor(colorData);
        int count = canifier.binToDecCount(colorData);
        String byteString = canifier.byteArrayToString(colorData);
        
        SmartDashboard.putNumber("Current Color: ", color);
        SmartDashboard.putNumber("Current Count: ", count);
        SmartDashboard.putString("Byte Tranfered: ", byteString);

        if(count + 2 < numRotations) {
            colorSpinner.setSpeed(this.speed);
        }
        else if(count < numRotations) {
            colorSpinner.setSpeed(this.lowspeed);
        }
        else {
            colorSpinner.setSpeed(0.0);
            
        }
    }
    public void spinColorWheelColor(int targetColor) {
        boolean colorData[] = canifier.getPinData();
        int color = canifier.binToDecColor(colorData);
        int count = canifier.binToDecCount(colorData);
        String byteString = canifier.byteArrayToString(colorData);
        
        SmartDashboard.putNumber("Current Color: ", color);
        SmartDashboard.putNumber("Current Count: ", count);
        SmartDashboard.putString("Byte Tranfered: ", byteString);

        if(color != targetColor) {
            colorSpinner.setSpeed(this.lowspeed);
        }
        else {
            colorSpinner.setSpeed(0.0);
        }
    }
    public void stopSpinning() {
        colorSpinner.setSpeed(0);
    }

    public void spinSpeed(double speed) {
        colorSpinner.setSpeed(speed);
    }

    public void resetCount(boolean value) {
        canifier.resetPin(value);
    }

    public void extend() {
        colorSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void retract() {
        colorSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

} 