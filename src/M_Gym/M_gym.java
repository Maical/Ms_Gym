/**
 * This is the main class that controls most of the action events
 */
package M_Gym;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

/**
 *
 * @author Michael Liu & Maria Hoang
 */
public class M_gym extends Application {

    private Login logPane;
    private Navigation navPane;
    private ViewAll vPane;
    private AddMember aPane;
    private EditMember ePane;
    private IndividualView iPane;
    private Stage stage;
    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem exitItem;
    //Credentials can be changed here
    private String user = "admin"; 
    private String pw = "root";

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Scene scene = firstScene();
        primaryStage.getIcons().add(new Image("file:myfiles/d.jpg"));
        primaryStage.setTitle("M's Gym");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("loginstyle.css");
        primaryStage.show();

    }

    /**
     *
     * @return the login page
     */
    public Scene firstScene() {
        logPane = new Login();
        logPane.btnFirst.setOnAction(e -> eventCode(e));
        Scene scene = new Scene(logPane, 300, 200);
        return scene;
    }

    /**
     *
     * @return the individual member page
     */
    public Scene individualScene() {
        iPane = new IndividualView();
        iPane.btnNext.setOnAction(e -> singleCode(e));
        iPane.btnPrev.setOnAction(e -> singleCode(e));
        iPane.btnHome.setOnAction(e -> singleCode(e));
        iPane.btnDel.setOnAction(e -> singleCode(e));
        iPane.btnSearch.setOnAction(e -> singleCode(e));
//        iPane.btnAddPic.setOnAction(e -> singleCode(e));
        Scene scene = new Scene(iPane, 600, 400);
        scene.getStylesheets().add("main.css");
        return scene;
    }
    
    
    ///April 25, trying to do dynamic edits
    public Scene individualScene2(int i, String a, String b, String c, double d, String z) {
        iPane = new IndividualView(i,a,b,c,d,z);
        iPane.btnNext.setOnAction (e -> singleCode(e));
        iPane.btnPrev.setOnAction(e -> singleCode(e));
        iPane.btnHome.setOnAction(e -> singleCode(e));
        iPane.btnDel.setOnAction(e -> singleCode(e));
        iPane.btnSearch.setOnAction(e -> singleCode(e));
        iPane.btnE2.setOnAction(e -> singleCodeE(e));
//        iPane.btnAddPic.setOnAction(e -> singleCode(e));
        Scene scene = new Scene(iPane, 600, 400);
        scene.getStylesheets().add("main.css");
        return scene;
    }

    /**
     *
     * @param e
     */
    public void singleCode(ActionEvent e) {
        if (e.getSource().equals(iPane.btnNext)) {
            iPane.incrementIndex();
        } else if (e.getSource().equals(iPane.btnPrev)) {
            iPane.decrementIndex();
        } else if (e.getSource().equals(iPane.btnHome)) {
            stage.setScene(navigationScene());
        } else if (e.getSource().equals(iPane.btnDel)) {
            iPane.delRecord();
            stage.setScene(ViewPane());
        } else if (e.getSource().equals(iPane.btnSearch)) {
            stage.setScene(editScene());
        }
        
        //NEWWWWWWWWWWWWWWWWWWWWWWW FEAATURES
//        } else if (e.getSource().equals(iPane.btnAddPic)){
//            iPane.addPicture();
//        }

    }
    public void singleCodeE(ActionEvent e){
        if (e.getSource().equals(iPane.btnE2)){       
            iPane.grabEverything();
             int i = iPane.getIdz();
            String nm = iPane.getNamez();
            String ad = iPane.getAddz();       
            String ac = iPane.getAccz();
            double bl = iPane.getBalz();
            String sd = iPane.getSdatez();
            stage.setScene(editScene(i,nm, ad, ac, bl, sd));
        }
    }

    /**
     *
     * @return the individual member page
     */
    public Scene editScene() {
        ePane = new EditMember();
        ePane.btnNext.setOnAction(e -> editCode(e));
        ePane.btnPrev.setOnAction(e -> editCode(e));
        ePane.btnCancel.setOnAction(e -> editCode(e));
        ePane.btnSave.setOnAction(e -> editCode(e));
//        ePane.btnSearch.setOnAction(e -> editCode(e));
        Scene scene = new Scene(ePane, 600, 400);
        scene.getStylesheets().add("main.css");
        return scene;
    }
    //MADE A SECOND ONE TO CONTINUE THIS DEMO
    public Scene editScene(int i, String a, String b, String c, double d, String z) {
         ePane = new EditMember(i, a, b, c, d, z);
        ePane.btnCancel2.setOnAction(e -> editCode2(e));
        ePane.btnSave2.setOnAction(e -> editCode2(e));
//        ePane.btnSearch.setOnAction(e -> editCode(e));
        Scene scene = new Scene(ePane, 600, 400);
        scene.getStylesheets().add("main.css");
        return scene;
    }
    public void editCode2(ActionEvent e) {
        if (e.getSource().equals(ePane.btnCancel2)) {
            stage.setScene(ViewPane());
        }
        else if (e.getSource().equals(ePane.btnSave2)) {
            ePane.editRecord();
            stage.setScene(ViewPane());
        }
    }
    /**
     *
     * @param e
     */
    public void editCode(ActionEvent e) {
        if (e.getSource().equals(ePane.btnNext)) {
            ePane.incrementIndex();
        } else if (e.getSource().equals(ePane.btnPrev)) {
            ePane.decrementIndex();
        } else if (e.getSource().equals(ePane.btnCancel)) {
            stage.setScene(individualScene());
        } else if (e.getSource().equals(ePane.btnSave)) {
            ePane.editRecord();
            stage.setScene(ViewPane());
        }
        //THESE NEW METODS I MADE FOR THE SECOND VIEW.

    }

    /**
     *
     * @return the navigation page with menu
     */
    public Scene navigationScene() {
        BorderPane root = new BorderPane();

        //MENU BARS
        menuBar = new MenuBar();
        fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);

        exitItem = new MenuItem("_Exit");
        exitItem.setMnemonicParsing(true);
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCombination.SHORTCUT_DOWN));
        exitItem.setOnAction(e -> eventCode(e));
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);

        //END THE MENU STUFF
        //Shifted this to be before root.setstuff...
        navPane = new Navigation();
        navPane.btnA.setOnAction(e -> eventCode(e));
        navPane.btnB.setOnAction(e -> eventCode(e));
        navPane.btnC.setOnAction(e -> eventCode(e));

        root.setTop(menuBar);
        root.setCenter(navPane);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("nav.css");
        return scene;
    }

    /**
     *
     * @return all the records
     */

    public Scene ViewPane() {
        vPane = new ViewAll();
        vPane.btnHome.setOnAction(e -> eventCode(e));
        vPane.btnAdd.setOnAction(e -> viewCodes(e));
        vPane.btnDelete.setOnAction(e -> viewCodes(e));
        vPane.btnSearch.setOnAction(e -> viewCodes(e));
//        vPane.row.setOnMouseClicked(e-> viewCodesMouse(e));
        ///test
        vPane.table.setOnMouseClicked(e->viewCodesMouse(e));
        //
        Scene scene = new Scene(vPane, 600, 400);
        scene.getStylesheets().add("main.css");
        return scene;
    }

    /**
     *
     * @param e
     */
    ///April 25/16, this code is for mouse events, creates a new contructor to pass in the parameters that are SET by scene 1. 
    public void viewCodesMouse(MouseEvent e){
        if(e.getClickCount() == 2){ 
        //DEMO 
//        Stage stage2 = new Stage();
        int i = vPane.getIdz();
        String nm = vPane.getNamez();
        String ad = vPane.getAddz();       
        String ac = vPane.getAccz();
        double bl = vPane.getBalz();
        String sd = vPane.getSdatez();
        stage.setScene(individualScene2(i,nm, ad, ac, bl, sd));
//        stage2.show();
        }
   
    
    }
        
    public void viewCodes(ActionEvent e) {
        if (e.getSource().equals(vPane.btnAdd)) {
            stage.setScene(AddPane());
        } else if (e.getSource().equals(vPane.btnDelete)) {
            stage.setScene(individualScene());
        } else if (e.getSource().equals(vPane.btnSearch)) {
            stage.setScene(individualScene());
            iPane.searchIndex();
        }
    }

    /**
     *
     * @return the member that is added
     */
    public Scene AddPane() {
        aPane = new AddMember();
        aPane.btnSave.setOnAction(e -> addCode(e));
        aPane.btnCancel.setOnAction(e -> addCode(e));
        Scene scene = new Scene(aPane, 600, 400);
        scene.getStylesheets().add("main.css");
        return scene;
    }

    //EVENTS
    /**
     *
     * @param e
     */
    public void addCode(ActionEvent e) {
        if (e.getSource().equals(aPane.btnSave)) {
            aPane.saveRecord();
        } else if (e.getSource().equals(aPane.btnCancel)) {
            stage.setScene(ViewPane());
        }
    }

    /**
     *
     * @param e
     */
    public void navCodes(ActionEvent e) {
        if (e.getSource().equals(vPane.btnAdd)) {
            stage.setScene(AddPane());
        }
    }

    /**
     *
     * @param e
     */
    public void eventCode(ActionEvent e) {

        if (e.getSource().equals(exitItem)) {
            System.exit(0);
        }

        if (e.getSource().equals(logPane.btnFirst)) {
            if ((user.equals(logPane.getTextField1())) && (pw.equals(logPane.getTextField2()))) {  ///THIS IS THE USENAME LOGIN 

                stage.setScene(navigationScene());
            } else {
                logPane.warningMessage();                                                           //THIS IS THE PASSWORD LOGIN 
            }
        } //view all from navPane
        else if (e.getSource().equals(navPane.btnA)) {
            stage.setScene(ViewPane()); //CAPS SHOULD CHANGE THIS.
        } else if (e.getSource().equals(navPane.btnB)) {
            stage.setScene(individualScene());
        } else if (e.getSource().equals(navPane.btnC)) {
            stage.setScene(firstScene());
        } //         Increment and decrement individual views..
        else if (e.getSource().equals(vPane.btnHome)) {
            stage.setScene(navigationScene());

        }
    }

}
