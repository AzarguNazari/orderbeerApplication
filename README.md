### Minimal setup:

- Check if `JAVA_HOME` path variable exists in your system if not set it 
- Open the `gradle.properties` file and update the path for Glassfish server
- Execute the global build via `gradlew build`
- Deploy the EAR from `ear/build/libs` inside Glassfish, e.g., copy to `%GLASSFISH_HOME%\glassfish\domains\domain1\autodeploy`
- Execute `gradlew initServer` task at least once
- Open `http://localhost:8080/frontend` to see the initial UI of the frontend project


### Configuration

- If you are using windows, download glassfish 5 and extract it on C:\glassfish5
- Keep your gradle version 6 or higher than 6


### Project Description
For description of classes, methods and so on, see javadoc comments

#### backend
    * `src/main/java`
        * `de.uniba.dsg.dsam.backend`
            * beans (Consist all stateful/ stateless bean)
            * entities (Entities for JPA)
        * resources
            * META-INF
                * persistence.xml (For JPA related configuration)
#### frontend
    * `src/main/java`
        * `de.uniba.dsg.dsam.client` (Consists all the servlets which are handling application request and response).
        * webapp (Consists all the jsp files)
            * images (images for frontend)
            * WEB-INF
                * web.xml (Mapping specific web request url's to servlet's)
#### shared
    * `src/main/java`
        * `de.uniba.dsg.dsam`
            * model (Consists all DTO's)
            * persistence (Interface's for application beans)