package org.usfirst.frc.team2960.robot.Auto.Modes;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team2960.robot.Auto.Actions.DrivePathAction;
import org.usfirst.frc.team2960.robot.Auto.Actions.ResetPoseFromPathAction;
import org.usfirst.frc.team2960.robot.Auto.AutoModeBase;
import org.usfirst.frc.team2960.robot.Auto.AutoModeEndedException;
import org.usfirst.frc.team2960.robot.Auto.Paths.PathContainer;
import org.usfirst.frc.team2960.robot.Auto.Paths.TestPath;

public class testMode extends AutoModeBase {
    @Override
    protected void routine() throws AutoModeEndedException {
        PathContainer testPath = new TestPath();
        double start = Timer.getFPGATimestamp();
        runAction(new ResetPoseFromPathAction(testPath));
        runAction(new DrivePathAction(testPath));
    }
}
