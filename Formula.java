import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class Formula extends Application {
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Formula");
		
		FormulaMaths formulaMaths = new FormulaMaths();
		
		//container for everything
		VBox main = new VBox();
		
		//title
		Label title = new Label("Future/present value of investments");
		
		//instructions
		Label instructions = new Label("Please input a whole number for the amount, the annual interest rate in percent (not decimal), and a whole number for the year.");
		
		//container for form
		GridPane inputGrid = new GridPane();
		//left side text
		Label amount = new Label("Amount:");
		inputGrid.add(amount, 0, 0);
		Label annualInterestRate = new Label("Annual interest rate:");
		inputGrid.add(annualInterestRate, 0, 1);
		Label numberOfYears = new Label("Number of years:");
		inputGrid.add(numberOfYears, 0, 2);
		Label calculate = new Label("Calculate:");
		inputGrid.add(calculate, 0, 3);
		
		//right side input
		TextField amountTxt = new TextField();
		inputGrid.add(amountTxt, 1, 0);
		TextField annualInterestRateTxt = new TextField();
		inputGrid.add(annualInterestRateTxt, 1, 1);
		TextField numberOfYearsTxt = new TextField();
		inputGrid.add(numberOfYearsTxt, 1, 2);
		//radio buttons
		VBox radioCalculate = new VBox();
		ToggleGroup calculateValue = new ToggleGroup();
		RadioButton futureValue = new RadioButton("Future value");
	    futureValue.setToggleGroup(calculateValue);
	    futureValue.setSelected(true);
	    RadioButton presentValue = new RadioButton("Present value");
	    presentValue.setToggleGroup(calculateValue);
	    radioCalculate.getChildren().addAll(futureValue, presentValue);
	    radioCalculate.setSpacing(5);
	    inputGrid.add(radioCalculate, 1, 3);
	    
	    //submit and reset buttons
	    Button submit = new Button("Submit");
	    Button clear = new Button("Clear");
	    HBox buttons = new HBox();
	    buttons.getChildren().addAll(submit, clear);
	    buttons.setAlignment(Pos.CENTER);
	    buttons.setSpacing(10);
	    inputGrid.add(buttons, 1, 4);
	    
	    //display the result of calculation or error message
	    Label result = new Label();
	    
	    //submit button actionevent
	    submit.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
                if (futureValue.isSelected()){
	                try {
		                double toReturn = formulaMaths.futureValue( Integer.parseInt(amountTxt.getText()), 
		                		Double.parseDouble(annualInterestRateTxt.getText()), 
		                		Integer.parseInt(numberOfYearsTxt.getText()));
		                result.setText("If you invest the value " + amountTxt.getText() + 
		                		" now then in " + numberOfYearsTxt.getText() + 
		                		" year(s) and at the annual interest rate of " + annualInterestRateTxt.getText() + 
		                		"% it will be worth " + toReturn + ".");
                	} catch (NumberFormatException nfe) {
                		result.setText("Please enter a number for each input box");
                	} catch (Exception e) {
                		System.out.println(e.getMessage());
                	}
                } else if (presentValue.isSelected()) {
                	try {
		                double toReturn = formulaMaths.presentValue( Integer.parseInt(amountTxt.getText()), 
		                		Double.parseDouble(annualInterestRateTxt.getText()), 
		                		Integer.parseInt(numberOfYearsTxt.getText()));
		                result.setText("To achieve the future value of " + amountTxt.getText() + 
		                		" in " + numberOfYearsTxt.getText() + 
		                		" year(s) at the annual interest rate of " + annualInterestRateTxt.getText() + 
		                		"% you will need to invest " + toReturn + " at the present time.");
                	} catch (NumberFormatException nfe) {
                		result.setText("Please enter a number for each input box");
                	} catch (Exception e) {
                		System.out.println(e.getMessage());
                	}
	                
                }
            }
	    });
	    
	    //reset button actionevent
	    clear.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		amountTxt.clear();
	    		annualInterestRateTxt.clear();
	    		numberOfYearsTxt.clear();
	    		result.setText(null);
	    	}
	    });
	    
	    //settings for the input grid
		inputGrid.setAlignment(Pos.CENTER);
		inputGrid.setVgap(10);
		inputGrid.setHgap(10);
		inputGrid.setPadding(new Insets(20,10,20,10));
		
		//add elements to the container for everything
		main.getChildren().addAll(title, instructions, inputGrid, result);
		
		//
		//SECOND TAB
		//
		
		//container for everything on the second tab
		VBox main2 = new VBox();
		
		//title
		Label title2 = new Label("Basic Financial Ratios");
		
		//left side labels of second tab
		Label typeOfRatio = new Label("Type of ratio:");
		Label ratioFactorOneLabel = new Label();
		Label ratioFactorTwoLabel = new Label();
		
		//drop down menu
		ObservableList<String> options = FXCollections.observableArrayList("Current", "Working Capital", "Debt to Equity", "Gross Profit Margin");
		ComboBox<String> combobox = new ComboBox<String>(options);
		
		//textfields
		TextField ratioFactorOneText = new TextField();
		TextField ratioFactorTwoText = new TextField();
		
		//submit and reset buttons
		Button submit2 = new Button("Submit");
	    Button clear2 = new Button("Clear");
	    HBox buttons2 = new HBox();
	    buttons2.getChildren().addAll(submit2, clear2);
	    buttons2.setAlignment(Pos.CENTER);
	    buttons2.setSpacing(10);
	    
	    //gridpane for input area
		GridPane ratioInputGrid = new GridPane();
		ratioInputGrid.add(typeOfRatio, 0, 0);
		ratioInputGrid.add(ratioFactorOneLabel, 0, 1);
		ratioInputGrid.add(ratioFactorTwoLabel, 0, 2);
		ratioInputGrid.add(combobox, 1, 0);
		ratioInputGrid.add(ratioFactorOneText, 1, 1);
		ratioInputGrid.add(ratioFactorTwoText, 1, 2);
		ratioInputGrid.add(buttons2, 1, 3);
		
		//settings for the input grid for input area on second tab
		ratioInputGrid.setAlignment(Pos.CENTER);
		ratioInputGrid.setVgap(10);
		ratioInputGrid.setHgap(10);
		ratioInputGrid.setPadding(new Insets(20,10,20,10));
	    
	    //display the result of calculation or error message for second tab
	    Label result2 = new Label();
	    
	    //submit button on second tab actionevent
	    submit2.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
                if (combobox.getValue() != null){
                	//get value from drop down menu
	                switch (combobox.getValue()) {
		                case "Current":
		                case "Debt to Equity":
		                case "Gross Profit Margin":
		                	try {
		                		double ratioFactorOne = Double.parseDouble(ratioFactorOneText.getText());
		                		double ratioFactorTwo = Double.parseDouble(ratioFactorTwoText.getText());
				                double toReturn = formulaMaths.divide(ratioFactorOne, ratioFactorTwo);
				                result2.setText("The " + combobox.getValue() + " ratio of " + ratioFactorOne + " and " + ratioFactorTwo + " is " + toReturn);
		                	} catch (NumberFormatException nfe) {
		                		result2.setText("Please enter a number for each input box");
		                	} catch (Exception e) {
		                		System.out.println(e.getMessage());
		                	}
		                	break;
		                case "Working Capital":
		                	try {
		                		double ratioFactorOne = Double.parseDouble(ratioFactorOneText.getText());
		                		double ratioFactorTwo = Double.parseDouble(ratioFactorTwoText.getText());
				                double toReturn = formulaMaths.minus(ratioFactorOne, ratioFactorTwo);
				                result2.setText("The " + combobox.getValue() + " ratio of " + ratioFactorOne + " and " + ratioFactorTwo + " is " + toReturn);
		                	} catch (NumberFormatException nfe) {
		                		result2.setText("Please enter a number for each input box");
		                	} catch (Exception e) {
		                		System.out.println(e.getMessage());
		                	}
		                	break;
	                }
                } else {
                	//if no value value has been selected in drop down menu
                	result2.setText("Please select an option from the drop down menu");
                }
            }
	    });
	    
	    //reset button on second tab actionevent
	    clear2.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		ratioFactorOneText.clear();
	    		ratioFactorTwoText.clear();
	    		result2.setText(null);
	    	}
	    });
		
		//actionevent for drop down menu
		combobox.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		switch (combobox.getValue()) {
	    			case "Current":
	    				ratioFactorOneLabel.setText("Current assets:");
	    				ratioFactorTwoLabel.setText("Current liabilities:");
	    				break;
	    			case "Working Capital":
	    				ratioFactorOneLabel.setText("Current assets:");
	    				ratioFactorTwoLabel.setText("Current liabilities:");
	    				break;
	    			case "Debt to Equity":
	    				ratioFactorOneLabel.setText("Total debt:");
	    				ratioFactorTwoLabel.setText("Total equity:");
	    				break;
	    			case "Gross Profit Margin":
	    				ratioFactorOneLabel.setText("Gross profit:");
	    				ratioFactorTwoLabel.setText("Revenue:");
	    				break;
	    		}
	    	}
		});
		
		//add elements to the container for everything on second tab
		main2.getChildren().addAll(title2, ratioInputGrid, result2);
		
		//make a tab panel at the top
		TabPane tabpane = new TabPane();
		//do not let user close the tabs
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		//first tab
		Tab timeValueOfMoney = new Tab();
		timeValueOfMoney.setText("Time Value of Money");
		timeValueOfMoney.setContent(main);
		tabpane.getTabs().add(timeValueOfMoney);
		//second tab
		Tab basicFinancialRatios = new Tab();
		basicFinancialRatios.setText("Basic Financial Ratios");
		basicFinancialRatios.setContent(main2);
		tabpane.getTabs().add(basicFinancialRatios);
		
		Scene scene = new Scene(tabpane);
		stage.setScene(scene);
		stage.setWidth(800);
		stage.setHeight(500);
		stage.show();
	} //end start method
	
	public static void main(String args[]) {
		
		launch(args);
		
	} //end main method
	
} //end Formula class