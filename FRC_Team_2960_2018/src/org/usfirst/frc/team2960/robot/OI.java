package org.usfirst.frc.team2960.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2960.robot.Subsytems.Drive;
import org.usfirst.frc.team2960.robot.Subsytems.Intake;

public class OI {

    private Drive drive = Drive.getInstance();
    private Intake intake = Intake.getInstance();


    public void driveRobot(Joystick joystick) {
        drive.setSpeed(-joystick.getRawAxis(5), joystick.getRawAxis(1));

        if(joystick.getRawButton(1)) {
            drive.moveForwardInch(10);
        }

        if(joystick.getRawButton(2)) {
            drive.zeroSensors();
        }


    }

    public void operateRobot(Joystick joystick) {
        if(joystick.getRawButton(1)) {
            intake.setIntakeState(Intake.mIntakeState.forward);
        }
        else if (joystick.getRawButton(2)) {
            intake.setIntakeState(Intake.mIntakeState.backward);
        }
        else {
            intake.setIntakeState(Intake.mIntakeState.stop);
        }
    }

}
