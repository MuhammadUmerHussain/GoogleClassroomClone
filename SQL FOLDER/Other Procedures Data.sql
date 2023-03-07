use my_classroom;

Drop procedure if exists CreateClassroom;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `CreateClassroom`(
IN TeacherUsername varchar(35),
IN name varchar(50),
IN title varchar(50),
IN code varchar(50),
IN unique_code varchar(50),
IN descript varchar(150),
OUT QueryResult Boolean
)
COMMENT 'Creating Classroom by the teacher'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
		 Select count(*) into flag from user where user.username = TeacherUsername;
		 if flag = 0 then
            Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again
		
                               -- logic here--
                               
	select 2;
		  Select count(*) into flag from teacher where teacher.username = TeacherUsername;
          if flag = 0 then
              Insert into Teacher(`username`) values(TeacherUsername);
		  end if; set flag = 0;

	select 3 ;
		INSERT INTO class
		(`username`,`class_name`,`course_title`,`Course_Code`,`Unique_Class_code`,`Date_created`,`descript`)
		values (TeacherUsername ,name, title, code, unique_code, now(), descript);
	
    Set QueryResult = True;
       
    COMMIT;
	END ;;
DELIMITER ;




Drop procedure if exists EditClassroom;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `EditClassroom`(
IN class_id__ int,
IN TeacherUsername varchar(35),
IN name varchar(50),
IN title varchar(50),
IN code varchar(50),
IN descript varchar(150),
OUT QueryResult Boolean
)
COMMENT 'Edit Classroom by the teacher'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
		 Select count(*) into flag from user where user.username = TeacherUsername;
		 if flag = 0 then
            Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again
		
                               -- logic here--
                               
	select 2;
		  Select count(*) into flag from teacher where teacher.username = TeacherUsername;
          if flag = 0 then
                Insert into Teacher(`username`) values(TeacherUsername);
		  end if; set flag = 0;

	select 3 ;
		UPDATE `my_classroom`.`class` SET 
		`username` =  TeacherUsername, `class_name` = name, `course_title` = title
        ,`Course_Code` = code, `descript` = Descript
		 WHERE `class_id` = class_id__;

    Set QueryResult = True;
       
    COMMIT;
	END ;;
DELIMITER ;






Drop procedure if exists DeleteClassroom;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `DeleteClassroom`(
IN class_id__ int,
IN TeacherUsername varchar(35),
OUT QueryResult Boolean
)
COMMENT 'Delete Classroom by the teacher'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
     Select 1;
		 Select count(*) into flag from class where username = TeacherUsername and class_id = class_id__;
		 if flag = 0 then
            Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again
		
                               -- logic here--
				
	select 3 ;
			Delete from class where class_id = class_id__;
    
    Set QueryResult = True;
       
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists RegisterUser;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `RegisterUser`(
IN username varchar(35),
IN f_name varchar(50),
IN l_name varchar(50),
IN user_password varchar(50),
IN email varchar(50),
IN phone_no varchar(50),
IN gender varchar(1),
IN age int,
OUT QueryResult Boolean
)
COMMENT 'Creating/Inserting Users after registration'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
             
                    -- user already exists
		 Select count(*) into flag from user u 
         where u.username = username;
		 if flag != 0 then
            Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again

					-- logic here
		INSERT INTO `my_classroom`.`user` (`username`,`f_name`,`l_name`,`user_password`,`email`,
	    `phone_no`,`gender`,`age`)
       VALUES
         (username,f_name,l_name,user_password,email,phone_no,gender,age);

    Set QueryResult = True;
       
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists ValidateUser;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `ValidateUser`(
IN username varchar(35),
IN password varchar(50),
OUT QueryResult Boolean
)
COMMENT 'Creating/Inserting Users after registration'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;

		 Select count(*) into flag from user u 
         where u.username = username and u.user_password = password;
		 if flag = 0 then
            Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again
		
    Set QueryResult = True;
       
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists getThePasswordFromApi;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `getThePasswordFromApi`(
IN username varchar(100),
OUT password__ varchar(100)
)
COMMENT 'Need the Password for encryption purpose'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error";
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; 
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;

      Set password__ = null;
      Select user_password into password__ from user where user.username = username;
       
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists DeleteUser;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `DeleteUser`(
IN username varchar(35),
OUT QueryResult Boolean
)
COMMENT 'Deleting the Users'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    Select 1;
		Delete from user where user.username = username;
    
    Set QueryResult = True;
       
    COMMIT;
	END ;;
