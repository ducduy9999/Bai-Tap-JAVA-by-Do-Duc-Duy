package BaiTap10.Bai1;

public class Employee extends Person {
	private String viTri;
		
	
	@Override
	public void salary() {
		System.out.println("Luong cua nhan vien la: 5.000.000 VND ++");

	}


	public String getViTri() {
		return viTri;
	}


	public void setViTri(String viTri) {
		this.viTri = viTri;
	}
}
