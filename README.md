## Requirements 

You need Java 8 and Maven.

## Start Application

Run start application script: [runApp.cmd](https://github.com/IleanaDanielescu/Blog/blob/master/runApp.cmd)

## Accessing endpoints

Swagger UI is included in the application. To access it, open a browser and enter the following URL: http://localhost:8080/swagger-ui.html

## Available endpoints

Endpoint | Action | Params | Desciption
------------ | ------------- | ------- | -------
/api/articles/| GET || Retrieves all articles in the database
/api/articles/| POST |![image](https://user-images.githubusercontent.com/70023092/91549702-016f6100-e930-11ea-9912-d900c99681af.png)| Creates a new article and notifies subscribers using NotificationService
/api/articles/| PUT | ![image](https://user-images.githubusercontent.com/70023092/91548454-10551400-e92e-11ea-972e-6947a07d9e5b.png) | Update an existing article  and notifies subscribers using NotificationService
/api/articles/| DELETE |![image](https://user-images.githubusercontent.com/70023092/91548590-4397a300-e92e-11ea-8e21-73ad78b1ed8d.png) | Delete an article.
/api/articles/getPaginated| GET|![image](https://user-images.githubusercontent.com/70023092/91548753-89546b80-e92e-11ea-8c9f-82bf884f9ea4.png)| Retrieves an article page. First page has index 0. If you want to receive the first page, pageNumber parameter should be 0.
/api/articles/searchTitle/{title}|GET|![image](https://user-images.githubusercontent.com/70023092/91548666-64f88f00-e92e-11ea-9bc9-babc334686d6.png)| Search article by title
/api/comments/{articleId}| GET |![image](https://user-images.githubusercontent.com/70023092/91553072-8315bd80-e935-11ea-9d37-b9fe3a7346f0.png)| Retrieves all comments for a given article
/api/comments/{articleId}| POST|![image](https://user-images.githubusercontent.com/70023092/91553132-96288d80-e935-11ea-824a-daa3eddb3a5e.png)![image](https://user-images.githubusercontent.com/70023092/91553209-b0626b80-e935-11ea-8b07-9ce2e32f18b8.png)| Add comment to article
/api/comments/{commentId}| PUT |![image](https://user-images.githubusercontent.com/70023092/91553291-d12ac100-e935-11ea-9b0d-3cc3c14b2439.png)![image](https://user-images.githubusercontent.com/70023092/91553321-e0117380-e935-11ea-8744-a10878356a77.png)| Update comment
/api/comments/{id}| DELETE |![image](https://user-images.githubusercontent.com/70023092/91553351-ec95cc00-e935-11ea-8786-0ab9fc494ec9.png)| Delete comment
/api/subscription/| POST|![image](https://user-images.githubusercontent.com/70023092/91554569-0b955d80-e938-11ea-9b69-f575483d7d25.png)| Subscribe endpoint


## Authentication

Create/update/delete article endpoints need authentication. Authentication type is Basic Auth. For authentication, I used Spring Security In-Memory Authentication.
#### Credentials:
user:blogger
pass:1234

## Database

The application use h2 in-memory database. The database is created using a Code-First approach. 

At runtime, two articles are inserted in the database: 
![image](https://user-images.githubusercontent.com/70023092/91554048-097ecf00-e937-11ea-9efd-0aed88638e08.png)


## Notification Service

When an article is create or updated, an ArticleEvent is published. ArticleEventListener asynchronous calls sendNotifications method from NotificationService when an ArticleEvent is published. 

In sendNotifications, all subscribers are taken from the database. Method getNotificationStrategies is called for each subscriber. It decides which type of notification (SMS or email) will be sent.

The implementations for each type of notification is not implemented. It only writes log containing information regarding article and notification type.


