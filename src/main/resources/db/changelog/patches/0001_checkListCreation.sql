CREATE TABLE checklist_user (
  id serial PRIMARY KEY,
  name varchar(255) NOT NULL,
  role varchar(255),
  about varchar(255),
  login varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  pass_token varchar(255),
  salt varchar,
  deleted boolean NOT NULL
);

CREATE TABLE task (
  id serial PRIMARY KEY,
  description varchar(255) NOT NULL,
  parent_id int REFERENCES task,
  user_id int NOT NULL REFERENCES checklist_user,
  done boolean NOT NULL DEFAULT FALSE,
  deadline timestamptz NULL,
  deleted boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE event (
  id serial PRIMARY KEY,
  user_id int NOT NULL REFERENCES checklist_user,
  task_id int REFERENCES task,
  type varchar(255) NOT NULL,
  comment varchar(255),
  task_name varchar(255),
  new_value varchar(255),
  is_checklist boolean,
  deleted boolean NOT NULL DEFAULT FALSE,
  created timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);
