package BaiTap10.Bai1;

public class Boss extends Person {
	private String Boss;
	@Override
	public void salary() {
		System.out.println("Luong cua boss la: 10.000.000 VND + trach nhiem va hoa hong");


	}
	public String getBoss() {
		return Boss;
	}
	public void setBoss(String boss) {
		Boss = boss;
	}
}
