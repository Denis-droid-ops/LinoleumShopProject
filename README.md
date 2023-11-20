# LinoleumShopProject
linoleum accounting and sales database
----------------------------
## Что представляет собой этот проект?

Проект представляет собой CRUD web приложение с использованием базы данных, которое автоматизирует работу магазина по продаже линолеума и сопутствующие ей процессы

## Какие функции предоставляет?

-Регистрация, аутентификация и авторизация пользователей(Сущность Users), использование ролей(USER, ADMIN).

-Просмотр каталога товаров(т.е линолеума, сущность Linoleums), сортировка товаров по некоторым характеристикам, фильтрация товаров по цене, возможность изменения/удаления/добавления товаров. Последняя функция доступна только для администратора.

-Создание клиентских заказов(Cущность Order), предварительный подсчет суммы заказа(без сохранения в БД), хранение заказов в базе данных, обработка заказов(обработкой занимается только администратор).

-Поиск размеров линолеума без замеров по адресу(если адрес имеет стандартную планировку), возможность выбора размеров отдельных комнат, возможность расчета суммы без привязки к адресу

-CRUD операции для шаблонных размеров(Fragments, которые используются для поиска замеров по адресу), для планировок(LayoutsNames, все планировки привязаны к фрагментам и шаблонам). Доступно только для администратора.

-CRUD операции для самих шаблонов(сущность Layouts, смотреть ER диаграмму). Доступно только для администратора.

-Удаление аккаунта пользователя, изменение роли пользователя(доступно только для администратора).

-Работа со складом: добавление/удаление рулонов(Сущность Roll) линолеума; расчет номера рулона, от которого необходимо отрезать заказ. Доступно только для администратора.

-Валидация входных данных, как на стороне клиента, так и на стороне сервера.

## Какие технологии, библиотеки использованы?
Java, JavaEE, Apache Tomcat, Maven, JDBC, PostgreSQL, H2 database, JUnit 5, Mockito, AssertJ, HTML, JSP, JSTL, Bootstrap 4, SLF4J, Logback

## ER диаграмма базы данных проекта

![Image](https://github.com/Denis-droid-ops/LinoleumShopProject/blob/master/erd.png)
Ссылка на диаграмму в drawsql.app: https://drawsql.app/teams/firamir-s-team/diagrams/linoleumshopdiagramm

## Как запустить проект?

Проект развернут на VDS сервере Ubuntu, поэтому чтобы запустить и посмотреть проект,
достаточно перейти по ссылке:
http://45.11.26.170:8080/

## Тестирование

В проекте написаны юнит тесты для пользовательского слоя(DAO, SERVICE, CONTROLLER(SERVLET))

## Скриншоты приложения

Главная страница:
![Image](https://i.ibb.co/tQ9LVKt/img1.jpg)

Страница аутентификации:
![Image](https://i.ibb.co/Csrw5XX/img2.jpg)

Главная страница с залогиненным администратором:
![Image](https://drive.google.com/uc?export=view&id=1KfbGmJf5hOtJZ0ATwFxv1Kyf8Xj4G5w7)

Страница с таблицей заказов(доступна только администратору):
![Image](https://i.ibb.co/FXKrk5J/img4.jpg)

Страница с таблицей пользователей(доступна только администратору):
![Image](https://drive.google.com/uc?export=view&id=1tvF2SgJc8RHPLlEkClxaLJZetLPbdR2x)

Страница с таблицей рулонов(доступна только администратору):
![Image](https://drive.google.com/uc?export=view&id=1M02ePNJN63gAoo4V8x8_0U8Dl71AhJUX)

Страница с таблицей фрагментов(доступна только администратору):
![Image](https://drive.google.com/uc?export=view&id=1SIJPiqolSItKtSa1htkBdjNCA3sWZ7yD)

Страница с таблицей товаров(доступна только администратору):
![Image](https://drive.google.com/uc?export=view&id=1lvghlnSCnKJP1_yo3DInwwcIUEulNJCs)

Страницы с оформлением заказа:
![Image](https://i.ibb.co/Kr4LKsh/img9.jpg)

![Image](https://i.ibb.co/PzSZBwC/img10.jpg)

![Image](https://i.ibb.co/jDHC0JH/img11.jpg)

![Image](https://i.ibb.co/jDHC0JH/img11.jpg)

![Image](https://drive.google.com/uc?export=view&id=1Gawcedbg0csaIDNWSHsqcjKTrLNdPtrN)

Страница с информацией об отрезаемых кусках и отрезаемых рулонах:
![Image](https://i.ibb.co/s5F5c58/img13.jpg)
   
## Авторы
Telegram: @gilbertGrp

GitHub profile: https://github.com/Denis-droid-ops