DELIMITER ;









Drop procedure if exists IsTeacherOfaClass;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `IsTeacherOfaClass`(
IN class_id__ int,
IN TeacherUsername varchar(35),
OUT QueryResult Boolean
)
COMMENT 'Is Teacher the Actual Teacher of the Classroom.'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    Select 1;
		 Select count(*) into flag from class where username = TeacherUsername and class_id = class_id__;
		 if flag = 0 then
            Set QueryResult = false;
			leave sp;
		 else 
             Set QueryResult = True;
		 end if;  set flag = 0;   -- for using it again		

    COMMIT;
	END ;;
DELIMITER ;


Drop procedure if exists IsStudentOfaClass;
DELIMITER ;;

CREATE  DEFINER=`root`@`localhost` PROCEDURE `IsStudentOfaClass`(
IN class_id__ int,
IN stdUsername varchar(35),
OUT QueryResult Boolean
)
COMMENT 'Is Student actually enrolled the Classroom.'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    Select 1;
		 Select count(*) into flag from class c join student s on
         c.class_id = s.class_id 
         where s.username = stdUsername and c.class_id = class_id__;
         
		 if flag = 0 then
            Set QueryResult = false;
			leave sp;
		 else 
             Set QueryResult = True;
		 end if;  set flag = 0;          -- for using it again		

    COMMIT;
	END ;;
DELIMITER ;






Drop procedure if exists JoinClassroom;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `JoinClassroom`(
IN stdUsername varchar(35),
IN unique_class_code varchar(50),
OUT QueryResult Boolean
)
COMMENT 'Join Classroom by the Student'
sp: BEGIN
     declare flag int; declare class_id__ int; declare teacherUsername varchar(35);
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
		 Select count(*) into flag from user where user.username = stdUsername;
			if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
			 end if;  set flag = 0;   -- for using it again  
	select 2;
      
		 Select count(*),class_id, username into flag, class_id__ ,teacherUsername from class
             where class.unique_class_code = unique_class_code;
		
        if flag = 0 or teacherUsername = stdUsername then
			    Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again         
	select 3;
         
                                  -- logic here -- 
                                 
	select 4;
		 --  select class_id into var_id from class where class.unique_class_code = unique_class_code;
		  Insert into student values(stdUsername,class_id__,null,now());

		Set QueryResult = true;
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists GetUniqueCodeOfClass;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `GetUniqueCodeOfClass`(
IN  class_id varchar(50),
Out uniqueCode varchar(15)
)
COMMENT 'Get Unique Code Of the Class'
sp: BEGIN
		Set uniqueCode = "-1";
        Select Unique_Class_code into uniqueCode from class where class.class_id =  class_id;
    COMMIT;
	END ;;
DELIMITER ;








Drop procedure if exists AllClassroomsOfTeacher;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `AllClassroomsOfTeacher`(
IN username varchar(35)
)
COMMENT 'Get All Classrooms Of the specified Teacher'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error";
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning";
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- logic here--
	     Select c.* from class c join teacher t on t.username = c.username 
         where t.username = username;
      
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists AllClassroomsOfStudent;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `AllClassroomsOfStudent`(
IN username varchar(35)
)
COMMENT 'Get All Classrooms Of the specified Student'
sp: BEGIN
     declare flag int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error";
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning";
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- logic here--
	     Select c.* from class c join student s on s.class_id  = c.class_id 
         where s.username = username;
      
    COMMIT;
	END ;;
DELIMITER ;








Drop procedure if exists CreatePost;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `CreatePost`(
IN teacherUsername varchar(35),
IN class_id varchar(50),
IN title varchar(50),
IN descript varchar(300),
OUT QueryResult Boolean
)

