-- Создание таблицы teachers
CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,          -- Уникальный идентификатор учителя, автоинкремент
    first_name VARCHAR(100),        -- Имя учителя
    last_name VARCHAR(100),         -- Фамилия учителя
    degree VARCHAR(50)              -- Степень (например, магистр, кандидат наук и т.д.)
);

-- Создание таблицы students
CREATE TABLE students (
    id SERIAL PRIMARY KEY,          -- Уникальный идентификатор студента, автоинкремент
    first_name VARCHAR(100),        -- Имя студента
    last_name VARCHAR(100),         -- Фамилия студента
    age INT CHECK (age >= 0),       -- Возраст студента, проверка на неотрицательное значение
    teacher_id INT,                 -- Идентификатор учителя, внешний ключ
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)  -- Связь с таблицей teachers
);

-- Вставка тестовых данных в таблицу teachers
INSERT INTO teachers (first_name, last_name, degree)
VALUES
    ('Иван', 'Петров', 'Кандидат наук'),
    ('Мария', 'Смирнова', 'Магистр');

-- Вставка тестовых данных в таблицу students
INSERT INTO students (first_name, last_name, age, teacher_id)
VALUES
    ('Алексей', 'Иванов', 20, 1),
    ('Ольга', 'Кузнецова', 22, 1),
    ('Дмитрий', 'Сидоров', 21, 1),
    ('Елена', 'Попова', 23, 2),
    ('Ирина', 'Васильева', 24, 2),
    ('Максим', 'Михайлов', 25, 1),
    ('Татьяна', 'Соколова', 22, 2),
    ('Юрий', 'Дмитриев', 20, 1),
    ('Наталья', 'Баранова', 21, 2),
    ('Владимир', 'Григорьев', 23, 1);
