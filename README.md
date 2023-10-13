## Logistic Company

Проект состоит из 3х основных микросервисов.

### DataLoader

Данный микросервси необходимо запускать первым.
Он обеспечивает создание таблиц и наполнением их данными из файла Data

```angular2html
    Customer.csv
    OperationHistory.csv
    Price.csv
    Product.csv
```

Все эти 4 файла вгружаются в базу через класс 
```java
    OperationHistoryDataLoader.class 
```

После окончания подгрузки необходимо запустить остальные микросервисы

### InfoManagmentService

Данный микросервис выгружает все данные из таблицы 
Info(требует доработки возможности пагинации)

### PriceManagmentService

Ходит в базу и выгружает цены - Стандартный CRUD

Все микросеврвисы можно мониторить с момощью Eureka server
http://localhost:8761/

С помощью Zipkin происходит мониторинг и логирование проброс логов
http://localhost:9411/

Весь входящий поток необходимо кидать на apiGateway
Согласно роутингу, который происходит по базовому алгоритму Роунд Робин

```application.yml
  cloud:
    gateway:
      routes:
        - id: price
          uri: lb://PRICE
          predicates:
            - Path=/api/v1/price/**
        - id: info
          uri: lb://INFO
          predicates:
            - Path=/api/v1/info/**
```