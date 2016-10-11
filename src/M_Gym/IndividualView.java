/**
 * This class will display each record individually
 */
package M_Gym;

import java.awt.image.BufferedImage;
import java.io.*;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * 
 * @author Michael Liu & Maria Hoang
 */

public class IndividualView extends BorderPane {

    private GridPane root;
    protected TextField txtName, txtAddress, txtPhone, txtGender, txtRegDate, txtPlan, txtID, txtBalance;
    private Label lblName, lblAddress, lblPhone, lblGender, lblRegDate, lblPlan, lblID, lblBalance;
    protected Button btnPrev, btnNext, btnHome, btnSearch, btnDel, btnAddPic;// btnExit;
    
    private ArrayList<GymMember> memberList = new ArrayList<GymMember>();
    private ArrayList<Integer> arl = new ArrayList<Integer>();
    private int currentIndex = 0;
    protected ImageView selectedImage;
    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem openItem, saveItem, exitItem;
    private Stage stage;
    
    //try out for new individual button, this is all for indiviudalview super edit.
    protected Button btnE2; // put this into second scene edit to see if its any different.
    
    //april 26 using the same grabbers
    private String sdatez, namez, accz, addz;
    private int idz;
    private double balz;
    
    //April 25, Add a make a payment feature. (now within the same class?
    protected Button btnPay;
    protected Payment pPane;

