package org.usfirst.frc.team2960.robot.Commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import org.usfirst.frc.team2960.robot.Commands.ElevatorMove;
import org.usfirst.frc.team2960.robot.Commands.IntakeMove;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;

import java.io.File;

public class RightSwitchAuto extends CommandGroup{

    public RightSwitchAuto() {
        File leftSwitchAuto = new File("CenterRightS.csv");
        Trajectory leftSwitchTrajectory = Pathfinder.readFromCSV(leftSwitchAuto);
        addParallel(new FollowTrajectory(leftSwitchTrajectory));
        //addParallel(new ElevatorMove(1));
        addSequential(new IntakeMove(Intake.mIntakeState.forward), 10);
    }

}
