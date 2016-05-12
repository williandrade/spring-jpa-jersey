# Skills in Spring, JPA and Jersey
Here you will find a scaffold of a project that aims to expose a REST service to list cities.
You need to implement this service using the technologies available on the POM file.

- Java
- REST with Jersey2
- JPA
- Spring 4 / Spring-boot
- Maven

#Database
The actual implementation uses H2 in memory as the database. You will find also the scripts 
for MySQL. The scripts insert a few entries in each table.

#Proposed exercise
The candidate must:
- Create Entity classes for the tables, including relationships
- Create the DAO classes to access Data. Feel free to use whatever method you like(Criteria, JPQL, Spring-data, etc).
- Create "Domain" classes, to mark JPA Sessions. This will be a layer to separate Controller layer from the model
- Create a GET REST service to retrieve the list of cities in the database, and return them as a JSON object.
- The service may receive the query param "country" as a String, to restrict the search. The parameter may be part of the Country name

   http://server:port/rest/cities[?country=name]

Feel free to modify the files included, add or remove packages, in every aspect you want. Just check the note regarding JUnit tests below.

#Expected results
After the implementation, the application should run after the following command line:

	java -jar target/spring-jpa-jersey.jar
    
or 

    mvn spring-boot:run
    
or deploy on Tomcat, or Jetty or an Application Server, as long as you include instructions for the deploy.


Then, open a browser and type :

    http://localhost:8090/rest/cities?country=Uni


It must return, at least the following (ids may vary) :

    [
        {
            "id":86,
            "name":"New York",
            "country":{
                "id":2,
                "name":"United States"
            }
        },
        {
            "id":87,
            "name":"Los Angeles",
            "country":{
                "id":2,
                "name":"United States"
            }
        },
        {
            "id":88,
            "name":"Atlanta",
            "country":{
                "id":2,
                "name":"United States"
            }
        }
    ]


#Unit tests

Included you will find JUnit tests, with commented lines. Those tests must run after the lines
are uncommented.

