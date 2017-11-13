package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by 120449 on 11/9/17.
 */
@Autonomous(name = "SafeZone")
public class MoveForward extends LinearOpMode {

    DcMotor left_drive;
    DcMotor right_drive;
    //  ColorSensor color;
//Add Servo here for button push
    @Override
    public void runOpMode() throws InterruptedException {
        //  color = hardwareMap.colorSensor.get("color");
        // color.enableLed(true);
        left_drive = hardwareMap.dcMotor.get("left");
        right_drive = hardwareMap.dcMotor.get("right");

        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
        mediaPlayer.start();


        waitForStart();
        left_drive.setPower(1);
        right_drive.setPower(-1);
        sleep(500);
        left_drive.setPower(0);
        right_drive.setPower(0);



    }

}
