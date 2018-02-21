package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.RobotMap;
import org.usfirst.frc.team6758.robot.commands.OperationPneumatics;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

	private static Solenoid grabber = new Solenoid(RobotMap.grabberPort);
	private static Solenoid puncher = new Solenoid(RobotMap.puncherPort);
	
    public void initDefaultCommand() {
        setDefaultCommand(new OperationPneumatics());
    }
    
    public void clampBox() {
    	grabber.set(false);
    }
    public void releaseBox() {
    	grabber.set(true);
    }
    public void kick() {
    	puncher.set(true);
    }
    public void retract() {
    	puncher.set(false);
    }
    
}

