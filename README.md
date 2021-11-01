The app can be directly executing the ApplicationContextListener on the IDE.

In order to run on Wildfly, application server, dependencies from the pom need to be excluded.

The database layer, was partly done. In this project configuration, in case a database is to be implemtented, a SQL DB should be used. Additional methods should be added on the Spring Data interfaces, located on the repositories modules. Each repository should correspond to at least an entity, which are located on the models module.

Currently, the integrated test buyItems(), on the web-api module, is not working properly.
