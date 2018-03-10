package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.RobotMap;
import org.usfirst.frc.team6758.robot.commands.ArmGrab;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncLeft extends CommandGroup {

    public EncLeft() {
//    	/*
//    	 * I'm gonna go on a limb and assume that this means the cubes
//    	 * are on the left. If you object, feel free to change it :)
//    	 */
//    	char switchPosition = DriverStation.getInstance().getGameSpecificMessage().charAt(0); // Will throw an error pre-game
//		// These are basic and should be changed to be 99.99% accurate.
//		if(switchPosition == 'L') {
//			addSequential(new EncDriveForward(1200));
//			addSequential(new EncTurnClock(RobotMap.rightAngle));
//			addSequential(new EncDriveForward(200));
//			addSequential(new ArmRelease());
//		}
//		else if(switchPosition == 'R') {
//			addSequential(new EncDriveForward(1200));
//		} else {
//			// Drive forward and make an educated guess?
//			System.out.println("ERROR - EncLeft.java");
//		}
    }
}
