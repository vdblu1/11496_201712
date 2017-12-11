package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 120449 on 11/14/17.
 */
@Disabled
public class DrivingClasses extends OpMode{
    DcMotor left_drive;
    DcMotor right_drive;
    DcMotor Arm;
    Servo Finger1;
    Servo   Finger2;
    Servo   GemMover;
    int progress = 0;
    boolean pressingA = false;
    boolean pressingB = false;
    boolean pressingX = false;
    double HoldPower = 0.0;
    float armPower;

    public void init()
    {
        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
        mediaPlayer.start();
        left_drive = hardwareMap.dcMotor.get("left");
        right_drive = hardwareMap.dcMotor.get("right");
        Arm = hardwareMap.dcMotor.get("Arm");
        Finger1 = hardwareMap.servo.get("rightFinger");
        Finger2 = hardwareMap.servo.get("leftFinger");
        GemMover = hardwareMap.servo.get("GemMover");
    }

    @Override
    public void loop() {}

    public void cAlgorithm() {

        final float nJoyX = (gamepad1.right_stick_x);
        float nJoyY = (gamepad1.right_trigger);
        float fPivYLimit = 1;
        //  int     nMotMixL;           // Motor (left)  mixed output           (-128..+127)
        //  int     nMotMixR;           // Motor (right) mixed output           (-128..+127)
        //float Forward = gamepad1.right_stick_x;
        //float Backward = -gamepad1.right_stick_x;
        float   nMotPremixL;
        float   nMotPremixR;
        float   fPivScale;      // Balance scale b/w drive and pivot    (   0..1   )

        if (nJoyY > 0) {
            // Forward
            nMotPremixL = (nJoyX>=0)? 1 : (1 + nJoyX);
            nMotPremixR = (nJoyX>=0)? (1 - nJoyX) : 1;
        } else {
            // Reverse
            nMotPremixL = (nJoyX>=0)? (1 - nJoyX) : 1;
            nMotPremixR = (nJoyX>=0)? 1 : (1 + nJoyX);
        }

        nMotPremixL = nMotPremixL * nJoyY/1;
        nMotPremixR = nMotPremixR * nJoyY/1;
        final float nPivSpeed = nJoyX;
        fPivScale = (Math.abs(nJoyY)>fPivYLimit)? 0 : (1 - Math.abs(nJoyY)/fPivYLimit);
        final float Left = (1-fPivScale)*nMotPremixL + fPivScale*( nPivSpeed);
        final float Right = (1-fPivScale)*nMotPremixR + fPivScale*(-nPivSpeed);
        left_drive.setPower(Left);
        right_drive.setPower(Right);
    }

    public void MyAlgorithm() {
        float Rotation = (gamepad1.right_stick_x);
        float Gas = (gamepad1.right_trigger);
        float reverse = (gamepad1.left_trigger);

        double Left = Gas;
        double Right = Gas;
        //Forward

        if (Rotation <0) {
            //Turn Right

            //left_drive.setPower((-Left*Rotation));
            //right_drive.setPower((Right*(1+Rotation)));

            left_drive.setPower((-Left/2));
            right_drive.setPower((Right/2));
        } else if (Rotation >0) {
            //Turn Left

            // left_drive.setPower((Left*(1-Rotation)));
            //right_drive.setPower((-Right*Rotation));
            left_drive.setPower((Left/2));
            right_drive.setPower((-Right/2));
        } else {
            left_drive.setPower(-Left);
            right_drive.setPower(-Right);
        }

        ///Backward

        double Left2 = reverse;
        double Right2 = reverse;

        if (Rotation < 0) {
            //Turn Left
            left_drive.setPower((-Left2/2));
            right_drive.setPower((Right2/2));
        } else if (Rotation > 0) {
            //Turn Right
            left_drive.setPower((Left2/2));
            right_drive.setPower((-Right2/2));
        } else {
            left_drive.setPower(Left2);
            right_drive.setPower(Right2);
        }
    }
    public void triggerDrive201711() {
        double direction = (gamepad1.left_stick_x);
        double forward = (gamepad1.right_trigger);
        double reverse = (gamepad1.left_trigger);
        double myPower = forward + reverse; //combine to simplify algorithm and prevent conflicting logic
        double stickSensativity = .2; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
        double triggSensativity = .2; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear

        direction = stickSensativity * (Math.pow(direction, 3)) + (1 - stickSensativity) * direction;
        myPower = triggSensativity * (Math.pow(myPower,3)) + (1 - triggSensativity)* direction;

        if (myPower==0) {//if power is zero pivot by running motors opposite direction
            left_drive.setPower(direction);
            right_drive.setPower(-direction);
        }else{
            if (direction >= 0) {//On power the max turn is when one motor is off and the other is full on
                left_drive.setPower(myPower);
                right_drive.setPower(myPower * (1-direction));
            }else{
                left_drive.setPower(myPower * (1-direction));
                right_drive.setPower(myPower);
            }
        }

    }
}


