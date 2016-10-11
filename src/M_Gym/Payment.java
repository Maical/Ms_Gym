/**
 * This class will display each record individually and allow editing
 */
package M_Gym;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * 
 * @author Michael Liu & Maria Hoang
 */

public class Payment extends BorderPane {

    private GridPane root;
    protected TextField txtName, txtAddress, txtPhone, txtGender, txtRegDate, txtPlan, txtID, txtBalance;
    private Label lblName, lblAddress, lblPhone, lblGender, lblRegDate, lblPlan, lblID, lblBalance;
    protected Button btnPrev, btnNext, btnCancel, btnPP;// btnExit;
    private ArrayList<GymMember> memberList = new ArrayList<GymMember>();
    private ArrayList<Integer> arl = new ArrayList<Integer>();
    private int currentIndex = 0;
    protected ImageView selectedImage;
    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem openItem, saveItem, exitItem;
    private Stage stage;
    private String tempString;
    private boolean valid = false;
    protected Button btnSave2, btnCancel2; //new buttons so the event codes bring u back to the previous scene.
    //April 25 still trying to do the same thing i did for indiv view
//    protected TextField txtPayAmt;
    protected ComboBox paymentOption;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Date today = new Date();
    private int id; 
    String name, address, accType ,sDate;
    double balance;
    protected TextField txtCCN, txtPayAmount;
    private Label lblCCN, lblPayAmount;
    private String sdatez, namez, accz, addz;
    private int idz;
    private double balz;
    private boolean back = false;
//    private double payAmount;
    
    //String sDate = dateFormat.format(today) + "";
//        txtRegDate.setText(sDate);

    public Payment(int idz, String namez, String accz, String addz, double balz, String sdatez) {
//        int id, String name, String address, String accType, double balance, String sDate
        super(); 
        this.sdatez = sdatez;
        this.idz = idz;
        this.namez = namez;
        this.accz = accz;
        this.addz = addz;
        this.balz = balz;
        selectedImage = new ImageView();
        paymentOption = new ComboBox();
        paymentOption.getItems().addAll("MasterCard", "Visa");
        paymentOption.setPromptText("Payment Type");
        lblCCN = new Label("Credit Card Number");
        txtCCN = new TextField();
        txtCCN.setPromptText("Enter credit card info");
        lblPayAmount = new Label("Charge Amount");
        txtPayAmount = new TextField();
        txtPayAmount.setPromptText("0.00");
        
        
        
        // creating the labels
        lblID = new Label("Account #: ");
        lblName = new Label("Name: ");
        lblAddress = new Label("Credit Card Type: ");
        lblPhone = new Label("Phone: ");
        lblGender = new Label("Gender: ");
        lblRegDate = new Label("Payment Date: ");
        lblPlan = new Label("Membership Plan: ");
        lblBalance = new Label("Balance: ");

        //creating the textfields
        txtID = new TextField();
        txtID.setEditable(false);
        txtName = new TextField();
        txtName.setEditable(false);
        txtAddress = new TextField();
//        txtAddress.setEditable(false);
        txtRegDate = new TextField();
        txtRegDate.setEditable(false);
        txtPlan = new TextField();
        txtPlan.setEditable(false);
        txtBalance = new TextField();
//        txtBalance.setEditable(false);

        btnPrev = new Button("Previous");

        btnNext = new Button("Next");

        btnCancel = new Button("_Cancel");
        btnCancel.setId("btnCancel");

        btnPP = new Button("_Process Payment");
        btnPP.setId("btnSave");
        //APRIL 26, I COULDNT FIGURE OUT HOW TO CLOSE THE WINODW THROUGH HERE AFTER IT SAVES, 
        //SO I WILL JUST PUT INTO A EVENT HANDLER IN INDIVIUDAL VIEW TO CLOSE TEH WINDOW AFTER IT SAVES
        btnPP.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                calculatePayment();
//                System.out.println(paymentOption.getValue());
                if(back==true){ //april 26 without making a boolean true for back, it will go back anwyays without me  finishing the loop
                Node  source = (Node)  event.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
                }
                
            }
        });

        //adding the labels and textfields to the scene
        root = new GridPane();
        root.add(lblID, 0, 0);
        root.add(lblName, 0, 1);
       
        root.add(lblRegDate, 0, 3);

        root.add(txtID, 1, 0);
        root.add(txtName, 1, 1);
        
        root.add(txtRegDate, 1, 3);
//        root.add(selectedImage, 3, 0);
        root.add(lblAddress, 0, 4); //switch
        root.add(paymentOption, 1, 4);
        root.add(lblPlan, 0, 2); //switch
        root.add(txtPlan, 1, 2); //switch
        root.add(lblCCN, 0,5);
        root.add(txtCCN, 1,5);

