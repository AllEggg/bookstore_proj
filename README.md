 Education bookstore project.

 Made by Spring Boot, JPA< Rest, cloud database Heroku (PostgreSQL).

 Implemented in project:
 - users are divided by roles (User / Admin). For example: only admin can create or delete author, meanwhile
every user can see a list of all authors.
 - CRUD requests for authors, books, orders
 - stock control by versions (if there are not enough books in warehouse, you'll get an error message)
 - possibility to add books to order, then to pay an order (it will decrease quantity of books in warehouse)
 - possibility to request sales report

 You can check how it works by Swagger. Just launch an application with your IDE enter the link in your browzer:
 http://localhost:8080/swagger-ui/index.html#/
 User, that has role ADMIN:
    Login: Admin1
    Password: 12345
 User, that has role USER:
    Login: User2
    Password: 12345

 To test sales request you should launch ActiveMQ (https://activemq.apache.org/), then uncomment JMSListener
 at book_store/report/MessageListener.java .

 If you have any suggestions for improvement, I would be grateful.
 
 -----------------------------------------------------------------------------------------------------------------------

Учебный проект книжного магазина.

 Написан с помощью Spring Boot, JPA, Rest, облачной базой данных Heroku (PostgreSQL). 

 В магазине реализовано:
 - разделение пользователей по ролям (User / Admin). Например, создать или удалить автора может только админ,
тем не менее, просмотреть список авторов может любой.
 - CRUD запросы для авторов, книг, заказов
 - контроль остатка книг на складе через версионирование (если книг недостаточно, то отобразится 
соответствующее сообщение)
 - возможность добавить книги в заказ, затем оплатить заказ (это уменьшит количество книг на складе)
 - возможность запросить отчёт о продажах

 Проверить работу можно в Swagger. Для этого после запускf приложения в IDE следует в браузере перейти по ссылке:
 http://localhost:8080/swagger-ui/index.html#/
 Пользователь с ролью ADMIN:
    Login: Admin1
    Password: 12345
 Пользователь с ролью USER:
    Login: User2
    Password: 12345
 
Для того, что бы протестировать отчёт следует включить ActiveMQ (https://activemq.apache.org/), после этого
раскомментировать JMSListener в book_store/report/MessageListener.java .

Если у вас есть предложения по доработке - буду крайне признателен.
