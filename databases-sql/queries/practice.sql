-- Module 8: Databases & SQL
-- Practice queries file

-- =====================
-- BASICS
-- =====================

-- Select all columns
SELECT * FROM employees;

-- Filter with WHERE
SELECT name, salary FROM employees WHERE department = 'Engineering';

-- Order results
SELECT name, salary FROM employees ORDER BY salary DESC;

-- Limit results
SELECT name FROM employees ORDER BY salary DESC LIMIT 5;

-- =====================
-- AGGREGATIONS
-- =====================

SELECT department, COUNT(*) AS headcount, AVG(salary) AS avg_salary
FROM employees
GROUP BY department
HAVING AVG(salary) > 70000;

-- =====================
-- JOINS
-- =====================

-- INNER JOIN
SELECT e.name, d.name AS department
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;

-- LEFT JOIN (include employees without a department)
SELECT e.name, d.name AS department
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;

-- =====================
-- SUBQUERIES
-- =====================

-- Employees earning above average salary
SELECT name, salary FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

-- =====================
-- WINDOW FUNCTIONS
-- =====================

-- Rank employees by salary within each department
SELECT name, department, salary,
       RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS rank
FROM employees;
