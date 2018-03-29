package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.hal.MatchInfoData;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Commands.IntakeMove;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;

public class SwitchRightSide extends CommandGroup{
	
	public SwitchRightSide(){
		addSequential(new IntakeAdjustMove(), .1);
	    String gameData;
	    gameData = DriverStation.getInstance().getGameSpecificMessage();
	    addSequential(new MoveForwardTime(.1, .5));
	    if (gameData.charAt(0) == 'R') {
	        addSequential(new MoveForwardDistanceVelocity(125, 102));
	        addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 0), 5);
	        addSequential(new TurnToTarget(90, 120));
	        addSequential(new MoveForwardDistanceVelocity(10, 102));
	        addSequential(new IntakeAdjustMove(), .75);
	        addSequential(new IntakeMove(Intake.mIntakeState.backward), 1.5);

	    }
	    else {
	    	addSequential(new MoveForwardDistanceVelocity(168, 102));
	   	}
	    addSequential(new MoveForwardDistanceVelocity(15, -30));
		}

}
