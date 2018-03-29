package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.commands.ArmGrab;
import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftTimed extends CommandGroup {

    public LeftTimed() {
			addSequential(new DriveForward(4.75));
			addSequential(new TurnClock(1));
			addSequential(new DriveForward(2));
			addSequential(new LiftArm());
			addSequential(new ArmRelease());
    }
}
