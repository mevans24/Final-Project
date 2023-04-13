import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
 
public final class caesarCipher extends Application {
 
    private Desktop desktop = Desktop.getDesktop();
 
    @Override
    public void start(final Stage stage) {
        stage.setTitle("Encrypt and Decrypt");
 
        final FileChooser fileChooser = new FileChooser();
 
        final Button openButton = new Button("Encrypt");
        final Button openButton2 = new Button("Decrypt");
        
        Text welcome = new Text();
        welcome.setText("Hi, I'm a program that can encrypt and decrypt files using a Ceasar cipher.\n");
        welcome.setX(10);
        welcome.setY(25);
        Text textBox = new Text();
        textBox.setText("Please enter a number into the text box. This will determine how much the code is shifted\nin compliance with a Ceasar cipher.\nThe minimum shift value is 1 and the maximum is 126.");
        textBox.setX(10);
        textBox.setY(40);

        final GridPane inputGridPane = new GridPane();
 
        TextField textField = new TextField();
        
        openButton.setLayoutX(150);
        openButton.setLayoutY(120);
        openButton2.setLayoutX(300);
        openButton2.setLayoutY(120);
        textField.setLayoutX(175);
        textField.setLayoutY(80);
        openButton.setStyle("-fx-background-color: mediumorchid");
        openButton2.setStyle("-fx-background-color: mediumorchid");


        Pane rootGroup = new Pane();
    
        rootGroup.setStyle("-fx-background-color: lavender");

        Random rand = new Random();
        textField.setText("1");
 
        openButton.setOnAction(
            
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                   try{
                        if (file != null) {
                            //openFile(file);
                            InputStream input = new FileInputStream(file);
                            
                            int ii = input.read();
                            String tf = "";
                            String ttf = "";
                            //int shift;
                            
                            
                            int shift = Integer.parseInt(textField.getText());

                            

                            //if (shift == 0) {
                            //    shift = rand.nextInt(25);
                            //}

                            //int shift;
                            //public static void submit(ActionEvent event) {
                            //int shift = Integer.parseInt(myTextField.getText());
                            //}



                            //read file by bit
                            while(ii != -1)
                            {
                                tf += (char)ii;
                                ii = input.read();
                            }
                        
                            
                           // input.close();/* */
                            //encrypt and out put file
                           // for(char iii : tf.toCharArray())
                           // {
                           //     ttf += (char) (iii+shift);
                           // }
                           // System.out.println(ttf);

                            input.close();
                            //encrypt and out put file
                            for(char iii : tf.toCharArray())
                            {
                                if((int)iii==10||(int)iii==13){
                                    ttf += "\n";
                                }
                                else{
                                    if((int)iii!=10){
                                     ttf += (char) (iii+shift);
                                    }
                                }
                            
                            
                            }
                            System.out.println(ttf);

                          PrintWriter out = new PrintWriter(file);
                           out.print(ttf);
                            out.close();

                            

                             
                        }
                    }
                    catch(Exception ee)
                    {
                        
                    }
                }
            });
 
        openButton2.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    try{
                        if (file != null) {
                            //openFile(file);
                            InputStream input = new FileInputStream(file);
                            
                            int ii = input.read();
                            String tf = "";
                            String ttf = "";
                            //int shift = 1;
                            int shift = Integer.parseInt(textField.getText());

                            //read file by bit
                            while(ii != -1)
                            {
                                tf += (char)ii;
                                ii = input.read();
                                
                            }
                        
                            
                            input.close();
                            

                            //decrypt
                            String tttf = "";
                            // for(char iiii : tf.toCharArray())
                            // {
                            //     tttf += (char) (iiii-shift);
                            // }
                            for(char iiii : tf.toCharArray())
                            {
                                if((int)iiii==10||(int)iiii==13){
                                    tttf += "\n";
                                }
                                else{
                                    if((int)iiii!=10){
                                     tttf += (char) (iiii-shift);
                                    }
                                }
                               // tttf += (char) (iiii-shift);
                            }
                            

                            System.out.println(tttf);

                          PrintWriter out1 = new PrintWriter(file);
                           out1.print(tttf);
                          out1.close();

                             
                        }
                    }
                    catch(Exception ee)
                    {
                        //e.getStackTrace();
                    }
                }
            });
                    

        inputGridPane.getChildren().addAll(openButton, welcome, openButton2, textField, textBox);
        

        
        rootGroup.getChildren().addAll(openButton, welcome, openButton2, textField, textBox);
    
        

        stage.setScene(new Scene(rootGroup,500,200));
       
        stage.show();
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
         
        private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        ); 
    }
            
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                caesarCipher.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}