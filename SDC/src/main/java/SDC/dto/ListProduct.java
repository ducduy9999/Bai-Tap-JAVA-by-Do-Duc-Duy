package SDC.dto;

import java.util.List;

import lombok.Data;
@Data
public class ListProduct {
	private List<Product> list;
	private int totalPage;
	private int page;
	private int size;
}
