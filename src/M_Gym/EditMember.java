/**
 * This class will display each record individually and allow editing
 */
package M_Gym;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.*;
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

public class EditMember extends BorderPane {

    private GridPane root;
    protected TextField txtName, txtAddress, txtPhone, txtGender, txtRegDate, txtPlan, txtID, txtBalance;
    private Label lblName, lblAddress, lblPhone, lblGender, lblRegDate, lblPlan, lblID, lblBalance;
    protected Button btnPrev, btnNext, btnCancel, btnSave;// btnExit;
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

    public EditMember() {
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
//        txtName.setEditable(false);
        txtAddress = new TextField();
        txtAddress.setEditable(false);
//        txtAddress.setEditable(false);
        txtRegDate = new TextField();
        txtRegDate.setEditable(false);
        txtPlan = new TextField();
        
//        txtPlan.setEditable(false);
        txtBalance = new TextField();
//        txtBalance.setEditable(false);

        btnPrev = new Button("Previous");

        btnNext = new Button("Next");

        btnCancel = new Button("_Cancel");
        btnCancel.setId("btnCancel");

        btnSave = new Button("_Save");
        btnSave.setId("btnSave");

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
        root.add(lblPlan, 2, 2);
        root.add(txtPlan, 3, 2);
        root.add(lblBalance, 2, 3);
        root.add(txtBalance, 3, 3);

        root.setHgap(5);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);

        //Buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnCancel, btnPrev, btnNext, btnSave);
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
        getTempString();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Edit Guide");
        alert.setContentText("To save the record:\nEdit the text and then press Save.\nTo cancel: press cancel to go back to record view.");
        alert.show();
    }
    ///DEEEELTEEEEEEEEEEEEEEEEE APRIL 25/16 TRYING TO DO A DYNAMIC EDIT PLUS NEW METHODS **************************************************************************
    public EditMember(int id, String name, String address, String accType, double balance, String sDate) {
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
//        txtName.setEditable(false);
        txtAddress = new TextField();
//        txtAddress.setEditable(false);
        txtRegDate = new TextField();
        txtRegDate.setEditable(false);
        txtPlan = new TextField();
//        txtPlan.setEditable(false);
        txtBalance = new TextField();
//        txtBalance.setEditable(false);

        btnPrev = new Button("Previous");

        btnNext = new Button("Next");

        btnCancel2 = new Button("_Cancel");
        btnCancel2.setId("btnCancel");

        btnSave2 = new Button("_Save");
        btnSave2.setId("btnSave");

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
        root.add(lblPlan, 2, 2);
        root.add(txtPlan, 3, 2);
        root.add(lblBalance, 2, 3);
        root.add(txtBalance, 3, 3);

        root.setHgap(5);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);

        //Buttons
        HBox buttons = new HBox();
        buttons.getChildren().addAll(btnCancel2, btnSave2);   //REMOVED NEXT 
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        buttons.setSpacing(10);
        this.setCenter(root);
        this.setBottom(buttons);

        setAlignmentsAndSizes();

        // Reading invidiually
        File file = new File("myfiles/members.txt");
        readFile(file);
//        display(memberList); take this out this fuckeds it up and starts at 0
//USE THESE INSTEAD, GRABS THE PASSED PARAMTERS AND SETS ACCORIDNGLY WOO!
        txtID.setText(""+id);
        txtName.setText(name);
        txtAddress.setText(address);
        txtRegDate.setText(sDate);
        txtPlan.setText(accType);
        txtBalance.setText(""+balance);
        getTempString(); //DEFINITELY NEED THIS LOL
        checkForImage(); //this is used for the new constructor,,but prob now that i think about can be extrapolated.
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(null);
//        alert.setTitle("Edit Guide");
//        alert.setContentText("To save the record:\nEdit the text and then press Save.\nTo cancel: press cancel to go back to record view.");
//        alert.show();
    }
    
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
//DEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEETE  APRIL 25/16 TRYING TO DO A DYNAMIC EDIT PLUS NEW METHODS *************************************
    /**
     * This method edits the record
     */
    public void editRecord() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Edit Record?", "Warning", JOptionPane.YES_NO_OPTION);
        String checkString = txtID.getText() + "|"
                + txtName.getText() + "|"
                + txtAddress.getText() + "|"
                + txtPlan.getText() + "|"
                + txtBalance.getText() + "|"
                + txtRegDate.getText() + "|";
//        System.out.println(checkString);
//        System.out.println(tempString);
        if (tempString.equals(checkString)) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("No Change");
            alert.setContentText("Same Record Entered");
            alert.show();
        } 
        else {
            if (dialogResult == JOptionPane.YES_OPTION) {
                saveRecord();

                if (valid == true) {
                    delRecord();
                }
            }
        }

    }

    /**
     * This method retrieves the current record
     */
    public void getTempString() {
        tempString = txtID.getText() + "|"
                + txtName.getText() + "|"
                + txtAddress.getText() + "|"
                + txtPlan.getText() + "|"
                + txtBalance.getText() + "|"
                + txtRegDate.getText() + "|";
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
//        System.out.println("Does it exist " + exists);
        if (exists) {
            Image image = new Image("file:myfiles/" + id + ".jpg", 120, 120, false, false);
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
            getTempString();
        } else {
            currentIndex++; //increment
            display(memberList); //refresh display
            getTempString();
//            System.out.println(tempString);
        }
    }

    /**
     * this method will decrement the index
     */
    public void decrementIndex() {
        if (currentIndex == 0) {
            currentIndex = (memberList.size() - 1); // Go back to max index.
            display(memberList);
            getTempString();
        } else {
            currentIndex -= 1;
            display(memberList);
            getTempString();
        }
    }

    /**
     * This method searches the indexes
     */
    public void searchIndex() {

        try {
            int num = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter member id ", "Search for Member", JOptionPane.INFORMATION_MESSAGE));
            if (arl.contains(num)) {
                currentIndex = arl.indexOf(num);
                display(memberList);
                getTempString();
            } else {
                JOptionPane.showMessageDialog(null, "Member not found", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Record Edited");
            alert.setContentText("Record Successfully Updated");
            alert.show();

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

            String name = txtName.getText();
            String address = txtAddress.getText();
            String regDate = txtRegDate.getText();
            double balance = Double.parseDouble(txtBalance.getText());
            String plan = txtPlan.getText();
            if (name.equals("Enter Name") || name.equals(" ")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Name");
                alert.setContentText("Enter Valid Name");
                alert.show();
            } else if (address.equals("Enter Address") || address.equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Address");
                alert.setContentText("Enter Valid Address");
                alert.show();
            } else if (!(plan.equals("Regular") || plan.equals("Premium"))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Plan");
                alert.setContentText("Enter Premium or Regular");
                alert.show();
            } else if (balance < 0) {
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
