use QuanLiHocSinh; 

SELECT * FROM QuanLiHocSinh.class;
INSERT INTO class(id, name, course_id) 
values (1, 'lop Java 1', 1);
INSERT INTO class(id, name, course_id) 
values (2, 'lop Java 2', 1);

SELECT * FROM class 
INNER JOIN course ON class.course_id = course.id;

