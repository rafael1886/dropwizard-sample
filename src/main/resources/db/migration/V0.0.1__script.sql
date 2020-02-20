create TABLE IF NOT EXISTS address (
    id bigserial NOT NULL,
    street varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    state varchar(255) NOT NULL,
    zip varchar(7) NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
	id bigserial NOT NULL,
	name varchar(128) NOT NULL,
	surname varchar(128) NOT NULL,
	address_id bigint not null,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT address_fk FOREIGN KEY (address_id) REFERENCES address(id)
);