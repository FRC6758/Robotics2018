package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.RobotMap;
import org.usfirst.frc.team6758.robot.commands.ArmGrab;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncRight extends CommandGroup {

    public EncRight() {
//    	/*
//    	 * I'm gonna go on a limb and assume that this means the cubes
//    	 * are on the right. If you object, feel free to change it :)
//    	 */
//    	char switchPosition = DriverStation.getInstance().getGameSpecificMessage().charAt(0); // Will throw an error pre-game
//		// These are basic and should be changed to be 99.99% accurate.
//		if(switchPosition == 'R') {
//			addSequential(new EncDriveForward(1200));
//			addSequential(new EncTurnCounter(RobotMap.rightAngle));
//			addSequential(new EncDriveForward(200));
//			addParallel(new LiftArm());
//			addSequential(new ArmRelease());
//		}
//		else if(switchPosition == 'L') {
//			addSequential(new EncDriveForward(1200)); // Drive forward longer since you're on the other side
//		} else {
//			System.out.println("ERROR - EncRight.java");
//		}
    }
}
