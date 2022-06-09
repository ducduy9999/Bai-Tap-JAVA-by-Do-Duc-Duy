package SDC.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table
@Data
public class BillItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int billItemsId;
	
	@Min(value = 1, message = "{billitems.quantity.min}")
	private int quantity;
	
	@Min(value = 1, message = "{billitems.buyPrice.min}")
	private int buyPrice;
	
	@NotEmpty(message = "{billitems.name.notempty}")
	@Size(min = 3, message = "{billitems.name.size}")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "idBill")
	private Bill bill;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	
}
