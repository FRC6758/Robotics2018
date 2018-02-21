package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.commands.OperationClimb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public WPI_TalonSRX climbArm = new WPI_TalonSRX(5);
	public WPI_TalonSRX climbWinch = new WPI_TalonSRX(6);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OperationClimb());
    }
}

