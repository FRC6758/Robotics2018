package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 *
 */
public class AutonChooser {
	protected String locations;
	protected char switchPosition;
	protected int location = -1, position;
	
	private SendableChooser<Command> m_chooser = new SendableChooser<>();
	private SendableChooser<Integer> locationChooser = new SendableChooser<>();

	public AutonChooser() {
		position = DriverStation.getInstance().getLocation();
	}
	
	public SendableChooser<Command> makeAuton(){
    	DriverStation.getInstance().waitForData();
    	//message = DriverStation.getInstance().getGameSpecificMessage();
    	//location = Robot.locationChooser.getSelected();
    	if(location == -1) return m_chooser;
    	//if(message != null) switchPosition = message.charAt(0);
    	
    	m_chooser.addDefault("POSITION " + location + " | SWITCH " + switchPosition, new Nothing());
    	switch(location) {
    	case 1:
    		m_chooser.addObject("Position 1 Switch", new Nothing());
    		m_chooser.addObject("Encoders", new EncAutonSwitch());
    		break;
    	case 2:
    		m_chooser.addObject("Position 2 Switch Time", new Nothing());
    		m_chooser.addObject("", new Location2());
    		break;
    	case 3:
    		m_chooser.addObject("Position 3 Switch Time", new Nothing());
    		break;
    	default:
    		m_chooser.addObject("No route: " + location+" , "+switchPosition, new Nothing());
    		break;
    	}
    	
		return m_chooser;
    }
	
	public SendableChooser<Integer> makeLocations(){
		
		locationChooser.addDefault("CURRENT POSITION: " + position + "", position);
		locationChooser.addObject("Position 1", 1);
		locationChooser.addObject("Position 2", 2);
		locationChooser.addObject("Position 3", 3);
		
		
		return locationChooser;
	}
	
	public void encChooseCross() {
	}
}
