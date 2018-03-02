package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.commands.ArmRelease;
import org.usfirst.frc.team6758.robot.commands.LiftArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleAuton extends CommandGroup {

    public SimpleAuton() {
        addSequential(new DriveForward(10, true));
        addSequential(new LiftArm());
        addSequential(new ArmRelease());
    }
}
