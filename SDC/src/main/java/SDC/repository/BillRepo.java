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

	@Query("SELECT count(b.id) as SL , MONTH(buyDate) as Thang FROM Bill b GROUP BY MONTH(buyDate)")
	List<Object[]> thongKeTheoThang();
	
//	@Query("SELECT count(b.id) as SL , BUYER(buyDate) as Thang FROM Bill b GROUP BY BUYER(buyDate)")
//	List<Object[]> thongKeSoLuong();
}
