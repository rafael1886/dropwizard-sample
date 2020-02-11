CREATE TABLE IF NOT EXISTS books (
	id bigserial NOT NULL,
	title varchar(255) NOT NULL,
	writer varchar(255) NOT NULL,
	user_id bigint not null,
	CONSTRAINT books_pkey PRIMARY KEY (id),
	CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(id)
);