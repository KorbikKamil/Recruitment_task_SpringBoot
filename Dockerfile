FROM openjdk:13-alpine3.10
ADD target/job-0.0.1-SNAPSHOT.jar .
EXPOSE 8181
CMD java -jar job-0.0.1-SNAPSHOT.jar