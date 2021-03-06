#План автоматизации тестирования

##Перечень автоматизируемых сценариев:

###- Позитивные сценарии:

0. Пользователь вводит валидные данные, выбирая оплату по дебетовой карте, операция проходит без отказа;
0. Пользователь вводит валидные данные, выбирая покупку с использованием кредита, операция проходит без отказа;
0. Пользователь вводит валидные данные, выбирает оплату по дебетовой карте, на карте при этом недостаточно средств 
   для выполнения операции - происходит отказ в операции(по условиям для этого и следующего сценария дан номер карты
   по которому операция будет отклонена);
0. Пользователь вводит валидные данные, выбирая оплату по кредитной карте - происходит отказ в выполнении операции.

### - Негативные сценарии:

0. Пользователь выбирает покупку с оплатой по дебетовой карте, заполняет невалидными данными поля;
0. Пользователь выбирает покупку с оплатой с помощью кредитной карты, заполняет невалидными данными поля;
### Варианты

- 2 варианта неполного номера карты - 15 знаков и 1 знак
- ввод букв в поле номера карты
- 13 месяц
- прошедший год
- неправильная дата карты
- имя владельца кириллицей
- ввод цифр в поле "Владелец"
- ввод спецсимволов в поле "Владелец"
- ввод cvc/cvv из одной цифры
- ввод cvc/cvv из нолей
- ввод пустого поля имя пользователя
- отправка пустой страницы и проверка подсказок
- проверка всплывающего окна операция одобрена

## Перечень используемых инструментов с обоснованием выбора

- Intellij IDEA Ultimate - расширенная версия IDEA Community, содержит инструменты для работы с базами данных и SQL
- Java 11 - язык написания авто-тестов
- Lombok - библиотека для оптимизации кода в авто-тестах
- JUnit5 - платформа для написания авто-тестов и их запуска
- Gradle - система управления проектами
- Allure - фреймворк для создания наглядной картины выполнения тестирования, сохранении отчетности и хронологии тестирования.
- Git и GitHub - хранение кода и автотестов
- Docker - инструмент для запуска СУБД MySQL и PostgreSQL, а также образа Node.js
- Docker Compose - инструмент для запуска мульти-контейнерных приложений и удобного доступа к конфигурации в файле формата yml
- Selenide - так как работаем с веб-страницей и ищем появившиеся значения с помощью html и css, создание Page Objects, 
  заполнение формы через веб-интерфейс.
- Faker - генерация недостающих данных для тестирования.
- RestAssured - отправка запросов симулятору банковских сервисов, проверка того, что он принимает запросы и генерирует 
  ответы, хорошо подходит для проверки структуры ответов.

## Возможные риски:

1. Техническая сложность настройки окружения. Для работы приложения нужно , помимо двух разных баз данных (MySQL, PostgreSQL)
   , развернуть симулятор платежной системы, написанный на другом языке. Нужно разобраться с тем как он работает, как взаимодействует
   с нашими БД и ОС. Возможны проблемы при настройки CI (если потребуется). Поэтому в ходе настройки окружения могут возникнуть сложности,
   требующие дополнительных временных затрат.
1. проблемы с запуском SUT
1. проблемы со сборкой образа Node.js 
1. трудности в написании авто-тестов

##Интервальная оценка с учётом рисков:
- Настройка с учетом рисков - 3 суток.
- Разработка - 7 суток.
- Отчеты - 3 дня.

##План сдачи работ

- Автотесты: после утверждения плана в течении 5 дней
- Результаты прогона автотестов: 3 дня после первого этапа
- Отчет по автоматизации: 2 дня после второго этапа