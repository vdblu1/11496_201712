package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
/**
 * Created by Mark on 11/18/2017.
 */

public class joint {
    String jointName = null;
    public int jointPosition = 0;
    DcMotorEx jointMotor = null;
    double expo = 0;
    boolean holding = false;
    double topSpeed = 1;
    PIDCoefficients PIDs = null;
    private Telemetry telemetry;
    public int value = 0;

    public void init(String name, DcMotorEx jointMotor, double expo, double topSpeed, Telemetry telemetry, boolean ReverseBit){
        jointName = name;
        this.jointMotor = jointMotor;
        this.jointMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.jointMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.jointMotor.setTargetPositionTolerance(3);
        this.expo = expo;
        this.topSpeed = topSpeed;
        this.telemetry = telemetry;
        if (ReverseBit) {this.jointMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
        this.jointMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.jointMotor.setTargetPosition(0);
        this.jointMotor.setPower(1);
        //PIDCoefficients pidNew = new PIDCoefficients(37, 0, 9);
        //this.jointMotor.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidNew);
    }

    //public int getPosition(){return jointPosition;}
    //public void setJointMotor(DcMotor hwMotorMap){jointMotor = hwMotorMap;}
    //public void setGearRatio(int Pinion, int Gear){}
    //public void setSensitivity(double value){expo=value;}
    public void moveByClicks(int value){
        jointMotor.setTargetPosition(jointMotor.getCurrentPosition()+value);
    }

    public void moveToClick(int value){
        //jointMotor.setVelocity(15,AngleUnit.DEGREES);
        jointMotor.setTargetPosition(value);
    }

    public void moveJointNew(double value){
        if (value!=0) {
            jointPosition = (int) Math.round(jointMotor.getCurrentPosition() + (value * 40));
            jointMotor.setTargetPosition(jointPosition);
        }
        PIDCoefficients PIDs = jointMotor.getPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData(jointName + " Tolerance ", jointMotor.getTargetPositionTolerance());
        telemetry.addData(jointName + " PID ", "%.04f, %.04f, %.04f", PIDs.p, PIDs.i, PIDs.d);
        telemetry.addData(jointName + " Current Position ", jointMotor.getCurrentPosition());
        telemetry.addData(jointName + " Set Position ", jointPosition);
        telemetry.addData(jointName + " Power ", value);
        telemetry.addData(jointName + " Power Get ", jointMotor.getPower());
    }


    public void moveJoint(double value){
        if (value!=0){
            holding=false;
            if (value>0) {
                jointPosition = jointPosition + 40;
            }
            else{
                jointPosition = jointPosition - 40;
                }
            jointMotor.setTargetPosition(jointPosition);
            jointMotor.setPower(Math.abs(Math.pow(value,2))/2);
        }
        else{
            if(!holding){
                jointPosition=jointMotor.getCurrentPosition();
                jointMotor.setTargetPosition(jointPosition);
                jointMotor.setPower(1);
                holding = true;
            }
        }

        //value = (expo * (Math.pow(value, 3))) + ((1 - expo) * value);
        //telemetry.addData(jointName + " Velocity ", jointMotor.getVelocity(AngleUnit.valueOf("Degrees")));
        telemetry.addData(jointName + " Tolerance ", jointMotor.getTargetPositionTolerance());
        telemetry.addData(jointName + " PID ", jointMotor.getPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION));
        //telemetry.addData(jointName + " Holding ", holding);
        telemetry.addData(jointName + " Current Position ", jointMotor.getCurrentPosition());
        telemetry.addData(jointName + " Set Position ", jointPosition);
        telemetry.addData(jointName + " Power ", value);
        //telemetry.addData(jointName + " Power Float ", jointMotor.getPowerFloat());
        //telemetry.update();
    }

}
