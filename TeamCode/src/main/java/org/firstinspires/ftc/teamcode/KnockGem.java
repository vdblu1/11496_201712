package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Knock off gem")
public class KnockGem extends LinearOpMode {
    AutonomousMethods methods = new AutonomousMethods();
    @Override public void runOpMode() {
        methods.init1();
        waitForStart();
        methods.knockGem("blue");
    }
}
