

create table sys_lock (
  id varchar(32) not null,
  lock_thread varchar(100) not null,
  lock_type varchar(50) not null,
  lock_object varchar(50) not null,
  lock_date timestamp not null,
  primary key (id),
  unique key lock_type (lock_type,lock_object)
);

show create table sys_lock;

insert into sys_lock(id,lock_thread,lock_type,lock_object,lock_date)
    values('001','thread-001','01','object_001',now());
insert into sys_lock(id,lock_thread,lock_type,lock_object,lock_date)
    values('002','thread-002','02','object_002',now());
insert into sys_lock(id,lock_thread,lock_type,lock_object,lock_date)
    values('003','thread-002','03','object_002',now());

select * from sys_lock;

create TABLE user(
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  user_id VARCHAR(32) NOT NULL UNIQUE ,
  user_name VARCHAR(50) NOT NULL ,
  pswd VARCHAR(8) NOT NULL
);

insert into user(id,user_id,user_name,pswd) values('1','haiyoung','Haiyoung','123');

SELECT * FROM user;

create table role(
  role_id VARCHAR(30) NOT NULL PRIMARY KEY ,
  role_name VARCHAR(100) NOT NULL
);

create table user_role(
  user_id VARCHAR(32) NOT NULL ,
  role_id VARCHAR(30) NOT NULL ,
  UNIQUE KEY (user_id,role_id)
);