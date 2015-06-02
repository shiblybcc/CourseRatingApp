/**********************************************************************************************
 * Steps to follow to deplay .ear on Glassfish server.
 *
 * @author: JLN (junior.lekane.nimpa@rwth-aachen.de)
 ***********************************************************************************************/
 - Install MySQL
 - configure MySQL: User =root, password= admin
 - create a database named "courserating"
 - Install Glassfish Server Open Source Edition 4.1
 - copy the jar "mysql-connector-java-5.1.35.jar" to ${GLASSFISH_INSTALL_DIR}/glassfish4/glassfish/lib
 - Note that we provided this ... (TODO ensure this...)
 - Start Glassfish
 - create a connection pool
   - Specify a name
   - Resource Type: javax.sql.ConnectionPoolDataSource
   - Database Driver Vendor: MySql
   - DataSource ClassName: com.mysql.jdbc.jdbc2.optional.MysqlDataSource
   - Specify the following properties:
     - User          = root
     - password      = admin
     - databaseName  = courserating
     - URL           = jdbc:mysql://localhost:3306/courserating
     - Url           = jdbc:mysql://localhost:3306/courserating
   - When you are done, try to ping to make sure that the setting was successful.
   - If ping failed, then the new connection pool was not configured properly. Try to fix the problem or contact me
 - create JNDI resource.
   - click on JDBC Resources
   - Click New
   - Set the JNDI name to jdbc/courserating
   - Choose the connection pool you created earlier
   - Select enabled
 - Stop Glassfish  "TODO complete this part"
 - copy "courserating.ear" to "" and start Glassfish
 - Open a browser and go the following URL: ""
 - Enjoy the application! 