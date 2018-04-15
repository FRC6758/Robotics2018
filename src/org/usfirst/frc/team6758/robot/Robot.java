/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

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
	
	public UsbCamera camera;

	public static Compressor compressor = new Compressor(0);

	public static Socket sock;

	public static final int mist = 5;
	
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
	    // TODO: Do something with the data
        //x, y, height, width, area = rect
        System.out.print(rts.length);
        System.out.print("I think I found it....");
        Rect[] target = checkRectum(rts);
        if(target.equals(rts)){
            System.out.print("NO NO NO NO NO NO.....");
        }
        else{
            System.out.print("Found it");

        }
    }

    private Rect[] checkRectum(Rect[] rts){
        for(int x=0; x < rts.length; x++){
            for(int y=0; y<rts.length; y++){
                int theHeightDifference = Math.abs(rts[x].height - rts[y].height);
                int theWidthDifference = Math.abs(rts[x].width - rts[y].width);
                if(theHeightDifference<=mist && theWidthDifference<=mist){
                    Rect[] target = new Rect[2];
                    target[0] = rts[x];
                    target[1] = rts[y];
                    System.out.print("I have found what you are looking for.");
                    return target;
                }
            }
        }
        return rts;
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

	@Override
	public void testPeriodic() {
		System.out.println("\nThis Should be Turning Clockwise. \nDO NOT TOUCH THE ROBOT");
		new Nothing(2).start();
		System.out.println("Starting...");
		new TurnClock(6).start();
		System.out.println("\nThis Should be Turning Counterclockwise. \nDO NOT TOUCH THE ROBOT");
		new Nothing(2).start();
		System.out.println("Starting...");
		new TurnCounter(6).start();
		System.out.println("\nThis Should be Driving Forward. \nDO NOT TOUCH THE ROBOT");
		new Nothing(2).start();
		System.out.println("Starting...");
		new DriveForward(6).start();
		System.out.println("\nThis Should be Driving Backwards. \nDO NOT TOUCH THE ROBOT");
		new Nothing(2).start();
		System.out.println("Starting...");
		new DriveBackwards(6).start();
		System.out.println("\nTest Complete. \nYou may now disable the robot.");
		new Nothing(10).start();
	}
}
