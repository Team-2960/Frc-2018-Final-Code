package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

import java.io.File;

public class TestAuto extends CommandGroup{

    public TestAuto() {
        //File testAuto = new File("TESTS.csv");
        //Trajectory testAutoTrajectory = Pathfinder.readFromCSV(testAuto);
        Waypoint[] points = new Waypoint[] {
                new Waypoint(0,0,0),
                new Waypoint(0.6096,0,0)
        };
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 5, 2, 60.0);
        Trajectory testAutoTrajectory = Pathfinder.generate(points, config);
        addSequential(new FollowTrajectory(testAutoTrajectory));
    }
}
