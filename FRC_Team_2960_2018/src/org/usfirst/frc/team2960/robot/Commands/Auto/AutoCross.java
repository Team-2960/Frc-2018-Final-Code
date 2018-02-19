package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCross extends CommandGroup
{

    public AutoCross() {
        addSequential(new MoveForwardTime(4, .5));
    }

}


