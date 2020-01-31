package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

//what does it do?

public class DrivePneumatics  {

	DoubleSolenoid doubleSolenoid;
	/**
	 * 
	 * @param forwardChannel the channel the forward solenoid is connected to on the PCM
	 * @param reverseChannel the channel the reverse solenoid is connected to on the PCM
	 */

	public DrivePneumatics(int forwardChannel, int reverseChannel) {
     doubleSolenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
}

	public void setHigh() {
		doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

public void setLow() {
	doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
}
	public void setoff() {
		doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
