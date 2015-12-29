Time Assistance
A sample spring project.

This project is basically a demonstration of spring 4 framework's web development. Here one can create an account and give entry for an event to get a email notification prior to the time set by user. User can also update his profile information and change, delete the event information.

I have followed here MVC design pattern and included spring security 4 login system which can simply be integrated to more complex project. I should mention here the key features have integrated with this project.

Spring Security 4: Login, Logout, Remember Me function and Password encryption. Role based home page implementation and also role based method level access control. This will clearly specify the boundary where the user can surf.

MVC Design Pattern: The project design structure include Controller Layer, Service Layer and Data Access Object with the help of Java Bean. Configuration and communication between beans is written with Spring bean configuration file.

Hibernate: Here I used Mysql Database (Any Database can be used) and to access the DB I used Hibernate to turn the database into object. Which replace the requirement of hand written complex query.

Database Pool Connection: To optimize the db connection like load shearing, disconnect idle connection I implement here DHCP basic data-source pool connection.

Form Validation: Spring standard form validation is used while creating new user account and when a user create a new event for notification. Here I used two validation group, Form and Hibernate validation group.

Apache Tiles: To manage the JSP pages I used Apache tiles as view resolver. Header, footer, news and menu section is designed and kept separately for re-usability.

RSS Feed: Local news are being parsed from www.abc.net.au rss feed.

Mail Service: Spring simple mail service in integrated to mail the user about his/her saved event prior to the selected duration.

Schedule Task: Schedule demon design for periodically read and update the news feed and check the active status of different events.

Under Progress: Forgotten password, add the event to Google calendar and parsing the thumbnail image from rss feed is under construction.

By
Kamol Kanti Roy
comolroy@gmail.com
www.comolroy.com.au
+61470601092