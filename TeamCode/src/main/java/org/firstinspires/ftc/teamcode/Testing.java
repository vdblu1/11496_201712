package org.firstinspires.ftc.teamcode;


import android.graphics.Color;
import android.media.MediaPlayer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;

@TeleOp(name="Test code")
public class Testing extends OpMode
{
    DcMotor left_drive;
    DcMotor right_drive;
    DcMotor Arm;
    Servo   Finger1;
    Servo   Finger2;
    Servo   GemMover;
    int progress = 0;
    boolean pressingA = false;
    boolean pressingB = false;

    public void init()
    {
        MediaPlayer mediaPlayer = MediaPlayer.create(hardwareMap.appContext, R.raw.nxtstartupsound);
        mediaPlayer.start();
        left_drive = hardwareMap.dcMotor.get("left");
        right_drive = hardwareMap.dcMotor.get("right");
        Arm = hardwareMap.dcMotor.get("Arm");
        Finger1 = hardwareMap.servo.get("Finger1");
        Finger2 = hardwareMap.servo.get("Finger2");
        GemMover = hardwareMap.servo.get("GemMover");
    }


    @Override
    public void loop() {
        //START Main Driving with triggers
        //Forward
/**
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
 left_drive.setPower(-Left);
 right_drive.setPower(Right);

 //Backward
 final float nJoyX1 = (gamepad1.right_stick_x);
 float nJoyY1 = (gamepad1.left_trigger);
 float fPivYLimit1 = 1;
 int     nMotMixL1;           // Motor (left)  mixed output           (-128..+127)
 int     nMotMixR1;           // Motor (right) mixed output           (-128..+127)
 //float Forward = gamepad1.right_stick_x;
 //float Backward = -gamepad1.right_stick_x;
 float   nMotPremixL1;
 float   nMotPremixR1;
 float   fPivScale1;      // Balance scale b/w drive and pivot    (   0..1   )

 if (nJoyY1 >= 0) {
 // Forward
 nMotPremixL1 = (nJoyX1>=0)? 1 : (1 + nJoyX1);
 nMotPremixR1 = (nJoyX1>=0)? (1 - nJoyX1) : 1;
 } else {
 // Reverse
 nMotPremixL1 = (nJoyX1>=0)? (1 - nJoyX1) : 1;
 nMotPremixR1 = (nJoyX1>=0)? 1 : (1 + nJoyX1);
 }

 nMotPremixL1 = nMotPremixL1 * nJoyY1/1;
 nMotPremixR1 = nMotPremixR1 * nJoyY1/1;
 final float nPivSpeed1 = nJoyX1;
 fPivScale1 = (Math.abs(nJoyY1)>fPivYLimit1)? 0 : (1 - Math.abs(nJoyY1)/fPivYLimit1);
 final float Left1 = (1-fPivScale1)*nMotPremixL1 + fPivScale1*( nPivSpeed1);
 final float Right1 = (1-fPivScale1)*nMotPremixR1 + fPivScale1*(-nPivSpeed1);
 left_drive.setPower(Left1);
 right_drive.setPower(-Right1);

 //END Main driving with triggers
 **/

        //Start NEW drive concept algorithm (To fix the turning)

        float Rotation = (gamepad1.right_stick_x);
        float Gas = (gamepad1.right_trigger);
        float reverse = (gamepad1.left_trigger);

        double Left = Gas;
        double Right = Gas;
        //Forward
        left_drive.setPower(Left);
        right_drive.setPower(-Right);
        if (Gas > 0){
            if (Rotation >0) {
                //Turn Right
                left_drive.setPower(Left);
                right_drive.setPower(Right);
            } else if (Rotation <0) {
                //Turn Left
                left_drive.setPower(-Left);
                right_drive.setPower(-Right);
            }
        }

        ///Backward

        double Left2 = reverse;
        double Right2 = reverse;
        left_drive.setPower(-Left2);
        right_drive.setPower(Right2);

        if (reverse > 0) { //Maybe this if loop will fix reverse turning?
            if (Rotation > 0) {
                //Turn Left
                left_drive.setPower(Left);
                right_drive.setPower(Right);
            } else if (Rotation < 0) {
                //Turn Right
                left_drive.setPower(-Left);
                right_drive.setPower(-Right);
            }
        }



        if (gamepad2.y) {

            progress = 3;

        }


        if(gamepad2.a)
        {
            if (pressingA == false) {


                if (progress < 3) {

                    progress++;


                }
                pressingA = true;
            }

        } else {
            pressingA = false;
        }
        if(gamepad2.b)
        {
            if (pressingB == false) {


                if (progress > 0) {

                    progress--;


                }
                pressingB = true;
            }

        } else {
            pressingB = false;
        }

        long startTime;
        long endTime;
        long duration = 0;
        if (progress == 0) {
            //Finger1.setPosition(convertDegress(180));
            //Finger2.setPosition(convertDegress(1));
            Finger1.setPosition(1);
            Finger2.setPosition(0);
        } else if (progress == 1) {
            //Finger1.setPosition(convertDegress(126));
            //Finger2.setPosition(convertDegress(54));
            Finger1.setPosition(0.7);
            Finger2.setPosition(0.3);
        } else if (progress == 2) {
            //Finger1.setPosition(convertDegress(99));
            //Finger2.setPosition(convertDegress(81));
            Finger1.setPosition(0.55);
            Finger2.setPosition(0.45);

            if (duration > 3){

                //new Servo to push block out
            }
        } else if (progress == 3) {
            //Finger1.setPosition(convertDegress(81));
            //Finger2.setPosition(convertDegress(99));
            startTime = System.currentTimeMillis();
            Finger1.setPosition(0.45);
            Finger2.setPosition(0.55);
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
        }


// Possibly use gamepad2.leftstickX to "Wiper blade" code
        //Limit power DONE
        Float armPower = gamepad2.left_stick_y /4;
        Arm.setPower(armPower);

        double newArmPower;
        newArmPower = 0.0;
            if (gamepad2.x) {
                newArmPower = Arm.getPower();

                if (newArmPower == gamepad2.left_stick_x) {

                    Arm.setPower(newArmPower);

                }
            }
        //  Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
/*
        if (gamepad2.dpad_up){
            GemMover.setPosition(0.95);

        } else if (gamepad2.dpad_down){
            GemMover.setPosition(0.2);

        }*/


        telemetry.addData("Finger 1 Position", Finger1.getPosition());
        telemetry.addData("Finger 2 Position",Finger2.getPosition());
        telemetry.addData("int Position", progress);
        telemetry.addData("boolean pressing A",pressingA);
        telemetry.addData("boolean pressing B",pressingB);
        telemetry.addData("gamepad2 B", gamepad2.b);
        telemetry.addData("Gamepad2 left stick ARM power",gamepad2.left_stick_y);
        telemetry.addData("duration of Milliseconds", duration);
    }
    /***
     * This is a method to accept inputs of degress from 0-180
     * This was made because it's much easier and faster to control the servos with degrees instead of a decimal.
     *
     * Usage is you call convertDegress(Degress) and it will return it to the program as a Decimal.
     */
    public double convertDegress(int degress){
        double finalNum = (degress / 180);
        return finalNum;
    }

}



