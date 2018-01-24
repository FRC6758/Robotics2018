package org.usfirst.frc.team6758.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	public static DoubleSolenoid blockPusher = new DoubleSolenoid(0, 1);
	public static DoubleSolenoid grabber = new DoubleSolenoid(2, 3);
	
    public void initDefaultCommand() {
        
    }
    
    public static void pushBlock() {
    	blockPusher.set(DoubleSolenoid.Value.kReverse);
    }
    public static void clampBox() {
    	grabber.set(DoubleSolenoid.Value.kForward);
    }
    public static void retract() {
    	blockPusher.set(DoubleSolenoid.Value.kForward);
    }
    public static void releaseBox() {
    	grabber.set(DoubleSolenoid.Value.kReverse);
    }
    public static void off() {
    	grabber.set(DoubleSolenoid.Value.kOff);
    	blockPusher.set(DoubleSolenoid.Value.kOff);
    }
    
}

