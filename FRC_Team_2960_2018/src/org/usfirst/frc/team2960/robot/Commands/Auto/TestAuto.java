package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Constants;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;
import org.usfirst.frc.team2960.robot.Subsytems.Elevator;

import java.io.File;

public class TestAuto extends CommandGroup{

    public TestAuto(/*Trajectory trajectory*/) {


        //addSequential(new FollowTrajectory(trajectory));
        //addSequential(new IntakeAdjustMove(), 1);
        //addParallel(new ElevatorMove(Elevator.mElevatorState.Switch, 170));
        addSequential(new MoveForwardDistance(240, .5));//Goes about 290, set to 180

        //addSequential(new ElevatorMove(Elevator.mElevatorState.Switch, 0));
        //addSequential(new TurnToTarget(30, .75));
    }
}
