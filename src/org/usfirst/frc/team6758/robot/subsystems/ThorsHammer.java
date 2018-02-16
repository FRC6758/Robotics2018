package org.usfirst.frc.team6758.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ThorsHammer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static WPI_TalonSRX thorsHammer = new WPI_TalonSRX(6);
	public static Encoder thorsEnc = new Encoder(4, 5);

    public void initDefaultCommand() {
        //setDefaultCommand(new Hammer());
    }
}

