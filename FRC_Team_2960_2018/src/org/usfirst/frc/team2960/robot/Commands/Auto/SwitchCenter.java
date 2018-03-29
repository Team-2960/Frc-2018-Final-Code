package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.hal.MatchInfoData;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Commands.IntakeMove;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;

public class SwitchCenter extends CommandGroup {

    public SwitchCenter(){
        /*addSequential(new MoveForwardDistance(5, 1));
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.charAt(0) == 'L'){
            addSequential(new TurnToTarget(-68.19));
            addParallel(new ElevatorMove(Elevator.mElevatorState.Switch), 5);
            addSequential(new MoveForwardDistance(145.339, 1));
            addSequential(new IntakeMove(Intake.mIntakeState.forward), 2);

        }
        else{
            addSequential(new TurnToTarget(59.98));
            addParallel(new ElevatorMove(Elevator.mElevatorState.Switch), 5);
            addSequential(new MoveForwardDistance(155.9, 1));
            addSequential(new IntakeMove(Intake.mIntakeState.forward), 2);
        }*/
        addSequential(new IntakeAdjustMove(), .1);
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        addSequential(new MoveForwardTime(.1, .5));
        if (gameData.charAt(0) == 'L') {
            addSequential(new MoveForwardTimeSide(.95, .5, false));
            addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 0), 5);
            //addParallel(new ElevatorTime(.4, .6));
            addSequential(new MoveForwardTime(.6, .5));
            addSequential(new MoveForwardTimeSide(.15, .5, true));
            addSequential(new IntakeAdjustMove(), .75);
            addSequential(new IntakeMove(Intake.mIntakeState.backward), 1.5);

        }
        else {
            addSequential(new MoveForwardTimeSide(.8, .5, true));
            addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 0), 5);
            //addParallel(new ElevatorTime(.4, .6));
            addSequential(new MoveForwardTime(.7, .5));
            addParallel(new MoveForwardTimeSide(.15, .5, false));
            addSequential(new IntakeAdjustMove(), .75);
            addSequential(new IntakeMove(Intake.mIntakeState.backward), 1.5);

        }
        addSequential(new MoveForwardTime(.5, -.5));
    }
}
