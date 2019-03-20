# imdb

How to start the imdb application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/sample-dropwiz-app-1.0-SNAPSHOT.jar server config.yml`
1. To check available resources visit swagger-ui page `http://localhost:8080/api/swagger`

---


Available endpoints
---

1. `/api/actors GET` 		- returns collection of Actors
1. `/api/genres GET` 		- returns collection of Genres
1. `/api/movies GET` 		- returns collection of Movies, method contains additional query parameters to narrow down result:
	1. `?release-year` 		- finds movies with year of release equal or higher than given parameter (default value is 1888)
	1. `?duration`			- finds movies with duration in minutes equal or less than given parameter (default value is 2147483647)
	1. `?actorId`			- finds movies with actor among cast with given id
1. `/api/movies/{id} GET` 	- finds movie with given in path param id
1. `/api/movies POST`		- uploads new movie to database
1. `/api/movies PUT` 		- updates existing movie
1. `/api/movies/{id} DELETE` - removes movie with given id from database

---


NoSQL vs SQL ?
---
Answer to this question is SQL.
Relational databases would help with keeping data in predefied tabel structures,
and to get quick access to desired related informations. Example of application like
movie library ideally fits into pattern of SQL, becouse its easy to define model of 
whole application and relation between them. 
When application has to handle a lot of complicated querying, database transactions 
and routine analysis of data its better to stick with a relational database.
In other words i would not choose NoSQL to design model of this task, but i would choose NoSQL to other task 
like searching informations and analysing big text data for example: articles store.