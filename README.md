
# App UrlShortCut

This application creates a service that allows sites to get short links for their pages
and navigate to them using these short links with recording the number of calls to these pages.
Also, you can get statistics of page calls.


This app from Java course [job4j.ru](https://job4j.ru)


[![Build Status](https://app.travis-ci.com/ikioresko/UrlShortCut.svg?branch=master)](https://app.travis-ci.com/ikioresko/UrlShortCut)
[![codecov](https://codecov.io/gh/ikioresko/UrlShortCut/branch/master/graph)](https://codecov.io/gh/ikioresko/UrlShortCut)


# Build app

Project used PostgreSQL.
You need install PostgreSQL and create db with name 'url' and tables from db/schema.sql.

For example:
```yml
CREATE DATABASE url;
```



# Run app 

For build the project you can use Maven:
```yml
mvn install
```

To run app use:
```yml
java -jar UrlShortCut-1.jar
```


![alt text](https://raw.githubusercontent.com/ikioresko/UrlShortCut/master/images/1.png)


![alt text](https://raw.githubusercontent.com/ikioresko/UrlShortCut/master/images/2.png)


![alt text](https://raw.githubusercontent.com/ikioresko/UrlShortCut/master/images/3.png)


![alt text](https://raw.githubusercontent.com/ikioresko/UrlShortCut/master/images/4.png)


![alt text](https://raw.githubusercontent.com/ikioresko/UrlShortCut/master/images/5.png)