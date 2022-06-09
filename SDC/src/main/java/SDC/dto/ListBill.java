package SDC.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class ListBill {
	private List<Bill> list;
	private int totalPage;
	private int page;
	private int size;
	private String fromDate;
	private String toDate;
}
