package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;
import org.usfirst.frc.team6758.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedMiddle extends CommandGroup{
	public TimedMiddle() {
		System.out.println("STARTING - TimedMiddle.java");
	}
	
	public void contInit() {
		System.out.println("Found GSM: " + Robot.switchPosition);
		// These are basic and should be changed to be 99.99% accurate.
		if(Robot.switchPosition == 'L') {
			System.out.println("Executing Left Switch Position Auton - TimedMiddle.java");
			addSequential(new DriveForward(2));
			addSequential(new TurnCounter(1));
			addParallel(new DriveForward(4.5));
			addSequential(new LiftArm());
			addSequential(new ArmRelease());
		}
		else if(Robot.switchPosition == 'R') {
			System.out.println("Executing Right Switch Position Auton - TimedMiddle.java");
			addSequential(new DriveForward(2));
			addSequential(new TurnClock(1));
			addParallel(new DriveForward(4.5));
			addSequential(new LiftArm());
			addSequential(new ArmRelease());
		} else {
			System.out.print("ERROR - TimedMiddle.java");
		}
	}
}
