## Запуск приложения
   1. Запустить необходимые базы данных и нужные контейнеры командой:
docker-compose up -d.

   2. В новой вкладке терминала ввести следующую команду в зависимости от базы данных:
   
   -   java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
      - для базы данных MySQL
      
   -   java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar artifacts/aqa-shop.jar 
       - для базы данных PostgreSQL.

  3. Приложение должно запуститься по адресу [http://localhost:8080/](http://localhost:8080/)
         
   4. Запустите тесты командой:

- при работе с postgres: gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/postgres -Dlogin=user -Dpassword=pass
  -Dapp.url=http://localhost:8080
- при работе с mySql: gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app -Dlogin=user -Dpassword=pass
  -Dapp.url=http://localhost:8080 .
  
  5. Для создания отчета Allure запустить команду 'gradlew allureReport' и 'gradlew allureServe'
   для Windows.
    
