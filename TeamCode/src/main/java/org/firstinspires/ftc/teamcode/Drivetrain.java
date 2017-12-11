package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Mark on 11/19/2017.
 */

class Drivetrain {
    //@Override

    //double direction = (gamepad1.left_stick_x);
    //double forward = (gamepad1.right_trigger);
    //double reverse = (gamepad1.left_trigger);

    DcMotor left_drive;
    DcMotor right_drive;
    //double direction = 0;
    //double forward = 0;
    //double reverse = 0;
    public String DrivetrainName="";
    double myPower = 0;
    boolean dtStopped = true;
    double steerSensativity = -0.5; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
    double pivotSensativity = 0.9; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
    double triggSensativity = 0; //Factor for exponential response to controls. -1 to 1. + less response, - more, 0 linear
    boolean armControling = false;

    public void init (String name, DcMotor left_drive, DcMotor right_drive){
        DrivetrainName=name;
        this.left_drive=left_drive;
        this.right_drive=right_drive;
        this.left_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE );
        this.right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE );
    }
    public void armDrive(double pivValue, double fwdValue){//allows the arm operator fine control of the pivot
            if (pivValue!=0) {
                pivValue = pivValue / 6;
                left_drive.setPower(pivValue);
                right_drive.setPower(-pivValue);
            }else {
                    fwdValue = fwdValue / 6;
                    left_drive.setPower(fwdValue);
                    right_drive.setPower(fwdValue);
                }

    }

    public void triggerDrive201711(double direction, double forward, double reverse) {

        myPower =forward - reverse; //combine to simplify algorithm and prevent conflicting logic
        myPower = triggSensativity * (Math.pow(myPower,3)) + (1 - triggSensativity)* myPower;

        if (myPower==0 && direction==0){dtStopped=true;}//requires direction stick to be neutral for pivot to init

        if (myPower==0 && dtStopped) {//if power is zero pivot by running motors opposite direction
            //direction = pivotSensativity * (Math.pow(direction, 3)) + (1 - pivotSensativity) * direction;
            direction = direction/4;
            left_drive.setPower(direction);
            right_drive.setPower(-direction);
        }else{
            dtStopped = false;
            direction = steerSensativity * (Math.pow(direction, 3)) + (1 - steerSensativity) * direction;
            if (direction >= 0) {//On power the max turn is when one motor is off and the other is full on
                left_drive.setPower(myPower);
                right_drive.setPower(myPower * (1-direction));
            }else{
                left_drive.setPower(myPower * (direction+1));
                right_drive.setPower(myPower);
            }
        }

    }


}
