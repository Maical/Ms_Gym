/*
    This class shows all the member records
 */
package M_Gym;

import java.io.*;
import java.util.*;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * 
 * @author Michael Liu & Maria Hoang
 */

public class ViewAll extends GridPane { 

    private TextArea txaDisplay;
    private GridPane pane;
    protected TableView table; //can i make this protected to access it?
    protected Button btnAdd, btnDelete, btnHome, btnSearch;
    private int RECORD_SIZE = 202;
    private ArrayList<GymMember> memberList = new ArrayList<GymMember>();
    
    //Created these private variables to store the table double clicks to store and pass back to controller.
    private String sdatez, namez, accz, addz;
    private int idz;
    private double balz;
//    protected TableRow<GymMember> row;

    public ViewAll() {
        super();

        //read file 
        File file = new File("myfiles/members.txt");
        readFile(file);

        //tableview features
        table = new TableView();
        table.setEditable(true);
        //
//        table.setRowFactory(tv->{return null;
//});
//        TableRow<GymMember> row = new TableRow<>();
        //working code;
        table.setRowFactory( tv -> {
        TableRow<GymMember> row = new TableRow<>();
         row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                GymMember rowData = row.getItem();
                System.out.println(rowData.getId());
                accz =rowData.getAccType();
                addz =rowData.getAddress();
                balz = rowData.getBalance();
                namez = rowData.getName();
                idz = rowData.getId();
                sdatez = rowData.getsDate();
//                   IndividualView iPane = new IndividualView();    
//                System.out.println(rowData);
//                stage.setScene(IndividualView());
            }
        });
    return row ;
        });
        //TableColumn name  
        TableColumn idCol = new TableColumn("Account #");
        TableColumn nameCol = new TableColumn("Name");
        TableColumn addressCol = new TableColumn("Address");
        TableColumn accountCol = new TableColumn("Account");
        TableColumn balanceCol = new TableColumn("Balance");

        //Add to Column 
        idCol.setCellValueFactory(
                new PropertyValueFactory<GymMember, String>("id") );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<GymMember, String>("name"));
        addressCol.setCellValueFactory(
                new PropertyValueFactory<GymMember, String>("address"));

        accountCol.setCellValueFactory(
                new PropertyValueFactory<GymMember, String>("accType"));
        balanceCol.setCellValueFactory(
                new PropertyValueFactory<GymMember, String>("balance"));


        //Convert ArrayList to ObservableList 
        ObservableList<GymMember> tryList = FXCollections.observableArrayList(memberList);
        table.setItems(tryList);
        table.getColumns().addAll(idCol, nameCol, addressCol, accountCol, balanceCol);//signUpCol);//PaymentCol);

        // create labels and buttons on this page
        Label showAll = new Label("Member Records");
        showAll.setId("records");
        btnAdd = new Button("Add Member");
        btnDelete = new Button("Edit/Delete Member");

        Image logout = new Image("file:myfiles/homee.png");
        btnHome = new Button();
        btnHome.setGraphic(new ImageView(logout));

        btnSearch = new Button("Search");

        // add the buttons to the page with some styling
        VBox btnCol = new VBox();
        btnCol.getChildren().addAll(btnSearch, btnAdd, btnDelete, btnHome);
        btnCol.setSpacing(10);
        btnCol.setPadding(new Insets(20, 20, 20, 20));

        this.add(showAll, 2, 2);
        this.add(btnCol, 3, 5);
        table.setPrefSize(450, 400);
        this.add(table, 0, 3, 3, 3);
        this.setPadding(new Insets(20, 20, 20, 20));
        

    }
    
    /**
     * 
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

            }
            //close file
            input.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found " + ex.getMessage());
        }
    }

    public String getSdatez() {
        return sdatez;
    }

    public String getNamez() {
        return namez;
    }

    public String getAccz() {
        return accz;
    }

    public String getAddz() {
        return addz;
    }

    public int getIdz() {
        return idz;
    }

    public double getBalz() {
        return balz;
    }
    

}
