package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 120449 on 11/9/17.
 */
@Autonomous(name = "SafeZone")
public class MoveForward extends LinearOpMode {

    DcMotor left_drive = null;
    DcMotor right_drive = null;
    DcMotor elbowMotor = null ;//= hardwareMap.dcMotor.get("elbow");
    Servo rightFinger = null ;//= hardwareMap.servo.get("rightFinger");
    Servo leftFinger = null;//= hardwareMap.servo.get("leftFinger");

    //  ColorSensor color;
//Add Servo here for button push
    @Override
    public void runOpMode(){//} throws InterruptedException {
        elbowMotor = hardwareMap.dcMotor.get("elbow");
        elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //  color = hardwareMap.colorSensor.get("color");
        // color.enableLed(true);
        left_drive = hardwareMap.dcMotor.get("left");
        right_drive = hardwareMap.dcMotor.get("right");
        rightFinger = hardwareMap.servo.get("rightFinger");
        leftFinger = hardwareMap.servo.get("leftFinger");



        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
        mediaPlayer.start();


        waitForStart();
        rightFinger.setPosition(0.53);
        leftFinger.setPosition(0.53);

        elbowMotor.setTargetPosition(elbowMotor.getCurrentPosition()+100);

        //elbowMotor.setTargetPosition(elbowMotor.getCurrentPosition());
        //elbowMotor.setPower(.25);
        //sleep(500);

        left_drive.setPower(-.5);
        right_drive.setPower(-.5);
        sleep(800);
        left_drive.setPower(0);
        right_drive.setPower(0);

        rightFinger.setPosition(0.47);
        leftFinger.setPosition(0.47);
        elbowMotor.setTargetPosition(0);

        left_drive.setPower(-.25);
        right_drive.setPower(-.25);
        sleep(150);
        left_drive.setPower(0);
        right_drive.setPower(0);




    }

}
