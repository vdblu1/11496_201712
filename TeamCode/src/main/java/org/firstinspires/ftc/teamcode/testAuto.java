package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by 120449 on 1/29/18.
 */
@Autonomous(name = "test")
public class testAuto extends LinearOpMode {
    drivetrain mydrive = new drivetrain();
    DcMotor left_drive;
    DcMotor right_drive;
    BNO055IMU imu;
    public void runOpMode(){


        left_drive = hardwareMap.dcMotor.get("left");
        right_drive = hardwareMap.dcMotor.get("right");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        mydrive.init("myDrive", left_drive, right_drive, imu, telemetry);
        waitForStart();
        mydrive.turn(45, 0.2);
        telemetry.addData("first one end", null);
        sleep(1000);
        mydrive.turn(45, 0.2);
        telemetry.addData("second one end", null);
        sleep(1000);
        mydrive.turn(-90,0.2);
        telemetry.addData("third end", null);
        sleep(10000);

    }
}
