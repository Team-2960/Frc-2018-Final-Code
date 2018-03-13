package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class TestAuto extends CommandGroup{

    public TestAuto() {


        //addSequential(new FollowTrajectory(trajectory));
        //addSequential(new IntakeAdjustMove(), 1);
        //addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 170));
        //addSequential(new MoveForwardDistance(240, .5));//Goes about 290, set to 180

        //addSequential(new ElevatorMove(Elevator.mElevatorState.Switch, 0));
        //addSequential(new TurnToTarget(30, .75));

        addSequential(new MoveForwardDistanceVelocity(183 / 1.8, 72));
    }
}
