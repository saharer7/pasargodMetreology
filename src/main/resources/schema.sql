//DROP TABLE IF EXISTS TBL_User;

CREATE TABLE tb_location
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    country    VARCHAR(250) NOT NULL,
    city       VARCHAR(250) NOT NULL,
    province   VARCHAR(250) NOT NULL
);


create table tb_weather(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    last_updated        datetime,
    temperature         double,
    is_day              boolean,
    wind_kph            double,
    wind_degree         double,
    wind_dir            varchar(50),
    pressure            double,
    humidity            double,
    cloud               double,
    feelslike           double,
    register_date       datetime,
    uv                  double,
    gust                double,
    condition_text      varchar(200),
    condition_icon      varchar(200),
    condition_code      varchar(200),
    fk_location         int
);

alter table tb_weather
    add foreign key (fk_location)
        references tb_location (id);



