package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ThorHoldAuton extends Command {

    public ThorHoldAuton() {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.thorsHammer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.thorsHammer.encThor.getRaw() < -480) {
    		Robot.thorsHammer.thorsHammer.set(-RobotMap.thorSpeed);
    	}
    	else if(Robot.thorsHammer.encThor.getRaw() > -330) {
    		Robot.thorsHammer.thorsHammer.set(RobotMap.thorSpeed*.3);
    	}
    	else {
    		Robot.thorsHammer.off();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	new ThorsAuton();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}