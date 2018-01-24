/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team6758.robot.commands.ExampleCommand;
import org.usfirst.frc.team6758.robot.subsystems.Encoders;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Robot extends TimedRobot {
	public static OI m_oi;

	public static MecanumDrive driveTrain;
	
	public static Joystick stick = new Joystick(0);
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	public UsbCamera camera;
	public Mat source, output;
	public GripPipeline grip;
	
	public static Compressor compressor = new Compressor(0);

	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		Pneumatics.testSolenoid.set(DoubleSolenoid.Value.kForward);
		
		grip = new GripPipeline();
		
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
            
            source = new Mat();
            output = new Mat();
            
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                //Imgproc.cvtColor(source, output, Imgproc.COLOR_BayerRG2BGR);
                outputStream.putFrame(output);
            }
        }).start();
		
//		camera = CameraServer.getInstance().startAutomaticCapture();
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
		
		grip.process(source);
		
	}

	@Override
	public void testPeriodic() {
	}
}
