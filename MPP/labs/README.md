# Movie rental
A movie rental shop manages information about movies and clients.
Create an application which allows to:
perform CRUD operations on movies and clients
rent movies
filter entities based on various criteria
reports: e.g. find the most rented movie

## Lab 2-4
design the solution for your problem using a CASE tool (use cases, class diagram, sequence diagram for each use case)
use feature driven development
layered architecture 
data validation
all functions will be documented and tested
 
use Java 8 features (lambda expressions, streams etc); the program should be written without if statements and loops
persistence: ‘in memory’, text files, xml, db (jdbc); you may use almost any RDBMS, but we only offer support for PostgreSQL; MS SQL Server is forbidden
 
## Lab 5: networking
- convert your project to a client-server application using sockets
- simulate an RPC server (messages sent between the client and the server must be handled in a unitary manner (simulate RPC calls) )
- the server must be concurrent; use Java 8 language features; threads with ExecutorService
- on the client side, service calls must be non-blocking
- using external libraries for RPC is not allowed; only sockets
- only the db (jdbc) persistence is needed.
 
## Lab 6: remoting
- convert your project (Lab 5) from a client-server application using sockets to an application using RMI
- use Spring remoting
- data must be persisted to a database; use JdbcTemplate (Spring)
- use Gradle for dependency management
- xml configuration for Spring is not allowed; annotations and Java Config classes only
 
## Lab 7-10: Angular 2, Spring, JPA (Hibernate)
- convert your project (Lab 2-4) to a web application 
- use Angular 2
- use Spring --- xml config forbidden
- use Spring Data JPA (Hibernate) --- xml config forbidden
- log messages using SLF4J
-> for lab7: only one feature is enough - show the list of entities (e.g. clients)
-> for lab 10: all features (the iteration plan and specific and other requirements will be posted on the forum in the following weeks)
 
## Lab 11 [OPTIONAL LAB]: Validation
Validate data using:
JSR 303/349 bean validation
Angular form validation
 
## Lab 12: handling the n + 1 select problem
Continue working on the project from Lab 10-11 
All associations must be lazily loaded
Query the entities using:
Native SQL: org.hibernate.Query
JPQL: javax.persistence.Query
JPA Criteria API
Spring Queries with Named Entity Graphs
Note: you may choose one repository method and implement it using, in turn, each of the above mentioned query approaches; the rest of the repository methods may be implemented using any of the above mentioned query approaches.
 
