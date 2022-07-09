package SDC.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tăng id
	private int billId;
	
	private String couponCode;
	private int discount;
	private int totalPay;

	@Min(value = 1, message = "{bill.quantity.min}") // validation đa ngôn ngữ
	private int quantity;

	private Date buyDate;

	@NotEmpty(message = "{bill.name.notempty}")
	@Size(min = 3, message = "{bill.name.size}")
	private String name;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

//	@ManyToOne
//	@JoinColumn(name = "coupon_discountAmount")
//	private Coupon coupon;
//
//	@ManyToOne
//	@JoinColumn(name = "coupon_couponCode")
//	private Coupon coupon1;



}
