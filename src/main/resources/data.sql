INSERT INTO USERS (NAME, EMAIL, PASSWORD, CALORIES_PER_DAY)
VALUES ('User', 'user@yandex.ru', '{noop}password', 2005),
       ('Admin', 'admin@gmail.com', '{noop}admin', 1900),
       ('Guest', 'guest@gmail.com', '{noop}guest', 2000);

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO MEAL (date_time, description, calories, user_id)
VALUES ('2020-01-30 10:00:00', 'Завтрак', 500, 1),
       ('2020-01-30 13:00:00', 'Обед', 1000, 1),
       ('2020-01-30 20:00:00', 'Ужин', 500, 1),
       ('2020-01-31 0:00:00', 'Еда на граничное значение', 100, 1),
       ('2020-01-31 10:00:00', 'Завтрак', 500, 1),
       ('2020-01-31 13:00:00', 'Обед', 1000, 1),
       ('2020-01-31 20:00:00', 'Ужин', 510, 1),
       ('2020-01-31 14:00:00', 'Админ ланч', 510, 2),
       ('2020-01-31 21:00:00', 'Админ ужин', 1500, 2);

-- MODELS POPULATING ----

INSERT INTO RESTAURANT (NAME)
VALUES ( 'Astoria' ),
       ('Four seasons'),
       ('Prague');

INSERT INTO MENU (DATE_MENU)
VALUES ( CURRENT_DATE ),
       ( CURRENT_DATE+1);

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID, DATE_MENU)
VALUES ( 'Beef Stroganoff', 28, 1, 1 ),
       ( 'Tourin', 22, 1, 1 ),
       ( 'Caesar', 15, 1, 1 ),
       ( 'Espresso', 5, 1, 1 ),
       ( 'Eggs Benedict', 20, 2, 1 ),
       ( 'Buttermilk pancake', 15, 2, 1 ),
       ( 'Smoothie', 7, 2, 1 ),
       ( 'Chicken schnitzel', 22, 3,2 ),
       ( 'Prague salad', 14, 3, 2 ),
       ( 'San Pellegrino 750ml', 8, 3, 2 );

INSERT INTO VOTE (DATE_VOTE, RESTAURANT_ID, USER_ID)
VALUES ( CURRENT_DATE, 1, 1  ),
       (CURRENT_DATE, 2, 2)
