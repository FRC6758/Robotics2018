package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(0);
	public static WPI_TalonSRX rearLeft = new WPI_TalonSRX(1);
	
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
	public static WPI_TalonSRX rearRight = new WPI_TalonSRX(3);
	
	public static SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
	public static SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
	
	public static DifferentialDrive driveTrain = new DifferentialDrive(left, right); 
	
	public static boolean front;
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public static boolean getFront() {
		return front;
	}

	public static void setFront(boolean front) {
		DriveTrain.front = front;
	}

	

}
