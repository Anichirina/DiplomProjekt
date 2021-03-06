# Отчёт по итогам автоматизации
Для автоматизации тестирования были запланированы сценарии успешной покупки тура по карте и в кредит, отклоненной покупки по карте с недостаточным балансом, ввода невалидных данных пользователем. Эти сценарии были автоматизированы, тесты проверяют работу сайта и баз данных. Также была запланирована автоматизация сценариев поведения системы при падении базы данных и при отсутствии связи с сервером. Данные кейсы не могут быть автоматизированны.
### Краткое описание
- В ходе автоматизации тестирования были реализованы позитивные и негативные сценарии заполнения тестовой страницы.
- Реализована поддержка двух БД - MySQL и PostgreSQL.
Для написания и развертывания тестовых сценариев были использованы:
1. Intellij IDEA
1. Java 11
2. Docker для развертывания тестовой среды и упаковки готового тествого продукта
2. Junit 5
2. Selenide
2. Lombok для упрощения работы с классами и Faker для генерации данных
2. Allure для подготовки отчетности
2. MySql и PostgreSQL как заявленные поддерживаемые БД
2. Приложение было протестировано по всем запланированным позитивным и негативным сценариям.
### Количество тест-кейсов
Общее количество тест-кейсов = 46

Из них:

позитивных тест-кейсов = 10%
негативных тест-кейсов = 90%
Это отчет по ALLURE
![2021-07-15_12-38-39](https://user-images.githubusercontent.com/72977774/125768628-ae1d5a63-b2d4-4ced-a4ba-6e4c8fbfba5a.png)

- [отчет Allure](http://192.168.0.107:52026/index.html)
- Это отчет по Gragle
![2021-07-15_12-30-41](https://user-images.githubusercontent.com/72977774/125768696-fd2d8758-c4d8-4431-8303-3ca676045b52.png)
![2021-07-15_12-30-25](https://user-images.githubusercontent.com/72977774/125768699-0b14f729-6a1e-4439-821d-ca77a43f3aff.png)


### Во время проведения автоматизации тестирования сработали следующие риски:

- отсутствовали удобные локаторы для составления запросов.
- отсутствовала информация о формате ввода полей
-   сложность при передаче параметров через командную строку, и в работе с properties.
Данные риски были учтены на этапе планирования. 
### Общий итог по времени
- Настройка потребовала вдвое меньше времени, чем было запланировано. Закладывалось время на риски, связанные с технической сложностью настройки, учитывая, что нужно было развернуть 2 базы данных, разобраться как они будут работать с платежной системой.
- На автоматизацию было затрачено около 80 часов. Времени понадобилось значительно меньше запланированного, т.к. не возникло серьезных рисков.
- На создание отчетов затрачено времени в соответствии с планом.