//                txtCCN = new TextField();
//        txtCCN.setPromptText("Enter credit card info");
//        lblPayAmount = new Label("Charge Amount");
//        txtPayAmount = new TextField();
//        txtPayAmount.setPromptText("0.00");
        root.add(lblBalance, 0, 6);
        root.add(txtBalance, 1, 6);
        root.add(lblPayAmount,0,7);
        root.add(txtPayAmount,1,7);

        root.setHgap(5);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);

        //Buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnCancel, btnPP);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        buttons.setSpacing(10);
        this.setCenter(root);
        this.setBottom(buttons);

        setAlignmentsAndSizes();

        // Reading invidiually
//        File file = new File("myfiles/members.txt");
//        readFile(file);
//        display(memberList);
        //getTempString 2.0 uses constructor passed variables.. instead of grabbing the displayed text
        getTempString();
        showFromIndView();
       
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(null);
//        alert.setTitle("Edit Guide");
//        alert.setContentText("To save the record:\nEdit the text and then press Save.\nTo cancel: press cancel to go back to record view.");
//        alert.show();
    }
    
    public void showFromIndView(){
        System.out.println(tempString);
        txtID.setText(idz+"");
        txtName.setText(namez);
        txtPlan.setText(accz);
        String sDate = dateFormat.format(today) + "";
        txtRegDate.setText(sDate);
        txtBalance.setText(balz+"");
    }
    
    public void calculatePayment(){
        try{
       if(paymentOption.getValue()==null){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Invalid Credit Card");
        alert.setContentText("Select Credit Card");
        alert.show();
       }
       else if ((txtCCN.getText()==null)||(txtCCN.getText().length()<12)){  //this causes issues.. need try catch incase they enter letters...(Integer.parseInt(txtCCN.getText())<1000000000)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Invalid Credit Card Number");
        alert.setContentText("Enter 12 digit credit card");
        alert.show();
       }
       else if ((txtPayAmount.getText()==null) || (Double.parseDouble(txtPayAmount.getText())<.01)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText(null);
            alert.setTitle("Invalid Amount");
            alert.setContentText("Enter a charge amount greater than 1 cent");
        alert.show();
       }
       else{
           System.out.println("Got this far");
           moreCalc();
           
           
       }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    public void moreCalc(){
        try{
            double charge = Double.parseDouble(txtPayAmount.getText());
            if (charge> balz){
                balz = 0.00;
                finalCalc();
            }
            else{
                balz = balz - charge;
                System.out.println(balz);
                finalCalc();
            }
           
        }catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setHeaderText(null);
            alert.setTitle("Invalid Charge Amount");
            alert.setContentText("Enter a charge amount greater than 1 cent");
        }
    }
    
    public void finalCalc(){
        editRecord();
        int dialogResult = JOptionPane.showConfirmDialog(null, "Payment Processed \n Generate Receipt?", "Generate Receipt", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION){
            generateReceipt();
        }
        back = true;
        //else {close..
//
    }

    public void generateReceipt(){
        try {
            int id = Integer.parseInt(txtID.getText());
            String sDate = dateFormat.format(today); 
            String name = namez;
            String address = addz;
            String regDate = sdatez;
            double balance = balz;
            String plan = accz;
            String forReceipt = "receipt for account " + id + " " +namez;
             if (balance < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Balance");
                alert.setContentText("Enter a valid balance");
                alert.show();
            } else {
                File file = new File("myfiles/"+forReceipt+".txt"); //_for_account_"+id+"_"+dateFormat.format(today)+"
                PrintWriter write = new PrintWriter(new FileWriter(file, true));
                write.print("\n"+ "==========Account Information===============");
                write.print("\n" + "Account Number: " + id);  
                write.print("\n"+ "Name: " +name );
                write.print("\n"+ "Address: " + address);
                write.print("\n" + "Registration date: " + regDate);
                write.print("\n"+ "Plan: " + plan);
                
                write.print("\n"+ "==========Payment Information===============");
                write.print("\n"+ "Transaction Date: " + sDate);
                write.print("\n"+ "Payment Type: " + paymentOption.getValue());// balance + "|");    
                write.print("\n"+"Credit Card Number: " + txtCCN.getText());
                write.print("\n"+"Balance Due: $" + txtBalance.getText());
                write.print("\n"+"Amount Charged to Credit Card: $" + txtPayAmount.getText());
                write.print("\n"+"Remaining Balance: $" + balz);
                write.print("\r");
                write.close();
                 System.out.println("Receipt Saved");  //APril 26 comment this out if needed
//                valid = true;
            }

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("File not found " + ex.getMessage());
            alert.show();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Invalid Entry ");
            alert.show();
        }
    
    }

    /**
     * This method edits the record
     */
    
    public void editRecord() {
//        int dialogResult = JOptionPane.showConfirmDialog(null, "Edit Record?", "Warning", JOptionPane.YES_NO_OPTION);
//        String checkString = txtID.getText() + "|"
//                + txtName.getText() + "|"
//                + txtAddress.getText() + "|"
//                + txtPlan.getText() + "|"
//                + txtBalance.getText() + "|"
//                + txtRegDate.getText() + "|";
////        System.out.println(checkString);
////        System.out.println(tempString);
//        if (tempString.equals(checkString)) {
//             Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText(null);
//            alert.setTitle("No Change");
//            alert.setContentText("Same Record Entered");
//            alert.show();
//        } 
//        else {
//            if (dialogResult == JOptionPane.YES_OPTION) {
                saveRecord();

//                if (valid == true) {
                    delRecord();
//                }
//            }
//        }

    }

    /**
     * This method retrieves the current record
     */
    //THIS TEMP STRING WORKS DIFFERENTLY, IT TAKES THE PRESET VALUES,
    //OTHERWISE IT WONT WORK BECXAUSE WE DOTN HAVE THESE TEXTFIELDS DISPLAYED TO GRAB...
    public void getTempString() {
        tempString = idz + "|"
                + namez + "|"
                + addz + "|"
                + accz + "|"
                + balz + "|"
                + sdatez + "|";
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

    /**
     * This method displays the records
     * @param al
     */
//    private void display(ArrayList<GymMember> al) {
//
//        int id = al.get(currentIndex).getId();
//        String convertId = id + "";
//        txtID.setText(convertId);
//
//        //Add Name
//        String name = al.get(currentIndex).getName();
//        txtName.setText(name);
//
//        //Address
//        String address = al.get(currentIndex).getAddress();
//        txtAddress.setText(address);
//
//        //accType
//        Double balance = al.get(currentIndex).getBalance();
//        String cBalance = balance + "";
//        txtBalance.setText(cBalance);
//
//        //balance
//        String accType = al.get(currentIndex).getAccType();
//        txtPlan.setText(accType);
//
//        //get SignUpDate
//        String sDate = al.get(currentIndex).getsDate();
//        txtRegDate.setText(sDate);
//
//        // image stuff
//        File file = new File("myfiles/" + id + ".jpg");
//        boolean exists = file.exists();
////        System.out.println("Does it exist " + exists);
//        if (exists) {
//            Image image = new Image("file:myfiles/" + id + ".jpg", 120, 120, false, false);
//            selectedImage.setImage(image);
//        } else if (!exists) {
//            Image image = new Image("file:myfiles/dd.png", 120, 120, false, false);
//            selectedImage.setImage(image);
//        }
//    }

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
//    public void incrementIndex() {
//        if (currentIndex == (memberList.size() - 1)) {
//            currentIndex = 0;
//            display(memberList);
//            getTempString();
//        } else {
//            currentIndex++; //increment
//            display(memberList); //refresh display
//            getTempString();
////            System.out.println(tempString);
//        }
//    }
//
//    /**
//     * this method will decrement the index
//     */
//    public void decrementIndex() {
//        if (currentIndex == 0) {
//            currentIndex = (memberList.size() - 1); // Go back to max index.
//            display(memberList);
//            getTempString();
//        } else {
//            currentIndex -= 1;
//            display(memberList);
//            getTempString();
//        }
//    }
//
//    /**
//     * This method searches the indexes
//     */
//    public void searchIndex() {
//
//        try {
//            int num = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter member id ", "Search for Member", JOptionPane.INFORMATION_MESSAGE));
//            if (arl.contains(num)) {
//                currentIndex = arl.indexOf(num);
//                display(memberList);
//                getTempString();
//            } else {
//                JOptionPane.showMessageDialog(null, "Member not found", "Not Found", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
//
//        }
//    }

    /**
     * This method deletes the record
     */
    public void delRecord() {
        try {
            File inputFile = new File("myfiles/members.txt");
            File tempFile = new File("myfiles/myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = tempString;
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
                System.out.println("Error");
                return;
            }
            boolean successful = tempFile.renameTo(inputFile);
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not edit file");
            }
        //            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //            alert.setHeaderText(null);
        //            alert.setTitle("Record Edited");
        //            alert.setContentText("Record Successfully Updated");
        //            alert.show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method saves the record
     */
    public void saveRecord() {
       
        try {
            int id = Integer.parseInt(txtID.getText());

            String name = namez;
            String address = addz;
            String regDate = sdatez;
            double balance = balz;
            String plan = accz;
             if (balance < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Balance");
                alert.setContentText("Enter a valid balance");
                alert.show();
            } else {
                File file = new File("myfiles/members.txt");
                PrintWriter write = new PrintWriter(new FileWriter(file, true));
                write.print("\n" + id);
                write.print("|" + name + "|");
                write.print(address + "|");
                write.print(plan + "|");
                write.print(balance + "|");
                write.print(regDate + "|");
                write.print("\r");
                write.close();
                valid = true;
            }

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("File not found " + ex.getMessage());
            alert.show();
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Invalid Entry ");
            alert.show();
        }
    }

}
