-- b) Mostrar los salarios y nombres de los jugadores que ganan menos que el promedio.
select salary.value as Salario, personal_details.player_name as Nombre from salary 
inner join personal_details on (salary.player_id=personal_details.player_id )
WHERE value < (SELECT AVG(salary.value) FROM salary)