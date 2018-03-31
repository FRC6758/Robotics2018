package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LowerArmTimed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TimedMiddle extends CommandGroup{
	public TimedMiddle() {
		System.out.println("STARTING - TimedMiddle.java");
		
	}
	
	public void finishInit() {
		System.out.println("Found GSM: " + Robot.switchPosition);
		// These are basic and should be changed to be 99.99% accurate.
		if(Robot.switchPosition == 'L') {
			System.out.println("Executing Left Switch Position Auton - TimedMiddle.java");
			addSequential(new DriveForward(1)); //stops before boxes in the middle
			addSequential(new TurnCounter(1.2));
			addSequential(new DriveForward(2.5)); //completes the journey
			addSequential(new TurnClock(.4));
			addSequential(new DriveForward(2));
			addSequential(new LowerArmTimed(.25));
			addSequential(new ArmRelease());
		}
		else if(Robot.switchPosition == 'R') {
			System.out.println("Executing Right Switch Position Auton - TimedMiddle.java");
			addSequential(new DriveForward(1));
			addSequential(new TurnClock(.5));
			addSequential(new DriveForward(1.8));
			addSequential(new TurnCounter(.55));
			addSequential(new DriveForward(2));
			addSequential(new LowerArmTimed(.25));
			addSequential(new ArmRelease());
		} else {
			System.out.print("Something went wrong when recieving the game data, I will do nothing.");
		}
	}
}
