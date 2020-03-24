-- a) Contar el numero de argentinos que juegan en FC Barcelona
select count(personal_details.id) from personal_details
inner join other_details on personal_details.player_id = other_details.player_id and other_details.club = 'FC Barcelona'