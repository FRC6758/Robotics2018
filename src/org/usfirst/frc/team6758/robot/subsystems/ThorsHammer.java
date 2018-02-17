package org.usfirst.frc.team6758.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
>>>>>>> 4f1008bdfa50e38c526f936a79051ecb4d0c5fda
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ThorsHammer extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
<<<<<<< HEAD
	public static WPI_TalonSRX thorsHammer = new WPI_TalonSRX(6);
	public static Encoder thorsEnc = new Encoder(4, 5);
=======
	double angle = 0;
	boolean active = false;
	
	public WPI_TalonSRX thorsHammer = new WPI_TalonSRX(6);
	public Encoder encThor = new Encoder(4, 5, false, EncodingType.k4X);
>>>>>>> 4f1008bdfa50e38c526f936a79051ecb4d0c5fda

    public void initDefaultCommand() {
        //setDefaultCommand(new Hammer());
    }
}

