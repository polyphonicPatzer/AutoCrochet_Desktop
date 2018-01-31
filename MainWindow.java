//Carissa Ward

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;


public class MainWindow extends Application
{
	boolean alreadyInitialized = false;
	AnchorPane root;
	Scene scene;
	
	List<ChoiceBox<String>> boxes = new ArrayList<ChoiceBox<String>>();
	TextField size;
	
	List<Row> rowList;
	
	HBox hb = new HBox(); //holds 4 selections and go button
	
	String hs;
	String yw;
	String p;	
	
	Double cir;
	
	public void start(Stage stage) throws Exception
	{
		root = new AnchorPane();

		getBounds(stage); //gives stage maximized screen boundaries and initializes scene.
		placeMinuteInstructions(0, false);
		placeInstructionsBG();
		
		makeBoxes();
		
		addGoButton();


		stage.setScene(scene);
		stage.setTitle("Auto Crochet Pattern");
		stage.show();
	}
	
	private void makeBoxes()
	{
		ChoiceBox<String> hookSize = new ChoiceBox<String>(FXCollections.observableArrayList("(Hook Size)", "G")); // "E", "F", "G", "H", "I", "J", "K", "L"
		boxes.add(hookSize);
		ChoiceBox<String> yarnWeight = new ChoiceBox<String>(FXCollections.observableArrayList("(Yarn Weight)", "Light-Medium", "Medium"));//"Fine", "Light", "Light-Medium", "Medium", "Bulky", "SuperBulky"
		boxes.add(yarnWeight);
		ChoiceBox<String> pattern = new ChoiceBox<String>(FXCollections.observableArrayList("(Pattern)", "Ball", "Hat"));
		boxes.add(pattern);
		for (ChoiceBox<String> cb : boxes)
		{
		    cb.setStyle("-fx-font: 20 arial; -fx-base: #5F9F9F;");
			cb.getSelectionModel().selectFirst();
			hb.getChildren().add(cb);
		}
		Label label = new Label("Circumference (in centimeters):");
		label.setFont(new Font("Times New Roman", 25));
		size = new TextField ();
		size.setPrefWidth(80);

		hb.getChildren().addAll(label, size);
	    hb.setSpacing(30);
	    hb.setLayoutY(10);
		root.getChildren().add(hb);

	}
	
	private void getBounds(Stage stage)
	{
		Screen screen = Screen.getPrimary();
		Rectangle2D dimensions = screen.getVisualBounds();
		scene = new Scene(root, dimensions.getWidth(), dimensions.getHeight());
		stage.setX(dimensions.getMinX());
		stage.setY(dimensions.getMinY());
		stage.setWidth(dimensions.getWidth());
		stage.setHeight(dimensions.getHeight());
		
		
		Rectangle rect1 = new Rectangle();
		
		rect1.setX(0);
		rect1.setY(0);
		rect1.setHeight(dimensions.getHeight());
		rect1.setWidth(dimensions.getWidth());
		rect1.setFill(Color.PALETURQUOISE);
		
		root.getChildren().add(rect1);
		
		
//		Screen screen = Screen.getPrimary();
//		//Rectangle2D dimensions = screen.getVisualBounds();
//		scene = new Scene(root, 2000, 1000);
//		stage.setX(0);
//		stage.setY(0);
//		stage.setWidth(2000);
//		stage.setHeight(1000);
//		
//		
//		Rectangle rect1 = new Rectangle();
//		
//		rect1.setX(0);
//		rect1.setY(0);
//		rect1.setHeight(1000);
//		rect1.setWidth(2000);
//		rect1.setFill(Color.PALETURQUOISE);
//		
//		root.getChildren().add(rect1);
	}
	
	private void addGoButton()
	{
	    Button goButton = new Button("GO");
	   
	    goButton.setLayoutX(100);
	    goButton.setLayoutY(100);
	    goButton.setStyle("-fx-font: 30 arial; -fx-base: #009900;");
	    goButton.setOnAction(new EventHandler<ActionEvent>(){
	    	@Override public void handle(ActionEvent e){
	    		
	    		errorRect();
	    		placeInstructionsBG();
	    		placeMinuteInstructions(0, false);
	    		
	    		boolean filledOut = true;
	    		for (ChoiceBox<String> cb : boxes)
	    		{
	    			if (cb.getValue().charAt(0) == '(') {filledOut = false;}
	    		}
	    		
	    		if (alreadyInitialized)
	    		{
	    			errorLabel("The pattern has already been choosen.");
	    		}
	    		else if(!filledOut)
	    		{
	    			errorLabel("Please select all fields");
	    		}
	    		else if (!isInt(size.getText()))
	    		{
	    			errorLabel("Please enter a valid number as the circumference.");
	    		}
	    		else if (Double.parseDouble(size.getText())>81)
	    		{
	    			errorLabel("That is too large, please select a smaller circumference.");
	    		}
	    		else if (Double.parseDouble(size.getText())<7)
	    		{
	    			errorLabel("That is too small, please select a larger circumference.");
	    		}
	    		else
	    		{
	    			cir = Double.parseDouble(size.getText());
	    			hs = boxes.get(0).getValue();
	    			yw = boxes.get(1).getValue();
	    			p = boxes.get(2).getValue();
	    			if (p == "Hat") {errorLabel("Hat circumference should be 3 cm shorter than head circumference.");}
	    			getInstructions();
	    				//alreadyInitialized = true;
//	    			else
//	    			{
//	    				errorLabel("That combination is not recommended or is currently uncalculated.");
//	    			}
	    		}
	    	}});
		hb.getChildren().add(goButton);
	}
	
