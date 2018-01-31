//Carissa Ward


public class Sphere extends EqPattern
{

	public Sphere(double c, String yw)
	{
		super(c, yw);
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
	
}
