package tse.fise2.image3.cardmatcher.model;



import java.awt.*;
import java.io.*;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tse.fise2.image3.cardmatcher.sift.Descriptor;
import tse.fise2.image3.cardmatcher.sift.Sift;
import tse.fise2.image3.cardmatcher.util.Audio;
import tse.fise2.image3.cardmatcher.util.ImageUtil;
import tse.fise2.image3.cardmatcher.util.MsgUtil;

public abstract class Camera{

    // a timer for acquiring the video stream
    private ScheduledExecutorService timer;
    // the OpenCV object that realizes the video capture
    private VideoCapture capture = new VideoCapture();
    // a flag to change the button behavior
    private boolean cameraActive = false;
    // the id of the camera to be used
    private static int cameraId = 0;
    // learning mode
    private boolean learningmode;
    //testing mode
    private boolean testingmode;
    private Boolean auto;

    private Card card= new Card("2coeur");
    private Descriptor descCard;

    //
    private Label label = new Label();
    //
    private Mat frame = new Mat();
    private ImageView imagedetection;


    public void openCamera(ImageView crframe, Button btn) throws InterruptedException, IOException {
        // TODO Autogenerated
        if (!this.cameraActive)
        {
            // start the video capture
            this.capture.open(cameraId);

            // is the video stream available?
            if (this.capture.isOpened())
            {
                this.cameraActive = true;

                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run()
                    {
                        // grab and process a single frame
                        frame = grabFrame();
                        Scalar scalar = new Scalar(182, 74, 108);
                        if (learningmode) {
                            // definition rectangle color
                            scalar= new Scalar(0, 0, 255);
                            // mettre couleur de la même palette que l'appli !!
                        }
                        else{
                            // definition rectangle color
                            scalar= new Scalar(0, 255, 0);
                        }
                        //Rectangle to capture frame
                       // Imgproc.rectangle(frame,new Point(200, 80), new Point(440, 400),scalar, 1);
                        Imgproc.rectangle(frame,new Point(200, 50), new Point(600, 650),scalar, 1);

//detection automatique-------------------------------------------------------------------------------------------------
//                         Rect rectCrop = new Rect(new Point(200, 80), new Point(440, 400));
//                        Mat crop_frame = new Mat(frame,rectCrop);
//                        if (ImageUtil.detectCard(crop_frame)) {
//
//                          Camera.this.notifyAll();
//                        }

//----------------------------------------------------------------------------------------------------------------------
                        // convert and show the frame
                        MatOfByte buffer1 = new MatOfByte();
                        Imgcodecs.imencode(".png", frame, buffer1);
                        // à revoir car pas trop bien compris
                        Image imageToShow = new Image(new ByteArrayInputStream(buffer1.toArray()));
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                crframe.setImage(imageToShow);
                            }
                        });


                    }
                };


                // à revoir aussi pour bien comprendre
                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);


                // update the button content
                if (this.learningmode || this.testingmode) {
                   btn.setText("Capture");
                }

                // close webcam when no capture taken
                Stage stage =(Stage)(btn.getScene().getWindow());
                stage.setOnCloseRequest((new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we)
                    {
                        stopAcquisition();
                    }
                }));


            }
            else
            {
                // log the error
                System.err.println("Impossible to open the camera connection...");
            }

        }


        else
        {

            // the camera is not active at this point
            this.cameraActive = false;

            // stop the timer
            this.stopAcquisition();
            if (this.learningmode || this.testingmode) {
                // update again the button content
                btn.setText("Restart");
                // play sound when taking pic
                Audio.play_sound(getClass().getResource("media/shot_sound.wav"));
                // Name the capture and save it in a folder
                this.showInputTextDialog();
                AddImageDetection(crframe);
//                System.out.println(this.label.getText());
            }
        }
    }

    public void AddImageDetection(ImageView  det_frame)
    {

        imagedetection = det_frame;
    }

    public void setTestingmode(boolean testingmode) {
        this.testingmode = testingmode;
    }


    public abstract void saveImage( ) throws IOException;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    private void showInputTextDialog() throws InterruptedException, IOException {
        if(learningmode) {
//            System.out.println("learning");
            TextInputDialog dialog = new TextInputDialog("Write here");
            dialog.setTitle("Save picture");
            dialog.setHeaderText("Enter the name of the picture ");
            dialog.setContentText("Name:");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name -> {
                card.setName(name);
                this.label.setText(name);
                try {
                    this.saveImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }else if(testingmode)
        {
//            System.out.println("test");
            Thread.sleep(2000);

           //calcul du descripteur de l'image test
            Rect rectCrop = new Rect(new Point(202, 82), new Point(438, 398));
            Mat crop_frame = new Mat(getFrame(),rectCrop);




            Descriptor desc = Sift.getDescriptor(crop_frame, "");
            //nom de la carte ayant le meilleur score de correspondance
            ScoreImage sm= Sift.getImageBestScore(desc.getDescriptor());
            desc.setImageName(sm.getImageName());
            setDescCard(desc);
            card.setName(sm.getImageName());


            MsgUtil.DisplayMsg("this card belongs to class "+card.getName()+" with the proximity score  "+sm.getScore() );

            this.saveImage();
            InputStream stream = null;
            try {

                String userHome = System.getProperty("user.dir"); // return c:\Users\${current_user_name}
                String folder = userHome + "/apprentissage";
                stream = new FileInputStream(folder+"/"+sm.getImageName()+".png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(stream);
            imagedetection.setImage(image);


        }

    }

    private Mat grabFrame() {
        // init everything
        Mat frame = new Mat();

        // check if the capture is open
        if (this.capture.isOpened()) {
            try {
                // read the current frame
                this.capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()) {
                    //mettre en nuances de gris
                    //Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR5652RGB);
                }
            } catch (Exception e) {
                // log the error
                System.err.println("Exception during the image elaboration: " + e);
            }
        }
        return frame;

    }

    public Label getLabel() {
        return label;
    }

    public Mat getFrame() {
        return frame;
    }

    public void stopAcquisition() {
        if (this.timer != null && !this.timer.isShutdown()) {
            try {
                // stop the timer
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
                this.capture.release();
            } catch (InterruptedException e) {
                // log any exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }
        }
        if (this.capture.isOpened()) {
            // release the camera
            this.capture.release();
        }
    }

    public boolean isCameraActive() {
        return cameraActive;
    }

    public void setCameraActive(boolean cameraActive) {
        this.cameraActive = cameraActive;
    }

    public boolean isLearningmode() {
        return learningmode;
    }

    public void setLearningmode(boolean learningmode) {
        this.learningmode = learningmode;
    }

    public void setDescCard(Descriptor descCard) {
        this.descCard = descCard;
    }

    public Boolean isAuto()
{
    return auto;
}
}

