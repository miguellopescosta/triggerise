The app can be directly executed via ApplicationContextListener on the IDE.

In order to run this app on a Wildfly server, some of the dependencies from the pom should exclude inner dependencies.

The database layer, was partly done since the main objective was not implementing a DB. Because we are using JPA, a SQL DB should be used in case we want to complete the implementation. Additional methods should be added on the Spring Data interfaces, located on the repositories modules. Each repository should correspond to at least an entity, which are located on the models module.

Only two Rest services were implemented, for the sole purpose of providing a complete view of the architecture and possibilitate integrated tests.

Currently, all tests, unity and integrated tests, working properly.