	public void errorLabel(String txt)
	{		
		Label label3 = new Label(txt);
		label3.setFont(new Font("Times New Roman", 25));
		label3.setLayoutX(5);
		label3.setLayoutY(50);
		root.getChildren().add(label3);
	}
	public void errorRect()
	{
		Rectangle rect4 = new Rectangle();
		
		rect4.setX(5);
		rect4.setY(50);
		rect4.setHeight(30);
		rect4.setWidth(800);
		rect4.setFill(Color.PALETURQUOISE);
		
		root.getChildren().add(rect4);
	}
	
	public void getInstructions()
	{
		Pattern retrievedPattern = new Pattern(p, cir, yw);
		rowList = retrievedPattern.getPattern();
		for (int i = 0; i < rowList.size(); i++)
		{
			HBox a = rowList.get(i).getHB();
			if (i<20)
			{
				a.setLayoutY(100 + (i*35));
				a.setLayoutX(20);
			}
			if (i>=20 && i<40)
			{
				a.setLayoutY(100 + ((i-20)*35));
				a.setLayoutX(380);
			}
			if (i>=40 && i<60)
			{
				a.setLayoutY(100 + ((i-40)*35));
				a.setLayoutX(750);
			}
			if (i>=60)
			{
				a.setLayoutY(100 + ((i-60)*35));
				a.setLayoutX(1130);
			}
			root.getChildren().add(a);
		}
		
		for (int i = 0; i < rowList.size(); i++)
		{
			CheckBox cb = rowList.get(i).getCB();
			int f = i;
			cb.selectedProperty().addListener(new ChangeListener<Boolean>(){
				public void changed(ObservableValue<? extends Boolean> ov,
						Boolean old_val, Boolean new_val){
					if (cb.isSelected()) 
					{
						placeMinuteInstructions(f, true);//f != rowList.size() - 1);
					}
				}
			});
		}
	}

	public void placeMinuteInstructions(int num, boolean notOnlyBG)
	{
		int x = 1600; //400
		int y = 95; //100
		Rectangle bg = makeRect(x-50, y, 300, 300);
		
		if (notOnlyBG)
		{
			Label label2 = new Label(rowList.get(num).getRowInstructions());
			label2.setFont(new Font("Times New Roman", 25));
			label2.setLayoutX(x);
			label2.setLayoutY(y);
			
			VBox vb = rowList.get(num).getCheckBoxes();
			vb.setSpacing(9);
			vb.setLayoutX(x-45);
			vb.setLayoutY(y+3);
			try{root.getChildren().remove(vb);}catch(Exception e){}
			root.getChildren().addAll(bg, label2, vb);
		}
		else
		{
			root.getChildren().addAll(bg);
		}
	}
	
	public Rectangle makeRect(int layoutX, int layoutY, int height, int width)
	{
		Rectangle rect = new Rectangle();
		
		rect.setX(layoutX);
		rect.setY(layoutY);
		rect.setHeight(height);
		rect.setWidth(width);
		rect.setFill(Color.MEDIUMAQUAMARINE);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(1);
		
		return rect;
	}
	
	public void placeInstructionsBG()
	{
		Rectangle rect3 = new Rectangle();
		
		rect3.setX(15);
		rect3.setY(95);
		rect3.setHeight(705);
		rect3.setWidth(1505);
		rect3.setFill(Color.MEDIUMAQUAMARINE);
		rect3.setStroke(Color.BLACK);
		rect3.setStrokeWidth(1);
		root.getChildren().add(rect3);
	}
	
	public String printGrid(List<Integer> stitchCounts) 
	{
		if (stitchCounts == null)
		{
			return "error";
		}
		String s = "";
		for(int i = 0; i < stitchCounts.size(); i++)
		{
			s = s.concat(String.valueOf(stitchCounts.get(i)));
			s = s.concat("\n");
		}		
		return s;
	}
	
	public static boolean isInt(String s) {
		try 
		{ 
	        Double.parseDouble(s); 
	    } 
		catch(NumberFormatException e) 
		{ 
	        return false; 
	    } 
		catch(NullPointerException e) 
		{
	        return false;
	    }
	    return true;
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
