INSERT INTO library_schema.DEFAULT_RETURN_TIME VALUES(7);

INSERT INTO library_schema.ROLES(
NAME, STATUS, CREATION_DATE, CREATED_BY, MODIFICATION_DATE, MODIFIED_BY)
VALUES ('ROLE_ADMIN', 'A', current_timestamp, 'ADMIN', current_timestamp, 'ADMIN');

INSERT INTO library_schema.ROLES(
NAME, STATUS, CREATION_DATE, CREATED_BY, MODIFICATION_DATE, MODIFIED_BY)
VALUES ('ROLE_LIBRARIN', 'A', current_timestamp, 'ADMIN', current_timestamp, 'ADMIN');

INSERT INTO library_schema.ROLES(
NAME, STATUS, CREATION_DATE, CREATED_BY, MODIFICATION_DATE, MODIFIED_BY)
VALUES ('ROLE_CUSTOMER', 'A', current_timestamp, 'ADMIN', current_timestamp, 'ADMIN');


INSERT INTO library_schema.USERS(
	FIRST_NAME, LAST_NAME, MOBILE_NO, EMAIL_ID, CREATION_DATE, CREATED_BY, MODIFICATION_DATE, MODIFIED_BY)
	VALUES ('Varun', 'Singla', '9888907479', 'varunsingla05@gmail.com', current_timestamp, 'ADMIN', current_timestamp, 'ADMIN');
	
INSERT INTO library_schema.USERS_CREDENTIALS(
USER_ID, USERNAME, PASSWORD, PASSWORD_EXPIRY_DATE, CREATION_DATE, CREATED_BY, MODIFICATION_DATE, MODIFIED_BY)
VALUES (1, 'admin', 'admin', current_timestamp+interval '30 day', current_timestamp, 'ADMIN', current_timestamp, 'ADMIN');

INSERT INTO library_schema.USER_ROLE_MAPPING(
	USER_ID, ROLE_ID)
	VALUES (1, 1);