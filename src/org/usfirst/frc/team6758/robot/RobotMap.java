/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6758.robot;

public class RobotMap {
	//Encoders
	public static final int 
		encLPortA = 0, encLPortB = 1,
		encRPortA = 2, encRPortB = 3,
		encThorPortA = 4, encThorPortB = 5;
	public static final double
		PPI = 67.4, PPD = 10;
	
	//PCM
	public static final int grabberPort = 1,
							puncherPort = 0;
	
	//Motor Controllers
	public static final int
		mDJ = 0, mKeegan = 1,
		mApache = 2, mAndo = 3,
		mDeter = 4, mTrevor = 5,
		mKristina = 6, mDane = 7;

	/*
	 * SPEED CONTROLS
	 * 
	 * Using this to control speeds for auton and teleop speeds
	 * that are fixed to reduce the amount of places they may be.
	 * 
	 * All speeds are on a scale of 0-1
	 */
	public static final double elevatorSpeed = .85;
	public static final double elevatorAutonSpeed = .4;
	public static final double defaultAutonSpeed = .4;
	public static final double twistSlowMultiplier = .55;
	
	
	/*
	 * Thors Hammer Encoder
	 * 
	 * 0 to the encoder is all the way up (90 degrees)
	 * 
	 * ~ -900 is were the arm stops moving on the downward angle
	 */
	public static final double thorUpThresh = -200;
}
