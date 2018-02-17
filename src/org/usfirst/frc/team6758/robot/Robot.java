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
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Flywheels;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;
import org.usfirst.frc.team6758.robot.subsystems.ThorsHammer;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
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
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("DEFAULT FORWARD", new AutoDriveLeft());
		m_chooser.addObject("Auton", new Auton());
		m_chooser.addObject("AutonDrive", new AutonDrive());
		SmartDashboard.putData("Auto mode", m_chooser);
	
		camera = CameraServer.getInstance().startAutomaticCapture(0);
		
		//sock = new Socket();
		//Thread thr = new Thread(new CommsThread(this));
		//thr.start();
    	
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
		
//		Command autonomous = new Auton();
//		
//		autonomous.start();
	
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
		

		if(OI.stick.getRawButton(2)) DriveTrain.driveTrain.arcadeDrive(-stick.getY()*.8, stick.getTwist());
		else DriveTrain.driveTrain.arcadeDrive(-stick.getY()*.95, stick.getTwist()*.67);
		
		Thread th2 = new Thread(new Runnable() {
			
			public void run() {
				if(OI.stick.getTrigger()) Pneumatics.releaseBox();
				else Pneumatics.clampBox();
			}
		});
		th2.start();
		
		Thread th3 = new Thread(new Runnable() {
			public void run() {
				//POV CONTROLS
				pov = OI.stick.getPOV(0);
						
				if(pov != 1) {
							
					//FlyWheels
					if(pov > 355 || pov < 5) Flywheels.toss();
					else if(pov > 175 && pov < 185) Flywheels.grab();
					else Flywheels.off();
						
					//Thors Hammer
					if(pov > 85 && pov < 95) ThorsHammer.thorsHammer.set(.35);
					else if(pov > 265 && pov < 275);
					else ThorsHammer.thorsHammer.set(0);
				}
			}
		});
		th3.start();
	}

	@Override
	public void testPeriodic() {
	}
}
