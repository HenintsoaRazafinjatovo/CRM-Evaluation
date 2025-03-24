CREATE VIEW total_budget_par_client AS
SELECT customer_id, SUM(valeur) AS total_budget
FROM budget
GROUP BY customer_id;
