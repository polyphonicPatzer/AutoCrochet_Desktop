import java.util.List;

public class HatTest{
	public static void main(String[] args) throws Exception{
		Hat h = new Hat(40, "Medium");


		int count2 = 0;

		List<Double> initArray = h.initArray;
		System.out.println("initArray");
		for (Double d : initArray){
			System.out.println(count2 + " " + d);
			count2++;
		}

		List<Double> modifiedArray = h.modifiedArray;
		System.out.println("\nmodifiedArray");
		int count = 0;
		for (Double d : modifiedArray){
			System.out.println(count + " " + d);
			count++;
		}

		System.out.println("\nstitchCounts");
		List<Integer> stitchCounts = h.stitchCounts;
		int count3 = 0;
		for (Integer i : stitchCounts){
			System.out.println(count3 + " " + i);
			count3++;
		}
	}
}