package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

import java.io.File;

public class AutoCross extends CommandGroup
{
    public AutoCross() {
        addSequential(new MoveForwardTime(1, -.2));
    }

}


