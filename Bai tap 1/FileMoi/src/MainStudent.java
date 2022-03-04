
public class MainStudent {

	public static void main(String[] args) {
		Studen st1 = new Studen();
		Studen st2 = new Studen();
		Studen st3 = new Studen(); // tao doi tuong sutden
		// n Studen
		int[] numbers = new int[3];
		// number[0] ... number[3 - 1]

		Studen[] studentArray = new Studen[5]; // tao doi tuong mang chua studen

		studentArray[0] = st1; //
		studentArray[1] = new Studen();

		//

		for (int i = 0; i < studentArray.length; i++) {
			studentArray[i] = new Studen(); //
			studentArray[i].input();
		}

		for (int i = 0; i < studentArray.length; i++) {
			studentArray[i].info();
		}

	}

}
