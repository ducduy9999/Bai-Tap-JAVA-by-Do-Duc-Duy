SELECT * FROM BaiTapCoBan.tiem_sach;
#gộp 2 
SELECT *
FROM book
INNER JOIN tiem_sach ON tiem_sach.book_id = book.id