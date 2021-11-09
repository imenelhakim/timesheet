FROM openjdk:8
EXPOSE 8083
ADD /target/timesheet-mysql.jar timesheet-mysql.jar
ENTRYPOINT ["java","-jar","timesheet-mysql.jar"]
#ADD /target/timesheet-0.0.1-SNAPSHOT.jar timesheet-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","timesheet-0.0.1-SNAPSHOT.jar"]