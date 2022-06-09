package SDC.dto;

import java.util.List;

import lombok.Data;
@Data
public class ListBillItems {
	private List<BillItems> list;
	private int totalPage;
	private int page;
	private int size;
	
}
