package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.lang.*;

@TeleOp(name="Mark's Code", group="Test Code")
public class MainDrive extends OpMode
{
    MainDriveExtension needABetterName = new MainDriveExtension();

    public void init()
    {}
    @Override
    public void loop() {
        needABetterName.ArmControl();
        needABetterName.driveControlConditional();
        needABetterName.RobotFingerLogic();
    }
}