package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by 120449 on 12/12/17.
 */

public class MainDriveExtension extends OpMode{
    DcMotor leftMotor = null; //hardwareMap.dcMotor.get("left");
    DcMotor rightMotor = null; //hardwareMap.dcMotor.get("right");
    DcMotorEx elbowMotor = null ;//= hardwareMap.dcMotor.get("elbow");
    DcMotorEx shoulderMotor = null;//= hardwareMap.dcMotor.get("shoulder");
    Servo rightFinger = null ;//= hardwareMap.servo.get("rightFinger");
    Servo leftFinger = null;//= hardwareMap.servo.get("leftFinger");
    //Servo   GemMover ;//= hardwareMap.servo.get("GemMover");
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
    Drivetrain myDrive = new Drivetrain();
    ElapsedTime holdTimer = new ElapsedTime();
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");
        myDrive.init("myDrive", leftMotor, rightMotor);

        elbowMotor = (DcMotorEx)hardwareMap.get(DcMotor.class,"elbow");
        elbow.init("elbow", elbowMotor, 0, 1,telemetry, false);

        shoulderMotor = (DcMotorEx)hardwareMap.get(DcMotor.class,"shoulder");
        shoulder.init("shoulder", shoulderMotor, 0, 1, telemetry, false);

        leftFinger = hardwareMap.servo.get("leftFinger");
        rightFinger = hardwareMap.servo.get("rightFinger");
        leftFinger.setDirection(Servo.Direction.REVERSE);

        //GemMover = hardwareMap.servo.get("GemMover");

        //shoulder.expo=.2;
        //elbow.expo = .2;
        //telemetry.setAutoClear(false);
    }
    @Override
    public void loop() {}

    public void ArmControl() {
                /*if (((gripPosition==3)&&(!driveControling))&&((gamepad2.left_stick_x!=0)||(gamepad2.right_trigger!=0))){
            myDrive.armDrive(gamepad2.left_stick_x,gamepad2.right_trigger);//if holding the arm operator can drive
            armControling=true;
        }else {
            armControling=false;
            myDrive.armDrive(0,0);
        }

        if ((!armControling)&&(gamepad1.left_stick_x!=0 || gamepad1.right_trigger!=0 || gamepad1.left_trigger!=0)){
            myDrive.triggerDrive201711(gamepad1.left_stick_x, gamepad1.right_trigger,gamepad1.left_trigger);
            driveControling=true;
        }else {
            driveControling=false;
            myDrive.triggerDrive201711(0,0,0);
        }
        */

        if (gripPosition==3){
            if (((gamepad1.left_stick_x!=0) || (gamepad1.right_trigger!=0) || (gamepad1.left_trigger!=0))){
                driveNeutral=false;
            }  else {driveNeutral=true;}

            if ((gamepad2.right_trigger!=0) || (gamepad2.left_stick_x!=0)){
                armNeutral=false;
            } else {armNeutral=true;}

            if (dtStopped) {
                if ((!driveNeutral)&&(armNeutral)) {
                    driveControling = true;
                    dtStopped = false;
                }
                if ((!armNeutral) && (driveNeutral)) {
                    armControling = true;
                    dtStopped = false;
                }
            }else{
                if (driveNeutral && armNeutral){
                    dtStopped = true;
                    driveControling=false;
                    armControling=false;
                    myDrive.triggerDrive201711(0,0,0);
                    myDrive.armDrive(0,0);
                }
            }

        }else {
            driveControling=true;
            armControling=false;
        }

        if (armControling){
            myDrive.armDrive(gamepad2.left_stick_x,gamepad2.right_trigger);
        }
        /*else {
            myDrive.armDrive(0,0);
        }//if holding the arm operator can drive
        */

        if (driveControling){
            myDrive.triggerDrive201711(gamepad1.left_stick_x, gamepad1.right_trigger,gamepad1.left_trigger);
        }
        /*else {
            myDrive.triggerDrive201711(0,0,0);
        }
        */
    }

    public void driveControlConditional() {
        if (driveControling){
            myDrive.triggerDrive201711(gamepad1.left_stick_x, gamepad1.right_trigger,gamepad1.left_trigger);
        }
        /*else {
            myDrive.triggerDrive201711(0,0,0);
        }
        */

        //myDrive.triggerDrive201711(gamepad1.left_stick_x, gamepad1.right_trigger,gamepad1.left_trigger);
        // myDrive.armDrive(gamepad2.left_stick_x,gamepad2.right_trigger);

    }

    public void RobotFingerLogic() {
        if(gamepad2.right_bumper)
        {
            if (!pressingRB) {
                if (gripPosition < 3) {
                    gripPosition=gripPosition+1;
                }
                pressingRB = true;
            }
        } else {
            pressingRB = false;
        }

        if(gamepad2.left_bumper)
        {
            if (!pressingLB) {
                if (gripPosition > 0) {
                    gripPosition = gripPosition - 1;
                }
                pressingLB = true;
            }
        } else {
            pressingLB = false;

        }
        if(gamepad2.x) {
            if (!pressingX) {
                gripPosition = 4;
                pressingX = true;
            } else {
                pressingX = false;
            }
        }
        if ((gamepad2.right_stick_x!=0 || gamepad2.left_stick_x!=0)) {
            if ((gripPosition!=3)&&(gripPosition!=4)) {//allows the grip fingers to be manipulated as long as not holding
                double leftFingerStart = leftFinger.getPosition();
                double rightFingerStart = rightFinger.getPosition();
                double servoInc = .01;
                gripAt = 99;
                if (gamepad2.left_stick_x > 0) {
                    leftFinger.setPosition(leftFingerStart + servoInc);
                } else if (gamepad2.left_stick_x < 0) {
                    leftFinger.setPosition(leftFingerStart - servoInc);
                }
                if (gamepad2.right_stick_x > 0) {
                    rightFinger.setPosition(rightFingerStart - servoInc);
                } else if (gamepad2.right_stick_x < 0) {
                    rightFinger.setPosition(rightFingerStart + servoInc);
                }
            }

        }else {
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
                        if (holdTimer.seconds()<4){elbow.moveByClicks(100);}
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
        shoulder.moveJointNew(gamepad2.left_stick_y);
        elbow.moveJointNew(gamepad2.right_stick_y);

         /*
        if(gamepad2.a) {
            if (!pressingA) {
                elbow.moveToClick(-400);
                shoulder.moveToClick(-380);
                pressingA = true;
            } else {
                pressingA = false;
            }
        }
        if(gamepad2.b) {
            if (!pressingB) {
                elbow.moveToClick(-600);
                shoulder.moveToClick(-580);
                pressingB = true;
            } else {
                pressingB = false;
            }
        }
        */
    }

    public void TelemetryData(){
        //telemetry.addData("Finger 1 degrees", rightFinger.getPosition()*180);
        //telemetry.addData("Finger 2 degrees",leftFinger.getPosition()*180);
        telemetry.addData("Grip Position", gripPosition);
        telemetry.addData("DT Stopped", dtStopped);
        telemetry.addData("Driver Neutral", driveNeutral);
        telemetry.addData("ArmDrive Neutral", armNeutral);
        telemetry.addData("Driver Control", driveControling);
        telemetry.addData("Arm Control", armControling);
        //telemetry.addData("Hold Timer", holdTimer.seconds());
        //telemetry.addData("RB", gamepad2.right_bumper);
        //telemetry.addData("Pressing RB", pressingRB);
        //telemetry.addData("boolean pressing Right Bumper",pressingRB);
        //telemetry.addData("boolean pressing B",pressingLB);
        telemetry.update();
    }
}
