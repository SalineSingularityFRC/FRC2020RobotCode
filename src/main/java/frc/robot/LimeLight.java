package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLight{

    public NetworkTable table;
    public NetworkTableEntry tx, ty, ta, tv, ledMode;

    //constructor to create the limelight and its values
    //////Branden Amstutz
    public LimeLight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
        ledMode = table.getEntry("ledMode");
        
    }

    public void ledOn( LimeLight limeLight ){
        
        limeLight.ledMode.setDouble(1.0);

    }
    public void LEDOff(LimeLight limeLight){
        limeLight.ledMode.setDouble(3.0);
    }
}