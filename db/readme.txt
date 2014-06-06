mysqldump -uroot -p developers --routines --triggers > D:/developers_2014_02_19.sql

mysql --user=root -p --host=localhost developers < D:/developers_2014_02_19.sql
