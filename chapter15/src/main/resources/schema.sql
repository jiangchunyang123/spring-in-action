drop table if exists spittle;
drop table if exists spitter;

create table spitter (
  id        integer primary key auto_increment,
  username  varchar(25),
  password  varchar(25),
  firstname varchar(100),
  lastname  varchar(50)
);

create table spittle (
  id         integer primary key auto_increment,
  spitter    integer,
  message    varchar(2000),
  postedTime datetime
);
