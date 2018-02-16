package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flywheels extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static WPI_TalonSRX flyLeft = new WPI_TalonSRX(RobotMap.mTrevor);
	public static WPI_TalonSRX flyRight = new WPI_TalonSRX(RobotMap.mKristina);

    public void initDefaultCommand() {
        //setDefaultCommand();
    }
    
    public static void off() {
    	flyLeft.set(0);
    	flyRight.set(0);
    }
    public static void toss() {
    	flyLeft.set(1);
    	flyRight.set(-1);
    }
    public static void grab() {
    	flyLeft.set(1);
    	flyRight.set(-1);
    }
}

