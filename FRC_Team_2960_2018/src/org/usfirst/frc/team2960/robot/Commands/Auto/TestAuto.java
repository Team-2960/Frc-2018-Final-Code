package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2960.robot.Constants;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

import java.io.File;

public class TestAuto extends CommandGroup{

    public TestAuto(/*Trajectory trajectory*/) {
        //File testAuto = new File("TESTS.csv");
        //Trajectory testAutoTrajectory = Pathfinder.readFromCSV(testAuto);

        //addSequential(new FollowTrajectory(trajectory));
        addSequential(new MoveForwardDistance(1000, 1));
        addSequential(new TurnToTarget(90));
    }
}
