FROM openjdk:8
EXPOSE 8762
ADD /target/timesheet-1.0-SNAPSHOT.jar timesheet-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "timesheet-1.0-SNAPSHOT.jar"]