update developers.user u set u.role="ROLE_PLAYER" where name="www";
UPDATE developers.user u set u.password="123" where name="www";
insert into developers.user (name, password, email, photo, role) values ("spring", "123", "spring@sp.ua", "user_no_avatar.png", "ROLE_USER");
insert into developers.user (name, password, email, photo, role) values ("admin", "123", "admin@admin.ua", "user_no_avatar.png", "ROLE_ADMIN");
delete from developers.user where id<32;
insert into developers.user (name, password, email, photo, role) values ("administrator", "123", "admin@admin.ua", "user_no_avatar.png", "ROLE_ADMIN");
insert into developers.user (name, password, email, photo, role) values ("adm", "123", "admin@admin.ua", "user_no_avatar.png", "ROLE_ADMIN");
insert into developers.user (name, password, email, photo, role) values ("omg", "123", "omg@com.ua", "user_no_avatar.png", "ROLE_PLAYER");
insert into developers.user (name, password, email, photo, role) values ("qwe", "123", "qwe@com.ua", "user_no_avatar.png", "ROLE_PLAYER");
