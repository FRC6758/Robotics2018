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
import org.usfirst.frc.team6758.robot.autonomous.AutonChooser;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Elevator;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
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
	
	Command m_autonomousCommand;
	public SendableChooser<Command> m_chooser;
	public static SendableChooser<Integer> locationChooser;
	
	public UsbCamera camera;
	public Mat source, output;
	public GripPipeline grip;

	public static Compressor compressor = new Compressor(0);
	
	public static Socket sock;
	
	private int pov;
	protected int position;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Elevator elevator = new Elevator();
	public static final OI oi = new OI();
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		locationChooser = new AutonChooser().makeLocations();
		SmartDashboard.putData("Position", locationChooser);
		
		m_chooser = new AutonChooser().makeAuton();
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
		m_chooser = new AutonChooser().makeAuton();
		SmartDashboard.putData("Auto mode", m_chooser);
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
		
		if(OI.controller.getTriggerAxis(GenericHID.Hand.kLeft) > .9) pneumatics.clampBox();
		
		pov = OI.controller.getPOV();
		
		if(OI.stick.getRawButton(2)) driveTrain.driveTrain.arcadeDrive(-stick.getY()*.8, stick.getTwist());
		else driveTrain.driveTrain.arcadeDrive(-stick.getY()*.95, stick.getTwist()*.95);
		
				if(OI.stick.getTrigger() || OI.controller.getTriggerAxis(GenericHID.Hand.kLeft) > .9) pneumatics.releaseBox();
				else pneumatics.clampBox();
	}

	@Override
	public void testPeriodic() {
	}
}
