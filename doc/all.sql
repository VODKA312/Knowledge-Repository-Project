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

#分类 无限极扩展树形结构
drop table if exists `category`;
create table `category`(
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key(`id`)
) engine=innodb default charset=utf8mb4 comment ='分类';

insert into `category`(id, parent, name, sort) values (100,000,'理论知识',100);
insert into `category`(id, parent, name, sort) values (101,100,'操作系统理论知识',101);
insert into `category`(id, parent, name, sort) values (102,100,'图解基础算法与数据结构',102);

insert into `category`(id, parent, name, sort) values (200,000,'实践部分',200);
insert into `category`(id, parent, name, sort) values (201,200,'图解Linux系统源码',201);
insert into `category`(id, parent, name, sort) values (202,200,'算法与数据结构的C语言实现',202);

#文档表
drop table if exists `doc`;
create table `doc`(
    `id` bigint not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    `view_count` int default 0 comment '阅读数',
    `vote_count` int default 0 comment '点赞数',
    primary key(`id`)
) engine=innodb default charset=utf8mb4 comment ='文档';

#插入数据
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
(1,1,0,'文档1',1,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
(2,1,1,'文档1.1',1,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
(3,1,0,'文档2',2,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
(4,1,3,'文档2.1',1,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
(5,1,3,'文档2.2',2,0,0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) VALUES
(6,1,5,'文档2.2.1',1,0,0);