CREATE TABLE `constraintTab` (
	`c1` bigint  ,
	`bbb` bigint  NOT NULL  UNIQUE,
	`c2` varchar(5000)  ,
	CONSTRAINT `rule_one` 
		PRIMARY KEY(`c1`, `bbb`) 
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8  ;


CREATE TABLE IF NOT     EXISTS `halloworld` (
	`xxx` varchar(40) not null unique,
	`yyyy` integer
)ENGINE=InnoDB DEFAULT CHARACTER SET=utf8  ;

CREATE TABLE  `halloworld` (
	`xxx` varchar(40) not null unique,
	`yyyy` integer AUTO_INCREMENT ,
	`zzz` text
);

CREATE TABLE IF NOT EXISTS h (
	`a` bigint,
	`b` integer,
constraint `yyyy` primary key (a) ,
constraint zzzz foreign key (a) references halloworld (xxx)
)auto_increment = 0123456;

DroP TaBle If Exists `xxxxxx`, `yyyyy`;

insert into `xxxxxxx` (`a`,`b`,`c`, d) values (1, "xyzt", @xxxxx, 1.10000);
insert into `xxxxxxx` (`a`,`b`,`c`, d) values 
		(1, "xyzt", @xxxxx, 1.10000),
		(1, "xyzt", @xxxxx, 1.10000),
		(1, "xyzt", @xxxxx, 1.10000),
		(1, "xyzt", @xxxxx, 1.10000),
		(1, "xyzt", @xxxxx, 1.10000),
		(1, "xyzt", @xxxxx, 1.10000),
		(1, "xyzt", @xxxxx, 1.10000) ;


delete from `xxxxx`;
delete from `xxxxx`, yyyyy, `zzzz`;