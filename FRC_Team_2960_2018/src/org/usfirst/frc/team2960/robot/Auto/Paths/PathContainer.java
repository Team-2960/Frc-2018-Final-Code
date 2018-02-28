package org.usfirst.frc.team2960.robot.Auto.Paths;


import org.usfirst.frc.team2960.Util.Math.RigidTransform2d;
import org.usfirst.frc.team2960.Util.control.Path;

/**
 * Interface containing all information necessary for a path including the Path itself, the Path's starting pose, and
 * whether or not the robot should drive in reverse along the path.
 */
public interface PathContainer {
    Path buildPath();

    RigidTransform2d getStartPose();

    boolean isReversed();
}