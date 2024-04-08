# CriteriaQuery in Spring boot
The project aims to develop a dynamic search functionality for 
Phone objects using specifications in Java, while also demonstrating the utilization of criteria queries with Spring Data JPA. 
The system utilizes the 
concept of specifications to construct complex queries dynamically 
based on user-defined criteria. Each specification represents a set 
of conditions that filter Phone objects according to specific attributes 
such as price, model, year and so n.

The implementation revolves around the use of the Specification pattern, 
which allows for the creation of composable query predicates. 
This enables flexible and efficient querying of the database without 
the need to hardcode individual search scenarios.


## How to start:
**1.** Set up Environment Database Connection Variables:
Before starting the application, make sure to set up the environment variables for 
database connection, including DB_URL, DB_USERNAME, and DB_PASSWORD. These variables should be configured to provide
the necessary credentials and connection details for accessing your database.

**2.** Set Up Schema in Database:
Ensure that you have set up the appropriate schema in your database as specified in 
the DATASOURCE_URL. The schema should match the configuration to allow the application 
to interact with the database correctly.

**3.** Start the Application:
Upon starting the application, test data will be automatically inserted into the 
database. This data will be used for testing and demonstration purposes within the 
application.

**4.** Example of Requests:
After the application has started successfully, you can send GET requests to the 
specified endpoints to retrieve filtered data.

 - <span style="color: #808080">GET /phones</span> : Retrieve all phones with pagination. By default, the number of items per page is 10 units, sorted in 
ascending order by the producer parameter.
 - <span style="color: #808080">GET /phones?producer=Xiaomi&color=green&price=150,&year=2022</span> :  
Retrieve phones based on specified parameters producer is Xiaomi, color is Green, price from 150 and year is 2022.
By following these steps, you can start the application, interact with its endpoints, and retrieve filtered phone data according to your criteria.
 - <span style="color: #808080">GET /phones?producer=Xiaomi,Samsung&color=green,white&price=150,800&year=2022,2023,2024</span> : Also you can pass several parameters in the filter. 
