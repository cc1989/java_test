package test_arguments;

public class Arguments {
	public int[] test(int... vt){
		int[] temp = new int[vt.length];
		for (int i = 0; i < vt.length; i++)
			temp[i] = vt[i];
		return temp;
	}
}
