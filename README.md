# LinoleumShopProject
linoleum accounting and sales database
----------------------------
## Что представляет собой этот проект?

Проект представляет собой CRUD web приложение, которое автоматизирует работу магазина по продаже линолеума и сопутствующие ей процессы

## Какие функции предоставляет?

-Регистрация, аутентификация и авторизация пользователей, использование ролей(USER, ADMIN)

-Просмотр каталога товаров, сортировка товаров по некоторым характеристикам, фильтрация товаров по цене, возможность изменения/удаления/добавления товаров. Последняя функция доступна только для администратора.

-Создание клиентских заказов, предварительный подсчет суммы заказа(без сохранения в БД), хранение заказов в базе данных, обработка заказов(обработкой занимается только администратор)

-Поиск размеров линолеума без замеров по адресу(если адрес имеет стандартную планировку), возможность выбора размеров отдельных комнат, возможность расчета суммы без привязки к адресу

-CRUD операции для шаблонных размеров(фрагментов, которые используются для поиска замеров по адресу), для планировок(все планировки привязаны к фрагментам и адресам). Доступно только для администратора.

-CRUD операции для самих шаблонов(сущность Layouts, смотреть ER диаграмму). Доступно только для администратора.

-Удаление аккаунта пользователя, изменение роли пользователя(доступно только для администратора)

-Работа со складом: добавление/удаление рулонов линолеума; расчет номера рулона, от которого необходимо отрезать заказ. Доступно только для администратора.
