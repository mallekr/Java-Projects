/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Rich
 * JavaFX UI controls from http://docs.oracle.com/javafx/2/ui_controls/jfxpub-ui_controls.htm
 * Layout panes code adjusted from http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm#JFXLY102
 * Counting lines code courtesy of user Matt Byrne on stackoverflow (http://stackoverflow.com/questions/26448352/counting-the-number-of-lines-in-a-text-file-java)
 * 
 */
public class RPGGenerator extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    BorderPane border = new BorderPane();

    public void start(Stage primaryStage) {

        border = new BorderPane();
        border.setStyle("-fx-font-family: Times New Roman");
        border.setStyle("-fx-font-weight: bold;");
        HBox hbox = addHbox();
        border.setTop(hbox);
        border.setLeft(addGridPane());

        border.setCenter(addGridPane1());
        border.setPrefSize(900.0, 500.0);
        Scene scene = new Scene(border);
        primaryStage.setTitle("RPG Random Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox addHbox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonDND = new Button("D & D");
        buttonDND.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                border.setCenter(addGridPane1());
                
            }
        });
        
        Button buttonPara = new Button("Paranoia");
        buttonPara.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event) {
                border.setCenter(addGridPane2());
            }
            
        });
        buttonDND.setPrefSize(100, 20);
        buttonPara.setPrefSize(100, 20);

        hbox.getChildren().addAll(buttonDND);
        hbox.getChildren().addAll(buttonPara);

        return hbox;

    }

    private GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 5, 0, 5));

        TextArea out = new TextArea();
        out.setPrefSize(250, 150);
        ScrollPane scroll = new ScrollPane(out);
        out.setEditable(false);
        grid.add(out, 0, 8, 3, 5);

        Text title = new Text("Dice Roller");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        grid.add(title, 0, 0, 2, 1);

        Text d4 = new Text("D4");
        grid.add(d4, 0, 1);

        TextField tfd4 = new TextField();
        tfd4.setMaxWidth(50);
        Tooltip tooltipd4 = new Tooltip();
        tooltipd4.setText("Number of D4's you want to roll");
        tfd4.setTooltip(tooltipd4);
        tfd4.setText("1");
        grid.add(tfd4, 1, 1);

        Button d4b = new Button("Roll D4");
        d4b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd4.getText()); i ++){
                int roll = diceRoller(4);
                out.appendText("You rolled d4: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd4.getText()), 4);
            //out.appendText("You rolled " + Integer.parseInt(tfd4.getText()) + " D4's for a total of " + roll + "\n");
        });
        grid.add(d4b, 2, 1);

        Text d6 = new Text("D6");
        grid.add(d6, 0, 2);

        TextField tfd6 = new TextField();
        tfd6.setMaxWidth(50);
        Tooltip tooltipd6 = new Tooltip();
        tooltipd6.setText("Number of D6's you want to roll");
        tfd6.setTooltip(tooltipd6);
        tfd6.setText("1");
        grid.add(tfd6, 1, 2);

        Button d6b = new Button("Roll D6");
        d6b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd6.getText()); i ++){
                int roll = diceRoller(6);
                out.appendText("You rolled d6: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd6.getText()), 6);
            //out.appendText("You rolled " + Integer.parseInt(tfd6.getText()) + " D6's for a total of " + roll + "\n");
        });
        grid.add(d6b, 2, 2);

        Text d8 = new Text("D8");
        grid.add(d8, 0, 3);

        TextField tfd8 = new TextField();
        tfd8.setMaxWidth(50);
        Tooltip tooltipd8 = new Tooltip();
        tooltipd8.setText("Number of D8's you want to roll");
        tfd8.setTooltip(tooltipd8);
        tfd8.setText("1");
        grid.add(tfd8, 1, 3);

        Button d8b = new Button("Roll D8");
        d8b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd8.getText()); i ++){
                int roll = diceRoller(8);
                out.appendText("You rolled d8: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd8.getText()), 8);
            //out.appendText("You rolled " + Integer.parseInt(tfd8.getText()) + " D8's for a total of " + roll + "\n");
        });
        grid.add(d8b, 2, 3);

        Text d10 = new Text("D10");
        grid.add(d10, 0, 4);

        TextField tfd10 = new TextField();
        tfd10.setMaxWidth(50);
        Tooltip tooltipd10 = new Tooltip();
        tooltipd10.setText("Number of D10's you want to roll");
        tfd10.setTooltip(tooltipd10);
        tfd10.setText("1");
        grid.add(tfd10, 1, 4);

        Button d10b = new Button("Roll D10");
        d10b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd10.getText()); i ++){
                int roll = diceRoller(10);
                out.appendText("You rolled d10: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd10.getText()), 10);
            //out.appendText("You rolled " + Integer.parseInt(tfd10.getText()) + " D10's for a total of " + roll + "\n");
        });
        grid.add(d10b, 2, 4);

        Text d12 = new Text("D12");
        grid.add(d12, 0, 5);

        TextField tfd12 = new TextField();
        tfd12.setMaxWidth(50);
        Tooltip tooltipd12 = new Tooltip();
        tooltipd12.setText("Number of D12's you want to roll");
        tfd12.setTooltip(tooltipd12);
        tfd12.setText("1");
        grid.add(tfd12, 1, 5);

        Button d12b = new Button("Roll D12");
        d12b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd12.getText()); i ++){
                int roll = diceRoller(12);
                out.appendText("You rolled d12: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd12.getText()), 12);
            //out.appendText("You rolled " + Integer.parseInt(tfd12.getText()) + " D12's for a total of " + roll + "\n");
        });
        grid.add(d12b, 2, 5);

        Text d20 = new Text("D20");
        grid.add(d20, 0, 6);

        TextField tfd20 = new TextField();
        tfd20.setMaxWidth(50);
        Tooltip tooltipd20 = new Tooltip();
        tooltipd20.setText("Number of D20's you want to roll");
        tfd20.setTooltip(tooltipd20);
        tfd20.setText("1");
        grid.add(tfd20, 1, 6);

        Button d20b = new Button("Roll D20");
        d20b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd20.getText()); i ++){
                int roll = diceRoller(20);
                out.appendText("You rolled d20: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd20.getText()), 20);
            //out.appendText("You rolled " + Integer.parseInt(tfd20.getText()) + " D20's for a total of " + roll + "\n");
        });
        grid.add(d20b, 2, 6);

        Text d100 = new Text("D100");
        grid.add(d100, 0, 7);

        TextField tfd100 = new TextField();
        tfd100.setMaxWidth(50);
        Tooltip tooltipd100 = new Tooltip();
        tooltipd100.setText("Number of D100's you want to roll");
        tfd100.setTooltip(tooltipd100);
        tfd100.setText("1");
        grid.add(tfd100, 1, 7);

        Button d100b = new Button("Roll D100");
        d100b.setOnAction((ActionEvent event) -> {
            out.clear();
            for(int i = 0; i < Integer.parseInt(tfd100.getText()); i ++){
                int roll = diceRoller(100);
                out.appendText("You rolled d100: " + roll + "\n");
            }
            //int roll = diceRoller(Integer.parseInt(tfd100.getText()), 100);
            //out.appendText("You rolled " + Integer.parseInt(tfd100.getText()) + " D100's for a total of " + roll + "\n");
        });
        grid.add(d100b, 2, 7);

        return grid;
    }

    private GridPane addGridPane1() {
        //Button and text for D&D

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //output area
        TextArea out = new TextArea();
        out.setEditable(false);
        out.setPrefSize(300, 300);
        out.setWrapText(true);
        grid.add(out, 3, 1, 3, 10);

        Text gens = new Text("Random D & D Generators:");
        gens.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        grid.add(gens, 0, 0, 2, 1);

        Text output = new Text("Output:");
        output.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        grid.add(output, 3, 0, 2, 1);

        //Random NPC Generator
        Text npc = new Text("Random Villain:");
        grid.add(npc, 0, 1);

        Button villainb = new Button("Roll Random Villain");
        villainb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("npcbase.txt");
                out.appendText("Your villain is a " + get + ".\n");
                String get1 = ranGen("from.txt");
                out.appendText("From " + get1 + ".\n");
                String get2 = ranGen("motive.txt");
                out.appendText("Whose motivation is " + get2 + ".");
            }
        });
        grid.add(villainb, 1, 1);

        //Uncommon Treasure Generator
        Text unc = new Text("Uncommon Treasure");
        grid.add(unc, 0, 2);

        Button uncb = new Button("Roll Find");
        uncb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("uncommon.txt");
                out.appendText(get);
            }
        });
        grid.add(uncb, 1, 2);

        //Random menu items at an Inn
        Text menuText = new Text("What's on the menu?");
        grid.add(menuText, 0, 3);

        Button menuButton = new Button("Roll menu item");
        menuButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("menu.txt");
                out.appendText("Food: " +get + "\n\n");
                String get1 = ranGen("drinks.txt");
                out.appendText("Drink: " + get1 + "\n");
            }
        });
        grid.add(menuButton, 1, 3);

        //Random Magical Ropes
        Text rope = new Text("Random magical ropes:");
        grid.add(rope, 0, 4);

        Button ropeb = new Button("Roll magical rope");
        ropeb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("ropes.txt");
                out.appendText(get);
            }
        });
        grid.add(ropeb, 1, 4);

        return grid;
    }
    
    private GridPane addGridPane2(){
        //Buttons and text, etc. for Paranoia RPG
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        //output area
        TextArea out = new TextArea();
        out.setEditable(false);
        out.setPrefSize(300, 300);
        out.setWrapText(true);
        grid.add(out, 3, 1, 3, 10);

        Text gens = new Text("Random Paranoia Generators:");
        gens.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        grid.add(gens, 0, 0, 2, 1);

        Text output = new Text("Output:");
        output.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        grid.add(output, 3, 0, 2, 1);

        //Random NPC Generator
        Text npc = new Text("Experimental Equipment");
        grid.add(npc, 0, 1);

        Button rdb = new Button("Roll Equipment");
        rdb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("rd.txt");
                out.appendText(get);
            }
        });
        grid.add(rdb, 1, 1);

        //Uncommon Treasure Generator
        Text unc = new Text("Secret Societies");
        grid.add(unc, 0, 2);

        Button ssb = new Button("Roll Secret Society");
        ssb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("ss.txt");
                out.appendText(get);
            }
        });
        grid.add(ssb, 1, 2);

        //Random service group
        Text menuText = new Text("Service Group");
        grid.add(menuText, 0, 3);

        Button sgb = new Button("Roll Service Group");
        sgb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("servgroup.txt");
                out.appendText(get);
            }
        });
        grid.add(sgb, 1, 3);

        //Random Medication
        Text meds = new Text("Random Medication");
        grid.add(meds, 0, 4);

        Button medsb = new Button("Roll Medication");
        medsb.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                out.clear();
                String get = ranGen("meds.txt");
                out.appendText(get);
            }
        });
        grid.add(medsb, 1, 4);
        
        return grid;
    }

    private static String ranGen(String in) {
        String out = "";
        int i = 0;
        //out.clear();
        try {
            File menu = new File(in);
            Scanner scan = new Scanner(menu);
            try {
                //counting lines code courtesy of user Matt Byrne on stackoverflow (http://stackoverflow.com/questions/26448352/counting-the-number-of-lines-in-a-text-file-java)
                long line = Files.lines(Paths.get(in)).count();
                i = Math.toIntExact(line);
                //String i = Long.toString(line);

            } catch (IOException ex) {
                Logger.getLogger(RPGGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            String getMenu[] = new String[i];
            int roll = (int) (Math.random() * i);
            for (int j = 0; j < getMenu.length; j++) {
                getMenu[j] = scan.nextLine();
            }
            out = getMenu[roll];

            //out.appendText(getMenu[roll] + "\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RPGGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public static int diceRoller(/*int a,*/ int b) {
        int total = (int) (Math.random() * b + 1);

//int total = 0;

        /*for (int i = 0; i < a; i++) {
            total += (int) (Math.random() * b + 1);
        }*/

        return total;
    }

}
