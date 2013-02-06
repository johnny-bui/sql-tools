CREATE TABLE IF NOT EXISTS `halloworld` (
	`xxx` varchar(40) not null unique,
	`yyyy` integer
);

CREATE TABLE IF NOT EXISTS h (
	`a` bigint,
	`b` integer,
constraint `yyyy` primary key (a) ,
constraint zzzz foreign key (a) references halloworld (xxx)
);