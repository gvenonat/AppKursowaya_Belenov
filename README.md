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
1. скачать PostgreSQL
```
sudo apt install postgresql postgresql-contrib
sudo -i -u postgres
psql
ALTER USER postgres WITH PASSWORD '12345';
``` 
Установим также графический интерфейс ([example](https://www.pgadmin.org/download/pgadmin-4-apt/))
Создадим нашу БД "Учет продаж тарифных планов" - УПТП
![Иллюстрация к DB](https://github.com/luchikAR/AppKursowaya_Belenov/DB_java.jpg)
2. установить подключение к IDEA
