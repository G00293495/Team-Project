# Playlist Explorer App

**A Music Playlist Management App, using CRUD from Azure SQL, developed by _Team Lily_**

## About
A part of ATU's 2nd year Software-Electronic Engineering Java OOP Module's Team Project, this App was developed by Team Lily.
This project was developed with Java (JDK 19) Intellij IDE, Junit, JDBC & Azure SQL & an Azure Server.

## Description
Reading from Azure SQL, this app allows the user to create, update, and delete artists and songs from their music library, 
as well as search for and add new artists. 

Wuth Junit tests of the adding and removing of songs and artists.

## Setup

### Azure Sql

Created an Azure SQL Database and obtained its connection string.

Using a Song and an Artist table in the database, with columns for the relevant data.

Used the JDBC driver for SQL Server to our project's dependencies.

The Connection string to connect to the database using JDBC in our Java code (Driver Manager).

Wrote SQL queries to insert, update, and delete rows in the tables.

### Java

Created a Java project in Intellij IDE & Maeven.

Wrote classes for Song, Artist, appMenu to let the user interact with the console & Interface to handle database operations.

Ysed the JDBC Connection and PreparedStatement classes to execute SQL queries on the database, and parse the results.

We wrote JUnit tests for the database operations to ensure their correctness.

Snippet of connection establishment (note Static Connection, must be outside PSVM)
```
Static Connection conn;

try
        {
            conn = DriverManager.getConnection("jdbc:sqlserver://playlistserver.database.windows.net:1433;database=PlaylistExplorerDB;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;","user","password");
            System.out.println("Database Connection Successful!");
        }
catch (Exception e)
        {
            System.out.println("Connection Failed\n");
        }
```


## Features
Create, update, and delete artists and songs from your library

Search for and add new artists to your library

User-friendly interface & experience

Test-driven development approach using CRUD


### References
https://learn.microsoft.com/en-us/azure/azure-sql/database/connect-query-java?view=azuresql

https://www.mysqltutorial.org/mysql-jdbc-insert/

## Authors
**Mark Veerasingam**
**Richard Olamide**
**Sean Grehan**
**Paddy Downey**
**Lukas Buivydas**
