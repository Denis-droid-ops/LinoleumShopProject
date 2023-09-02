# LinoleumShopProject
linoleum accounting and sales database
----------------------------
## Что представляет собой этот проект?

Проект представляет собой CRUD web приложение с использованием базы данных, которое автоматизирует работу магазина по продаже линолеума и сопутствующие ей процессы

## Какие функции предоставляет?

-Регистрация, аутентификация и авторизация пользователей(Сущность Users), использование ролей(USER, ADMIN)

-Просмотр каталога товаров(т.е линолеума, сущность Linoleums), сортировка товаров по некоторым характеристикам, фильтрация товаров по цене, возможность изменения/удаления/добавления товаров. Последняя функция доступна только для администратора.

-Создание клиентских заказов(Cущность Order), предварительный подсчет суммы заказа(без сохранения в БД), хранение заказов в базе данных, обработка заказов(обработкой занимается только администратор)

-Поиск размеров линолеума без замеров по адресу(если адрес имеет стандартную планировку), возможность выбора размеров отдельных комнат, возможность расчета суммы без привязки к адресу

-CRUD операции для шаблонных размеров(Fragments, которые используются для поиска замеров по адресу), для планировок(LayoutsNames, все планировки привязаны к фрагментам и шаблонам). Доступно только для администратора.

-CRUD операции для самих шаблонов(сущность Layouts, смотреть ER диаграмму). Доступно только для администратора.

-Удаление аккаунта пользователя, изменение роли пользователя(доступно только для администратора)

-Работа со складом: добавление/удаление рулонов(Сущность Roll) линолеума; расчет номера рулона, от которого необходимо отрезать заказ. Доступно только для администратора.

## Какие технологии, библиотеки использованы?
Java, JavaEE, Apache Tomcat, Maven, JDBC, PostgreSQL, H2 database, JUnit 5, Mockito, AssertJ, HTML, JSP, JSTL, Bootstrap 4, SLF4J, Logback

## ER диаграмма базы данных проекта

![Image](https://github.com/Denis-droid-ops/LinoleumShopProject/blob/master/erd.png)
Ссылка на диаграмму в drawsql.app: https://drawsql.app/teams/firamir-s-team/diagrams/linoleumshopdiagramm
