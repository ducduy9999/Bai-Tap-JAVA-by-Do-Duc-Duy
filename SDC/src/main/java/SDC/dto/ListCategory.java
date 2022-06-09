package SDC.dto;

import java.util.List;

import lombok.Data;

@Data
public class ListCategory {
	private List<Category> list;
	private int totalPage;
	private int page;
	private int size;
	
}
