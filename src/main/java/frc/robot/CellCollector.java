package frc.robot;

import frc.controller.MotorController;
import frc.controller.motorControllers.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 * Creates a class called CellCollector to make the cell collector go forward, reverse, and off
 * 
 * @param no param
 * @author Brian Liu
 */
public class CellCollector {

    MotorController motorControl;
    DoubleSolenoid doubleSolenoid; 

    private final double forwardSpeed = 0.25;
    private final double reverseSpeed = 0.25;

    /**
     * Creates the constructor for CellCollector
     * 
     * @param collectorMotor1
     * @author Brian Liu
     */
    public CellCollector(int collectorMotor1, int forwardChannel, int reverseChannel) {
        motorControl = new Spark(collectorMotor1, true, 0);
        doubleSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
    }


    /**
     * Set the speed of the cell collector to forwardSpeed
     * 
     * @param no param
     * @author Brian Liu
     */
    public void cellCollectorForward() {
        motorControl.setSpeed(forwardSpeed);
    }

    /**
     * Set the speed of the cell collector to reverseSpeed
     * 
     * @param no param
     * @author Brian Liu
     */

    public void cellCollectorReverse() {
        motorControl.setSpeed(reverseSpeed);
    }
        /**
         * Set the speed of the cell collector to 0 
         * 
         * @param no param
         * @author Brian Liu 
         */
    public void cellCollectorOff() {
        motorControl.setSpeed(0);
    }

    public void setHigh() {
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setLow() {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}