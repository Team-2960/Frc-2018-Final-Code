package org.usfirst.frc.team2960.robot.Pid;

import edu.wpi.first.wpilibj.PIDOutput;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class MovePidOutput implements PIDOutput {

    private Drive drive;

    public MovePidOutput(Drive drive){
        this.drive = drive;
    }
    /**
     * Set the output to the value calculated by PIDController.
     *
     * @param output the value calculated by PIDController
     */
    @Override
    public void pidWrite(double output) {
        drive.setSpeed(output, output);
    }
}
