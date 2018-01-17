package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Encoders extends Subsystem {
	
	public static Encoder enc0 = new Encoder(RobotMap.enc0APort, RobotMap.enc0BPort, false, Encoder.EncodingType.k4X);
	public static Encoder enc1 = new Encoder(RobotMap.enc1APort, RobotMap.enc1BPort, false, Encoder.EncodingType.k4X);
	
    public void initDefaultCommand() {
    	
    }
    
}