COMMENT 'post created by the teacher'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
		Select count(*) into flag from class where class.class_id = class_id and class.username = teacherUsername;
		 if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again
	select 2;      
		
                         -- logic here--
        
	select 3;
		INSERT INTO post (`class_id`,`title`,`descript`,`dateCreated`)
		   VALUES (class_id, title, descript, now());

         Set QueryResult = True;      
    COMMIT;
	END ;;
DELIMITER ;






Drop procedure if exists EditPost;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `EditPost`(
IN post_id int,
IN teacherUsername varchar(35),
IN class_id varchar(50),
IN title varchar(50),
IN descript varchar(300),
OUT QueryResult Boolean
)

COMMENT 'post edited by the teacher'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
		Select count(*) into flag from class where class.class_id = class_id and class.username = teacherUsername;
		 if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again
	select 2;      
		
                         -- logic here--
        
	select 3;
       UPDATE  post SET	`title` = title,`descript` = descript  WHERE post.post_id = post_id;
 
      Set QueryResult = True;
      
    COMMIT;
	END ;;
DELIMITER ;


Drop procedure if exists DeletePost;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `DeletePost`(
IN post_id int,
IN teacherUsername varchar(35),
OUT QueryResult Boolean
)

COMMENT 'post deleted by the teacher'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
    
         Select count(*) into flag from post p join class c on p.class_id = c.class_id
         where p.post_id = post_id and c.username = teacherUsername;
		 if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again
	select 2;      
		
                         -- logic here--
        
	select 3;

       Delete From  `my_classroom`.`post` WHERE post.`post_id` = post_id;
      Set QueryResult = True;
      
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists CreateAssignment;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `CreateAssignment`(
IN teacherUsername varchar(35),
IN class_id varchar(50),
IN title varchar(50),
IN totalMarks int,
IN due_date varchar(30),
IN descript varchar(300),
OUT QueryResult Boolean
)
COMMENT 'Assignment created by the teacher'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
     Declare date_ datetime;
     
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
     
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
		Select count(*) into flag from class where class.class_id = class_id and class.username = teacherUsername;
		 if flag = 0 then
				Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again
	select 2;      
		
        
                              -- logic here--
        
	select 3;
   select convert(CAST(due_date AS DATE), datetime) into date_;
	select date_;
		INSERT INTO assignment
        (`class_id`,`a_title`,`total_marks`,`Date_created`,`due_date`,`descript`)
        values
        (class_id, title,totalMarks, now(), date_ ,descript);

  Set QueryResult = True;
	   
    COMMIT;
	END ;;
DELIMITER ;



Drop procedure if exists EditAssignment;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `EditAssignment`(
IN assignment_id int,
IN teacherUsername varchar(35),
IN class_id varchar(50),
IN title varchar(50),
IN totalMarks int,
IN due_date varchar(30),
IN descript varchar(300),
OUT QueryResult Boolean
)
COMMENT 'Assignment edited by the teacher'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
     Declare date_ datetime;
     
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
     
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
		Select count(*) into flag from class where class.class_id = class_id and class.username = teacherUsername;
		 if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again
	select 2;      
		
        
                              -- logic here--
        
	select 3;
	select convert(CAST(due_date AS DATE), datetime) into date_;

		Update `my_classroom`.`assignment` set
			`a_title` = title, `total_marks` = totalMarks,
			`due_date` = date_, `descript` = descript
         where assignment.a_id = assignment_id;

  Set QueryResult = True;
  
    COMMIT;
	END ;;
DELIMITER ;



Drop procedure if exists DeleteAssignment;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `DeleteAssignment`(
IN assignment_id int,
IN teacherUsername varchar(35),
OUT QueryResult Boolean
)
COMMENT 'Assignment deleted by the teacher'
sp: BEGIN
     DECLARE flag int;
     
	 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
		Select count(*) into flag from class c join assignment assign on 
        c.class_id = assign.class_id 
        where assign.a_id = assignment_id and c.username = teacherUsername;
        
		 if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
		 end if;  set flag = 0;   -- for using it again
	select 2;      
		
        
                              -- logic here--
        
	select 3;
         Delete from assignment where a_id = assignment_id;
  
  Set QueryResult = True;
    COMMIT;
	END ;;
DELIMITER ;








