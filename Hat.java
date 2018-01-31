//Carissa Ward

public class Hat extends EqPattern
{

	public Hat(double c, String yw)
	{
		super(c, yw);
	}
	
	public void modifiedArray()
	{
		for(int i = initArray.size()-1; i >=0 ; i--)
		{
			if (i % 2 == 1) {
				modifiedArray.add(initArray.get(i));
			}
		}
		
		for(int i = 0; i < initArray.size(); i++)
		{
			modifiedArray.add(initArray.get(0));
		}
	}
}