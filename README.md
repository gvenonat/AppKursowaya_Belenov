# Belenov_AIS
## Как запустить:
1. Скачать OpenJFX 12 ([example](https://github.com/Devorlon/OpenJFX-Installation-Linux))
2. Распокавать библитеку в удобной вам дирриктории
3. Подключить библиотеку к среде разработки
```
IDEA -> File -> Project Structure -> Lib && Global Lib -> '+' -> Java -> 'path' -> add
```

## Описание
Создать десктопное приложение по теме "АИС учета продаж тарифных планов операторов сотовой связи"
- Подключение и работа с БД
- Интерфейс
- Доп. логика по теме

## БД - PostgreSQL
1. Cкачать PostgreSQL
```
sudo apt install postgresql postgresql-contrib
sudo -i -u postgres
psql
ALTER USER postgres WITH PASSWORD '12345';
``` 
2. Установим также графический интерфейс ([example](https://www.pgadmin.org/download/pgadmin-4-apt/))

И создадим нашу БД "Учет продаж тарифных планов" - УПТП
Иллюстрация из [ERwin Data Modeler](https://ru.wikipedia.org/wiki/ERwin_Data_Modeler):

![Иллюстрация к DB](https://github.com/luchikAR/AppKursowaya_Belenov/blob/main/BD_java.jpg)

3. SQL-скрипт находится в папке resources
