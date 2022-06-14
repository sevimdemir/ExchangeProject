# Exchange Application

## Online Exchange API

This is the backend server or online foreign exchange application.
Developed by using SpringBoot(2.6.7) and PostgreSql.
It runs on an embedded Tomcat via port 8080.

http://localhost:8080/

It has 2 different controller(ExchangeRateController, ExchangeTransactionController)

ExchangeRateController - exchange rate return controller
ExchangeTransactionController - amount conversion controller

## DB structure

EXCHANGE_RATES

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `numbers` | identifier |
| `currency`     | `string` | Your currency type |
| `rate`   | `numbers`| Your exchange rate |
| `operation_date`   | `date`| Your operation date |

EXCHANGE_TRANSACTIONS

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `numbers` | identifier |
| `from_currency`     | `string` | Your from currency type |
| `to_currency`   | `string`| Your to currency type |
| `from_rate`   | `number`| Your from rate |
| `to_rate`   | `number`| Your to rate |
| `amount`   | `number`| Your amount |
| `convert_amount`   | `number`| Your converted amount |
| `transaction_date`   | `date`| Your transaction date |

## Technologies

- JDK 11
- Spring boot
- PostgreSql
- Lombok, mapstruct, swagger, mockito, faker
- BugSnag

##  Setup & Run

This project uses Docker as container. In order to run project you need to pre-install Docker.
https://docs.docker.com/get-docker/

Before running project install Java 11+ and set JAVA_HOME as enviroment variable.

```bash 
  ./mvnw clean install
  docker-compose build --no-cache
  docker-compose up --force-recreate
```
After running project you can visit http://localhost:8080/swagger-ui/index.html for open API specification.

### ExchangeRateController Controller

```http
  POST /api/v1/rates/convertion-rate
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `fromCurrency` | `string` | **Required**. from currency |
| `toCurrency` | `string` | **Required**. to currency |

### ExchangeTransactionController Controller

```http
  POST /api/v1/transaction/convertion-amount
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `fromCurrency` | `string` | **Required**. from currency |
| `toCurrency` | `string` | **Required**. to currency |
| `amount` | `number` | **Required**. conversion amount |

```http
  POST /api/v1/transaction/list
```

| Parametre | Tip     | Açıklama                |
| :-------- | :------- | :------------------------- |
| `transactionId` | `number` | **Required**. exchange_transations table id |
| `transactionDate` | `date` | **Required**. transaction date |
| `page` | `number` | **Required**. page count |
| `size` | `number` | **Required**. page size |

## Test Coverage

![Logo](https://i.ibb.co/BwWrNp1/exchange-Project-Coverage.png)