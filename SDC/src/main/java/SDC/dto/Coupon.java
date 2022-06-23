package SDC.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String couponCode;
	
	@NotEmpty(message = "{coupon.name.notempty}")
	@Size(min = 3, message = "{coupon.name.size}")
	private String name;
	
	private int discountAmount;
	private Date expiredDate;


}
