show tables;
# DROP TABLE IF EXISTS user;
create TABLE user(
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  user_id VARCHAR(32) NOT NULL UNIQUE ,
  user_name VARCHAR(50) NOT NULL ,
  pswd VARCHAR(8) NOT NULL
)

insert into user(id,user_id,user_name,pswd) values('1','haiyoung','Haiyoung','123');

SELECT * FROM user;