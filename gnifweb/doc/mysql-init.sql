create database gnif default character set utf8 collate utf8_general_ci;
use gnif;

create table gc_property_ (
  key_ varchar(128),
  value_ varchar(128),
  rev_ integer default 0,
  primary key (key_)
);

insert into gc_property_ (key_, value_) values ('gnif.version', '1.0');
insert into gc_property_ (key_, value_) values ('devtool.version', '1.0');
insert into gc_property_ (key_, value_) values ('auth.version', '1.0');

create table auth_user (
  id integer not null,
  account varchar(128) not null,
  password varchar(128),
  name varchar(128),
  pri_group_id integer,
  org_id integer not null,
  telephone varchar(128),
  mobile varchar(128),
  email varchar(128),
  type integer,
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table auth_group (
  id integer not null,
  code varchar(128),
  org_id integer,
  name varchar(128),
  description varchar(1024),
  scope_ integer,
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table auth_organization (
  id integer not null,
  pid integer default 0 not null,
  name varchar(128),
  full_name varchar(256),
  address varchar(128),
  telephone varchar(128),
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table auth_user_group (
  user_id integer not null,
  group_id integer not null,
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (user_id, group_id)
);

create table auth_resource (
  id integer not null,
  pid integer default 0 not null,
  type integer,
  name varchar(128),
  code varchar(128),
  index_ integer,
  action varchar(128),
  url varchar(256),
  icon varchar(128),
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table auth_group_resource (
  group_id integer not null,
  res_id integer not null,
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (group_id, res_id)
);

create table auth_pri_group (
  id integer not null,
  name varchar(128) not null unique,
  description varchar(255),
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table auth_pri_define (
  id integer not null,
  pid integer default 0,
  name varchar(128),
  pri_key varchar(128),
  def_value varchar(128),
  model varchar(128),
  pindex integer,
  pri_type integer default 0,
  description varchar(255),
  detail varchar(1024),
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table auth_privilege (
  id integer not null,
  pri_group_id integer not null,
  pri_key varchar(128) not null,
  pri_value varchar(128) not null,
  status integer default 0 not null,
  remark varchar(1024),
  create_by varchar(128),
  create_time date,
  update_by varchar(128),
  update_time date,
  primary key (id)
);

create table dev_data_model_ (
  id_ integer not null,
  name_ varchar(128),
  java_name_ varchar(128),
  db_name_ varchar(128),
  primary key (id_)
);

create table dev_data_field_ (
  id_ integer not null,
  model_id_ integer not null,
  name_ varchar(128),
  java_name_ varchar(128),
  java_type_ varchar(128),
  db_name_ varchar(128),
  db_type_ varchar(128),
  primary key (id_)
);
