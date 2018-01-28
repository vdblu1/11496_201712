package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
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
    Servo lowerRightFinger = null ;//= hardwareMap.servo.get("lowerRightFinger");
    Servo lowerLeftFinger = null;//= hardwareMap.servo.get("lowerLeftFinger");
    Servo   gemMover ;//= hardwareMap.servo.get("GemMover");
    ColorSensor gemColorSense = null;
    double gemMoverParkPos = .45;
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


    float hsvValues[] = {0F, 0F, 0F};// hsvValues is an array that will hold the hue, saturation, and value information.
    final float values[] = hsvValues;// values is a reference to the hsvValues array.
    final double SCALE_FACTOR = 255; // sometimes it helps to multiply the raw RGB values with a scale factor to amplify/attentuate the measured values.
    String gemColor = "unknown";

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

        lowerLeftFinger = hardwareMap.servo.get("lowerLeftFinger");
        lowerRightFinger = hardwareMap.servo.get("lowerRightFinger");
        lowerGrip.init("lowerGrip", lowerRightFinger, lowerLeftFinger, telemetry);

        gemMover = hardwareMap.servo.get("gemMover");
        gemColorSense = hardwareMap.colorSensor.get("gemColorSense");



        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
        mediaPlayer.start();


        waitForStart();

        while (opModeIsActive()) {
            //Once encoders are installed on drive motors set runtoposition to current position to set brakes
            gemMover.setPosition(gemMoverParkPos);
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

            Color.RGBToHSV((int) (gemColorSense.red() * SCALE_FACTOR),
                    (int) (gemColorSense.green() * SCALE_FACTOR),
                    (int) (gemColorSense.blue() * SCALE_FACTOR),
                    hsvValues);
            if ((hsvValues[0]>350) || (hsvValues[0]<10)) {
                gemColor = "red";
            }else if (hsvValues[0]>205 && (hsvValues[0]<255)){
                gemColor = "blue";
            }else gemColor = "blue";



            telemetry.addData("gemColor", gemColor);
            telemetry.addData("gemMover Pos", gemMover.getPosition());
            telemetry.update();
            //sleep(3000);

            if (gemColor !="unknown") {
                if (alianceColor == gemColor) {
                    leftMotor.setPower(-0.1);
                    rightMotor.setPower(.1);
                    sleep(425);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    sleep(300);
                    leftMotor.setPower(0.1);
                    rightMotor.setPower(-.1);
                    sleep(300);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                } else {
                    leftMotor.setPower(0.1);
                    rightMotor.setPower(-.1);
                    sleep(425);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    sleep(300);
                    leftMotor.setPower(-0.1);
                    rightMotor.setPower(.1);
                    sleep(300);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                }
            }
            sleep(300);
            gemMover.setPosition(gemMoverParkPos);

            if (alianceColor == "red"){
                if (stoneLoc=="timer"){
                    leftMotor.setPower(.50);
                    rightMotor.setPower(-.50);
                    sleep(750);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    sleep(100);
                    leftMotor.setPower(.50);
                    rightMotor.setPower(.50);
                    sleep(1000);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                }else {

                    leftMotor.setPower(-.50);
                    rightMotor.setPower(-.50);
                    sleep(1000);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    sleep(100);
                    rightMotor.setPower(-.50);
                    leftMotor.setPower(.50);
                    sleep(250);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                    rightMotor.setPower(.50);
                    leftMotor.setPower(.50);
                    sleep(800);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                }
            }
            if (alianceColor == "blue"){
                if (stoneLoc=="timer"){
                    leftMotor.setPower(.50);
                    rightMotor.setPower(-.50);
                    sleep(150);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    sleep(100);
                    leftMotor.setPower(.50);
                    rightMotor.setPower(.50);
                    sleep(1200);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                }else {

                    leftMotor.setPower(.50);
                    rightMotor.setPower(.50);
                    sleep(1000);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    sleep(100);
                    rightMotor.setPower(.50);
                    leftMotor.setPower(-.50);
                    sleep(250);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                    rightMotor.setPower(.50);
                    leftMotor.setPower(.50);
                    sleep(800);
                    rightMotor.setPower(0);
                    leftMotor.setPower(0);
                }
            }


            elbow.moveByClicks(100);
            sleep(1000);
            lowerGrip.setGripPosition(0);
            sleep(1000);
            leftMotor.setPower(-.5);
            rightMotor.setPower(-.5);
            sleep(200);
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            sleep(1000);
            requestOpModeStop();

        }





    }

}
