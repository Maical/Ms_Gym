/*
    This class displays the login
 */
package M_Gym;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * 
 * @author Michael Liu & Maria Hoang
 */

public class Login extends GridPane{
    
    protected Button btnFirst;
    private TextField userTextField;
    private PasswordField pwBox;
    private Text actiontarget = null;
    
    public Login(){

        super();
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Login to M's Gym");
        scenetitle.setId("welcome");
        this.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        this.add(userName, 0, 1);

        userTextField = new TextField();
        this.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        this.add(pw, 0, 2);

        pwBox = new PasswordField();
        this.add(pwBox, 1, 2);

        btnFirst = new Button("Sign in"); //button messes itup
        btnFirst.setDefaultButton(true);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btnFirst);
        this.add(hbBtn, 1, 4);
        
        actiontarget = new Text();
        actiontarget.setId("actiontarget");
        this.add(actiontarget, 1, 6);

    }
    
    /**
     * 
     * @return  the username
     */
    public String getTextField1(){
       String string1 = userTextField.getText();
       return string1; 
    }
    
    /**
     * 
     * @return the password
     */
    public String getTextField2(){
       String string2 = pwBox.getText();
       return string2; 
    }
    
    /**
     * This method displays an error message if invalid credentials
     */
    public void warningMessage(){
       actiontarget.setText("Invalid Credentials");
    }
    
}