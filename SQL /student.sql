use QuanLiHocSinh;

SELECT * FROM QuanLiHocSinh.student;


DELETE FROM student WHERE student_code = 'ST2';  # để xóa bớt đi 1 thằng trùng nhau

INSERT INTO STUDENT(person_id, student_code) #kết nối quan hệ id của cột person sang id
VALUE(1, 'ST1');

INSERT INTO STUDENT(person_id, student_code) 
VALUE(2, 'ST2');

SELECT p.* , s.student_code #để ntn thì tên bảng sẽ ko bị trùng nếu để SLECT * FROM sẽ bị trùng cột
FROM STUDENT s #chú ý khi đã đặt tên viết tắt ko thể viêt theo tên bảng nữa ( trong SQL)
INNER JOIN person ON s.person_id = p.id #lệnh John là để gộp dữ liệu của 2 bảng làm 1, lệnh on là để cho 2 bảng bằng nhau
WHERE s.student_code = 'ST1'; 

SELECT * FROM STUDENT , person 
WHERE student.person_id = person.id;

SELECT *
FROM STUDENT
LEFT JOIN person ON student.person_id = person.id; #left john nghĩa là lấy cột giá trị bên trái ghép vs cột giá trị bên phải cột nào bằng nhau lấy ra

SELECT *
FROM STUDENT
RIGHT JOIN person ON student.person_id = person.id; #left john nghĩa là lấy cột giá trị bên phải ghép vs cột giá trị bên phải cột nào bằng nhau lấy ra
