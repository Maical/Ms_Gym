/*
   This class displays the navigation
 */
package M_Gym;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

/**
 * 
 * @author Michael Liu & Maria Hoang
 */

public class Navigation extends VBox {

    protected Button btnA;
    protected Button btnB;
    protected Button btnC;

    public Navigation() {

        super(20);
        HBox title = new HBox();
        title.setAlignment(Pos.TOP_CENTER);
        Label lblNav = new Label("Navigation Menu");

        Image members = new Image("file:myfiles/member.png");
        btnA = new Button();
        btnA.setGraphic(new ImageView(members));

        Image indv = new Image("file:myfiles/indv.png");
        btnB = new Button();
        btnB.setGraphic(new ImageView(indv));

        Image logout = new Image("file:myfiles/logout.png");
        btnC = new Button();
        btnC.setGraphic(new ImageView(logout));

        title.getChildren().addAll(btnA, btnB, btnC);
        title.setSpacing(10);
        this.getChildren().addAll(lblNav, title);
        this.setAlignment(Pos.CENTER);

    }
}
