package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ThorsAuton extends Command {

	boolean flag;
	
    public ThorsAuton() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.thorsHammer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.thorsHammer.encThor.getRaw() < -200) {
    		Robot.thorsHammer.thorsHammer.set(-RobotMap.thorSpeed);
    	}
    	else {
    		Robot.thorsHammer.thorsHammer.set(0);
    		flag = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.thorsHammer.thorsHammer.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}