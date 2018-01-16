package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 120449 on 11/9/17.
 */
//@Autonomous(name="Autonomous_11496", group="Competition")

public class Autonomous_11496 extends LinearOpMode {
    String alianceColor = "unkown"; // "red,blue"
    String stoneLoc = "unknown"; // "timer,audience"

    DcMotor leftMotor = null; //hardwareMap.dcMotor.get("left");
    DcMotor rightMotor = null; //hardwareMap.dcMotor.get("right");
    DcMotorEx elbowMotor = null ;//= hardwareMap.dcMotor.get("elbow");
    DcMotorEx shoulderMotor = null;//= hardwareMap.dcMotor.get("shoulder");
    Servo rightFinger = null ;//= hardwareMap.servo.get("rightFinger");
    Servo leftFinger = null;//= hardwareMap.servo.get("leftFinger");
    Servo   gemMover ;//= hardwareMap.servo.get("GemMover");
    int gripPosition = 0;
    int gripAt = 0;
    boolean pressingRB = false;
    boolean pressingLB = false;
    boolean pressingA = false;
    boolean pressingB = false;
    boolean pressingX = false;
    boolean pressingY = false;
    boolean armNeutral = false;
    boolean driveNeutral = false;
    boolean armControling = false;
    boolean driveControling = false;
    boolean dtStopped = true;

    joint elbow = new joint();
    joint shoulder = new joint();
    drivetrain myDrive = new drivetrain();
    ElapsedTime holdTimer = new ElapsedTime();
    gripper lowerGrip = new gripper();


    public void acceptParam(String alianceColor, String stoneLoc){
        this.alianceColor = alianceColor;
        this.stoneLoc = stoneLoc;
    }
    @Override
    public void runOpMode(){//} throws InterruptedException {
        telemetry.addData("alianceColor", alianceColor);
        telemetry.addData("stoneLoc", stoneLoc);
        telemetry.update();

        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");
        myDrive.init("myDrive", leftMotor, rightMotor);

        elbowMotor = (DcMotorEx)hardwareMap.get(DcMotor.class,"elbow");
        elbow.init("elbow", elbowMotor, 0, 1,telemetry, false);

        shoulderMotor = (DcMotorEx)hardwareMap.get(DcMotor.class,"shoulder");
        shoulder.init("shoulder", shoulderMotor, 0, 1, telemetry, false);

        leftFinger = hardwareMap.servo.get("leftFinger");
        rightFinger = hardwareMap.servo.get("rightFinger");
        lowerGrip.init("lowerGrip", rightFinger, leftFinger, telemetry);

        gemMover = hardwareMap.servo.get("gemMover");

        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
        mediaPlayer.start();


        waitForStart();

        while (opModeIsActive()) {
            //Once encoders are installed on drive motors set runtoposition to current position to set brakes

            lowerGrip.setGripPosition(3);
            sleep(1000);
            elbow.moveByClicks(-200);
            sleep(800);

            while (gemMover.getPosition()<1) {
                gemMover.setPosition(gemMover.getPosition() + .005);
                sleep(15);
            }

            telemetry.addData("gemMover Pos", gemMover.getPosition());
            telemetry.update();
            sleep(3000);

            gemMover.setPosition(.58);
            telemetry.addData("gemMover Pos", gemMover.getPosition());
            telemetry.update();
            sleep(3000);

            elbow.moveByClicks(175);
            sleep(1000);
            lowerGrip.setGripPosition(0);
            sleep(1000);
            requestOpModeStop();

        }





    }

}
