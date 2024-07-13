DROP SCHEMA IF EXISTS `football-league-management`;

CREATE SCHEMA `football-league-management`;

use `football-league-management`;

CREATE TABLE `members` (
  `id` int not null auto_increment,
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `email` varchar(50) default null,
  `active` tinyint default 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_unique` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `roles` (
  `id` int not null auto_increment,
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `authorities5_ibfk_2` FOREIGN KEY (`id`) REFERENCES `members` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4;


create table `rule` (
	`id` int not null auto_increment,
    `min_age` int default null,
    `max_age` int default null,
    `min_number` int default null,
    `max_number` int default null,
    `max_foreign_number` int default null,
    `number_of_goal_type` int default null,
    `max_score_time` time default null,
    `win_point` int default null,
    `draw_point` int default null,
    `loss_point` int default null,
    `ranking_order` varchar(10) default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `league` (
	`id` int not null auto_increment,
    `name` nvarchar(128) default null,
    `country_name` nvarchar(128) default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `matches` (
	`id` int not null auto_increment,
    `date_time` datetime default null,
    `home_ground` nvarchar(128) default null,
    `team_1_goal` int default null,
    `team_2_goal` int default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `round` (
	`id` int not null auto_increment,
    `name` nvarchar(128) default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `team` (
	`id` int not null auto_increment,
    `name` nvarchar(128) default null,
    `home_ground` nvarchar(128) default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `player` (
	`id` int not null auto_increment,
    `name` nvarchar(128) default null,
    `birthday` date default null,
    `position` nvarchar(128) default null,
    `goal` int default 0,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `player_type` (
	`id` int not null auto_increment,
    `name` nvarchar(128) default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `match_team` (
	`match_id` int not null,
    `team_id` int not null,
    primary key(`match_id`, `team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `reporter` (
	`id` int not null auto_increment,
    `time` time default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `goal_type` (
	`id` int not null auto_increment,
    `name` varchar(10) default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

create table `chart` (
	`id` int not null auto_increment,
    `win` int default null,
    `draw` int default null,
    `loss` int default null,
    `goal_difference` int default null,
    `chart_rank` int default null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


alter table `matches`
add column `league_id` int default null,
add constraint `match_league_fk` foreign key (`league_id`) references `league`(`id`);

alter table `match_team`
add constraint `match_mt_fk` foreign key(`match_id`) references `matches`(`id`),
add constraint `team_mt_fk` foreign key(`team_id`) references `team`(`id`);

alter table `player`
add column `team_id` int default null,
add constraint `player_team_fk` foreign key(`team_id`) references `team`(`id`);

alter table `reporter`
add column `match_id` int default null,
add constraint `reporter_result_fk` foreign key(`match_id`) references `matches`(`id`);

alter table `reporter`
add column `player_id` int default null,
add constraint `reporter_player_fk` foreign key(`player_id`) references `player`(`id`);

alter table `chart`
add column `team_id` int default null,
add constraint `team_chart_id` foreign key(`team_id`) references `team`(`id`);

alter table `chart`
add column `league_id` int default null,
add constraint `chart_league_fk` foreign key(`league_id`) references `league`(`id`);

alter table `matches`
add column `round_id` int default null,
add constraint `match_round_fk` foreign key(`round_id`) references `round`(`id`);

alter table `reporter`
add column `goal_type_id` int default null,
add constraint `reporter_gt_fk` foreign key(`goal_type_id`) references `goal_type`(`id`);

alter table `player`
add column `player_type_id` int default null,
add constraint `player_pt_fk` foreign key(`player_type_id`) references `player_type`(`id`);

INSERT INTO `football-league-management`.`player_type` (`id`, `name`) VALUES ('1', 'Trong nước');
INSERT INTO `football-league-management`.`player_type` (`id`, `name`) VALUES ('2', 'Ngoài nước');

INSERT INTO `football-league-management`.`members` (`id`, `user_id`, `pw`, `email`, `active`) VALUES ('1', 'khoa', '{noop}123', 'khoa@gmail.com', '1');
INSERT INTO `football-league-management`.`roles` (`id`, `user_id`, `role`) VALUES ('1', 'khoa', 'ROLE_ADMIN');

INSERT INTO `football-league-management`.`rule` (`id`, `min_age`, `max_age`, `min_number`, `max_number`, `max_foreign_number`, `number_of_goal_type`, `max_score_time`, `win_point`, `draw_point`, `loss_point`, `ranking_order`) VALUES ('1', '18', '40', '11', '22', '3', '3', '90:00:00', '3', '1', '0', 'dsc');

INSERT INTO `football-league-management`.`round` (`id`, `name`) VALUES ('1', 'Vòng 1');
INSERT INTO `football-league-management`.`round` (`id`, `name`) VALUES ('2', 'Vòng 2');

INSERT INTO `football-league-management`.`goal_type` (`id`, `name`) VALUES ('1', 'A');
INSERT INTO `football-league-management`.`goal_type` (`id`, `name`) VALUES ('2', 'B');
INSERT INTO `football-league-management`.`goal_type` (`id`, `name`) VALUES ('3', 'C');