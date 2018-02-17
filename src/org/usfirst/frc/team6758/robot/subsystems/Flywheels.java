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
	public static WPI_TalonSRX flyLeft = new WPI_TalonSRX(RobotMap.mDeter);
	public static WPI_TalonSRX flyRight = new WPI_TalonSRX(RobotMap.mTrevor);

    public void initDefaultCommand() {
        //setDefaultCommand();
    }
    
    public void off() {
    	flyLeft.set(0);
    	flyRight.set(0);
    }
    public void toss() {
    	flyLeft.set(RobotMap.flywheelSpeed);
    	flyRight.set(-RobotMap.flywheelSpeed);
    }
    public void grab() {
    	flyLeft.set(-RobotMap.flywheelSpeed);
    	flyRight.set(RobotMap.flywheelSpeed);
    }
}