Drop procedure if exists RemoveStudentFromClass;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `RemoveStudentFromClass`(
IN teacherUsername varchar(35),
IN class_id varchar(50),
IN stdUsername varchar(35),
OUT QueryResult Boolean
)
COMMENT 'Remove Student From Class by the teacher'
sp: BEGIN
     DECLARE flag int;
 DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    							-- Verification--
	select 1;
		Select count(*) into flag from class c join student s on s.class_id = c.class_id 
        where c.class_id = class_id and c.username = teacherUsername and s.username = stdUsername;
		 if flag = 0 then
			leave sp;
		 end if;  set flag = 0;   -- for using it again
    select 2;     
    
							-- logic here--
                            
         delete from Student s where s.class_id = class_id and s.username = stdUsername;
 
	Set QueryResult = True;
		
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists ViewAllPost;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `ViewAllPost`(
IN class_id varchar(50)
)
COMMENT 'All posts to be viewed in the classroom'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	-- select 1;
		Select count(*) into flag from class where class.class_id = class_id;
        if flag = 0 then
			leave sp;
		 end if;  set flag = 0;   -- for using it again select 2;      
		
                         -- logic here--
                         
          Select p.* from class c join post p on c.class_id = p.class_id 
          where c.class_id = class_id order by p.dateCreated desc;
          
    COMMIT;
	END ;;
DELIMITER ;



Drop procedure if exists ViewAllAssignment;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `ViewAllAssignment`(
IN class_id varchar(50)
)
COMMENT 'All posts to be viewed in the classroom'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	-- select 1;
		Select count(*) into flag from class where class.class_id = class_id;
        if flag = 0 then
			leave sp;
		 end if;  set flag = 0;   -- for using it again select 2;      
		
                         -- logic here--
                         
          Select p.* from class c join assignment p on c.class_id = p.class_id 
          where c.class_id = class_id order by p.Date_created desc;
          
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists AllStudents;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `AllStudents`(
IN class_id int
)
COMMENT 'All Students of the class'
sp: BEGIN
     DECLARE flag int; 
     DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	-- select 1;
		Select count(*) into flag from class where class.class_id = class_id;
        if flag = 0 then
			leave sp;
		 end if;  set flag = 0;   -- for using it again	
    -- select 2; 
    
                         -- logic here--
                         
          Select s.* from class c join student s on c.class_id = s.class_id and s.class_id= class_id;
          
    COMMIT;
	END ;;
DELIMITER ;














Drop procedure if exists CommentOnPost;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `CommentOnPost`(
IN post_id int,
IN by_student varchar(35),
IN by_Teacher varchar(35),
IN comment_text varchar(255),
OUT QueryResult Boolean
)
COMMENT 'comment insert on the post'
sp: BEGIN
     DECLARE flag int; 
     DECLARE teacherUsername varchar(35); DECLARE class_id__ int;
	 
     DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
    
    Select count(*), c.username, c.class_id into flag, teacherUsername, class_id__ 
        from post p join class c  on p.class_id = c.class_id
        where p.post_id = post_id;

        if flag = 0 then
           Set QueryResult = false;
           leave sp; 
		end if;  set flag = 0;   -- for using it again
	
    
								-- logic --
    select 2;
	    if by_student is not null and by_teacher is not null then   -- one user can comment at one time.
           Set QueryResult = false;
           leave sp; 
	    end if;              

     select 3;   
    	if by_student is not null  then
							-- student enrolled in the class or not.
			select count(*) into flag from class c join student s on c.class_id = s.class_id
            where c.class_id = class_id__ and s.username = by_student;
			if flag = 0 then
			    Set QueryResult = false;
				leave sp; 
			 end if;  set flag = 0;   -- for using it again  
             
             INSERT INTO post_comment
            (`post_id`,`comment_time`,
            `comment_text`,`comment_by_std_username`,`comment_by_teacher_username`)
            VALUES(post_id, now(), comment_text, by_student,null);
         end if;
         
	  select 4;
    	if by_teacher is not null  then
		   select if(by_teacher = teacherUsername, 1, 0) into flag;
		   if  flag = 0  then    -- valid teacher username be provided.
			    Set QueryResult = false;
				leave sp;
			end if;
		
            INSERT INTO post_comment
            (`post_id`,`comment_time`,
            `comment_text`,`comment_by_teacher_username`)
            VALUES(post_id, now(), comment_text, teacherUsername);
	    end if;
        
    Set QueryResult = True;
    
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists AllCommentsOnPost;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `AllCommentsOnPost`(
IN post_id__ int
)
COMMENT 'All Comments on a post in the class'
sp: BEGIN
     DECLARE flag int; 
     DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	-- select 1;
