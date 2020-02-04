package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.controller.motorControllers.Spark;

public class ColorSensor{

    Spark colorSpinner;
    Canifier canifier;
    private double speed = SmartDashboard.getNumber("Current Color Motor Speed: ", 0.75); //subject to change



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

        if(count <= numRotations) {
            colorSpinner.setSpeed(this.speed);
        }
        else{
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


        if(color != targetColor) {
            colorSpinner.setSpeed(this.speed/2);
        }
        else {
            colorSpinner.setSpeed(0.0);
        }
    }

} 