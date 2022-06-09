//package SDC.dto;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimeZone;
//
//import lombok.Data;
//
//@Data
//public class CouponDTO {
//	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	
//	private Long id;
//
//	private String couponCode;
//
//	private String name;
//
//	private String discountAmount;
//
//	private String expiredDate;
//
//	private String createDateCoupon;
//	
//	public Date getSubmissionDateConverted(String timezone) throws ParseException {
//		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
//		return dateFormat.parse(this.expiredDate);
//	}
////	 public void setSubmissionDate(Date date, String timezone) {
////	        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
////	        this.discountAmount = dateFormat.format(date);
////	    }
////	 public void setSubmissionDate1(Date date, String timezone) {
////	        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
////	        this.expiredDate = dateFormat.format(date);
////	    }
//
////	public CouponDTO(Coupon original) {
////		this.couponCode = original.getCouponCode();
////		this.name = original.getName();
////		this.discountAmount = original.getDiscountAmount();
////		this.expiredDate = original.getExpiredDate();
////	}
//}
