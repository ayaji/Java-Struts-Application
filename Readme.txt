______________________________________________________________________________

SMART PORTABLES User Document
______________________________________________________________________________
RUN the program:

1. Start tomcat server
2. Copy "SmartPortables" folder to the tomcat webapp folder.
3. In order to start the application open the browser and type http://localhost/SmartPortables/Index.html
4. Start the MongoDB server with mongo.exe and mongod.exe with database name as smartportables and collection name as userReviews
5. Start the MySQL database with database name as smartportables and table names as users and orderdetails.
6. Products are fetched from Database

----------------------------------------------------------------------------------------------------------------------------------------------------
Note:

To compile Java files, I use this command:

javac -cp "C:/tommy/tomcat-7.0.34-preconfigured/apache-tomcat-7.0.34/lib/mongo-java-driver-3.2.2.jar;C:\tommy\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\servlet-api.jar;C:\tommy\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mysql-connector-java-5.1.23-bin.jar;C:\tommy\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\gson-2.6.2.jar;" *********.java
------------------------------------------------------------------------------------------------------------------------------------------------------
Role Information
______________________________________________________________________________
There are three ROLES:
1.Storemanager
2.Customer
3.Salesman
______________________________________________________________________________

Admin User:
______________________________________________________________________________
User Name: storemanager
Password: storemanager

1. Store Manager can add/update/delete products.
2. Product added by the Store Manager can be viewed by logging in as customer or salesman.
3. Store Manager can check the product inventory graph and details(Report).
4. Store Manager can check which are the products on sale and the products with rebate amount. 
5. Store Manager can check the product sales graph and report.
6. Store Manager can check the daily sales report.

______________________________________________________________________________

Customer:
______________________________________________________________________________
First, Create a new user account as Customer 

Customer can,

1. login to the website and view product
2. Browse different products and add item to cart 
3. Update cart items by increasing the number of products
3. Customers will be able to write a review on particular product
4. Proceed and Check out from shopping cart by providing the user info
5. View the order 
6. Cancel the order
7. view Trending products
8. Will be able to search for products

___________________________________________________________________________

Salesman:
______________________________________________________________________________

First, Create a new user account as Salesman 

Salesman can,

1. login to the website and view product
2. Browse different products and add item to cart 
3. Update cart items by increasing the number of products
3. Customers will be able to write a review on particular product
4. Proceed and Check out from shopping cart by providing the user info
5. View the order 
6. Cancel the order
6. view Trending products
7. Create/Add a new user for the application.

------------------------------------------------------------------------------------------------------------------------------------------------------

