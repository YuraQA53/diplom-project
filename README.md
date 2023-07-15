# diplom-project


## Дипломный проект профессии «Тестировщик ПО»

Дипломный проект представляет собой автоматизацию тестированя веб-сервиса покупки тура "Путешествие дня - Марракэш", взаимодействующего с СУБД и API Банка.
[Задание](https://github.com/netology-code/qa-diploma)

[План тестирования](https://github.com/YuraQA53/diplom-project/blob/main/documents/Plan.md)

[Отчет по итогам тестирования](https://github.com/YuraQA53/diplom-project/blob/main/documents/Report.md)

[Отчет по итогам автоматизации](https://github.com/YuraQA53/diplom-project/blob/main/documents/Summary.md)

## Подготовка и настройка окружения

* Java 11
* IntelliJ IDEA 2021.3.2 с подключенными библиотеками файл [build.gradle](https://github.com/YuraQA53/diplom-project/blob/main/build.gradle) :
* JUnit Jupiter
* Selenide
* Selenium Java
* Lombok
* JavaFaker
* WebDriverManager
* Apache Commons DbUtils
* MySQL Connector
* PostgreSQL JDBC Driver
* Allure


## Установка и запуск приложения, автотестов и отчетов

Учётные данные и url для подключения задаются в файле [application.properties](https://github.com/netology-code/qa-diploma/blob/master/application.properties)

* запустить IntelliJ IDEA с проектом с репозитария
             ```
  * git clone    https://github.com/YuraQA53/diplom-project
             ```

  * перейти в папку с проектом 
         ```
     cd     diplom-project
         ```
  * запустить docker container (настройки в файле docker-compose.yml)
      ```
         docker-compose up -d
      ```   
  * дождаться запуска контейнеров
* в терминале IntelliJ IDEA запустить SUT:
    - с использованием БД MySQL командой 
      ```
         java -jar artifacts/aqa-shop.jar "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app"
      ```
    - с использованием БД PostgreSQL командой
       ```
          java -jar artifacts/aqa-shop.jar "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app"
       ```
* в терминале IntelliJ IDEA запустить автотесты командой:
    - для конфигурации БД MySql: 
      ```
         ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
      ```
    - для конфигурации БД PostgreSQL: 
      ```
         ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
      ```
* в терминале IntelliJ IDEA запустить отчеты командой:
      ```
         ./gradlew allureReport (первоначальная команда)
      ```
      ```
         ./gradlew allureServe (запуск и открытие отчетов)
      ```
* в терминале IntelliJ IDEA остановить SUT
CTRL+C

 