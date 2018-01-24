package org.usfirst.frc.team2960.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;

public class OI {

    private Drive drive = Drive.getInstance();


    public void DriveRobot(Joystick joystick) {
        drive.setSpeed(-joystick.getRawAxis(5), joystick.getRawAxis(1));

        if(joystick.getRawButton(1)) {
            drive.moveForwardInch(10);
        }

        if(joystick.getRawButton(2)) {
            drive.zeroSensors();
        }
    }

    public void operateRobot(Joystick joystick) {

    }

}
