

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