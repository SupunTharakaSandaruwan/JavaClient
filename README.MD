# Java client

This repository contains a java client which can be used to terminate the connection with BE based on the response bytes received by the client

## Getting Started
### 1. Prerequisites
Make sure you have the following prerequisites installed on your machine:

- Java
- Maven
### 2. Clone the Repository

```
git clone https://github.com/SupunTharakaSandaruwan/javaClient.git
```

### 3. Build the Project
Navigate to the project directory and build the project using Maven:
```
mvn clean install -Dmaven.test.skip=true
```
### 4. Copy the JAR
Copy the generated JAR file from the target/ directory to a separate location.

### 5. Create Properties File
Create a properties file named config.properties under the same location where the .jar file is located and insied the above properties file, define the following details.


 1. Edit the config.properties file and define the file upload path:

   ```
   server.address=<BE_HOST>
   server.port=<BE_PORT>
   resource.url=<RESOURCE_URL>
   response.complete=false
   #if you want to get the complete response set the response.complete value to true
   bytes.to.read=1000000
   #if you want to terminated the connection based on the bytes received  set the response.complete value to false and set the number of bytes in bytes.to.read
   use.https=false
   # Only support http
   authorization.enabled=true
   # if your BE with Oauth2 protection set the authorization.enabled to true else to false
   authorization.token=<your_access_token>
   ```

### 6. Run the Application
Run the application using the generated JAR file:
```
java -jar javaClient-1.0-SNAPSHOT.jar
```
Replace <your-jar-filename> with the actual name of the JAR file.


* Contributors
Supun Tharaka Sandaruwan
Feel free to contribute or report issues!
