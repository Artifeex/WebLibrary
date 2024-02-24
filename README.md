# Веб-Библиотека
Проект состоит из двух веток:
1) jdbc-template: функционал 1-5, реализованный с помощью JDBCTemplate
2) spring-jpa: функционал 1-9, реализованный с помощью Hibernate и Spring Data JPA

Использованные технологии:
1) Spring Core
2) Spring MVC
3) PostgreSQL
4) Thymleaf
5) Hibernate
6) Spring Data JPA

Основной функционал:
1) Добавление и редактирование книг
2) Добавление и редактирование книг
3) Выдача(назначение) книги человеку
4) Доступ к информации о владельце книги. При возвращении книги в библиотеку установка статуса книги как свободная
5) Получение информации о том, какими книгами владеет человек
6) Поиск книги по названию (началу названия)
7) "Просроченность" книг. Когда человек забирает книгу, то в БД сохраняется время, когда он это сделал. Если после 10 дней человек не вернул книгу, то в его профиле "просроченнная" книга помечается красным цветом
8) Сортировка книг по дате публикации
9) Пагинация (в запросе можно указать страницу и количество книг на странице)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/1618ca8c-2810-4af4-8c28-d0b9337e498c)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/276adf7f-a163-4b90-a170-08bedc8cd7f7)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/5eb0ef29-903e-4486-aa1a-e6fd261421a4)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/a293d1d3-a4a8-4c10-8a45-09e2be85e6d1)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/aa562c37-004f-4c86-a870-d3b4b8f228b9)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/1ade955d-cafc-4f4f-9fcc-fd1156306f8a)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/4addd0ac-c15b-4245-bf83-3a98ccaf4dad)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/374daf77-6f6a-44f0-a1fe-7971c49f773a)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/03985020-98f3-488c-96fc-6939ded7aae5)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/92ae1813-4083-4bda-8103-c8b1a3eeb389)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/b5e1706c-cf04-445f-9b0e-7b63d6b08a81)

![image](https://github.com/Artifeex/WebLibrary/assets/71323102/4ccf4670-3e8a-47d6-a507-05364188348d)


