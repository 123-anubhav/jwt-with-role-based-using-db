# SpringBoot_JWT-App-ROLE-BASED-With-MYSQL
# URL's

1) http://localhost:8080/api/register
 {
  "uname": "anubhav",
  "pwd": "anubhav",
  "phno": 78543210,
  "roles": ["USER"] or ["ADMIN"] -> ON YOUR REQUIREDMENT BASES  
}

2) http://localhost:8080/api/login
 {
  "uname": "anubhav",
  "pwd": "anubhav"
}


you will get token for access secure apis like this output token
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmhpIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTcyOTUyODQyMiwiZXhwIjoxNzI5NTY0NDIyfQ.09bjz_FAgixkyYy9MU7ZUaTIJ3bulMcrBkMhhy7IyZg
 
3)  http://localhost:8080/api/admin/adminRoleWelcome  -> for access ADMIN ROLE URL

you go to postman go to authorization in that Auth Type - Bearer Token and in right hand token input side put your token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmhpIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTcyOTUyODQyMiwiZXhwIjoxNzI5NTY0NDIyfQ.09bjz_FAgixkyYy9MU7ZUaTIJ3bulMcrBkMhhy7IyZg then you will receive response of requesting rest api.



IF YOU DONT PASS TOKEN THEN 403 WILL COME AND ALSO IF USER WANT TO ACCESS ADMIN ROLE THEN ALSO 403 FORBIDDEN COME BECAUSE NORMAL USER CAN'T ACCESS ADMIN REST API
