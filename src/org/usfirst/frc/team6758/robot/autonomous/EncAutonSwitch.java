package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncAutonSwitch extends CommandGroup {

	int encLeft, encRight;
	int pulses = 86000;
	
    public EncAutonSwitch(int location, char switchPosition) {
    	Robot.driveTrain.resetDistance();
    	encLeft = Robot.driveTrain.encLeft.get();
    	encRight = Robot.driveTrain.encRight.get();
    	
    	if(true) Robot.driveTrain.left.set(.6);
    	else Robot.driveTrain.left.set(0);
    	if(true) Robot.driveTrain.right.set(.6);
    	else Robot.driveTrain.right.set(0);
    }
}
