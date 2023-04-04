# 2 web-сервиса для отслеживания обновлений контента по ссылкам. 
В сервисе поддерживаются:
- Вопросы со StackOverflow
- Репозитории GitHub
Управление подписками (ссылками) происходит через чат с ботом в Telegram. При новых изменениях в чат отправляется уведомление.

Сервисы будут общаться между собой как напрямую (по HTTP), так и асинхронно (очередь сообщений). Для хранения данных будет использоваться СУБД PostgreSQL.

# Список зависимостей проекта
- spring-boot-dependencies
- spring-cloud-dependencies
- testcontainers-bom
- lombok
- junit-jupiter-engine
- hamcrest
- mockito-core
- liquibase-core
- spring-boot-starter-jdbc
- spring-boot-starter-validation
# Запуск проекта
- Склонировать проект
```bash
git clone git@github.com:Stasik371/TinkoffBot.git
cd TinkoffBot
```
- Запуск docker-compose
```docker
docker compose up -d
```
