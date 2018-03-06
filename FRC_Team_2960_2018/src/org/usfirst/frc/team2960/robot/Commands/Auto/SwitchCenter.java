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
        addSequential(new MoveForwardDistance(5, 1));
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
        }
    }
}
