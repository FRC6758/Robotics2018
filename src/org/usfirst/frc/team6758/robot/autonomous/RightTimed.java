package org.usfirst.frc.team6758.robot.autonomous;

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
    	/*
    	 * I'm gonna go on a limb and assume that this means the cubes
    	 * are on the right. If you object, feel free to change it :)
    	 */
    	System.out.println("STARTING - RightTimed.java");
		System.out.println("Executing Right Switch Position Auton - RightTimed.java");
		addSequential(new DriveForward(4.75));
		addSequential(new TurnClock(2));			
		addSequential(new DriveForward(1));
		addParallel(new LiftArm());
		addSequential(new ArmRelease());
    }
}
