package org.firstinspires.ftc.teamcode;


 import android.graphics.Color;
 import android.media.MediaPlayer;
 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import com.qualcomm.robotcore.hardware.DcMotor;
 import com.qualcomm.robotcore.hardware.Servo;
 import java.lang.*;

@TeleOp(name="MainDriving")
public class Main extends OpMode
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
        Finger1 = hardwareMap.servo.get("rightFinger");
        Finger2 = hardwareMap.servo.get("leftFinger");
        GemMover = hardwareMap.servo.get("GemMover");
    }

    @Override
    public void loop() {
        float Rotation = (gamepad1.right_stick_x);
        float Gas = (gamepad1.right_trigger);
        float reverse = (gamepad1.left_trigger);

        double Left = Gas;
        double Right = Gas;
        //Forward
        left_drive.setPower(Left);
        right_drive.setPower(-Right);

            if (Rotation <0) {
                 //Turn Right
                 left_drive.setPower(Left);
                 right_drive.setPower(Right);
             } else if (Rotation >0) {
                 //Turn Left
                 left_drive.setPower(-Left);
                 right_drive.setPower(-Right);
             }


            ///Backward

            double Left2 = reverse;
            double Right2 = reverse;
            left_drive.setPower(-Left2);
            right_drive.setPower(Right2);


                if (Rotation > 0) {
                    //Turn Left
                    left_drive.setPower(Left);
                    right_drive.setPower(Right);
                } else if (Rotation < 0) {
                    //Turn Right
                    left_drive.setPower(-Left);
                    right_drive.setPower(-Right);
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
        if (progress == 0) {
            Finger1.setPosition(1);
            Finger2.setPosition(0);
        } else if (progress == 1) {
            Finger1.setPosition(0.7);
            Finger2.setPosition(0.3);
        } else if (progress == 2) {
            Finger1.setPosition(0.55);
            Finger2.setPosition(0.45);

        } else if (progress == 3) {
            Finger1.setPosition(0.45);
            Finger2.setPosition(0.55);
        }

        Float armPower = gamepad2.left_stick_y /4;
        Arm.setPower(armPower);


    }

}



