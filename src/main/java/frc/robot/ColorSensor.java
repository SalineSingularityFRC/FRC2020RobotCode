package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.controller.motorControllers.Spark;

public class ColorSensor{

    Spark colorSpinner;
    Canifier canifier;
    DoubleSolenoid colorSolenoid;
    int pistonExtend;
    int pistonRetract;

    private double speed = SmartDashboard.getNumber("Current Color Motor Speed: ", 0.75); //subject to change



    public ColorSensor(int colorSensorPort){
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

        if(count <= numRotations) {
            colorSpinner.setSpeed(this.speed);
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
            colorSpinner.setSpeed(this.speed/2);
        }
        else {
            colorSpinner.setSpeed(0.0);
        }
    }

    public void extend() {
        colorSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void retract() {
        colorSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

} 