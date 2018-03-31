package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftTimed extends CommandGroup {

    public LeftTimed() {
    		
    }
    
    public void finishInit() {
    	if(Robot.switchPosition == 'L') {
    		addSequential(new TurnCounter(.2));
			addSequential(new DriveForward(3));
			addSequential(new TurnClock(.45));
			addSequential(new DriveForward(2));
			//addSequential(new TurnClock(1));
			//addSequential(new DriveForward(2));
			addSequential(new LiftArm());
			addSequential(new ArmRelease());
		}
		else if(Robot.switchPosition == 'R') {
			addSequential(new DriveForward(4));
			System.out.println("This function has not arrived yet, however; I will go straight");
//			addSequential(new DriveForward(6));
//			addSequential(new TurnClock(1));
//			addSequential(new DriveForward(4));
//			addSequential(new TurnClock(2));
//			addSequential(new DriveForward(1));
//			addParallel(new LiftArm());
//			addSequential(new ArmRelease());
		}
		else {
			System.out.print("Something went wrong when recieving the game data, I will do nothing.");
		}
    }
}
