package frc.robot;
//Imports package for Double Solenoid
import edu.wpi.first.wpilibj.DoubleSolenoid;
//creates public class for Drive Pneumatics
public class DrivePneumatics {
	
	//Declares doubleSolenoid
	DoubleSolenoid doubleSolenoid;

	/**
	 * 
	 * @param forwardChannel the channel that the forward solenoid is connected to on the PCM
	 * @param reverseChannel the channel that the reverse solenoid is connected to on the PCM
	 */
	
	
	//creates constructor for doubleSolenoid

	public DrivePneumatics(int forwardChannel, int reverseChannel){
		doubleSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
	}

	//method to set Double Solenoid Value high
	public void setHigh(){
		doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	//method to set Double Solenoid Value low
	public void setLow(){
		doubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	//method to set Double Solenoid Value off
	public void setOff(){		
		doubleSolenoid.set(DoubleSolenoid.Value.kOff);
	}

}