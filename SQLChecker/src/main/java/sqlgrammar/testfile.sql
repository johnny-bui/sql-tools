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


select * from Author;
select `Id`, Athor from Author;
select distinct Author from Author;
select all Id from Author;
select a+b from xxxx as x , yyyyy as y;
select count(*) from Author as a;
select count(*) from ( select Author from Author ) as A ;
select a, b, c from ( select Author from Author ) as A , something as S, prd;

select all * from 
			  `xxx` as `x`
 			, `yyy` as `y`
 			, (select `a`, `b`
				from `mytable` where -- something come here
			) as `s`
			, `c`
			, `d`
;


select 1 + @x_3; -- syntax OK, semantic depends on @x_3
-- select 1 + @3 -- syntax wrong
select `x` , `y`; -- syntax OK, but semantic not OK (no table choose)