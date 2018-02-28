package org.usfirst.frc.team2960.robot.Auto.Paths;

import org.usfirst.frc.team2960.Util.Math.RigidTransform2d;
import org.usfirst.frc.team2960.Util.control.Path;
import org.usfirst.frc.team2960.robot.Auto.Paths.PathBuilder.Waypoint;

import java.util.ArrayList;

public class TestPath implements PathContainer{

    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(16, 160, 0, 0));
        sWaypoints.add(new Waypoint(46, 160, 0, 60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return null;
    }

    @Override
    public boolean isReversed() {
        return true;
    }
}
