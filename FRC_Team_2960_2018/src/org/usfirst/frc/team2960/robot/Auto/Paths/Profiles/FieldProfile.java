package org.usfirst.frc.team2960.robot.Auto.Paths.Profiles;

/**
 * Interface that holds all the field measurements required by the PathAdapter
 * 
 * @see PathAdapter
 */
public interface FieldProfile {

    //CHANGE

    public double getRedCenterToBoiler();

    public double getRedWallToAirship();

    public double getRedCenterToHopper();

    public double getRedWallToHopper();

    public double getBlueCenterToBoiler();

    public double getBlueWallToAirship();

    public double getBlueCenterToHopper();

    public double getBlueWallToHopper();

}
