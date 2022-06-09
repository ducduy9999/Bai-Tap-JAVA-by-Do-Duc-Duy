package SDC.dto;

import java.util.List;

import lombok.Data;
@Data
public class ListUser {
	private List<User> list;
	private int totalPage;
	private int page;
	private int size;
	
}
