package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;


public class TestAuto extends CommandGroup{

    public TestAuto() {


        //addSequential(new FollowTrajectory(trajectory));
        //addSequential(new IntakeAdjustMove(), 1);
        //addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 170));
        //addSequential(new MoveForwardDistance(240, .5));//Goes about 290, set to 180

        //addSequential(new ElevatorMove(Elevator.mElevatorState.Switch, 0));
        //addSequential(new TurnToTarget(30, .75));
        //GOOOD
        //addSequential(new IntakeAdjustMove(), .8);
        addSequential(new MoveForwardDistanceVelocity(215, 102));//183 ++ 172 ++ dist 248 speed 42
        //addParallel(new ElevatorMove(Elevator.mElevatorState.ScaleUp,0 ));
        addSequential(new TurnToTarget(30, 120));
        //addSequential(new MoveForwardDistanceVelocity(215, 50));

    }
}
