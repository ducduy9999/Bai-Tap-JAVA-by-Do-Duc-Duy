SELECT * FROM QuanLiHocSinh.preson;
#index là câu lệnh giúp tìm kiếm nhanh hơn
SELECT * FROM

SELECT COUNT(*) AS 'SL', AGE FROM person GROUP BY age
) temp WHERE temp.SL > 1;
SELECT SL, Year FROM
#tương đương
SELECT COUNT(*) AS 'SL', AGE FROM person
WHERE person.name = 'A'
GROUP BY age HAVING SL > 1; #câu lệnh having áp dụng cho bảng select đc, còn where sẽ áp dụng cho bảng gốc ( person)
# UNION ALL lệnh gộp 2 bảng


SELECT SL, Year FROM
(
SELECT COUNT(*) AS 'SL', year(birthdate) AS 'Year' FROM person
GROUP BY year(brithdate)
) T WHERE SL > 1;