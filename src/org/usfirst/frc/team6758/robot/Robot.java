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
import org.usfirst.frc.team6758.robot.subsystems.Climber;
import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6758.robot.subsystems.Elevator;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	
	protected int position;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Elevator elevator = new Elevator();
	public static final OI oi = new OI();
	public static final Climber climber = new Climber();
	
	//public static PowerDistributionPanel pdp = new PowerDistributionPanel(0);
	public static BuiltInAccelerometer accel = new  BuiltInAccelerometer();
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		locationChooser = new AutonChooser().makeLocations();
		SmartDashboard.putData("Position", locationChooser);
		
		m_chooser = new AutonChooser().makeAuton();
		SmartDashboard.putData("Auto mode", m_chooser);
		
		SmartDashboard.putNumber("DriverStation Location", DriverStation.getInstance().getLocation());
	
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
			m_chooser = new AutonChooser().makeAuton();
			SmartDashboard.putData("Auto mode", m_chooser);
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
		
		driveTrain.resetDistance();
	}
	
	public void autonData(Point[] pts, Rect[] rts) {
		// TODO: Do something with the data
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//SmartDashboard.putNumber("PDP Temp: ", pdp.getTemperature()*2.8 + 30);
		//SmartDashboard.putNumber("Power Consumed", pdp.getTotalPower
		SmartDashboard.putNumber("X-Axis Acceleration", accel.getX());
		SmartDashboard.putNumber("Y-Axis Acceleration", accel.getY());
	}

	@Override
	public void testPeriodic() {
	}
}
