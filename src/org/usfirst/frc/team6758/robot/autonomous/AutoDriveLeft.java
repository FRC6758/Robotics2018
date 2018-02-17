package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveLeft extends Command {

	boolean flag;
	
    public AutoDriveLeft() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    	Robot.driveTrain.resetDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveTrain.encLeft.getRaw() < 8000) {
    		Robot.driveTrain.turnClockwise(RobotMap.defaultAutonSpeed*.8);
    	}
    	else {
    		Robot.driveTrain.stop();
    		flag = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.resetDistance();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
