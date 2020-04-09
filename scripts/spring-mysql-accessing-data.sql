create database db_spring_mysql_example; -- Creates the new database
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
grant all on db_spring_mysql_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