--		Select count(*) into flag from post where post.post_id = post_id__;
--         if flag = 0 then
-- 			leave sp;
-- 		 end if;  set flag = 0;   -- for using it again	
 --   select 2; 
    
                         -- logic here--
                         
          Select pc.* from post p join post_comment pc on pc.post_id = p.post_id 
          where pc.post_id = post_id__
          order by pc.comment_time asc;
          
    COMMIT;
	END ;;
DELIMITER ;







Drop procedure if exists AssignGrade;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `AssignGrade`(
IN assignment_id varchar(35),
IN stdUsername varchar(35),
IN teacherUsername varchar(35),
IN MarksObt int,
OUT QueryResult Boolean
)
COMMENT 'Assign Grade by the teacher to a specific Student'
sp: BEGIN
     DECLARE flag int; DECLARE flag2 int;
     DECLARE class_id__ int;
     
     DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	select 1;
    
		Select count(*), c.class_id into flag, class_id__
        from assignment assign join class c 
        on assign.class_id = c.class_id 
        where assign.a_id = assignment_id and c.username = teacherUsername;    
        
		 if flag = 0 then
			Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again
	
    
    select 2;  -- checking if student enrolled in the class.
        select count(*) into flag from student 
        where class_id = class_id__ and username = stdUsername;
		
        if flag = 0 then
			Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again
         
        
                              -- logic here--
        
	select 3;
	select count(*) into flag2 from grade
	where std_username = stdUsername and assign_id = assignment_id;

	select "3*";
		if flag2 = 0 then
			select "4*";
			INSERT INTO `my_classroom`.`grade`
			(`std_username`,`assign_id`,`marks_obtained`)
			VALUES (stdUsername, assignment_id, MarksObt);
		else 
			select "4**";
			update `my_classroom`.`grade`        -- 
			set marks_obtained = MarksObt 
			where std_username = stdUsername and assign_id = assignment_id; 
		
		end if;  set flag2 = 0;   -- for using it again
    
			Set QueryResult = True;

    COMMIT;
	END ;;
DELIMITER ;












Drop procedure if exists ViewGrade;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `ViewGrade`(
IN assignment_id varchar(35),
IN stdUsername varchar(35)
)
COMMENT 'View Grade by the student or Teacher'
sp: BEGIN
     DECLARE flag int; DECLARE flag2 int;
     DECLARE class_id__ int; Declare teacherUsername varchar(35);
     
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
--    --  select 1; 
		Select count(*) into flag from grade
        where assign_id = assignment_id and std_username = stdUsername;              
		 if flag = 0 then
         
             	Select * from grade
        where assign_id = assignment_id and std_username = stdUsername; 
         
			leave sp;
		 end if;  set flag = 0;   -- for using it again

	-- select 2;           
    	Select * from grade
        where assign_id = assignment_id and std_username = stdUsername;          
	   
    COMMIT;
	END ;;
DELIMITER ;




Drop procedure if exists GetTeacherUsernameFromClassId;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `GetTeacherUsernameFromClassId`(
IN class_id int,
OUT username_ varchar(35)
)
COMMENT 'All posts to be viewed in the classroom'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
	-- select 1;
		Select count(*),username into flag, username_ from class where class.class_id = class_id;
        if flag = 0 then
			leave sp;
		 end if;  set flag = 0;   -- for using it again select 2;      
          
    COMMIT;
	END ;;
DELIMITER ;



Drop procedure if exists GetStudentData;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `GetStudentData`(
IN username varchar(35)
)
COMMENT 'Get the Student Data'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    
		Select username, f_name, l_name, email, phone_no, gender, age from user
        where user.username = username;
    
    
    COMMIT;
	END ;;
