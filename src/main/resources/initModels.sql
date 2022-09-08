CREATE TABLE PUBLIC.RESTAURANT (
                                   ID      INTEGER PRIMARY KEY AUTO_INCREMENT ,
                                   NAME    VARCHAR(128)    UNIQUE   NOT NULL
);
CREATE UNIQUE INDEX RESTAURANT_UNIQUE_NAME_IDX ON RESTAURANT(NAME);

CREATE TABLE PUBLIC.DISH (
                             ID      INTEGER PRIMARY KEY AUTO_INCREMENT ,
                             NAME            VARCHAR(128)                NOT NULL    ,
                             PRICE           INTEGER CHECK (PRICE > 0)   NOT NULL    ,
                             DATE_MENU       INTEGER    REFERENCES MENU(ID)          ,
                             RESTAURANT_ID   INTEGER                     NOT NULL    ,
                             FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT(ID) ON DELETE CASCADE
);
CREATE INDEX DISH_RESTAURANTID_DATEMENU ON DISH(RESTAURANT_ID, DATE_MENU);

CREATE TABLE VOTE (
                      ID          INTEGER PRIMARY KEY AUTO_INCREMENT ,
                      DATE_VOTE       DATE     DEFAULT CURRENT_DATE              NOT NULL              ,
                      USER_ID         INTEGER  REFERENCES USERS(ID)      ON DELETE CASCADE             ,
                      RESTAURANT_ID   INTEGER  REFERENCES RESTAURANT(ID) ON DELETE CASCADE             ,
                      CONSTRAINT VOTE_DATEVOTE_USERID UNIQUE (DATE_VOTE, USER_ID)
);
CREATE TABLE MENU (
                      ID          INTEGER PRIMARY KEY AUTO_INCREMENT       ,
                      DATE_MENU   DATE    DEFAULT CURRENT_DATE    NOT NULL
);