package com.rapidkeys;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.scene.paint.*;
public class TypingGame extends Application
{
	Label wordsCenter; // If only one word at a time or one sentence at a time
	Label[] story = new Label[4]; // If its a story; 
	int mode = 0; 
	File filename = new File("D:/test.txt");
	int done = 0 ;
	static Stage primaryStage;
	TextGraber getwords = null;
	public static void main (String[] args )
	{
		launch(args);
	}
	@Override
	public void start(Stage ppp )throws Exception
	{
		primaryStage=ppp;
		BorderPane menuLay = new BorderPane(); // Main Menue Scene 
		Scene menu = new Scene(menuLay,600,550);
		menuLay.setStyle("-fx-background-color: #E8E8E8");
		// Top of the menu
		Label mainTop = new Label("RapidKeys");
		mainTop.setFont( new Font( "Ubuntu",  24 ) );
		mainTop.setTextFill(Color.DARKBLUE);
		mainTop.setPadding(new Insets(15,12,15,12));
		StackPane title = new StackPane();
		title.getChildren().add(mainTop);
		menuLay.setTop(title);
		//Main Content
		//Main Page
		Button startGameBut = new Button("Start Game");
		startGameBut.setOnAction(e->
		{
			primaryStage.hide();
			BeginGame game = new BeginGame(mode, filename);
		});
		StackPane startButPane = new StackPane();
		startButPane.setPadding(new Insets(0,120,0,0));
		startButPane.getChildren().add(startGameBut);
		
		//Settings
		VBox settingsBox = new VBox(5);
		Label setL1 = new Label("Enter Text File Location");
		TextField enterFileLocation = new TextField();
		enterFileLocation.setPromptText("ex. C:/Users/Johnny/Documents/words.txt");
		enterFileLocation.setOnAction(e->
		{
			String a = new String (enterFileLocation.getText());
			File b = new File(a);
			File c = new File(a+".txt");
			System.out.println(a+"\n"+b.exists()+"\n"+c.exists());
			if (b.exists()==false&&c.exists()==false)
			{
				startGameBut.setDisable(true);
				startGameBut.setTextFill(Color.RED);
				startGameBut.setText("Invalid File Location");
			}
			else
			{
				startGameBut.setDisable(false);
				startGameBut.setTextFill(Color.BLACK);
				startGameBut.setText("Start Game");
				if (b.exists() == true)
				{
					filename=b;
				}
				else
				{
					filename=c;
				}
				
			}
		});
		final ToggleGroup modeButList = new ToggleGroup();
		RadioButton mode0 = new RadioButton("Word by Word");
		mode0.setToggleGroup(modeButList);
		mode0.setOnAction(e-> mode=0);
		RadioButton mode1 = new RadioButton("Line by Line");
		mode1.setToggleGroup(modeButList);
		mode1.setOnAction(e-> mode=1);
		RadioButton mode2 = new RadioButton("Whole File  ");
		mode2.setToggleGroup(modeButList);
		mode2.setOnAction(e-> mode=2);
		mode0.setSelected(true);
		VBox butListMode = new VBox();
		butListMode.getChildren().addAll(mode0,mode1,mode2);
		settingsBox.getChildren().addAll(setL1,enterFileLocation,new Label("Display Settings"),butListMode);
		//History 
		VBox historyPage = new VBox(2);
		//Left Pane
		VBox navigation = new VBox(2);
		navigation.setPadding(new Insets(10,10,10,10));
		menuLay.setLeft(navigation);
		//Buttons
		//MainPageBut
		Button mainPageBut = new Button("MainPage");
		mainPageBut.setPrefSize(100,20);
		mainPageBut.setOnAction(e ->
		{
			menuLay.setCenter(startButPane); 
		});
		navigation.getChildren().add(mainPageBut);
		//SettingsBut
		Button settingsBut = new Button("Settings");
		settingsBut.setPrefSize(100,20);
		settingsBut.setOnAction(e ->
		{
			menuLay.setCenter(settingsBox);
		});
		navigation.getChildren().add(settingsBut);
		//HistoryBut
		Button historyBut = new Button("History");
		historyBut.setPrefSize(100,20);
		historyBut.setOnAction(e ->
		{
			//updateHistory();
			menuLay.setCenter(historyPage);
		});
		navigation.getChildren().add(historyBut);
		//Bottom Credits
		VBox credits = new VBox();
		Label credits0 = new Label ("Credits");
		Label credits1 = new Label ("Created By: Austin");
		Label credits2 = new Label ("Version 1.0");
		Label credits3 = new Label ();
		credits0.setTextFill(Color.BLACK);
		credits1.setTextFill(Color.BLACK);
		credits2.setTextFill(Color.BLACK);
		credits3.setTextFill(Color.BLACK);
		credits.getChildren().addAll(credits0,credits1,credits2,credits3);
		credits0.setAlignment(Pos.CENTER);
		credits1.setAlignment(Pos.CENTER);
		credits2.setAlignment(Pos.CENTER);
		credits3.setAlignment(Pos.CENTER);
		credits.setAlignment(Pos.CENTER);
		menuLay.setBottom(credits);
		
		
		//
		menuLay.setCenter(startButPane);
		primaryStage.setScene(menu);
		primaryStage.show();
	}
}