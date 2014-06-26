ALTER TABLE user ADD email varchar(45) DEFAULT NULL;
ALTER TABLE developers.user ADD `photo` varchar(45) DEFAULT NULL;
ALTER TABLE developers.user ADD `role` varchar(45) DEFAULT "player";

update developers.`user` u inner join developers.info i on u.id=i.user set u.email=i.email, u.photo=i.photo;

DELETE FROM developers.`user` where email IS NULL OR photo IS NULL;
DROP table developers.`info`;