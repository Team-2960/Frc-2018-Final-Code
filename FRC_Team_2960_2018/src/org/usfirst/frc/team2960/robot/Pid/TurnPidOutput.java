package org.usfirst.frc.team2960.robot.Pid;

import edu.wpi.first.wpilibj.PIDOutput;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class TurnPidOutput implements PIDOutput {

    private Drive drive = Drive.getInstance();

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
