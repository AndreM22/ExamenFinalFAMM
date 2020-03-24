-- c) Obtener los nombres de los jugadores que tienen un control de la pelota debajo del promedio
select personal_details.player_name as Nombre from personal_details
inner join player_stats on personal_details.player_id=player_stats.player_id
WHERE ball_control < (SELECT AVG(player_stats.ball_control) FROM player_stats) 