package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by 120449 on 12/12/17.
 */

public class AutonomousMethods extends LinearOpMode {
    DcMotor leftMotor = null; //hardwareMap.dcMotor.get("left");
    DcMotor rightMotor = null; //hardwareMap.dcMotor.get("right");
    DcMotorEx elbowMotor = null ;//= hardwareMap.dcMotor.get("elbow");
    DcMotorEx shoulderMotor = null;//= hardwareMap.dcMotor.get("shoulder");
    Servo rightFinger = null ;//= hardwareMap.servo.get("rightFinger");
    Servo leftFinger = null;//= hardwareMap.servo.get("leftFinger");
    joint elbow = new joint();
    joint shoulder = new joint();

  //  ColorSensor Color;

    ElapsedTime holdTimer = new ElapsedTime();

    int position = 0;
    int gripAt = 0;
    @Override public void runOpMode() {
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");
        elbowMotor = (DcMotorEx)hardwareMap.get(DcMotor.class,"elbow");
        elbow.init("elbow", elbowMotor, 0, 1,telemetry, false);

        shoulderMotor = (DcMotorEx)hardwareMap.get(DcMotor.class,"shoulder");
        shoulder.init("shoulder", shoulderMotor, 0, 1, telemetry, false);

        leftFinger = hardwareMap.servo.get("leftFinger");
        rightFinger = hardwareMap.servo.get("rightFinger");
        leftFinger.setDirection(Servo.Direction.REVERSE);

       // Color = hardwareMap.colorSensor.get("colorOnFinger");

        waitForStart();
        //Leave empty, this class is just a placeholder for autonomous methods
    }

    public void knockGem(String TeamColor) {

        elbow.moveToClick(-258);
        fingerSwitch(4); //set the position of the fingers to 4 or "snowplow"
        shoulder.moveToClick(-370);
        elbow.moveToClick(-525);
        elbow.moveToClick(-820);
        shoulder.moveToClick(-1000); //this number we will probably have to play with, as it can range in testing from -1000,
                                           // to -1050
        if (TeamColor.equals("blue")) {
           /* if (Color.blue() == 100) //We need to get the values that the color sensor would return for blue, and red.
            {
                //means red ball on left, so move left finger
                leftFinger.setPosition(0);
            }
            if (Color.red() == 100)
            {
                //means red ball on right, so move right finger.
                rightFinger.setPosition(0);

            }*/
           telemetry.addData("TeamColor: ",TeamColor);
        } else if (TeamColor.equals("red")) {
         /*  if (Color.red() == 100)
            {
                //means blue ball on left, so move left finger.
                leftFinger.setPosition(0);
            }
            if (Color.blue() == 100) {
                //means blue ball on right, so move right finger.
                rightFinger.setPosition(0);
            }*/
            telemetry.addData("TeamColor: ",TeamColor);
        } else {
            telemetry.addData("Teamcolor not set to Red or blue. Value is: ",TeamColor);
        }

    }

    public void fingerSwitch(int position){

        switch (position) {
            case 0: //wide open start position
                rightFinger.setPosition(0);
                leftFinger.setPosition(0);
                gripAt = 0;
                break;
            case 1: //both 45 degrees from body
                rightFinger.setPosition(.25);
                leftFinger.setPosition(.25);
                gripAt = 1;
                break;
            case 2: //release position
                rightFinger.setPosition(.47);
                leftFinger.setPosition(.47);
                //if (holdTimer.seconds()<4){elbow.moveByClicks(100);} don't want this since we are running autonomously.
                gripAt = 2;
                break;
            case 3: //hold position
                rightFinger.setPosition(.53);
                leftFinger.setPosition(.53);
                holdTimer.reset();
               elbow.moveByClicks(-100);
                gripAt = 3;
                break;
            case 4: //snowplow position
                rightFinger.setPosition(.66);
                leftFinger.setPosition(.66);
                gripAt = 4;
                break;

        }
    }
}
