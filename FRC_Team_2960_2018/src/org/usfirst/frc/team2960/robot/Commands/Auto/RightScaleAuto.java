package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Commands.IntakeMove;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;

public class RightScaleAuto extends CommandGroup {
    public RightScaleAuto(){

        addSequential(new IntakeAdjustMove(), .1);
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        //addParallel(new ElevatorMove(Elevator.mElevatorState.ScaleBalanced), 5);
        if (gameData.charAt(1) == 'R') {
            addSequential(new IntakeAdjustMove(), .8);
            addSequential(new MoveForwardDistanceVelocity(215, 102));//183 ++ 172 ++ dist 248 speed 42
            addParallel(new ElevatorMove(Elevator.mElevatorState.ScaleUp,0 ), 2);
            addSequential(new TurnToTarget(25, 120));
            addSequential(new Wait(.5));
            //Test
            addSequential(new MoveForwardTime(.7, .5));
            //addSequential(new MoveForwardDistanceVelocity(14, 75));
            addSequential(new IntakeMove(Intake.mIntakeState.backwardSlow), 3);
            addSequential(new MoveForwardTime(.5, -.5));


        }
        else {
            addSequential(new MoveForwardTime(5, .2));
        }
    }
}
