/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import org.usfirst.frc.team6758.robot.autonomous.DriveForward;
import org.usfirst.frc.team6758.robot.autonomous.LeftTimed;
import org.usfirst.frc.team6758.robot.autonomous.Nothing;
import org.usfirst.frc.team6758.robot.autonomous.RightTimed;
import org.usfirst.frc.team6758.robot.autonomous.TimedMiddle;
import org.usfirst.frc.team6758.robot.subsystems.Climber;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Elevator;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	double controllerPOV;
	
	public static OI m_oi;
	public static Joystick stick = new Joystick(0);
	public static char switchPosition;
	
	Command m_autonomousCommand;
	public SendableChooser<Command> m_chooser;
	public static SendableChooser<Integer> locationChooser;
	
	public UsbCamera camera;

	public static Compressor compressor = new Compressor(0);
	
	protected int position;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Elevator elevator = new Elevator();
	public static final OI oi = new OI();
	public static final Climber climber = new Climber();

	public SendableChooser<Command> autonChooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		m_oi = new OI();
	
		camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(352,  240);
		camera.setFPS(30);
		
		//Auton chooser being activated
		autonChooser.addDefault("Nothing", new Nothing());
		autonChooser.addObject("Drive Forward TIMED", new DriveForward(12));
		autonChooser.addObject("Middle Cube TIMED", new TimedMiddle()); //TODO Dail in TimedMiddle()
		autonChooser.addObject("Left Cube TIMED", new LeftTimed()); //TODO Dail in LeftTimed()
		autonChooser.addObject("Right Cube TIMED", new RightTimed()); //TOOD Dail in RightTimed()
		System.out.println("AutonChooser Created - Robot.java : 80");
		
		SmartDashboard.putData("Auto mode", autonChooser);
		
		compressor.setClosedLoopControl(true);
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
		
		switchPosition = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		m_autonomousCommand = autonChooser.getSelected();
		System.out.println(autonChooser.getSelected() + " Selected!");
		
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
		
		driveTrain.resetDistance();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
