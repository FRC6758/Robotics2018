package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDrive extends Command {
	
	boolean flag;
	
    public AutonDrive() {
        // Use requires() here to declare subsystem dependencies
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//enc0Distance = enc0.getRaw();
    	
    	new Auton().start();
<<<<<<< HEAD:src/org/usfirst/frc/team6758/robot/autonomous/AutonDrive.java
    	new AutoDriveLeft().start();
    	System.out.println("DONE! AUTONDRIVE!");
        flag = true;
=======
    	if(true) {
    		new AutoDriveLeft().start();
    		System.out.println("DONE! AUTONDRIVE!");
        	flag = true;
    	}
>>>>>>> 4f1008bdfa50e38c526f936a79051ecb4d0c5fda:src/org/usfirst/frc/team6758/robot/commands/AutonDrive.java
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//NEEDS VARIABLES
    	return flag;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("AutonDrive Interrupted!");
    }
}
