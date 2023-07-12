package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.text.Font;
import javafx.beans.binding.*;

import javafx.beans.property.*;



public class My_calculator extends Application{
	
	
   
	TextField output_screen = new TextField();

    private boolean start =true;
    
	
	@Override
	public void start(Stage primaryStage)  {
		
	
		// Calculated expression output
	
		output_screen = new TextField();
		output_screen.setEditable(false);
        output_screen.setStyle("-fx-background-color: WHITE; -fx-border-color: BLACK;");
   
        output_screen.setFont(Font.font(20));
        output_screen.setPrefHeight(50);
		output_screen.setAlignment(Pos.CENTER_RIGHT);
	
		
		
		StackPane stackpane = new StackPane();
		stackpane.setPadding(new Insets(10));
		stackpane.getChildren().add(output_screen);
	
		
		// Grid pane
		
				 GridPane grid = new GridPane();
				 grid.setAlignment(Pos.TOP_CENTER);
			        grid.setHgap(10);
			        grid.setVgap(10);
			        grid.setPadding(new Insets(25, 25, 25, 25));
			       
		      
			   // Add buttons to grid
		        

			    grid.add(ClearButton("C"), 0, 0);
			    grid.add(nameButton("Laraib"), 1, 0,2,1);
			    
				
				grid.add(numberButton("("), 0, 1);
				grid.add(numberButton(")"), 1, 1);
				grid.add(operatorButton("^"), 2, 1);
				grid.add(Backspace("B"), 3, 1);
	
				
			        
				grid.add(numberButton("7"), 0, 2);
				grid.add(numberButton("8"), 1, 2);	
				grid.add(numberButton("9"), 2, 2);
				grid.add(operatorButton("/"), 3, 2);
				
				grid.add(numberButton("4"), 0, 3);
				grid.add(numberButton("5"), 1, 3);
				grid.add(numberButton("6"), 2, 3);
				grid.add(operatorButton("*"), 3, 3);
		
				grid.add(numberButton("1"), 0, 4);
				grid.add(numberButton("2"), 1, 4);
				grid.add(numberButton("3"), 2, 4);
				grid.add(operatorButton("-"), 3, 4);
				
				grid.add(numberButton("."), 0, 5);
				grid.add(numberButton("0"), 1, 5);
				grid.add(operatorButton("="), 2, 5);
				grid.add(operatorButton("+"), 3, 5);
				
		
		
			
				
		// output screen
	
		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		root.setCenter(grid);
		root.setStyle("-fx-background-color: AQUA;");
		
  
		
		
		Scene scene = new Scene(root, 260, 450);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		
		
	    
		    primaryStage.setTitle("Calculator");
		 
		    primaryStage.show();
		    
		    
	}
	
	
	// Numeric Buttons
	
	
	private Button numberButton(String ch) {
    
		Button button = new Button(ch);
		   
		button.setPrefSize(40, 40); 
		button.setStyle("-fx-background-color:YELLOW; -fx-text-fill:BLACK; -fx-font-size:20; -fx-font-weight:bold;-fx-background-radius: 10px");

		
		button.setOnAction(e ->{
			
			if(start) {
				output_screen.setText("");
				start=false;
			}
			String value = ((Button)e.getSource()).getText();
			
		// point handling
			
			boolean decimalExist=false;
			if(value==".") {
				
				// if point already exist
				
				String exp = output_screen.getText();
				
				for (int i=exp.length()-1;i>=0; i--) {
					
					char c = exp.charAt(i);
					if(c=='.') {
						decimalExist=true;
					}
					else if(Character.isDigit(c)){
				
						continue;
					}
					else {
						break;
					}
				}
				
			}
			
			if (decimalExist)
				return;
			
			output_screen.setText( output_screen.getText()+ value);
			
	
		});
		
		return button;
		
	}
	
	
	// Operator Buttons
	
	private Button operatorButton(String op) {
		
		Button button = new Button(op);
		   
		button.setPrefSize(40, 50); 
		button.setStyle("-fx-background-color:RED; -fx-text-fill:WHITE; -fx-font-size:20; -fx-font-weight:bold; -fx-background-radius: 10px");
		  
		// set on action
		
		button.setOnAction(e ->{
			
			String value = ((Button)e.getSource()).getText();
			
			if (value!="=") {
				
				output_screen.setText( output_screen.getText()+ value);
			
			}  
			
			else {
				
				String exp = output_screen.getText();
				double result = MathExpressions.evaluate(exp);
				output_screen.setText(String.valueOf(result));
				start=true;
			
			}
		
			
		});
		
		return button;
		
	}
		
	
	
	//Clear Button

	Button ClearButton(String c) {
		
		Button button = new Button(c);
		   
		button.setPrefSize(40, 40); 
		button.setStyle("-fx-background-color:BLUE; -fx-text-fill:WHITE; -fx-font-size:20; -fx-font-weight:bold; -fx-background-radius: 10px");
		  
		// Set on Action
		
		button.setOnAction(e ->{
			
			output_screen.setText("");
			start = true;
			
		});
		
		
		return button;
		
	}
	
	// Backspace Button

      Button Backspace(String b) {
  		
  		Button button = new Button(b);
  		   
  		button.setPrefSize(40, 40); 
  		button.setStyle("-fx-background-color:WHITE; -fx-text-fill:BLACK; -fx-font-size:20; -fx-font-weight:bold; -fx-background-radius: 10px");
  		  
  		
  	// Set on Action
		
  			button.setOnAction(e ->{
  		
  				String text = output_screen.getText();
  				
  				if (!text.isEmpty())
  					
  				{
  				text= text.substring(0, text.length()-1);
  				output_screen.setText(text);}
  				
  				
  			});
  		
  		return button;
  		
  	}
      

      Button nameButton(String name) {
  		
  		Button button = new Button(name);
  		   
  		button.setPrefSize(100, 40); 
  		button.setStyle("-fx-background-color:WHITE; -fx-text-fill:BLACK; -fx-font-size:20; -fx-font-weight:bold; -fx-background-radius: 10px");
  		return button;
      
      }}

      
     




















