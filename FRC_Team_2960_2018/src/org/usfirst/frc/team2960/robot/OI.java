package org.usfirst.frc.team2960.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2960.robot.Subsytems.*;

public class OI {

    private Drive drive = Drive.getInstance();
    private Intake intake = Intake.getInstance();
    private Winch winch = Winch.getInstance();
    private Elevator elevator = Elevator.getInstance();
    private boolean isButtonBox = false;
    //private boolean intakeOperatorOverride = false;

    public void driveRobot(Joystick joystick) {
        drive.setSpeed(-joystick.getRawAxis(5), joystick.getRawAxis(1));

        if (joystick.getRawButton(1)) {
            //drive.zeroSensors();
            //elevator.zeroSensors();
        }
        //if(!intakeOperatorOverride) {
        if (joystick.getRawButton(6)) {
            intake.setIntakeState(Intake.mIntakeState.rotate);
        } else if (joystick.getRawButton(5)) {
            intake.setIntakeState(Intake.mIntakeState.backward);
        } else {
            intake.setIntakeState(Intake.mIntakeState.stop);
        }
        if (joystick.getRawButton(2)) {
            intake.setIntakeState(Intake.mIntakeState.forward);
        }
        //}

    }


    public void operateRobot(Joystick joystick) {

        if (isButtonBox) {
            double range = 0;
            //Elevator
            if (joystick.getRawButton(16)) {
                elevator.testElevator(joystick.getRawAxis(4));
            } else {
                if (joystick.getRawButton(1)) {
//                range = (Constants.kElevatorGround - Constants.kElevatorSwitch);
//                range = range * (joystick.getRawAxis(4));
                    elevator.setState(Elevator.mElevatorState.Ground, range);
                } else if (joystick.getRawButton(3)) {
//                range = (Constants.kElevatorScaleDown - Constants.kElevatorSwitch);
//                range = range * (joystick.getRawAxis(4));
                    elevator.setState(Elevator.mElevatorState.Switch, range);
                } else if (joystick.getRawButton(5)) {
//                range = (Constants.kElevatorScaleBalanced - Constants.kElevatorScaleDown);
//                range = range * (joystick.getRawAxis(4));
                    elevator.setState(Elevator.mElevatorState.ScaleDown, range);
                } else if (joystick.getRawButton(7)) {
//                range = (Constants.kElevatorScaleUp - Constants.kElevatorScaleBalanced);
//                range = range * (joystick.getRawAxis(4));
                    elevator.setState(Elevator.mElevatorState.ScaleBalanced, range);
                } else if (joystick.getRawButton(9)) {
                    range = 0;
                    elevator.setState(Elevator.mElevatorState.ScaleUp, range);
                }
            }

            //Intake
            if (joystick.getRawButton(14)) {
                intake.setIntakeAdjustState(Intake.mIntakeAdjust.forward);
                //intakeOperatorOverride = true;
            } else if (joystick.getRawButton(13)) {
                intake.setIntakeAdjustState(Intake.mIntakeAdjust.backward);
                //intakeOperatorOverride = false;
            } else {
                intake.setIntakeAdjustState(Intake.mIntakeAdjust.stop);
                //intakeOperatorOverride = true;
            }


            //Winch
            if (joystick.getRawButton(15)) {
                winch.setWinchState(Winch.mWinchState.winchUp);
            } else
                winch.setWinchState(Winch.mWinchState.winchStop);

            //Hook
            if (joystick.getRawButton(12)) {
                winch.setHookState(Winch.mHookState.hookDeployment);
            } else if (joystick.getRawButton(11)) {
                winch.setHookState(Winch.mHookState.hookDeploymentbackword);
            } else
                winch.setHookState(Winch.mHookState.hookDeploymentStop);
        }
        else {

            /**
             * @author Grace Shenefelt
             */
        if (joystick.getRawButton(2)) {
            elevator.testElevator(joystick.getRawAxis(1));
        } else {
            if (joystick.getRawButton(12)) {
                elevator.setState(Elevator.mElevatorState.Ground, 0);
            } else if (joystick.getRawButton(11)) {
                elevator.setState(Elevator.mElevatorState.Switch, 0);
            } else if (joystick.getRawButton(10)) {
                elevator.setState(Elevator.mElevatorState.ScaleDown, 0);
            } else if (joystick.getRawButton(9)) {
                elevator.setState(Elevator.mElevatorState.ScaleBalanced, 0);
            } else if (joystick.getRawButton(8)) {
                elevator.setState(Elevator.mElevatorState.ScaleUp, 0);
            }
        }

        if (joystick.getRawButton(5)) {
            intake.setIntakeAdjustState(Intake.mIntakeAdjust.forward);
        } else if (joystick.getRawButton(3)) {
            intake.setIntakeAdjustState(Intake.mIntakeAdjust.backward);
        } else {
            intake.setIntakeAdjustState(Intake.mIntakeAdjust.stop);
        }
        if (joystick.getRawButton(6)) {
            winch.setHookState(Winch.mHookState.hookDeployment);
        } else if (joystick.getRawButton(4)) {
            winch.setHookState(Winch.mHookState.hookDeploymentbackword);

        } else {
            winch.setHookState(Winch.mHookState.hookDeploymentStop);
        }

        if (joystick.getRawButton(1)) {
            winch.setWinchState(Winch.mWinchState.winchUp);
        } else {
            winch.setWinchState(Winch.mWinchState.winchStop);
        }
        }


    }
}
