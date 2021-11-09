FROM openjdk:8
EXPOSE 8083
ADD /target/dockertimesheet.jar dockertimesheet.jar
ENTRYPOINT ["java","-jar","dockertimesheet.jar"]
#ADD /target/timesheet-0.0.1-SNAPSHOT.jar timesheet-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","timesheet-0.0.1-SNAPSHOT.jar"]