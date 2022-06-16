FROM openjdk:8
EXPOSE 8089
ADD target/fasthand-api.jar fasthand-api.jar
ENTRYPOINT ["java","-jar","/fasthand-api.jar"]