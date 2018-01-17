/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6758.robot.commands.ExampleCommand;
import org.usfirst.frc.team6758.robot.subsystems.Encoders;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *  Information
 * 
 *  This is the main robot code for our robot 
 *  in the 2018 FRC game Power Up
 *  
 *  Last updated on 1/15/2018 - Version 0.9.2
 *  
 *  -ADDED TalonSRX variables
 *  -FIXED location of variables for pneumatics, and encoders
 *  
 *  Current Version 0.9.2
 */
public class Robot extends TimedRobot {
	public static OI m_oi;

	public static MecanumDrive driveTrain;
	
	public static Joystick stick = new Joystick(0);
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public static Compressor compressor = new Compressor(0);

	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		WPI_TalonSRX frontLeft = new WPI_TalonSRX(0);
		WPI_TalonSRX frontRight = new WPI_TalonSRX(1);
		WPI_TalonSRX backLeft = new WPI_TalonSRX(2);
		WPI_TalonSRX backRight = new WPI_TalonSRX(3);
		
		Pneumatics.testSolenoid.set(DoubleSolenoid.Value.kForward);
		driveTrain = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
	}
	
	@Override
	public void robotPeriodic() {
		
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		//Will get the selected auto mode from a list
		m_autonomousCommand = m_chooser.getSelected();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(Encoders.enc0.getDistance());
		
		if(OI.stick.getTrigger()) {
			Pneumatics.testSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
		else{
			Pneumatics.testSolenoid.set(DoubleSolenoid.Value.kForward);
		}
		
		driveTrain.driveCartesian(stick.getX(), stick.getY(), stick.getTwist());
		

	}

	@Override
	public void testPeriodic() {
	}
}
