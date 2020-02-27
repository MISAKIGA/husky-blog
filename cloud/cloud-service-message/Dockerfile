FROM java:8
VOLUME /tmp
ADD scm.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]