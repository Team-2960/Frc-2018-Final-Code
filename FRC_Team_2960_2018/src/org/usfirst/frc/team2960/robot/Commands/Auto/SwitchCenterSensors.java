package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.hal.MatchInfoData;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Commands.IntakeMove;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;

public class SwitchCenterSensors extends CommandGroup{
	public SwitchCenterSensors() {
		
	addSequential(new IntakeAdjustMove(), .1);
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    addSequential(new MoveForwardDistanceVelocity(12, 72));
    if (gameData.charAt(0) == 'L') {
        addSequential(new TurnToTarget(45, 120));
        addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 0), 5);
        addSequential(new MoveForwardDistanceVelocity(100, 72));
        addSequential(new TurnToTarget(-45, 120));
        addSequential(new MoveForwardDistanceVelocity(20, 72));
        //addSequential(new MoveForwardTimeSide(.15, .5, true));
        addSequential(new IntakeAdjustMove(), .75);
        addSequential(new IntakeMove(Intake.mIntakeState.backward), 1.5);

    }
    else {
        addSequential(new TurnToTarget(-45, 120));
        addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 0), 5);
        addSequential(new MoveForwardDistanceVelocity(100, 72));
        addSequential(new TurnToTarget(45, 120));
        addSequential(new MoveForwardDistanceVelocity(20, 72));
        //addSequential(new MoveForwardTimeSide(.15, .5, true));
        addSequential(new IntakeAdjustMove(), .75);
        addSequential(new IntakeMove(Intake.mIntakeState.backward), 1.5);

    }
    addSequential(new MoveForwardDistanceVelocity(-12, 72));
	}
}
