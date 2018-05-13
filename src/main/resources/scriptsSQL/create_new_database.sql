CREATE TABLE IF NOT EXISTS employees (
  id                SERIAL,
  last_name         CHARACTER VARYING(255),
  first_name        CHARACTER VARYING(255),
  patronymic        CHARACTER VARYING(255),
  gender            CHARACTER VARYING(125),
  date_of_birth     DATE,
  phone_number      CHARACTER VARYING(255),
  mail              CHARACTER VARYING(255),
  employment_date   DATE,
  date_of_dismissal DATE,
  position          CHARACTER VARYING(255),
  salary            DOUBLE PRECISION,
  is_chief          BOOLEAN,
  CONSTRAINT pk_employees PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS departments (
  id            SERIAL,
  name          CHARACTER VARYING(255),
  creation_date DATE,
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
  ON DELETE NO ACTION
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
CREATE FUNCTION departments_stamp()
  RETURNS TRIGGER AS
$BODY$
BEGIN
  IF NEW.name IS NULL
  THEN
    RAISE EXCEPTION 'name of department cannot be null';
  END IF;
  IF NEW.creation_date IS NULL
  THEN
    RAISE EXCEPTION 'creation date cannot be null';
  END IF;
  IF NEW.parent_id <= 0
  THEN
    RAISE EXCEPTION 'parent id can be more than 0';
  END IF;
  IF NEW.parent_id > (SELECT max(d.id)
                      FROM departments d)
  THEN
    RAISE EXCEPTION 'current parent id cannot be more than  max parent id';
  END IF;
  RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER departments_stamp
BEFORE INSERT OR UPDATE ON departments
FOR EACH ROW EXECUTE PROCEDURE departments_stamp();
--------------------------------------------------------------------
CREATE FUNCTION employees_stamp()
  RETURNS TRIGGER AS
$BODY$
BEGIN
  IF NEW.last_name IS NULL
  THEN
    RAISE EXCEPTION 'last name of employee cannot be null';
  END IF;
  IF NEW.first_name IS NULL
  THEN
    RAISE EXCEPTION 'first name of employee cannot be null';
  END IF;
  IF NEW.patronymic IS NULL
  THEN
    RAISE EXCEPTION 'patronymic of employee cannot be null';
  END IF;
  IF NEW.gender IS NULL
  THEN
    RAISE EXCEPTION 'gender of employee cannot be null';
  END IF;
  IF NEW.date_of_birth IS NULL
  THEN
    RAISE EXCEPTION 'date of birth of employee cannot be null';
  END IF;
  IF NEW.phone_number IS NULL
  THEN
    RAISE EXCEPTION 'phone_number of employee cannot be null';
  END IF;
  IF NEW.mail IS NULL
  THEN
    RAISE EXCEPTION 'mail of employee cannot be null';
  END IF;
  IF NEW.employment_date IS NULL
  THEN
    RAISE EXCEPTION 'employment date cannot be null';
  END IF;
  IF NEW.position IS NULL
  THEN
    RAISE EXCEPTION 'position cannot be null';
  END IF;
  IF NEW.salary IS NULL
  THEN
    RAISE EXCEPTION 'salary cannot be null';
  END IF;
  IF NEW.salary <= 0
  THEN
    RAISE EXCEPTION 'salary cannot be more than 0';
  END IF;
  IF NEW.department_id <= 0
  THEN
    RAISE EXCEPTION 'department id can be more than 0';
  END IF;
  IF NEW.department_id > (SELECT max(d.id)
                          FROM departments d)
  THEN
    RAISE EXCEPTION 'current department id cannot be more than  max id';
  END IF;
  RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER employees_stamp
BEFORE INSERT OR UPDATE ON employees
FOR EACH ROW EXECUTE PROCEDURE employees_stamp();
--------------------------------------------------------------------
