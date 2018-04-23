# customer-api
This dockerized api is created using springboot , gradle and mongodb.


Endpoints:

*GET:*

 1) /age?age=<age>
 2) /id?customerId=<customerId>
 3) /showall (shows all data from customers)
 4) /firstname?firstName=<firstname>
 5) /lastname?lastName=<Lastname>
 6) /test ( to check if api is working)
 7) /email?email=<emailid>


*POST:*

   > /addcustomer 
   
   Requestbody: 
    ```
    {
       "customerId": 2,
       "firstName": "Neeraj",
       "lastName": "Agarwal",
       "age": 24,
       "memberships": ["club1","club2"],
       "address": {
       "addressLine1":"409",
       "addressLine2": "Drayton hayes",
       "city": "London",
       "zipcode":"UB3 4FL"
       },
       "email":"nagarwal16@sapient.com",
       "contacts": [
       {"name": "Balram","mobile": "123456789","relation":"friend"},
       {"name": "Sugnadha","mobile": "1234567","relation":"friend"},
       {"name": "Rahul","mobile": "123456","relation":"friend"}
       ],
       "gender":"male",
       "work":"Sapient Mns"
       }
       ```

*PUT:*

  > /updatecustomer
  
  Requestbody:  Same json as above ( give only things thats need update).
  
  finds customer with below order and updates the value as per  given json:
  
  firstName > lastname >gender > work > age
   

*DELETE:*


  > /deletecustomer
  
  RequestBody:
   ```
   {
      "customerId": 6	
   } 
  

