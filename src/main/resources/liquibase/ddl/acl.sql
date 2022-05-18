-- ACL Schema SQL for PostgreSQL

-- drop table acl_entry;
-- drop table acl_object_identity;
-- drop table acl_class;
-- drop table acl_sid;
--
create table if not exists acl_sid
(
    id        bigserial    not null primary key,
    principal boolean      not null,
    sid       varchar(100) not null,
    constraint unique_uk_1 unique (sid, principal)
);

insert into acl_sid (id, principal, sid)
 values ((1), (true), ('Admin')),
        ((2), (true), ('User')),
        ((3), (true), ('Admin1'));

create table if not exists  acl_class
(
    id            bigserial    not null primary key,
    class         varchar(100) not null,
    constraint unique_uk_2 unique (class)
);

insert into acl_class (id, class)
 values (1, 'book_store.dao.entity.Author'),
        (2, 'book_store.dao.entity.Book'),
        (3, 'book_store.dao.entity.BookOrder'),
        (4, 'book_store.dao.entity.BookStoreUser'),
        (5, 'book_store.dao.entity.Customer'),
        (6, 'book_store.dao.entity.OrderDetails'),
        (7, 'book_store.dao.entity.Role'),
        (8, 'book_store.dao.entity.Warehouse');

create table if not exists  acl_object_identity
(
    id                 bigserial primary key,
    object_id_class    bigint      not null,
    object_id_identity varchar(36) not null,
    parent_object      bigint,
    owner_sid          bigint,
    entries_inheriting boolean     not null,
    constraint unique_uk_3 unique (object_id_class, object_id_identity),
    constraint foreign_fk_1 foreign key (parent_object) references acl_object_identity (id),
    constraint foreign_fk_2 foreign key (object_id_class) references acl_class (id),
    constraint foreign_fk_3 foreign key (owner_sid) references acl_sid (id)
);

insert into acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
 values ((1), (1), (1), (null), (3), (true)),
        ((2), (1), (2), (null), (3), (true)),
        ((3), (2), (1), (null), (3), (true)),
        ((4), (2), (2), (null), (3), (true)),
        ((5), (2), (3), (null), (3), (true)),
        ((6), (2), (4), (null), (3), (true)),
        ((7), (2), (5), (null), (3), (true)),
        ((8), (2), (6), (null), (3), (true)),
        ((9), (5), (1), (null), (3), (true)),
        ((10), (3), (1), (null), (3), (true)),
        ((11), (3), (4), (null), (3), (true)),
        ((12), (3), (5), (null), (3), (true)),
        ((13), (6), (24), (null), (3), (true)),
        ((14), (6), (26), (null), (3), (true)),
        ((15), (6), (31), (null), (3), (true));



create table if not exists  acl_entry
(
    id                  bigserial primary key,
    acl_object_identity bigint  not null,
    ace_order           int     not null,
    sid                 bigint  not null,
    mask                integer not null,
    granting            boolean not null,
    audit_success       boolean not null,
    audit_failure       boolean not null,
    constraint unique_uk_4 unique (acl_object_identity, ace_order),
    constraint foreign_fk_4 foreign key (acl_object_identity) references acl_object_identity (id),
    constraint foreign_fk_5 foreign key (sid) references acl_sid (id)
);

insert into acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
 values (1, 1, 1, 1, 1, true, true, true),
        (2, 1, 2, 2, 1, true, true, true),
        (3, 1, 3, 3, 1, true, true, true),
        (4, 1, 4, 1, 2, true, true, true),
        (5, 1, 5, 3, 2, true, true, true),
        (6, 1, 6, 1, 3, true, true, true),
        (7, 1, 7, 3, 3, true, true, true),
        (8, 1, 8, 1, 4, true, true, true),
        (9, 1, 9, 3, 4, true, true, true),
        (10, 2, 1, 1, 1, true, true, true),
        (11, 2, 2, 2, 1, true, true, true),
        (12, 2, 3, 3, 1, true, true, true),
        (13, 2, 4, 1, 2, true, true, true),
        (14, 2, 5, 3, 2, true, true, true),
        (15, 2, 6, 1, 3, true, true, true),
        (16, 2, 7, 3, 3, true, true, true),
        (17, 2, 8, 1, 1, true, true, true),
        (18, 2, 9, 3, 4, true, true, true);

