package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by DEC1MAP on 1/1/2018.
 */

public class gripper {
    String gripName = null;
    //int gripPosition = 0;
    int gripAt = 0;
    Servo rightFinger = null ;//= hardwareMap.servo.get("rightFinger");
    Servo leftFinger = null;//= hardwareMap.servo.get("leftFinger");
    private Telemetry telemetry;


    public void init(String name, Servo rightFinger, Servo leftFinger, Telemetry telemetry ){
        this.leftFinger = leftFinger;
        this.rightFinger = rightFinger;
        leftFinger.setDirection(Servo.Direction.REVERSE);
        this.telemetry = telemetry;


    }

    public void setGripPosition(int gripPosition){

        if (gripPosition!=gripAt) {
            switch (gripPosition) {
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
                    //if (holdTimer.seconds()<4){elbow.moveByClicks(100);}
                    gripAt = 2;
                    break;
                case 3: //hold position
                    rightFinger.setPosition(.53);
                    leftFinger.setPosition(.53);
                    //holdTimer.reset();
                    //elbow.moveByClicks(-100);
                    gripAt = 3;
                    break;
                case 4: //snowplow position
                    rightFinger.setPosition(.66);
                    leftFinger.setPosition(.66);
                    gripAt = 4;
                    break;

            }//end switch
        } //end if
    }//end setGripPosition

    public void wiggleFingers( double lWiggle, double rWiggle        ){

        double leftFingerStart = leftFinger.getPosition();
        double rightFingerStart = rightFinger.getPosition();
        double servoInc = .01;
        gripAt = 99;
        if (lWiggle > 0) {
            leftFinger.setPosition(leftFingerStart + servoInc);
        } else if (lWiggle < 0) {
            leftFinger.setPosition(leftFingerStart - servoInc);
        }
        if (rWiggle > 0) {
            rightFinger.setPosition(rightFingerStart - servoInc);
        } else if (rWiggle < 0) {
            rightFinger.setPosition(rightFingerStart + servoInc);
        }

    }

}
