SELECT * FROM person;
select now(); #now la hàm sẵn ở SQL lấy giờ hiện tai

INSERT INTO person(id, name, age, brithdate) 
VALUES  (1, 'Nguyễn Văn A', 20, '2000-10-10' );
 
INSERT INTO person(id, name, age, brithdate)
VALUES  (2, 'Nguyễn Văn B', 14, '2001-2-6' );

INSERT INTO person(id, name, age, brithdate)
VALUES  (3, 'Nguyễn Văn C', 26, '2004-5-6' );

update person set name = 'C', age = 20 WHERE id = 3;
delete from person where id = 3;
----------------------------------
SELECT * FROM person ORDER BY age ASC, name DESC; #Hàm tăng dần giảm dần
SELECT name, age FROM person;
SELECT * FROM person WHERE age = 10 ORDER BY name ASC; #sap xep tăng dần

SELECT * FROM person WHERE name LIKE '%x%';  #hàm để tìm tên có chữ .... 
SELECT * FROM person WHERE name LIKE 'T%' ;   #hàm để tìm chữ T đầu tiên
SELECT * FROM person WHERE name LIKE '%A' ;   #hàm để tìm kết thúc bằng chữ A
SELECT * FROM person WHERE name LIKE 'A';  #hàm để tìm chữ bằng A
SELECT * FROM person WHERE name = 'Trinh Xuan B' ;
SELECT * FROM person WHERE age >= 10 AND age <= 20; 
SELECT * FROM person WHERE age BETWEEN 10 AND 20; #ham tim kiếm trong khoảng
SELECT * FROM person WHERE brithdate = '2000-10-10';
SELECT MAX(age) FROM person;
SELECT MIN(age) FROM person;
SELECT SUM(age) FROM person; #trả về giá trị tổng của 1 cột, 1 tập hợp dữ liệu hoặc một biểu thức.
SELECT COUNT(*) AS 'SL' FROM person; #dùng để đếm số lượng
SELECT * FROM person WHERE year(brithdate) > 2005;

SELECT COUNT(*) AS 'SL', age FROM person group by age; #ham tinh tong

SELECT COUNT(*) AS 'SL' FROM person;

SELECT COUNT(*) AS 'SL', year(brithdate) AS 'Nam Sinh' FROM person group by year(brithdate); 

SELECT * FROM person WHERE age IN(5,10,15); #liệt kê trong tập hợp
SELECT * FROM person WHERE age = (SELECT MAX(age) FROM person ); #liệt kê thằng lớn nhất, nhỏ nhất thì dùng MIN



