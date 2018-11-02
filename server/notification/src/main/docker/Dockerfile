FROM openjdk:8-jre-alpine

EXPOSE 8000

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/notification/notification.jar", \
     "--spring.config.location=/etc/notification/config/application.properties"]
