use my_classroom;
Drop procedure CreateTables;
 
DELIMITER ;;
CREATE  DEFINER=`root`@`localhost` PROCEDURE `CreateTables`()
DETERMINISTIC
COMMENT 'Creating all necessary tables'
BEGIN

	 DECLARE exit handler for sqlexception
	   BEGIN
		 Select "error";
	   ROLLBACK;
	 END;
	   
	 DECLARE exit handler for sqlwarning
	  BEGIN
		 -- WARNING
         Select "warning";
	  ROLLBACK;
	 END;

	START TRANSACTION;

	Create table if not exists user (
			  username varchar(35) primary key,
			  f_name varchar(35) not Null,
			  l_name varchar(35),
			  user_password varchar(100) not NUll,
			  email varchar(35) not NUll,
			  phone_no varchar(15),
			  gender varchar(1) not NUll,               -- check it later
			  age int
		   );
		   
		Create table if not exists Teacher (   -- needs updation 
			username varchar(35),
            department varchar(35),
            designation varchar(35),
            education varchar(35),
			
			
			FOREIGN KEY (username) REFERENCES user(username),
			primary key (username)
		);

Select "class";
		Create table if not exists Class (
			class_id int AUTO_INCREMENT primary key,
			username varchar(35),
			class_name varchar(35) Not Null,
			course_title varchar(35) Not Null,
			Course_Code varchar(35) Not Null,
            Unique_Class_code varchar(15) unique,
			Date_created dateTime not null,
			descript varchar(300),
			
			
			FOREIGN KEY (username) REFERENCES Teacher(username)
		);

Select "student";
		Create table if not exists Student (  -- needs updation
			username varchar(35),
			class_id int ,
			Department varchar(35),
			Date_joined dateTime,
			
			FOREIGN KEY (username) REFERENCES user(username),
			FOREIGN KEY (class_id) REFERENCES class(class_id),
			primary key (class_id,username)
		);

Select "Post";
		Create table if not exists Post (
			post_id int AUTO_INCREMENT,    -- one class can have multiple posts, so use id to distinguish betweeen them.
			class_id int,
			title varchar(35) not null,
			descript varchar(300) ,
			dateCreated dateTime,
			
			FOREIGN KEY (class_id) REFERENCES class(class_id),
			primary key (post_id)
		);

		Create table if not exists Assignment (
			a_id int AUTO_INCREMENT,       -- one class can have multiple assignments, so use id to distinguish betweeen them.
			class_id int,
			a_title varchar(35),
			total_marks int not null,
			Date_created dateTime,
			due_date dateTime not null,
			descript varchar(300),
            
			FOREIGN KEY (class_id) REFERENCES class(class_id),
			primary key (a_id)
		);


		Create table if not exists Grade (
			std_username varchar(35),         -- graded to which student
			assign_id int,                    -- graded on which assignment
			marks_obtained float,

			FOREIGN KEY (assign_id) REFERENCES Assignment(a_id),
			FOREIGN KEY (std_username) REFERENCES student(username),
			
			primary key (std_username, assign_id)
		);


		Create table if not exists post_comment (
			comment_id int AUTO_INCREMENT primary key,    -- post can has multiple comments
			post_id int ,                   			  -- post number of the class (class can have many posts).
			
			comment_time datetime not null,
			comment_text varchar(255) not null,
			comment_by_std_username varchar(35) default null,    -- comment can be made either by teacher and student
			comment_by_teacher_username varchar(35) default null,


			FOREIGN KEY (post_id) REFERENCES post(post_id),
			FOREIGN KEY (comment_by_std_username) REFERENCES student(username),
			FOREIGN KEY (comment_by_teacher_username) REFERENCES teacher(username)
        );


		Create table if not exists Assignment_Submission (
			
			submission_id int auto_increment,
            std_username varchar(35),         -- submission by student
			assign_id int,                     -- assignment of which class
            FileName varchar(50) not null,
            FileSize varchar(50) not null,
            FileContentType varchar(50) not null,
            Data_submitted datetime not null,
            DownloadUrl varchar(200),
            External_File LONGBLOB,
			
			FOREIGN KEY (assign_id) REFERENCES Assignment(a_id),
			FOREIGN KEY (std_username) REFERENCES student(username),
			
			primary key (submission_id)
		);

	COMMIT;
	END ;;
DELIMITER ;
