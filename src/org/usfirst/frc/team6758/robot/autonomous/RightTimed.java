package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.commands.ArmGrab;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightTimed extends CommandGroup {

    public RightTimed() {
    	System.out.println("STARTING - RightTimed.java");
    	if(Robot.switchPosition == 'R') {
		System.out.println("Executing Right Switch Position Auton - RightTimed.java");
			addSequential(new DriveForward(4.75));
			addSequential(new TurnClock(2));			
			addSequential(new DriveForward(1));
			addParallel(new LiftArm());
			addSequential(new ArmRelease());
    	}
    	else if(Robot.switchPosition == 'L') {
    		System.out.println("This feature is coming soon!");
//    		addSequential(new DriveForward(4.75));
//    		addSequential(new TurnClock(2));			
//    		addSequential(new DriveForward(3));
//    		addSequential(new TurnClock(2));
//    		addParallel(new LiftArm());
//    		addSequential(new ArmRelease());
    	}
    }
}
