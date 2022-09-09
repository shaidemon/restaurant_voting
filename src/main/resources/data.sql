INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);


INSERT INTO RESTAURANT (NAME)
VALUES ('Astoria'),
       ('Four seasons'),
       ('Prague');

INSERT INTO MENU (DATE_MENU)
VALUES (CURRENT_DATE),
       (CURRENT_DATE + 1);

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID, DATE_MENU)
VALUES ('Beef Stroganoff', 28, 1, 1),
       ('Tourin', 22, 1, 1),
       ('Caesar', 15, 1, 1),
       ('Espresso', 5, 1, 1),
       ('Eggs Benedict', 20, 2, 1),
       ('Buttermilk pancake', 15, 2, 1),
       ('Smoothie', 7, 2, 1),
       ('Chicken schnitzel', 22, 3, 2),
       ('Prague salad', 14, 3, 2),
       ('San Pellegrino 750ml', 8, 3, 2);

INSERT INTO VOTE (DATE_VOTE, RESTAURANT_ID, USER_ID)
VALUES  (CURRENT_DATE-1, 3, 1),
        (CURRENT_DATE, 1, 1),
        (CURRENT_DATE, 2, 2)
