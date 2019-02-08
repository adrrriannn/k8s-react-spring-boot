FROM openjdk:8-jre-alpine

EXPOSE 8000

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/auth/auth.jar", \
     "--spring.config.location=/etc/auth/config/application.properties"]
