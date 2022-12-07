package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name ="BRHighCV", group = "A")
//@Disabled
public class BRHighCV extends DriveMethods{
    OpenCvWebcam webcam;
    String result;

    public void runOpMode() {

        DamienCVPipelineBR_RR pipeline = new DamienCVPipelineBR_RR();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam.setPipeline(pipeline);
        webcam.setMillisecondsPermissionTimeout(2500);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {

            }
        });





        initMotorsBlue();
        clawClamp();
        sleep(500);


        while(!isStarted()) {
            telemetry.addLine("result: " + pipeline.getCurrentResultsStr());
            telemetry.update();
            result = pipeline.getCurrentResultsStr();

        }
        waitForStart();

        GoToHeight(300);

        driveForDistance(0.1, Variables.Direction.FORWARD,0.35,0);
        driveForDistance(0.62, Variables.Direction.RIGHT,0.35,0);
        driveForDistance(1.22, Variables.Direction.FORWARD,0.35,0);
        driveForDistance(0.45, Variables.Direction.LEFT, 0.35,0);
        goToHigh();
        driveForDistance(0.145, Variables.Direction.FORWARD,0.2,0);
        sleep(1000);
        clawRelease();
        sleep(200);
        driveForDistance(0.135, Variables.Direction.BACKWARD,0.35,0);
        goToDown();
        sleep(500);
        driveForDistance(0.35, Variables.Direction.RIGHT, 0.35,0);
        switch(result){
            case "purple":
                driveForDistance(1.35, Variables.Direction.LEFT, 0.35,0);
                break;
            case "yellow":
                driveForDistance(0.7, Variables.Direction.LEFT, 0.35,0);
            case "green":
                //You're where you need to be!
                break;

        }

        while (opModeIsActive()) {

        }
    }
}