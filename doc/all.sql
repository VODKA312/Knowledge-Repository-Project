drop table if exists `test`;
create table `test` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment ='测试';

insert into `test` (id, name, password) VALUES (1,'yyt','0312');
/**
  增加demo表
 */
drop table if exists `demo`;
create table `demo` (
                        `id` bigint not null comment 'id',
                        `name` varchar(50) comment '名称',
                        `password` varchar(50) comment '密码',
                        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment ='测试';

insert into `demo` (id, name, password) VALUES (1,'yytdev','0312');
/**
  增加电子书表
 */
drop table if exists `ebook`;
create table `ebook` (
                        `id` bigint not null comment 'id',
                        `name` varchar(50) comment '名称',
                        `category1_id` bigint comment '一级分类',
                        `category2_id` bigint comment '二级分类',
                        `description` varchar(200) comment '描述',
                        `cover` varchar(200) comment '封面',
                        `doc_count` int comment '文档数',
                        `view_count` int comment '阅读数',
                        `vote_count` int comment '点赞数',
                        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment ='电子书';

insert into `ebook` (id, name, description) VALUES (1,'操作系统算法测试1','测试文档1');
insert into `ebook` (id, name, description) VALUES (2,'操作系统算法测试2','测试文档2');
insert into `ebook` (id, name, description) VALUES (3,'操作系统算法测试3','测试文档3');