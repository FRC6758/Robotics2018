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
    		addSequential(new DriveForward(6, false));
            addSequential(new DriveClock(3, false));
            addSequential(new DriveForward(2, false));
            addSequential(new LiftArm());
            addSequential(new ArmRelease());
    		break;
    	case 'R':
    		addSequential(new DriveForward(7, false));
            addSequential(new DriveClock(3, false));
            addSequential(new DriveForward(3 ,false));
            addSequential(new DriveClock(2, false));
            addParallel(new LiftArm());
            addSequential(new ArmRelease());
    		break;
    	default:
    		System.out.println("FATAL ERROR - Pos1Swtich: 30");
    		addSequential(new DriveForward(15, false));
    	}
    }
}
