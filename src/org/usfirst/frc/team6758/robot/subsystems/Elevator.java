package org.usfirst.frc.team6758.robot.subsystems;

import org.usfirst.frc.team6758.robot.commands.OperationElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(4);
	
	public DigitalInput topLimit = new DigitalInput(4);
	public DigitalInput bottomLimit = new DigitalInput(5);
	
	public Elevator() {
		elevatorMotor.setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new OperationElevator());
	}

	public void stop() {
		elevatorMotor.set(0);
	}
	
}
