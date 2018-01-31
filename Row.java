//Carissa Ward

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Row 
{
	CheckBox rowCompleted = new CheckBox();
	int scNum;
	int incNum;
	int total;
	String incOrDec = " inc";
	
	int rINum = 0;
	int rowNum;
	
	int firstTotal;
	
	HBox rowInfo = new HBox();
	VBox vb = new VBox();
	
	List<CheckBox> checkBoxes = new ArrayList<CheckBox>();
	String rowInstructions = "";
	
	public Row(int prevTotal, int totalSC, int RN)
	{
		rowNum = RN;
		
		if (prevTotal == 0)
		{
			scNum = totalSC;
			incNum = 0;
			total = totalSC;
		}
		else if (totalSC == 0)
		{
			scNum = 0;
			incNum = prevTotal/2;
			total = 0;
			incOrDec = " dec";
		}
		else
		{
			total = totalSC;
			int difference = total - prevTotal;
			if (difference < 0)
			{
				incNum = Math.abs(difference);
				incOrDec = " dec";
				scNum = total + difference;
			}
			else
			{
				incNum = difference;
				scNum = prevTotal - difference;
			}
		}
		initRowInstructions();
		makeRICheckBoxes();
	}
	
	public HBox getHB()
	{
		Label label;
		if (total == 0)//Sphere specific which is bad.
		{
			label = new Label("Row " + rowNum + "   Decrease until closed.");
		}
		else
		{
			label = new Label("Row " + rowNum + "   " + String.valueOf(scNum) + " sc, " + String.valueOf(incNum) + incOrDec + ", " + String.valueOf(total) + " total.");
		}
		
		label.setFont(new Font("Times New Roman", 25));

		rowInfo.getChildren().addAll(rowCompleted, label);
		return rowInfo;
	}
	
	public CheckBox getCB()
	{
		return rowCompleted;
	}
	
	private void initRowInstructions()
	{
		int scTemp = scNum;
		int incTemp = incNum;
		int segments;
		double leftover = 0;
		if (incTemp == 0)
		{
			segments = scTemp;
		}
		else
		{
			segments = scTemp/incTemp;
			leftover = scTemp%incTemp;
			if (leftover > 0 && segments == 0) {segments++;}
		}
		while (incTemp > 0 || scTemp > 0)
		{
			if (scTemp > 0)
			{
				if (incTemp == 0)
				{
					rowInstructions = rowInstructions.concat(String.valueOf((int)scTemp) + " sc, ");
					scTemp = 0;
				}
				else if (scTemp - segments > 0)
				{
					rowInstructions = rowInstructions.concat(String.valueOf((int)segments) + " sc, ");
				}
				else
				{
					rowInstructions = rowInstructions.concat(String.valueOf((int)scTemp) + " sc, ");
				}
				scTemp = scTemp - segments;
				if (segments == 0) {scTemp = 0;}
			}
			else
			{
				rowInstructions = rowInstructions.concat("         ");
			}
			if (incTemp > 0)
			{
				rowInstructions = rowInstructions.concat("1"+incOrDec);
				incTemp--;
			}
			if (rowNum == 1)
			{
				rowInstructions = rowInstructions.concat(" in magic circle.");
			}
			rowInstructions = rowInstructions.concat("\n");
			rINum++;
		}	
	}
	
	public void makeRICheckBoxes()
	{
		for (int i = 0; i < rINum; i++)
		{
			CheckBox a = new CheckBox();
			vb.getChildren().add(a);
		}
	}
	
	public VBox getCheckBoxes()
	{
		return vb;
	}
	
	public String getRowInstructions()
	{
		return rowInstructions;
	}
}
