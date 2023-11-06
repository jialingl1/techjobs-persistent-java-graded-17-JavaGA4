--Part 1

--SELECT * FROM job; (to show entire table)
--SHOW COLUMNS FROM job; (to check data type of each column in the job table op 1)
--DESCRIBE job; (to check data type of each column in the job table op 2)
--SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'job'; (to check data type of each column and more in the job table op 3)

--Part 2

--SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3

-- DROP TABLE job;

--Part 4

--SELECT * FROM skill INNER JOIN job_skills ON skill.id = job_skills.skills_id WHERE job_skills.jobs_id is NOT Null ORDER BY name ASC;

