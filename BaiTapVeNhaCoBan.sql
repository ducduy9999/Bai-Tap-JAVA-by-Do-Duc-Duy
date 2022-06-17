use BaiTapCoBan;

#liệt kê các loại sach
SELECT * FROM BaiTapCoBan.book;

#Thêm data
INSERT INTO book( id, name, nam_san_xuat) VALUES 
(1, 'SACH_TOAN', 2000),
(2, 'SGK', 2003),
(3, 'SACH_TIENG_ANH', 2006),
(4, 'SACH_VAT_LI', 2007),
(5, 'SACH_KHOA_HOC', 2002);

#sửa data
UPDATE book
SET name = 'SACH_VAN'
WHERE name = '3';

#xóa data
DELETE FROM book WHERE id = 4 OR id = 5;

#tim kiếm loại sách
SELECT * FROM book WHERE name LIKE '%A%';   #tìm kiếm loại sách có chữ A trong tên
SELECT * FROM book WHERE name LIKE 'A%';  #tìm kiếm loại sách có chữ A ở đầu
SELECT * FROM book WHERE name LIKE '%A';  #tìm kiếm các loại sách có chữ A ở cuối
SELECT * FROM book WHERE name LIKE 'SACH_TOAN'; #tìm kiếm đúng tên sách
SELECT * FROM book WHERE nam_san_xuat >= 2003 AND nam_san_xuat <= 2006;
SELECT MAX(nam_san_xuat) FROM book; 
SELECT MIN(nam_san_xuat) FROM book;
SELECT * FROM book WHERE nam_san_xuat IN(2003,2006,2007); 
#Không cho tìm kiếm 
SELECT * FROM book WHERE not name = 'SGK';
#tìm kiếm tên 
SELECT * FROM book WHERE name in ('SGK', 'SACH_TOAN');

