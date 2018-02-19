package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.mDJ);
	public static WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.mKeegan);
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.mApache);
	public static WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.mAndo);
	
	public SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
	public SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
	
	public DifferentialDrive driveTrain = new DifferentialDrive(left, right); 
	
	public Encoder encLeft = new Encoder(RobotMap.encLPortA, RobotMap.encLPortB, false, Encoder.EncodingType.k4X);
	public Encoder encRight = new Encoder(RobotMap.encRPortA, RobotMap.encRPortB, false, Encoder.EncodingType.k4X);
	
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand();
	}
	
	public void resetDistance() {
		encLeft.reset();
		encRight.reset();
	}

	public void driveForward(double speed) {
		driveTrain.arcadeDrive(speed, 0);
	}
	
	public void driveBackward(double speed) {
		driveTrain.arcadeDrive(-speed, 0);
	}
	
	public void driveClock(double speed) {
		driveTrain.arcadeDrive(0, speed);
	}
	
	public void driveCounter(double speed) {
		driveTrain.arcadeDrive(0, -speed);
	}
	
	public void stop() {
		driveTrain.arcadeDrive(0, 0);
	}
}
