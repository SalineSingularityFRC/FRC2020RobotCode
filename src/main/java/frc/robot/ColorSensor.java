package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.controller.motorControllers.Spark;

public class ColorSensor{

    Spark colorSpinner;
    Canifier canifier;
    public static final double speed = SmartDashboard.getNumber("Current Color Motor Speed: ", 0.50); //subject to change
    public static final double lowspeed = SmartDashboard.getNumber("Current Color Motor Speed: ", 0.20); //subject to change



    public ColorSensor(int colorSensorPort){
        colorSpinner = new Spark(colorSensorPort, true, 0.00);
        canifier = new Canifier();
    }

    public void spinColorWheelRotations(int numRotations) {
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
        canifier.resetPin(true);
    }

} 