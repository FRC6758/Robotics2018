package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncDriveForward extends Command {

	private double pulses;
	private boolean flag, rightFlag, leftFlag;
	private int encLeft, encRight;
	
    public EncDriveForward(double measurement) {
        // Use requires() here to declare subsystem dependencies
        pulses = measurement;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    	Robot.driveTrain.resetDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		System.out.println("DRIVE TO DRISTANCE: " + pulses);
    		encLeft = Robot.driveTrain.encLeft.getRaw();
    		encRight = Robot.driveTrain.encRight.getRaw();
    		
    		System.out.println(pulses);
    		
    		if(Math.abs(encLeft) < pulses) Robot.driveTrain.left.set(RobotMap.defaultAutonSpeed);
    		else {
    			Robot.driveTrain.left.set(0);
    			leftFlag = true;
    		}
    		
    		System.out.println("Encoder Right: " + encRight);
    		if(Math.abs(encRight) < pulses) Robot.driveTrain.right.set(-RobotMap.defaultAutonSpeed);
    		else {
    			Robot.driveTrain.right.set(0);
    			leftFlag = true;
    		}
    		
    		if(leftFlag || rightFlag) {
    			flag = true;
    			Robot.driveTrain.stop();
    		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	Robot.driveTrain.resetDistance();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
