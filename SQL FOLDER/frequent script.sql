use my_classroom;
drop table if exists grade;
drop table if exists Assignment_Submission;
drop table if exists assignment;
drop table if exists post_comment;
drop table if exists post;
drop table if exists student2nf;
drop table if exists student;
drop table if exists class;
drop table if exists teacher;
drop table if exists  user;


CALL `my_classroom`.`CreateTables`();

SELECT * FROM grade;
SELECT * FROM assignment;
SELECT * FROM post_comment;
SELECT * FROM post;
SELECT * FROM student;
SELECT * FROM class;
SELECT * FROM teacher;
SELECT * FROM user;




insert into user values("Ashar88","Ashar","Saleem","fast123","asharsaleem55@gmail.com"
                        ,"03323328439","M","20");
insert into user values("faizan88","faizan","Saleem","faizan123","faizan@gmail.com"
                        ,"03323327777","M","20");
insert into user values("haider92","haider","Neutral","neutrality","haider@gmail.com"
                        ,"03328937577","M","24");
insert into user values("Yousuf2","Yousuf","sharif","fast1234","yousuf@gmail.com"
                        ,"03312345678","M","20");

call RegisterUser("Umer15","Umer","Hussain","Reactfast1234","Umer@gmail.com"
                        ,"03312789678","M","22",@var);                     

-- call DeleteUser("Umer15",@var);
-- call DeleteUser("Yousuf2",@var);

 call ValidateUser("Umer15", "Reactfast1234", @result);
 select @result;
                        
Call CreateClassroom("Yousuf2","DB-Fall 22","DB CS2005","890073","890073", "database class for section bse-5A",@var);
Call CreateClassroom("haider92","AP-Fall 22","DB CS2005","1254","1254", "database class for section bse-5A",@var);

 
 
 -- ****************** First verified input**********************
 
Call CreateClassroom("Yousuf2","DB-Fall 22","DB CS2005","73","73","database class for section bse-5A",@var);
Call CreateClassroom("faizan88","DB-Fall 22","DB CS2005","2743","2743" ,"database class for section bse-5A",@var);

-- Call EditClassroom(4,"faizan88","(edited) DB-Fall 22","DB CS2005","2743","(edited)database class for section bse-5A",@var);

-- Call DeleteClassroom(1,"Yousuf2", @var);

Call JoinClassroom("Yousuf2","1254",@var);
Call JoinClassroom("Ashar88","1254",@var);
Call JoinClassroom("Ashar88","73",@var);
Call JoinClassroom("Ashar88","2743",@var);


Call AllClassroomsOfStudent("Ashar88");

Call AllClassroomsOfTeacher("Ashar88");
Call AllClassroomsOfTeacher("haider92");
Call AllClassroomsOfTeacher("Yousuf2");

 -- ****************** First verified input**********************
 





 -- ****************** second verified input**********************
call CreatePost("haider92", "2", "Introduction to Database", "this is our first post for this classroom, here we will share with you the course outline",@var); 
call CreatePost("haider92", "2", "Introduction to Database", "this is our first post for this classroom",@var);
call CreatePost("haider92", "2", "lecture No:1", "this is our first lecture slides",@var);
call CreatePost("haider92", "2", "lecture No:2", "Slides are attached below",@var);


call EditPost(2,"haider92", "2", "(Duplicated)Introductio", "(Duplicated)this is our first post for this classroom",@var);
call DeletePost(2,"haider92", @var);


     -- incorrect data
call CreatePost("Ashar", "3", "Introduction to Database", "this is our first post for this classroom", @var);


call CreateAssignment("haider92", "2", "Assignment No:1",100, "2022/12/27","First Assignment here!!", @var);
call CreateAssignment("haider92", "2", "Assignment No:2",100, "2023/1/1","Second Assignment here!!", @var);
call CreateAssignment("haider92", "2", "Assignment No:3",100, "2023/5/17","Third Assignment here!!", @var);

call EditAssignment(2,"haider92", "2", "Assignment No:2",150, "2023/2/2","(Updated)Second Assignment here!!", @var);
call DeleteAssignment(2,"haider92", @var);


 -- call RemoveStudentFromClass("Yousuf2","3","Ashar88",@var );
 call ViewAllPost(2);

 -- ****************** second verified input**********************




 -- ****************** third verified input**********************


call ViewAllPost("2");
call AllStudents("2");

call GetClassData("2");

call GetStudentData("Ashar88");
call ViewAllAssignment("2");

call CommentOnPost(4,null,"haider92","Hey guyz i have missed some details, will share it in some other post",@var); 
call CommentOnPost(4,"Ashar88",null,"Alright teacher",@var); 
Select @var;
call CommentOnPost(4,"Yousuf2",null,"Please share it quick, sir.",@var); 

-- incorrect input
call CommentOnPost(1,"Yousuf2","haider","Please share it quick, sir.",@var); 

 call AllCommentsOnPost(4);


call AssignGrade(1,"Ashar88","haider92",59,@var);
call AssignGrade(1,"Yousuf2","haider92",85,@var);
call AssignGrade(1,"Yousuf2","haider92",25,@var);

call AssignGrade(3,"Yousuf2","haider92",99,@var);

call AssignGrade(2,"Yousuf2","haider92",99,@var);
call AssignGrade(2,"Yousuf2","haider92",99,@var);



call ViewGrade(1,"Yousuf2");
call ViewGrade(3,"Yousuf2");


 -- ****************** third verified input**********************

call SetDownloadLinkSubmission("http://localhost:8086/download/10", 9, @var);

call downloadFile("Ashar88", 3);


 -- ****************** third verified input**********************






Drop trigger if exists DeleteAssignment;
DELIMITER $$
CREATE TRIGGER DeleteAssignment
    Before DELETE
    ON assignment FOR EACH ROW
BEGIN
    Delete from grade g where g.assign_id = old.a_id;
	Delete from assignment_submission a where a.assign_id = old.a_id;
END$$    

DELIMITER ;




Drop trigger if exists DeletePost;
DELIMITER $$
CREATE TRIGGER DeletePost
    Before DELETE
    ON post FOR EACH ROW
BEGIN
    Delete from post_comment pc where pc.post_id = old.post_id;
END$$    

DELIMITER ;








Drop trigger if exists DeleteClass;
DELIMITER $$
CREATE TRIGGER DeleteClass
    Before DELETE
    ON class FOR EACH ROW
BEGIN
    Delete from assignment a where a.class_id = old.class_id;
    Delete from student s where s.class_id = old.class_id;
    Delete from post p where p.class_id = old.class_id;
END$$    

DELIMITER ;






Drop trigger if exists DeleteStudent;
DELIMITER $$
CREATE TRIGGER DeleteStudent
    Before DELETE
    ON student FOR EACH ROW
BEGIN
    Delete from grade g where g.std_username = old.username;
    Delete from post_comment c where c.comment_by_std_username = old.username;
    Delete from assignment_submission a where a.std_username = old.username;
END$$    

DELIMITER ;





Drop trigger if exists DeleteTeacher;
DELIMITER $$
CREATE TRIGGER DeleteTeacher
    Before DELETE
    ON teacher FOR EACH ROW
BEGIN
    Delete from class g where g.username = old.username;
    Delete from post_comment c where c.comment_by_teacher_username = old.username;
END$$    

DELIMITER ;





Drop trigger if exists DeleteUser;
DELIMITER $$
CREATE TRIGGER DeleteUser
    Before DELETE
    ON user FOR EACH ROW
BEGIN
    Delete from student s where s.username = old.username;
    Delete from teacher t where t.username = old.username;
END$$    

DELIMITER ;


