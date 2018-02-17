/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import java.net.Socket;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.usfirst.frc.team6758.robot.autonomous.AutoDriveLeft;
import org.usfirst.frc.team6758.robot.autonomous.Auton;
import org.usfirst.frc.team6758.robot.autonomous.AutonDrive;
import org.usfirst.frc.team6758.robot.autonomous.ThorHoldAuton;
import org.usfirst.frc.team6758.robot.autonomous.ThorsAuton;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Flywheels;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;
import org.usfirst.frc.team6758.robot.subsystems.ThorsHammer;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
	double controllerPOV;
	
	public static OI m_oi;
	public static Joystick stick = new Joystick(0);
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public UsbCamera camera;
	public Mat source, output;
	public GripPipeline grip;
	
	public static Compressor compressor = new Compressor(0);
	
	public static Socket sock;
	
	private int pov;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final ThorsHammer thorsHammer = new ThorsHammer();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Flywheels flywheels = new Flywheels();
	public static final OI oi = new OI();
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("DEFAULT FORWARD", new AutoDriveLeft());
		m_chooser.addObject("Auton", new Auton());
		m_chooser.addObject("AutonDrive", new AutonDrive());
		m_chooser.addObject("Thors Auton", new ThorsAuton());
		m_chooser.addObject("Thor Hold Auton", new ThorHoldAuton());
		SmartDashboard.putData("Auto mode", m_chooser);
	
		camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(352,  240);
		camera.setFPS(30);
		
		//sock = new Socket();
		//Thread thr = new Thread(new CommsThread(this));
		//thr.start();
    	
	}
	
	@Override
	public void robotPeriodic() {
		
	}

	@Override
	public void disabledInit() {
			Pneumatics.off();
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
	
	public void autonData(Point[] pts, Rect[] rts) {
		// TODO: Do something with the data
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		if(OI.controller.getAButtonPressed()) {
			System.out.println("A Button Pressed");
		}
		
		if(OI.controller.getBumper(GenericHID.Hand.kRight)) flywheels.grab();
		else if(OI.controller.getTriggerAxis(GenericHID.Hand.kRight) > .9) flywheels.toss();
		else flywheels.off();
		
		if(OI.controller.getTriggerAxis(GenericHID.Hand.kLeft) > .9) pneumatics.clampBox();
		
		pov = OI.controller.getPOV();
		
		if(OI.stick.getRawButton(2)) DriveTrain.driveTrain.arcadeDrive(-stick.getY()*.8, stick.getTwist());
		else DriveTrain.driveTrain.arcadeDrive(-stick.getY()*.95, stick.getTwist()*.67);
		
				if(OI.stick.getTrigger() || OI.controller.getTriggerAxis(GenericHID.Hand.kLeft) > .9) pneumatics.releaseBox();
				else pneumatics.clampBox();
		
		Thread th3 = new Thread(new Runnable() {
			public void run() {
				//POV CONTROLS
				if(pov == -1) {
					pov = OI.stick.getPOV(0);
				}
						
				if(pov != -1) {
					//Thors Hammer
					if(pov > 85 && pov < 95) thorsHammer.moveUp(RobotMap.thorSpeed);
					else if(pov > 355 || pov < 5) flywheels.toss();
					else if(pov > 265 && pov < 275) thorsHammer.moveDown(RobotMap.thorSpeed);
					else if(pov > 175 && pov < 185) flywheels.grab();
				}
				else {
					thorsHammer.off();
				}
			}
		});
		th3.start();
	}

	@Override
	public void testPeriodic() {
	}
}
