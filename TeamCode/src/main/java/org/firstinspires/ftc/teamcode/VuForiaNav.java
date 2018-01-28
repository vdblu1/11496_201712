package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by dec1map on 1/19/2018.
 */

public class VuForiaNav {

    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia = null;
    //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    //VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    // OR...  Do Not Activate the Camera Monitor View, to save power
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    RelicRecoveryVuMark vuMark = null;
    VuforiaTrackable relicTemplate = null;
    VuforiaTrackables relicTrackables = null;
    OpenGLMatrix pose=null;

    public void init(){
        parameters.vuforiaLicenseKey = "AWQe7S7/////AAAAma1Aoas5pUqFqEpSQKyez56Kigux0TjSrqlIOD37+t2JHyhsC4ge8gQBKKF19qCWWSZ4G4MD/Hw7Xy+5y4zHHGPU6h2BM8uCHESTgsaM7i1bNQkgYBXb+o+WHPeShsW0f7/NqGR4iO5tuXPLeTOjM0HCP2QWxW0z7kUWQjiwoBFxt0lcf14kETsRgIpvoC6lTdyB5OYK9o5aqrgZdmwojqaaUXbd9D+MEEJ+VGE4D8Hg2fQZ/ux+zgstjw1neYeXIJGdbisC6pJW8QFEO8xp/TLNiRSoqslB5chUD8VQIUAdjcX1lwMf4tRDWn4KshaeNU264C0PP6tFbK8VVsRn1tC6kFBIcfml5VpJWZAq3ona";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();

        vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            vuMark = RelicRecoveryVuMark.from(relicTemplate);
        }

    }

    public OpenGLMatrix getCurrentPosition(){
            pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
            return pose;
    }


    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

}