DELIMITER ;




Drop procedure if exists GetTeacherData;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `GetTeacherData`(
IN username varchar(35)
)
COMMENT 'Get the Teacher Data'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    
		Select username, f_name, l_name, email, phone_no, gender, age from user
        where user.username = username;
    
    
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists GetClassData;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `GetClassData`(
IN class_id int
)
COMMENT 'Get the Class Data'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    
		Select * from class
        where class.class_id = class_id;
    
    
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists UploadAndSubmitAssignment	;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `UploadAndSubmitAssignment`(
IN stdUsername varchar(35),
In assignmentId int,
IN FileName varchar(100),
IN FileSize varchar(100),
IN FileContentType varchar(100),
In External_File LONGBLOB,
OUT QueryResult Boolean,
OUT Id varchar(35)
)
COMMENT 'UploadAndSubmitAssignment'
sp: BEGIN
     DECLARE flag int; 	 
	 DECLARE class_id__ int;
    
    DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
  set id = -10;                  
                    -- does assignment exist or not.
		Select count(*),class_id into flag,class_id__ from assignment a where a.a_id = assignmentId;
        if flag = 0 then
			Set QueryResult = false;
			leave sp;
		 end if;  set flag = 0;   -- for using it again 
  
  set id = -2;
					-- is student of the class
         Call IsStudentOfaClass(class_id__, stdUsername, @flag2);
         if @flag2 = 0 then
			Set QueryResult = false;
			leave sp;
		 end if;  
         
   set id = -3;
   
   
                          -- Logic Here --
	
	INSERT INTO `my_classroom`.`assignment_submission`
		(`std_username`,`assign_id`, `FileName`, `FileSize`,`Data_submitted`,`FileContentType`,`External_File`)
		VALUES(stdUsername, assignmentId, FileName, FileSize,now(), FileContentType, External_File);

        SELECT LAST_INSERT_ID() into Id;
		 
        Set QueryResult = true;
        
    COMMIT;
	END ;;
DELIMITER ;






Drop procedure if exists SetDownloadLinkSubmission	;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `SetDownloadLinkSubmission`(
IN downloadURl varchar(100),
In submission_Id int,
OUT QueryResult Boolean
)
COMMENT 'SetDownloadLinkSubmission'
sp: BEGIN
     DECLARE flag int; 	 
     DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
                         -- Verification here--
 	select 1;

    UPDATE assignment_submission s SET s.DownloadUrl = downloadURl
    WHERE s.submission_id = submission_Id;
        Set QueryResult = true;
Select 2;
        
    COMMIT;
	END ;;
DELIMITER ;









Drop procedure if exists downloadFile;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `downloadFile`(
IN stdUsername varchar(35),
In assignmentId int
)
COMMENT 'Download the file'
sp: BEGIN
     DECLARE flag int; DECLARE var_id int;
	 DECLARE exit handler for sqlexception
	   BEGIN
		  select "error"
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
         select "warning"
	  ROLLBACK;
	 END;

	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    
		Select * from assignment_submission s
        where s.std_username = stdUsername and s.assign_id = assignmentId;
    
    
    COMMIT;
	END ;;
DELIMITER ;





Drop procedure if exists isSubmissionAvaliable;
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `isSubmissionAvaliable`(
IN stdUsername varchar(35),
In assignmentId int,
OUT QueryResult Boolean
)
COMMENT 'Checking if the Submission Avaliable or not'
sp: BEGIN
     DECLARE flag int; 	 
     DECLARE exit handler for sqlexception
	   BEGIN
         select "error"; Set QueryResult = false;
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         select "warning"; Set QueryResult = false;
	  ROLLBACK;
	 END;
	START TRANSACTION;
    SET FOREIGN_KEY_CHECKS=0;
    
    
		Select count(*) into flag from assignment_submission s
        where s.std_username = stdUsername and s.assign_id = assignmentId;
    
		if flag = 0 then
			Set QueryResult = false;
			leave sp;
		end if;  set flag = 0;   -- for using it again 
    
      Set QueryResult = True;
    
    COMMIT;
	END ;;
DELIMITER ;