    public IndividualView() {
        super();
        selectedImage = new ImageView();
        // creating the labels
        lblID = new Label("Account #: ");
        lblName = new Label("Name: ");
        lblAddress = new Label("Address: ");
        lblPhone = new Label("Phone: ");
        lblGender = new Label("Gender: ");
        lblRegDate = new Label("Registration Date: ");
        lblPlan = new Label("Membership Plan: ");
        lblBalance = new Label("Balance: ");

        //creating the textfields
        txtID = new TextField();
        txtID.setEditable(false);
        txtName = new TextField();
        txtName.setEditable(false);
        txtAddress = new TextField();
        txtAddress.setEditable(false);
        txtRegDate = new TextField();
        txtRegDate.setEditable(false);
        txtPlan = new TextField();
        txtPlan.setEditable(false);
        txtBalance = new TextField();
        txtBalance.setEditable(false);
        
       
        btnPrev = new Button("Previous");

        btnNext = new Button("Next");

        btnHome = new Button("_Home");
        
        btnSearch = new Button("_Edit");
        btnSearch.setId("btnEdit");
        
        btnDel = new Button("_Delete");
        btnDel.setId("btnDel");
        
        
        btnPay = new Button("make a payment");
        btnPay.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //can use grabEverything() or check
                double check = Double.parseDouble(txtBalance.getText());
                if(check > 0.00){
               Stage stage2 = new Stage();
//               stage2.initModality(Modality.WINDOW_MODAL);
//               stage2.initOwner(stage2);
               stage2.setScene(payScene());
               stage2.setTitle("Payment for account #" +txtID.getText());
               stage2.show();
                }
                else{
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Cannot make payment");
                alert.setContentText("Account # " + txtID.getText() + " has paid up to date");
                alert.show();
                }
            }
        });
        
        btnAddPic = new Button("Add an Image");
        //try out lamba expression APRIL 25 *****************
        btnAddPic.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               addPicture();
            }
            
        });

        //adding the labels and textfields to the scene
        root = new GridPane();
        root.add(lblID, 0, 0);
        root.add(lblName, 0, 1);
        root.add(lblAddress, 0, 2);
        root.add(lblRegDate, 0, 3);

        root.add(txtID, 1, 0);
        root.add(txtName, 1, 1);
        root.add(txtAddress, 1, 2);
        root.add(txtRegDate, 1, 3);
        root.add(selectedImage, 3, 0);
         root.add(btnAddPic, 3, 1);
        root.add(lblPlan, 2, 2);
        root.add(txtPlan, 3, 2);
        root.add(lblBalance, 2, 3);
        root.add(txtBalance, 3, 3);

        root.setHgap(5);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);
        
        //Buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnSearch, btnPrev, btnHome, btnNext, btnDel, btnPay);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        buttons.setSpacing(10);
        this.setCenter(root);
        this.setBottom(buttons);

        setAlignmentsAndSizes();

        // Reading invidiually
        File file = new File("myfiles/members.txt");
        readFile(file);
        display(memberList);
    }
    //Test Second Constructor 
      public IndividualView(int id, String name, String address, String accType, double balance, String sDate) {
        super();
        selectedImage = new ImageView();
        // creating the labels
        lblID = new Label("Account #: ");
        lblName = new Label("Name: ");
        lblAddress = new Label("Address: ");
        lblPhone = new Label("Phone: ");
        lblGender = new Label("Gender: ");
        lblRegDate = new Label("Registration Date: ");
        lblPlan = new Label("Membership Plan: ");
        lblBalance = new Label("Balance: ");

        //creating the textfields
        txtID = new TextField();
        txtID.setEditable(false);
        txtName = new TextField();
        txtName.setEditable(false);
        txtAddress = new TextField();
        txtAddress.setEditable(false);
        txtRegDate = new TextField();
        txtRegDate.setEditable(false);
        txtPlan = new TextField();
        txtPlan.setEditable(false);
        txtBalance = new TextField();
        txtBalance.setEditable(false);
        

        btnPrev = new Button("Previous");

        btnNext = new Button("Next");

        btnHome = new Button("_Home");
        
        btnSearch = new Button("_Edit");
        btnSearch.setId("btnEdit");
        
        btnDel = new Button("_Delete");
        btnDel.setId("btnDel");
        
        btnAddPic = new Button("Add an Image");
        btnE2 = new Button("Super Edit");
        //try out lamba expression, wwo u can do this in here instead of event, call its own method. neato. Aprul 25
        btnAddPic.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               addPicture();
            }
            
        });

        //adding the labels and textfields to the scene
        root = new GridPane();
        root.add(lblID, 0, 0);
        root.add(lblName, 0, 1);
        root.add(lblAddress, 0, 2);
        root.add(lblRegDate, 0, 3);

        root.add(txtID, 1, 0);
        root.add(txtName, 1, 1);
        root.add(txtAddress, 1, 2);
        root.add(txtRegDate, 1, 3);
        root.add(selectedImage, 3, 0);
         root.add(btnAddPic, 3, 1);
        root.add(lblPlan, 2, 2);
        root.add(txtPlan, 3, 2);
        root.add(lblBalance, 2, 3);
        root.add(txtBalance, 3, 3);

        root.setHgap(5);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);
        
        //Buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnSearch, btnPrev, btnHome, btnNext, btnDel, btnE2);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        buttons.setSpacing(10);
        this.setCenter(root);
        this.setBottom(buttons);

        setAlignmentsAndSizes();

        // Reading invidiually ////NEW FEATURES 
        File file = new File("myfiles/members.txt");
        readFile(file);
        txtID.setText(""+id);
        txtName.setText(name);
        txtAddress.setText(address);
        txtRegDate.setText(sDate);
        txtPlan.setText(accType);
        txtBalance.setText(""+balance);
        checkForImage(); //this is used for the new constructor,,but prob now that i think about can be extrapolated.
        checkForIndex(); //checks the index, so that u start when u open at!
    }
    
    public void addPicture(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("..\\..\\"));
        fileChooser.setTitle("Add an Image to Account");
        ExtensionFilter filterImg = new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(filterImg);
        File selectedFile = fileChooser.showOpenDialog(stage);      
        if (selectedFile != null) {
            File file = new File(selectedFile.getPath());
            try {
                String cid = txtID.getText();
                // retrieve image
                BufferedImage bi = ImageIO.read(file);
                File outputfile = new File("myfiles/"+cid+".jpg");
                ImageIO.write(bi, "jpg", outputfile);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Image Added");
                alert.setContentText("Image Added for Account #" + cid);
                alert.show();
            } catch (IOException e) {
                System.out.println("Invalid Image" + e);
            }
        }
        display(memberList);
    }
     
    //APRIL 25 TRYING TO CREATE A SCENE WITHIN ANOTHER SCENE
    //April 26, this new payscene, constructs a new payment borderpane...
    //so i call form individual view, a button, 
    //that button has a method that calls a new scene,
    //that scene has a method that calls the constructor, has 1 event code for it outside
    //i also did lamda expressions INSIDE TOO
    public Scene payScene() {
        grabEverything();
       
        
          pPane = new Payment(idz, namez, accz, addz, balz, sdatez); //new Constructor...
//        pPane.btnPP.setOnAction(e -> payCode(e));
//        pPane.btnPrev.setOnAction(e -> editCode(e));
        pPane.btnCancel.setOnAction(e -> payCode(e));
//        pPane.btnPP.setOnAction(e -> payCode(e));
//        ePane.btnSearch.setOnAction(e -> editCode(e));

// this opens up the pay Scene cool...
        Scene scene = new Scene(pPane, 400, 600);
        scene.getStylesheets().add("main.css");
        return scene;   
 
    }
    //april26
    //tried making an event in another class that calls another class! what what.
    //this is getting SUPER confusing to link..
    public void payCode(ActionEvent e){
        if(e.getSource()==pPane.btnCancel){
            pPane.getScene().getWindow().hide();
        }
//        else if (e.getSource()==pPane.btnPP){
//            display(memberList);
//        }
    }
    /**
     * this method deletes a record
     */
    public void delRecord() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Delete Record?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {
                File inputFile = new File("myfiles/members.txt");
                File tempFile = new File("myfiles/myTempFile.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                String lineToRemove = txtID.getText() + "|"
                        + txtName.getText() + "|"
                        + txtAddress.getText() + "|"
                        + txtPlan.getText() + "|"
                        + txtBalance.getText() + "|"
                        + txtRegDate.getText() + "|";
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(lineToRemove)) {
                        continue;
                    }
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                if (!inputFile.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }
                boolean successful = tempFile.renameTo(inputFile);
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Could not rename file");
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Record Deleted");
                alert.setContentText("Record Successfully Deleted");
                alert.show();

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (dialogResult == JOptionPane.NO_OPTION) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Delete Canceled");
            alert.setContentText("Record Deletion Canceled");
            alert.show();
        }

    }

    /**
     * This method reads the file
     * @param file
     */
    public void readFile(File file) {
        try {
            Scanner input = new Scanner(file);
            input.useDelimiter("\\|\\s*");
            while (input.hasNext()) {
                int id = input.nextInt();
                String name = input.next();
                String address = input.next();
                String accType = input.next();
                double balance = input.nextDouble();
                String sDate = input.next();
                GymMember member = new GymMember(id, name, address, accType, balance, sDate);
                memberList.add(member);
                arl.add(id);

            }
            input.close();//close file

        } catch (FileNotFoundException ex) {
            txtID.setText("File not found " + ex.getMessage());
        }
    }
    
    private void checkForIndex() {
     for (int i = 0; i < memberList.size(); i++)
         if(memberList.get(i).getId() == Integer.parseInt(txtID.getText())){
             currentIndex = i;
             
         }
     }
    
    /**
     * This method displays the records
     * @param al
     */
    private void display(ArrayList<GymMember> al) {

        int id = al.get(currentIndex).getId();
        String convertId = id + "";
        txtID.setText(convertId);

        //Add Name
        String name = al.get(currentIndex).getName();
        txtName.setText(name);

        //Address
        String address = al.get(currentIndex).getAddress();
        txtAddress.setText(address);

        //accType
        Double balance = al.get(currentIndex).getBalance();
        String cBalance = balance + "";
        txtBalance.setText(cBalance);

        //balance
        String accType = al.get(currentIndex).getAccType();
        txtPlan.setText(accType);

        //get SignUpDate
        String sDate = al.get(currentIndex).getsDate();
        txtRegDate.setText(sDate);

        // image stuff
        File file = new File("myfiles/" + id + ".jpg");
        boolean exists = file.exists();
        if (exists) {
            Image image = new Image("file:myfiles/" + id + ".jpg", 120, 120, false, false);
            selectedImage.setImage(image);
        } else if (!exists) {
            Image image = new Image("file:myfiles/dd.png", 120, 120, false, false);
            selectedImage.setImage(image);
        }
    }
    
    //April 25 2016, Check for image does this make sense?
    public void checkForImage(){
         File file = new File("myfiles/" + txtID.getText() + ".jpg");
        boolean exists = file.exists();
        if (exists) {
            Image image = new Image("file:myfiles/" + txtID.getText() + ".jpg", 120, 120, false, false);
            selectedImage.setImage(image);
        } else if (!exists) {
            Image image = new Image("file:myfiles/dd.png", 120, 120, false, false);
            selectedImage.setImage(image);
        }
    }
    /**
     * This method styles the page
     */
    private void setAlignmentsAndSizes() {
        this.setPadding(new Insets(20, 20, 20, 20));

        btnPrev.setPrefWidth(70);
        btnNext.setPrefWidth(70);
        txtID.setPrefSize(10, 10);
        GridPane.setHalignment(lblID, HPos.RIGHT);
        GridPane.setHalignment(lblName, HPos.RIGHT);
        GridPane.setHalignment(lblAddress, HPos.RIGHT);
        GridPane.setHalignment(lblPhone, HPos.RIGHT);
        GridPane.setHalignment(lblGender, HPos.RIGHT);
        GridPane.setHalignment(lblRegDate, HPos.RIGHT);
        GridPane.setHalignment(lblPlan, HPos.RIGHT);
        GridPane.setHalignment(lblBalance, HPos.RIGHT);
        GridPane.setHalignment(btnPrev, HPos.RIGHT);
        GridPane.setHalignment(btnNext, HPos.RIGHT);
    }

    /**
     * this method will increment the index
     */
    public void incrementIndex() {
        if (currentIndex == (memberList.size() - 1)) {
            currentIndex = 0;
            display(memberList);
        } else {
            currentIndex++; //increment
            display(memberList); //refresh display
        }
    }

    /**
     * this method will decrement the index
     */
    public void decrementIndex() {
        if (currentIndex == 0) {
            currentIndex = (memberList.size() - 1); // Go back to max index.
            display(memberList);
        } else {
            currentIndex -= 1;
            display(memberList);
        }
    }

    /**
     * This method searches the indexes
     */
    public void searchIndex() {

        try {
            int num = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Account #", "Search for Account #", JOptionPane.INFORMATION_MESSAGE));

            if (arl.contains(num)) {
                currentIndex = arl.indexOf(num);
                display(memberList);
            } else {
                JOptionPane.showMessageDialog(null, "Member not found\nProceeding to Record View", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    //this is continued ,, all for super edit view APRIL 25. , setters and getters. 

    public String getSdatez() {
        return sdatez;
    }

    public void grabEverything(){
        idz = Integer.parseInt(txtID.getText());
        
//        System.out.println(idz);
        sdatez = txtRegDate.getText();
        namez = txtName.getText();
         accz = txtPlan.getText();
          addz = txtAddress.getText();    
          balz = Double.parseDouble(txtBalance.getText());
    }
//    public void setSdatez(String sdatez) {
//        sdatez = txtRegDate.getText();
//    }

    public String getNamez() {
        return namez;
    }

//    public void setNamez(String namez) {
//        namez = txtName.getText();
//    }

    public String getAccz() {
        return accz;
    }

//    public void setAccz(String accz) {
//        accz = txtPlan.getText();
//    }

    public String getAddz() {
        return addz;
    }

//    public void setAddz(String addz) {
//        addz = txtAddress.getText();
//    }

    public int getIdz() {
        return idz;
    }

//    public void setIdz(int idz) {
//        idz = Integer.parseInt(txtID.getText());
//    }

    public double getBalz() {
        return balz;
    }

//    public void setBalz(double balz) {
//        balz = Double.parseDouble(txtBalance.getText());
//    }
    
    
                     
                      
                      
                        
                 
                        
}
