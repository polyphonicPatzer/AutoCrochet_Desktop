
//Carissa Ward
//Thanks to Gary Alexander for help figuring out the equation.
import java.util.ArrayList;
import java.util.List;

public class EqPattern 
{
	String hookSize;
	String yarnWeight;
	String patternType;
	double circumference;
	
	double stitchHeight = 1;
	double stitchWidth = 1;
	
	double R; //major radius
	List<Double> initArray = new ArrayList<Double>();
	List<Double> modifiedArray = new ArrayList<Double>();
	List<Integer> stitchCounts = new ArrayList<Integer>();
	
	public EqPattern(double c, String yw)
	{
		yarnWeight = yw;
		setMeasurements();
		
		circumference = (double) c;
		R = circumference/(2*Math.PI);
		
		initArray();
		modifiedArray();
		getStitchCounts();
	}
	
	public void setMeasurements()
	{
		if (yarnWeight == "Medium")
		{
			stitchWidth = .73333333;
			stitchHeight = .615384615;
		}
		else if (yarnWeight == "Light-Medium")
		{
			stitchWidth = .615384615;
			stitchHeight = .5;
		}
	}
	
	public List<Integer> getStitchArray()
	{
		return stitchCounts;
		//return "This is a test String. Here is the result of k=.6153846154: \n" + printGrid();//output; //hook G, medium yarn
	}
	
	public void initArray()
	{
		int i = 0;
		double answer;
		while ((answer = equation(i)) > 0)
		{
			if (answer> 6){initArray.add(answer);}
			i++;
		}
	}
	
	public void modifiedArray()
	{
		for(int i = initArray.size()-1; i >=0 ; i--)
		{
			modifiedArray.add(initArray.get(i));
		}
		for(int i = 0; i < initArray.size(); i++)
		{
			modifiedArray.add(initArray.get(i));
		}
	}
	
		public void getStitchCounts()
	{
		for(int i = 0; i < modifiedArray.size(); i++)
		{
			Double stitchrowcount = modifiedArray.get(i)/stitchWidth;
			int stitchesInRow = (int) Math.round(stitchrowcount);
			stitchCounts.add(stitchesInRow);
		}
	}
		
	public double equation(int k)
	{
		double output = 2* 3.14159 * R * Math.cos((k*stitchHeight)/R);
			return output;
	}
}
