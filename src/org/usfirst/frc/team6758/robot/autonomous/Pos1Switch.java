package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos1Switch extends CommandGroup {

    public Pos1Switch(char switchPosition) {
    	switch(switchPosition) {
    	case 'L':
    		addSequential(new DriveForward(163, true));
            addSequential(new DriveClock(90, true));
            addParallel(new LiftArm());
            addSequential(new ArmRelease());
    		break;
    	case 'R':
    		addSequential(new DriveForward(228, true));
            addSequential(new DriveClock(90, true));
            addSequential(new DriveForward(131 ,true));
            addSequential(new DriveClock(90, true));
            addParallel(new LiftArm());
            addSequential(new ArmRelease());
    		break;
    	default:
    		System.out.println("FATAL ERROR - Pos1Swtich: 30");
    		addSequential(new DriveForward(163, true));
    	}
    }
}
