package robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class OpenCVExperiment {
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Open the built in camera
        VideoCapture capture = new VideoCapture();
        capture.open(0);
        // Wait a second for camera exposure
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Read the image
        Mat frame = new Mat();
        capture.read(frame);
        
        // Make the image greyscale
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
        
        // Read the image into a buffer that can be written (as .png)
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".jpg", frame, buffer);
        
        // Write the image to disk
        File image = new File("example.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(image);
            fos.write(buffer.toArray());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(image.exists());
        
        
    }
}
