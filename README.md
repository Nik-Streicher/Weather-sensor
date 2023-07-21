# Weather sensor
REST application, developed in the course.

Program for processing data from a weather sensor.
The Sample program registers a new sensor in the database, and sends 1000 posts.

# Usage

Registration of the new sensor\
`POST /sensors/registration`

Adding measurements \
`POST /measurements/add`

Viewing all measurements \
`GET /measurements`

Viewing count of rainy days \
`GET /measurements/rainyDaysCount`

For run Sample, compile and run `Main.java` in consumer package.
