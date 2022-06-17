SELECT * FROM QuanLiHocSinh.class_student;

INSERT INTO class_student(class_id, student_id)
VALUES(1, 1), (2, 2), (2, 1);

SELECT * FROM class c
INNER JOIN class_student cs ON cs.class_id = c.id
INNER JOIN student s ON s.person_id = cs.student_id;
#quan hệ nhiều nhiều (n-n) thì mới cần dùng bảng trung gian, quan hệ 1-n thì ko cần bảng trung gian