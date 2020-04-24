URL to swagger : http://localhost:8080/swagger-ui.html

URL to working ID: http://localhost:8080/v1

Other Installations - GIT, Maven, Java 8, Cassandra 

Project :

1. clone the project https://github.com/phgangal/fridgeManagement
2. navigate to the root folder using git bash or windows command line
3. run the command maven clean package
4. Navigate <root path to cloned project >\frisgemenegement\
5. Run the command java -jar targetMyRetail-0.0.1-SNAPSHOT.jar

Cassandra and run the following commands


CREATE KEYSPACE IF NOT EXISTS "fridgemanagement" WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

USE keyspace fridgeManagement;

create table soda (sodaId integer, fridgeId integer,sodaBrandName text, PRIMARY KEY (sodaId) );

insert into soda (sodaId, fridgeId,sodaBrandName) values (1, 1,'Mountain Dwe');
insert into soda (sodaId, fridgeId,sodaBrandName) values (2, 3,'Pepsi');
insert into soda (sodaId, fridgeId,sodaBrandName) values (3, 2,'Pepsi');
insert into soda (sodaId, fridgeId,sodaBrandName) values (4, 2,'Mountain Dwe');
insert into soda (sodaId, fridgeId,sodaBrandName) values (5, 1,'Coke');
insert into soda (sodaId, fridgeId,sodaBrandName) values (6, 2,'Red Bull');
insert into soda (sodaId, fridgeId,sodaBrandName) values (7, 1,'Mountain Dwe');
insert into soda (sodaId, fridgeId,sodaBrandName) values (8, 2,'Pepsi');
insert into soda (sodaId, fridgeId,sodaBrandName) values (9, 1,'Coke');
insert into soda (sodaId, fridgeId,sodaBrandName) values (10, 3,'Mountain Dwe');
insert into soda (sodaId, fridgeId,sodaBrandName) values (11, 2,'Sprite');
insert into soda (sodaId, fridgeId,sodaBrandName) values (12, 1,'Fanta');
insert into soda (sodaId, fridgeId,sodaBrandName) values (13, 1,'Coke Diet');


create table fridge (fridgeId int, fridgeName text,  sodasInFridge list<int>, PRIMARY KEY (fridgeId) );

insert into fridge (fridgeId,fridgeName,sodasInFridge) values (1, 'Fridge1',[1,2,3]);
insert into fridge (fridgeId,fridgeName,sodasInFridge) values (2, 'Fridge2',[4,5,6]);
insert into fridge (fridgeId,fridgeName,sodasInFridge) values (3, 'Fridge3',[7,8,9]);
insert into fridge (fridgeId,fridgeName,sodasInFridge) values (4, 'Fridge4',[10,11,12]);
insert into fridge (fridgeId,fridgeName,sodasInFridge) values (5, 'Fridge5',[13]);


USE "fridgemanagement";
create table user (userName text,  password text, userId int,  PRIMARY KEY (userName) );

insert into user (userName,  password, userId) values ('administrator','iry1vHxJx23iQW6SaMkQqohkf0g=', 1);
insert into user (userName,  password, userId) values ('user','7FulXJQyvZt0NKmy4GRAyBZJjpc=', 2)
