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
class BeginGame {
	int mode;
	File location;
	TextGraber getwords = null;
	String currentString = null;
	int on;
	FlowPane contenta = new FlowPane(new Label("ERROR"));
	Stage gameStage;
	ScrollPane contentb = new ScrollPane();
	VBox content = new VBox(2);
	Scene gameScene;
	BeginGame(int choosenmode,File filename)
	{
		mode =choosenmode;
		location = filename;
		getwords = new TextGraber(filename);
		getText();
		on = 0;
		gameStage = new Stage();
		contenta.setAlignment(Pos.CENTER);
		if (mode==2)
		{
		contenta.setAlignment(Pos.CENTER_LEFT);
		}
		content.setAlignment(Pos.TOP_CENTER);
		content.getChildren().add(new Label("RapidKeys"));
		gameScene = new Scene(content, 600, 300);
		gameStage.setScene(gameScene);
		if ( mode == 0 || mode == 1 )
		{
			content.getChildren().add(contenta);
		}
		if ( mode == 2 )
		{
			contentb.setHbarPolicy(ScrollBarPolicy.NEVER);
			contentb.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			content.getChildren().add(contentb);
			contentb.setContent(contenta);
		}
		gameScene.setOnKeyTyped(
			new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
					
                    String code = e.getCharacter().toString();
                    checkLet(code);
                    if(currentString==null)
                    {
                    	
                    }

                }
            }); 
		gameStage.show();
			
	}
	public void checkLet ( String let)
	{
		char w = let.charAt(0);
		if (let.charAt(0)!=(currentString.charAt(on)))
		{
			drawWord(false);
		}
		else
		{

			on++;
			drawWord(true);
			if(on==currentString.length())
			{
				getText();
			}
		}
	}
	public void drawWord( boolean correct)
	{
		
		
		FlowPane words = new FlowPane();
		int p = 0;
		String word = currentString;
		contenta.getChildren().clear();
	    for ( String s : word.split( "" ) )
	    {
	        Label l = new Label( s );
	        if (p<on)
	        {
	        l.setTextFill(Color.GREEN);
	
	        }
	        if (p==on)
	        {
	        	if (correct)
	        	{
	        	l.setTextFill(Color.BLUE);
	        	}
	        	else
	        	{
	        	l.setTextFill(Color.RED);
	        	}
	        }
	        if (p>on)
	        {
	        	l.setTextFill(Color.DARKGRAY);
	        }
	        l.setPrefWidth( 20 );
	        l.setAlignment( Pos.CENTER );
	        l.setFont( new Font( "Arial",  24 ) );
	        contenta.getChildren().add( l );
			p++;
	    }

	    //contenta = words;
	}
	public void getText() 
	{
		on=0;
		System.out.println("I was called");
		switch(mode)
		{
			case 0: 
				currentString=getwords.getLabel1();
				break;
			case 1: 
				currentString=getwords.getLabel2();
				break;
			
			case 2: 
				currentString=getwords.getLabel3();
				break;
			
			default: 
				System.out.println("Error in TypingGame:getText() switch statment not given a valid mode");
				break;
		}
		if (currentString==null)
		{
			TypingGame.primaryStage.show();
			gameStage.close();
		}
		else{
		drawWord(true);
		}
	}
}
