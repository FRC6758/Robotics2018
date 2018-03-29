package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.RobotMap;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncMiddle extends CommandGroup {
	public EncMiddle() {
//		char switchPosition = DriverStation.getInstance().getGameSpecificMessage().charAt(0); // Will throw an error pre-game
//		// These are basic and should be changed to be 99.99% accurate.
//		if(switchPosition == 'L') {
//			addSequential(new EncDriveForward(300));
//			addSequential(new EncTurnCounter(RobotMap.rightAngle));
//			addSequential(new EncDriveForward(500));
//			addSequential(new EncTurnClock(RobotMap.rightAngle));
//			addSequential(new DriveForward(800));
//			addSequential(new ArmRelease());
//		}
//		else if(switchPosition == 'R') {
//			addSequential(new DriveForward(200));
//			addSequential(new EncTurnClock(RobotMap.rightAngle));
//			addSequential(new DriveForward(24));
//			addSequential(new EncTurnCounter(RobotMap.rightAngle));
//			addSequential(new DriveForward(900));
//			addSequential(new ArmRelease());
//		} else {
//			System.out.println("ERROR - EncMiddle.java");
//		}
	}
}
