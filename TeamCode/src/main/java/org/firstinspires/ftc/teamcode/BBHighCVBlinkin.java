package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name ="BBHighCVBlinkin", group = "A")
//@Disabled
public class BBHighCVBlinkin extends DriveMethods{
    OpenCvWebcam webcam;
    private String result;

    public void runOpMode() {

        DamienCVPipelineRB_BB pipeline = new DamienCVPipelineRB_BB();
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



        initMotorsBlueBlinkin();
        clawClamp();
        sleep(500);
        result = pipeline.getCurrentResultsStr();
        while(!isStarted()) {
            telemetry.addLine("result: " + pipeline.getCurrentResultsStr());
            telemetry.update();
            result = pipeline.getCurrentResultsStr();
            switch (result) {
                case "purple":
                    setBlinkinColor(Variables.BlinkinColor.PURPLE);
                    break;
                case "green":
                    setBlinkinColor(Variables.BlinkinColor.GREEN);
                    break;
                case "yellow":
                    setBlinkinColor(Variables.BlinkinColor.YELLOW);
                    break;
            }
        }

        waitForStart();

        GoToHeight(300);

        driveForDistance(0.1, Variables.Direction.FORWARD,0.35);
        driveForDistance(0.65, Variables.Direction.LEFT,0.35);
        driveForDistance(1.22, Variables.Direction.FORWARD,0.35);
        driveForDistance(0.36, Variables.Direction.RIGHT, 0.35);
        goToHigh();
        driveForDistance(0.07, Variables.Direction.FORWARD,0.2);
        sleep(500);
        GoToHeight(4150);
        sleep(500);
        clawRelease();
        sleep(200);
        goToHigh();
        sleep(500);
        driveForDistance(0.17, Variables.Direction.BACKWARD,0.35);
        goToDown();
        sleep(500);
        driveForDistance(0.35, Variables.Direction.LEFT, 0.35);
        switch(result){
            case "purple":
                driveForDistance(1.50, Variables.Direction.RIGHT, 0.35);
                break;
            case "yellow":
                driveForDistance(0.7, Variables.Direction.RIGHT, 0.35);
                break;
            case "green":
                //You're where you need to be!
                break;

        }
        setBlinkinColor(Variables.BlinkinColor.GREEN_PULSE);
        // Done!

        while (opModeIsActive()) {

        }
    }
}
