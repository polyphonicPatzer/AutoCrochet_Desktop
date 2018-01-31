//Carissa Ward

import java.util.ArrayList;
import java.util.List;

public class Pattern 
{
	Double cir;
	String p;
	String yarnWidth;
	List<Row> patternInfo = new ArrayList<Row>();
	
	public Pattern(String pattern, Double circumference, String yw)
	{
		cir = circumference;
		p = pattern;
		yarnWidth = yw;
	}
	
	//public ArrayList<Row> getPattern()
	public List<Row> getPattern()
	{
		//loop through getStitchArray() return
		//make a Row object for each int
		//return array of Row Objects
		
		if (p == "Ball")
		{
			Sphere ball = new Sphere(cir, yarnWidth);
			List<Integer> rowArray = ball.getStitchArray();
			
			patternInfo.add(new Row(0, rowArray.get(0), 1));
			for (int i = 1; i < rowArray.size(); i++)
			{
				patternInfo.add(new Row(rowArray.get(i-1), rowArray.get(i), i+1));
			}
			patternInfo.add(new Row(rowArray.get(rowArray.size()-1), 0, rowArray.size()+1));
			
			return patternInfo;
		}
		else if (p == "Hat")
		{
			Hat hat = new Hat(cir, yarnWidth);
			List<Integer> rowArray = hat.getStitchArray();
			
			patternInfo.add(new Row(0, rowArray.get(0), 1)); //adds 0 and first
			for (int i = 1; i < rowArray.size(); i++)
			{
				patternInfo.add(new Row(rowArray.get(i-1), rowArray.get(i), i+1));
			}
			int lastI = rowArray.size()-1;
			patternInfo.add(new Row(rowArray.get(lastI-1), rowArray.get(lastI), lastI+2));
			
			return patternInfo;
		}
		else 
		{
			return null;
		}
	}
}
