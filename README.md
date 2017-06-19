
The folder contains mainly AmazonShoe.sql ,WServer.zip,AmazonClient.zip.

Steps for Installation:

1.Creating a database.
First in order to create the amazon database ,run the AmazonShoe.sql in the MySQL work bench. After running this the database of amazonshoes is created. It mainly contains 5 attributes Productid,BrandName,Size,Instock,Price. 

2.Running Client and Server
	
Now unzip the WServer and the WClient folder and then import them in NetBeans.Now in the operation part for the AmazonWS replace all root and root2207 with your SQL user id and passwordNow first right click on WServer and then Deploy.Now go to AmazonClient and in that go to AmazonClass.java. Now right click on Amazonclass and click on ‘Run File’.

3.Output interpretation.
	First it ask for the Unique iD of the customer in order to keep trace of the order placed.
     	Now it ask for the sequence from the user in order to perform operation of placing a shoe order.
In order to go in sequence when entered  1 its displays a list of shoe brands.Now it asks from user to enter the brand from selected shoe brands.It now displays the information of shoes of brand that is selected from the user.Now it again ask for sequence .Inorder to place a order select 2nd option.Now it ask for productid number from the above list shown.If the shoe is not sold then the order is placed.

