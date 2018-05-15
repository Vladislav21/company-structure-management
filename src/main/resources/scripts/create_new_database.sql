CREATE TABLE IF NOT EXISTS employees (
  id                SERIAL,
  last_name         CHARACTER VARYING(255) NOT NULL,
  first_name        CHARACTER VARYING(255) NOT NULL,
  patronymic        CHARACTER VARYING(255) NOT NULL,
  gender            CHARACTER VARYING(125) NOT NULL,
  date_of_birth     DATE                   NOT NULL,
  phone_number      CHARACTER VARYING(255) NOT NULL,
  mail              CHARACTER VARYING(255) NOT NULL,
  employment_date   DATE                   NOT NULL,
  date_of_dismissal DATE,
  position          CHARACTER VARYING(255) NOT NULL,
  salary            DOUBLE PRECISION       NOT NULL,
  is_chief          BOOLEAN,
  CONSTRAINT pk_employees PRIMARY KEY (id),
  CONSTRAINT positive_salary CHECK (salary > 8000)
);

CREATE TABLE IF NOT EXISTS departments (
  id            SERIAL,
  name          CHARACTER VARYING(255) NOT NULL,
  creation_date DATE                   NOT NULL,
  chief_id      INTEGER,
  parent_id     INTEGER,
  CONSTRAINT pk_departments PRIMARY KEY (id),
  CONSTRAINT fk_departments_to_employees FOREIGN KEY (chief_id)
  REFERENCES employees (id)
  MATCH SIMPLE
  ON UPDATE SET NULL
  ON DELETE SET NULL,
  CONSTRAINT fk_departments_to_departments FOREIGN KEY (parent_id)
  REFERENCES departments (id)
  MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE CASCADE
);
--------------------------------------------------------------------
DROP INDEX IF EXISTS departments_chief_id;
CREATE INDEX departments_chief_id
  ON departments
  USING BTREE (chief_id);

DROP INDEX IF EXISTS departments_parent_id;
CREATE INDEX departments_parent_id
  ON departments
  USING BTREE (parent_id);
--------------------------------------------------------------------
ALTER TABLE employees
  ADD COLUMN department_id INTEGER,
  ADD CONSTRAINT fk_employees_to_departments FOREIGN KEY (department_id)
REFERENCES departments (id)
MATCH SIMPLE
ON UPDATE SET NULL
ON DELETE SET NULL;
--------------------------------------------------------------------
DROP INDEX IF EXISTS employees_department_id;
CREATE INDEX employees_department_id
  ON employees
  USING BTREE (department_id);
--------------------------------------------------------------------
CREATE TABLE funds (
  id                 SERIAL,
  fund_of_department DOUBLE PRECISION,
  department_id      INTEGER,
  CONSTRAINT pk_funds PRIMARY KEY (id),
  CONSTRAINT fk_funds_to_departments FOREIGN KEY (department_id)
  REFERENCES departments (id)
  MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE CASCADE
);

CREATE TABLE logs_of_department (
  id     SERIAL,
  action CHARACTER VARYING(255) NOT NULL,
  date   DATE DEFAULT now(),
  CONSTRAINT pk_logs_of_department PRIMARY KEY (id)
);

