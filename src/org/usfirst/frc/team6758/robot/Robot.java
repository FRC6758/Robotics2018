/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.opencv.core.*;
import org.usfirst.frc.team6758.robot.autonomous.*;
import org.usfirst.frc.team6758.robot.subsystems.*;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.net.Socket;

public class Robot extends TimedRobot {
	public static OI m_oi;
	public static char switchPosition;
	
	Command m_autonomousCommand;

	public static Rect[] rts;

	public UsbCamera camera;

	public static Compressor compressor = new Compressor(0);

	public static Socket sock;
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Elevator elevator = new Elevator();
	public static final OI oi = new OI();
	public static final Climber climber = new Climber();
	public static final TimedMiddle timedMiddle = new TimedMiddle();
	public static final RightTimed timedRight = new RightTimed();
	public static final LeftTimed timedLeft = new LeftTimed();

	public SendableChooser<Command> autonChooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		m_oi = new OI();
	
		camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(352,  240);
		camera.setFPS(30);
		
		//Auton chooser being activated
		autonChooser.addDefault("Nothing", new Nothing(15));
		autonChooser.addObject("Drive Straight - 12s", new DriveForward(12));
		autonChooser.addObject("Drive Straight - 5s", new DriveStraight());
		autonChooser.addObject("Middle Cube TIMED", timedMiddle); //TODO Dial in TimedMiddle()
		autonChooser.addObject("Left Cube TIMED", timedLeft); //TODO Dial in LeftTimed()
		autonChooser.addObject("Right Cube TIMED", timedRight); //TODO Dial in RightTimed()
		System.out.println("AutonChooser Created - Robot.java : 80");
		
		SmartDashboard.putData("Auto mode", autonChooser);

        sock = new Socket();
        Thread thr = new Thread(new CommsThread(this));
        thr.start();

		compressor.setClosedLoopControl(true);
	}
	
	@Override
	public void robotPeriodic() {
	}

	@Override
	public void disabledInit() {
	}

    public void autonData(Point[] pts, Rect[] rts) {
	    //TODO: Do something with the data
        this.rts = rts;
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
		timedLeft.finishInit();
		timedMiddle.finishInit();
		timedRight.finishInit();
		
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

	public static WPI_TalonSRX motor = new WPI_TalonSRX(7);
	double pValue = 0.15 , iValue = 0.1 , dValue = 1, output;
	@Override
	public void testPeriodic() {
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		motor.config_kP(0, pValue, 50);
		motor.config_kI(0, iValue ,50);
		motor.config_kD(0, dValue, 50);
		output = SmartDashboard.getNumber("Position", 0);
		motor.pidWrite(output);

	}
}
