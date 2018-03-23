package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Commands.IntakeMove;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;


public class TestAuto extends CommandGroup{

    public TestAuto() {


        //addSequential(new FollowTrajectory(trajectory));
        //addSequential(new IntakeAdjustMove(), 1);
        //addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 170));
        //addSequential(new MoveForwardDistance(240, .5));//Goes about 290, set to 180

        //addSequential(new ElevatorMove(Elevator.mElevatorState.Switch, 0));
        addSequential(new IntakeAdjustMove(), .8);
        addSequential(new MoveForwardDistanceVelocity(215, 102));//183 ++ 172 ++ dist 248 speed 42
        addParallel(new ElevatorMove(Elevator.mElevatorState.ScaleUp,0 ), 2);
        addSequential(new TurnToTarget(27, 120));
        addSequential(new Wait(.5));
        addSequential(new MoveForwardTime(.2, .3));
        //addSequential(new MoveForwardDistanceVelocity(14, 75));
        addSequential(new IntakeMove(Intake.mIntakeState.backwardSlow), 3);


        //TEST TURN
        //addSequential(new TurnToTarget(45, 120));
        //addSequential(new MoveForwardDistanceVelocity(15, 75));

        //addSequential(new MoveForwardDistanceVelocity(215, 50));

    }
}
