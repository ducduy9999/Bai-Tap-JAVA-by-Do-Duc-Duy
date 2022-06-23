package SDC.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SDC.dto.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer> {
	@Query("SELECT u FROM Bill u WHERE u.buyDate >= :from")
	Page<Bill> searchByFrom(@Param("from") Date from, Pageable pageable);

	@Query("SELECT u FROM Bill u WHERE u.buyDate <= :to")
	Page<Bill> searchByTo(@Param("to") Date to, Pageable pageable);

	@Query("SELECT u FROM Bill u WHERE u.buyDate >= :from " + "AND u.buyDate <= :to")
	Page<Bill> searchByFromTo(@Param("from") Date from, @Param("to") Date to, Pageable pageable);

	@Query("SELECT u FROM Bill u JOIN u.user r " + "WHERE r.id = :rId")
	Page<Bill> searchByUser(@Param("rId") int userId, Pageable pageable);

	@Query("SELECT u FROM Bill u JOIN u.user r " + "WHERE r.id = :rId" + " AND u.buyDate >= :from "
			+ " AND u.buyDate <= :to AND u.name LIKE :x")
	Page<Bill> searchByAll(@Param("rId") int userId, @Param("from") Date from, @Param("to") Date to,
			@Param("x") String name, Pageable pageable);

	@Query("SELECT u FROM Bill u WHERE u.buyDate >= :date")
	List<Bill> searchByDate(@Param("date") Date date);

	@Query("SELECT count(b.id) AS SL , MONTH(buyDate) AS thang FROM Bill b GROUP BY MONTH(buyDate)")
	List<Object[]> thongKeTheoThang();
	
	@Query("SELECT count(b.id) AS SL , u.username AS name FROM Bill b JOIN b.user u GROUP BY u.username")
	List<Object[]> thongKeTheoNguoiMua();
	
	@Query("SELECT count(b.id) AS SL , c.couponCode AS CouponCode FROM Bill b INNER JOIN Coupon c ON b.couponCode = c.couponCode GROUP BY c.couponCode")
	List<Object[]> thongKeTheoCoupon();
}
