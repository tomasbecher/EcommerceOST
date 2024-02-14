CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    name text NOT NULL,
    last_name text NOT NULL,
    email text NOT NULL UNIQUE
);

CREATE TABLE role (
    id SMALLINT PRIMARY KEY,
    name text NOT NULL,
    description text NOT NULL
);


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    person_id INT4 NOT NULL,
    password TEXT NOT NULL,
    receive_newsletter BOOL NOT NULL,
    last_login TIMESTAMP,
    enabled BOOL NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE user_role (
    user_id INT4 NOT NULL,
    role_id SMALLINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE refresh_token (
    id SERIAL PRIMARY KEY,
    token text NOT NULL,
    expiry_date TIMESTAMP WITH TIME ZONE NOT NULL,
    user_id int4 NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE subscription_type (
    id int2 PRIMARY KEY,
    description TEXT,
    price DOUBLE PRECISION
);

INSERT INTO subscription_type (id, description, price)
VALUES
    (0, 'No subscription', 0),
    (1, 'Monthly', 9.99),
    (2, 'Yearly', 90);



CREATE TABLE subscription_state (
    id int2 PRIMARY KEY,
    description TEXT
);


INSERT INTO subscription_state (id, description)
VALUES
    (0, 'Inactive'),
    (1, 'Active'),
    (2, 'Expired'),
    (3, 'Cancelled');


CREATE TABLE user_subscription (
    id SERIAL PRIMARY KEY,
    user_id int4,
    subscription_type_id int2,
    subscription_state_id int2,
    start_date TIMESTAMP,
    expiration_date TIMESTAMP,
    auto_renewal bool,
    count_monthly int4,
    count_yearly int4,
    FOREIGN KEY (subscription_type_id) REFERENCES subscription_type(id),
    FOREIGN KEY (subscription_state_id) REFERENCES subscription_state(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);