--returns data for API calls

-- query for classes
sElEcT cc.class_id, c.class_name, c.class_loc_id, cc.cp_id, cp.cp_name
FROM current_cps cc
JOIN classes c
	ON cc.class_id = c.class_id
JOIN course_producers cp
	ON cc.cp_id = cp.cp_id
GROUP BY cc.class_id
-- returns table of all current classes that have a CP

-- query for student queue
SELECT sq.student_id, sq.student_name, sq.class_id -- returns your student queue in one list
FROM student_queue sq
ORDER BY sq.student_id

-- query for adding an active CP
-- EXPECTING: CP email=email, CP password = cpPassword, class ID (radio buttons?) = classID, class passcode = cPasscode
SELECT cp.cp_password, cp.cp_id
FROM course_producers cp
WHERE cp.cp_email = email
-- save this in variables (valPass, cpId)
SELECT c.class_code
FROM classes
WHERE c.class_id = classID
-- save this in a variable(valCode)
-- have logic that calls the following only if cpPassword = valPass and cPasscode = valCode
INSERT INTO current_cps
VALUES (cpId, classID)

-- query for removing a CP
-- EXPECTING: CP id = CpID
DELETE FROM current_cps c
WHERE c.cp_id = CpId

-- query to register a CP
-- EXPECTING: name = cpName, email = cpEmail, password = cpPassword
SELECT COUNT(*)
FROM course_producers cp
WHERE cp.cp_email = cpEmail
-- save this in a variable (ct)
-- if ct == 0 then call the following
INSERT INTO course_producers copr
VALUES(cpName, cpEmail, cpPassword)

-- query to add students to a queue
-- EXPECTING: student name = stuName, student phone = stuPhone, class id = classID (radio button)
INSERT INTO student_queue
VALUES (stuName, stuPhone, classID)
-- auto increments student ID for sorting

-- query to remove student from a queue
-- EXPECTING: student ID = stuID, class ID = classID (this is always available as long as you've queried the queue locally)
DELETE FROM student_queue sq
WHERE sq.student_id = stuID AND sq.class_id = classID
-- update front of queue table here as well
SELECT fq.student_id
FROM front_of_queue fq
WHERE fq.class_id = classID
-- save this in a variable, if the variable = stuID then do both statements below
SELECT MIN(sq.student_id)
FROM student_queue sq
WHERE sq.class_id = classID
-- save this in a variable newFront
UPDATE front_of_queue
SET student_id = newFront
WHERE class_id = classID
