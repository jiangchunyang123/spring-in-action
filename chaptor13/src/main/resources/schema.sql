drop table if exists spittle;

create table spittle (
  id       integer primary key auto_increment,
  message  varchar(2000),
  time     datetime,
  latitude varchar(100),
  logitude varchar(100)
);
