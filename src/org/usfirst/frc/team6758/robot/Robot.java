/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.opencv.core.Mat;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Flywheels;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;
import org.usfirst.frc.team6758.robot.subsystems.ThorsHammer;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
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
	
	private boolean toggle = true;

	public static MecanumDrive driveTrain;
	
	public static Joystick stick = new Joystick(0);
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public UsbCamera camera;
	public Mat source, output;
	public GripPipeline grip;
	
	public static Compressor compressor = new Compressor(0);

	public static Encoder enc0 = new Encoder(0, 1);
	public static Encoder enc1 = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	
	public static Socket sock;
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		//m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		//grip = new GripPipeline();
		
//		new Thread(() -> {
//            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//            camera.setResolution(640, 480);
//            
//            CvSink cvSink = CameraServer.getInstance().getVideo();
//            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
//            
//            source = new Mat();
//            output = new Mat();
//            
//            while(!Thread.interrupted()) {
//                cvSink.grabFrame(source);
//                //Imgproc.cvtColor(source, output, Imgproc.COLOR_BayerRG2BGR);
//                outputStream.putFrame(output);
//            }
//        }).start();
	
		camera = CameraServer.getInstance().startAutomaticCapture(0);
		
		sock = new Socket();
		Thread thr = new Thread(new CommsThread());
		thr.start();
		
//		
//		source = new Mat();
//		CvSink input = CameraServer.getInstance().getVideo();
//    	
//    	input.grabFrame(source);
    	
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
		//m_autonomousCommand = m_chooser.getSelected();

		// schedule the autonomous command (example)
		//if (m_autonomousCommand != null) {
		//	m_autonomousCommand.start();
		//}
		
		//Command autonomous = new Auton();
		
		//autonomous.start();
	
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

	private boolean keepRunning = true;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		

		if(OI.stick.getRawButton(2)) DriveTrain.driveTrain.arcadeDrive(-stick.getY()*.8, stick.getTwist());
		else DriveTrain.driveTrain.arcadeDrive(-stick.getY()*.95, stick.getTwist()*.67);
		
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				keepRunning = false;
				try {
					ThorsHammer.thorsHammer.set(OI.stick.getX()*.5);
				} catch(Exception e) {
					System.out.println("Thor's hammer didn't thor: "+e.getMessage());
					return;
				}
				keepRunning = true;
				
				
			}
			
		});
		if(keepRunning) th.start();
		
		int pov = OI.stick.getPOV();
		
		
		Thread th2 = new Thread(new Runnable() {
			
			public void run() {
				if(OI.stick.getTrigger()) Pneumatics.releaseBox();
				else Pneumatics.clampBox();
			}
		});
		th2.start();
		
		Thread th3 = new Thread(new Runnable() {
			public void run() {
				if(OI.stick.getThrottle() == 1) {
					Flywheels.flyRight.set(1);
					Flywheels.flyLeft.set(-1);
				}
				else if(OI.stick.getThrottle() == -1) {
					Flywheels.flyLeft.set(1);
					Flywheels.flyRight.set(-1);
				}
				else {
					Flywheels.flyLeft.set(0);
					Flywheels.flyRight.set(0);
				}
			}
		});
		th3.start();
	}

	@Override
	public void testPeriodic() {
	}
}
