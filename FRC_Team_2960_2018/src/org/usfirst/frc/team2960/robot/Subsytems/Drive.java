package org.usfirst.frc.team2960.robot.Subsytems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem{

    private static Drive mInstance;


    //static instance method
    public static Drive instance()  {
        if (mInstance == null) {
            mInstance = new Drive();
        }
        return mInstance;
    }



    @Override
    protected void initDefaultCommand() {

    }
}
