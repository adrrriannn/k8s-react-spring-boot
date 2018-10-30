FROM openjdk:8-jre-alpine

EXPOSE 8000

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/indexing/indexing.jar", \
     "--spring.config.location=/etc/indexing/config/application.properties"]
