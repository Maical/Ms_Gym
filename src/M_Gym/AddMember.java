/**
 * This class adds a member record
 */
package M_Gym;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AddMember extends GridPane {

    BorderPane root;
    protected TextField txtName, txtAddress, txtRegDate, txtPlan, txtID, txtBalance;
    private Label lblName, lblAddress, lblRegDate, lblPlan, lblID, lblBalance;
    protected Button btnSave, btnCancel;// btnExit;
    private Stage stage;
    private ArrayList<GymMember> memberList = new ArrayList<GymMember>();
    private ArrayList<Integer> arl = new ArrayList<Integer>();
    private int currentIndex = 0;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Date today = new Date();

    public AddMember() {
        super();

        lblID = new Label("Account #: ");
        lblName = new Label("Name: ");
        lblAddress = new Label("Address: ");

        lblRegDate = new Label("Registration Date: ");
        lblPlan = new Label("Membership Plan: ");
        lblBalance = new Label("Balance: ");

        txtID = new TextField();
        txtID.setEditable(false);
        txtName = new TextField();
        txtAddress = new TextField();

        txtRegDate = new TextField();
        txtRegDate.setEditable(false);
        txtPlan = new TextField();
        txtBalance = new TextField();
        txtBalance.setEditable(false);

        btnSave = new Button("_Save");

        btnCancel = new Button("_Cancel");

        this.add(lblID, 0, 1);
        this.add(lblName, 2, 1);
        this.add(lblAddress, 0, 2);
        this.add(lblRegDate, 0, 3);

        this.add(txtID, 1, 1);
        this.add(txtName, 3, 1);
        this.add(txtAddress, 1, 2);
        this.add(txtRegDate, 1, 3);

        this.add(lblPlan, 2, 2);
        this.add(txtPlan, 3, 2);
        this.add(lblBalance, 2, 3);
        this.add(txtBalance, 3, 3);

        //Buttons
        this.add(btnSave, 1, 4);
        this.add(btnCancel, 2, 4);
        setAlignmentsAndSizes();
        // Reading invidiually...
        File file = new File("myfiles/members.txt");
        readFile(file);
        display(memberList);

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
                arl.add(id);
                String name = input.next();
                String address = input.next();
                String accType = input.next();
                double balance = input.nextDouble();
                String sDate = input.next();
                GymMember member = new GymMember(id, name, address, accType, balance, sDate);
                memberList.add(member);

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

        int arraycheck = al.size();
        int id = (arraycheck + 1);
        //checkwith arraylist to see if id exists
        if (arl.contains(id)) {
            id++;
            while (arl.contains(id)) {
                id++;
            }
        }

        String convertId = id + "";
        txtID.setText(convertId);
        //Add Name
        String name = "Enter Name";
        txtName.setText(name);
        //Address
        String address = "Enter Address";
        txtAddress.setText(address);
        //accType
        Double balance = 0.00;
        String cBalance = balance + "";
        txtBalance.setText(cBalance);
        //balance
        String accType = "Regular";
        txtPlan.setText(accType);
        //getSignUpDate as todays date

        String sDate = dateFormat.format(today) + "";
        txtRegDate.setText(sDate);

    }

    /**
     * This method styles the page
     */
    private void setAlignmentsAndSizes() {
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setHgap(5);
        this.setVgap(20);
        this.setAlignment(Pos.CENTER);
        btnSave.setPrefWidth(70);
        btnCancel.setPrefWidth(70);

        txtID.setPrefSize(10, 10);
        GridPane.setHalignment(lblID, HPos.RIGHT);
        GridPane.setHalignment(lblName, HPos.RIGHT);
        GridPane.setHalignment(lblAddress, HPos.RIGHT);

        GridPane.setHalignment(lblRegDate, HPos.RIGHT);
        GridPane.setHalignment(lblPlan, HPos.RIGHT);
        GridPane.setHalignment(lblBalance, HPos.RIGHT);

        GridPane.setHalignment(btnSave, HPos.RIGHT);
        GridPane.setHalignment(btnCancel, HPos.RIGHT);

    }

    /**
     * this method saves the record
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Record Added");
                alert.setContentText("Record Successfully added");
                alert.show();
                //            readFile(file);
                ///check if it exists for next record.
                //INCEREMENT RECORD SO THAT NEXT RECORD DOES NOT REPEAT
                id++;
                if (arl.contains(id)) {
                    id++;
                    while (arl.contains(id)) {
                        id++;
                    }
                }
                txtID.setText(id + "");
                txtName.setText("Enter Name");
                txtAddress.setText("Enter Address");
                txtPlan.setText("Regular");
                //           
                //            display(memberList);
            }
        } catch (IOException ex) {
            //            txtName.setText("File not found " + ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("File not found " + ex.getMessage());
            alert.show();
        } catch (NumberFormatException ex) {
            //            txaDisplay.setText("Wrong number " + ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Invalid Entry " + ex.getMessage());
            alert.show();
        }
    }

}
