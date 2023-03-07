
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


