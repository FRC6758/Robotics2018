package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.RobotMap;
import org.usfirst.frc.team6758.robot.commands.DriveRobot;

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
		setDefaultCommand(new DriveRobot());
	}
	
	public void resetDistance() {
		encLeft.reset();
		encRight.reset();
	}

	public void driveForward(double speed) {
		//not sure if this goes backwards or forwards
		left.set(RobotMap.defaultAutonSpeed-.05);
		right.set(-RobotMap.defaultAutonSpeed);
	}
	
	public void driveBackward(double speed) {
		//This may drive forward
		left.set(-RobotMap.defaultAutonSpeed-.05);
		right.set(RobotMap.defaultAutonSpeed);
	}
	
	public void driveClock(double speed) {
		//This might go Counterclockwise
		left.set(RobotMap.defaultAutonSpeed);
		right.set(RobotMap.defaultAutonSpeed);
	}
	
	public void driveCounter(double speed) {
		//This might go Clockwise
		left.set(-RobotMap.defaultAutonSpeed);
		right.set(-RobotMap.defaultAutonSpeed);
	}
	
	public void stop() {
		left.set(0);
		right.set(0);
	}
}
