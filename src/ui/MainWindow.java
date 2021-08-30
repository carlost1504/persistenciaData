package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;


public class MainWindow extends Stage {


    private Button openText;
    private Button openImage;
    private ImageView imagePost;
    private TextArea textPost;

    public MainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent parent = loader.load();


            openImage = (Button) loader.getNamespace().get("openImage");
            imagePost = (ImageView) loader.getNamespace().get("imagePost");

            openText = (Button) loader.getNamespace().get("openText");
            textPost = (TextArea) loader.getNamespace().get("textPost");

            Scene scene = new Scene(parent, 600, 400);
            setScene(scene);

            init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void init() {
    	openImage.setOnAction(event->{
    		doOpenImage();
    	});
    	openText.setOnAction(event->{
    		doOpenText();
    	});
    }
    
    public void doOpenText(){
    	FileChooser fc=new FileChooser();
    	fc.setTitle("Abra una imagen");
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("TXT","*.txt")
    	);
    	File file=fc.showOpenDialog(this);
    	
    	try {
    		FileInputStream fis=new FileInputStream(file);
    		ByteArrayOutputStream baos= new ByteArrayOutputStream();
    		
    		byte[] buffer= new byte[3];
    		int bytesview= 0;
    		while ((bytesview=fis.read(buffer))!=-1) {
				System.out.println(bytesview);
				baos.write(buffer,0,bytesview);
			}
    		fis.close();
    		baos.close();
    		String lector=baos.toString();
    		textPost.setText(lector);
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    
    public void doOpenImage(){
    	FileChooser fc=new FileChooser();
    	fc.setTitle("Abra una imagen");
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("PNG","*.png"),
    			new ExtensionFilter("JPG","*.jpg")
    	);
    	File file=fc.showOpenDialog(this);
    	System.out.println(file.exists());
    	
    	if(file!=null) {
    		
    		Image image=new Image("file:"+file.getAbsolutePath());
    		imagePost.setImage(image);
    	}
    }

}
