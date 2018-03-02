package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos3Switch extends CommandGroup {

    public Pos3Switch(char switchPosition) {
    	if(switchPosition == 'R') {
    		addSequential(new DriveForward(5, false));
    		addSequential(new DriveCounter(2, false));
    		addSequential(new DriveForward(1, false));
    		addSequential(new LiftArm());
    		addSequential(new ArmRelease());
    	}
    	else if(switchPosition == 'L') {
    		addSequential(new DriveForward(7, false));
    		addSequential(new DriveCounter(2, false));
    		addSequential(new DriveForward(3, false));
    		addSequential(new LiftArm());
    		addSequential(new ArmRelease());
    	}
    	else {
    		addSequential(new DriveForward(8, false));
    	}
    }
}
