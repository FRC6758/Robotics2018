package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	public static WPI_TalonSRX motorController0 = new WPI_TalonSRX(0);
	public static WPI_TalonSRX motorController1 = new WPI_TalonSRX(1);
	
	public static WPI_TalonSRX motorController2 = new WPI_TalonSRX(2);
	public static WPI_TalonSRX motorController3 = new WPI_TalonSRX(3);
	
	public static SpeedControllerGroup left = new SpeedControllerGroup(motorController0, motorController1);
	public static SpeedControllerGroup right = new SpeedControllerGroup(motorController2, motorController3);
	
	public static DifferentialDrive driveTrain = new DifferentialDrive(left, right); 
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}	

}
