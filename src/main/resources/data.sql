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

INSERT INTO MENU (DATE_MENU, RESTAURANT_ID)
VALUES (CURRENT_DATE-1, 1),
       (CURRENT_DATE, 1),
       (CURRENT_DATE, 2);

INSERT INTO MENU_ITEM (NAME, PRICE, DATE_MENU, RESTAURANT_ID)
VALUES ('Beef Stroganoff', 28, CURRENT_DATE-1, 1),
       ('Caesar', 15, CURRENT_DATE-1, 1),
       ('Espresso', 5, CURRENT_DATE-1, 1),
       ('Tourin', 22, CURRENT_DATE, 1),
       ('Caesar', 15, CURRENT_DATE, 1),
       ('Espresso', 5, CURRENT_DATE, 1),
       ('Eggs Benedict', 20, CURRENT_DATE, 2),
       ('Buttermilk pancake', 15, CURRENT_DATE, 2),
       ('Smoothie', 7, CURRENT_DATE, 2),
       ('Chicken schnitzel', 22, CURRENT_DATE, 3),
       ('Prague salad', 14, CURRENT_DATE, 3),
       ('San Pellegrino 750ml', 8, CURRENT_DATE, 3);

INSERT INTO VOTE (DATE_VOTE, RESTAURANT_ID, USER_ID)
VALUES  (CURRENT_DATE-1, 1, 1),
        (CURRENT_DATE, 1, 1),
        (CURRENT_DATE, 2, 2)
