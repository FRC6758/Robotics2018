/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class RobotMap {
	//DIO
	public static int enc0APort = 0, enc0BPort = 1,
			enc1APort = 2, enc1BPort = 3;
	
	//PCM
	public static int grabberPort1 = 0, grabberPort2 = 1;
	
	//Limit Switch
	public static DigitalInput limitSwitch0, limitSwitch180;
	
}
