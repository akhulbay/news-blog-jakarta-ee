# news-blog-jakarta-ee

A ***news blog*** implements authorization. There are pages for login and registration. It is possible to add new news for registered users. Also, they can add comments to the news. If the user is not authorized, he can only view the news and comment on this news without the possibility of adding.

The application implements roles. The administrator has the ability to delete both news and comments. The admin also has access to a page with a list of all users. The admin can block users by specifying the reason for blocking. Accordingly, after blocking, the user cannot log in through his blocked account. It is also possible to unlock the user.

A search is implemented on the news page.

____
## Was used in the project:
+ HTTP Servlets
+ JDBC (PostgreSQL)
+ Bootstrap 
____

## Some screenshots:
News page:
![image](https://user-images.githubusercontent.com/117244670/234934252-501a190f-6c68-4c51-83a9-a6a220feb788.png)
News details:
![image](https://user-images.githubusercontent.com/117244670/234934346-37655a71-ee72-4c06-a951-46ec42a5e850.png)
Add news page:
![image](https://user-images.githubusercontent.com/117244670/234934506-869fdbb2-21f8-415e-a861-121e7576aa36.png)
Admin view (able to delete news and comments):
![image](https://user-images.githubusercontent.com/117244670/234934830-f74efbf8-f599-4011-a8fd-32445f70ca5e.png)
Admin also can watch all the users:
![image](https://user-images.githubusercontent.com/117244670/234935053-bbdaa679-eb6b-4490-b0ba-730594651c16.png)
Detailed information about user:
![image](https://user-images.githubusercontent.com/117244670/234935188-9fc16041-4091-4b0d-b305-455101fdd6fb.png)
Blocked users list:
![image](https://user-images.githubusercontent.com/117244670/234935358-d67229b3-e78f-40b6-8ed1-14883a947595.png)
After blocking user is not allowed to auth to his/her account:
![image](https://user-images.githubusercontent.com/117244670/234935609-7f832dd0-5475-40c4-9b1d-daa03e5cbf8f.png)
