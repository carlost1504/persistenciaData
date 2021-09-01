package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Post;

import java.io.*;

import javax.imageio.stream.FileImageInputStream;


public class MainWindow extends Stage {


    private Button openText;
    private Button openImage;
    private ImageView imagePost;
    private TextArea textPost;
    private Button saveText1;

    public MainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent parent = loader.load();


            openImage = (Button) loader.getNamespace().get("openImage");
            imagePost = (ImageView) loader.getNamespace().get("imagePost");
            
            saveText1 = (Button) loader.getNamespace().get("saveText1");
            
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
    	
    	saveText1.setOnAction(event->{
    		//doSaveText();  //guardado forma corta
    		guardarJavaByteCode();
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
    
    public void doSaveText(){
		
		try {
			String texto = textPost.getText();
			File ret =new File("text.txt");
			FileOutputStream fos=new FileOutputStream(ret);
			fos.write(texto.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void guardarJavaByteCode() {
    	
    	try {
    		Post post=new Post(textPost.getText());
        	File ret =new File("javaTextByte.txt");
			FileOutputStream fos=new FileOutputStream(ret);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(post);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void loadData() {
    	try {
			File f= new File("javaTextByte.text");
			FileInputStream fis=new FileInputStream(f);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Post post=(Post) ois.readObject();
			textPost.setText(post.getText());
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
