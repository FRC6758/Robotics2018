package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pos2Switch extends CommandGroup {

    public Pos2Switch(char switchPosition) {
        if(switchPosition == 'R') {
        	addSequential(new DriveForward(2, false));
        	addSequential(new DriveClock(2, false));
        	addSequential(new DriveForward(2, false));
        	addSequential(new DriveCounter(2, false));
        	addSequential(new DriveForward(1, false));
        	addSequential(new LiftArm());
        	addSequential(new ArmRelease());
        }
        else if(switchPosition == 'L') {
        	addSequential(new DriveForward(2, false));
        	addSequential(new DriveCounter(2, false));
        	addSequential(new DriveForward(2, false));
        	addSequential(new DriveClock(2, false));
        	addSequential(new DriveForward(1, false));
        	addSequential(new LiftArm());
        	addSequential(new ArmRelease());
        }
        else {
        	addSequential(new DriveForward(2, false));
        	addSequential(new DriveClock(2, false));
        	addSequential(new DriveForward(4, false));
        	addSequential(new DriveCounter(2, false));
        	addSequential(new DriveForward(4, false));
        }
        if(switchPosition == 'l') {
        	
        }
    }
}
