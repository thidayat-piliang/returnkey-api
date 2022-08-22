1. run application : gradlew bootRun / ./gradle bootRun
2. test application
    a. using swagger, http://localhost:8080/swagger-ui/index.htm
    b. direct url

3. url for testing :

   - get All orders : http://localhost:8080/orders
   - pending returns : http://localhost:8080/pending/returns?emailAddres=john%40example.com&orderId=RK-478
   - returns : http://localhost:8080/returns?token=RK-478%7Cjohn%40example.com
   - get return : http://localhost:8080/returns/4028928382c693ea0182c69946df0000
   - set return status : http://localhost:8080/returns/http%3A%2F%2Flocalhost%3A8080%2Freturns%2F4028928382c693ea0182c69946df0000/items/NIKE-7/qc/REJECTED