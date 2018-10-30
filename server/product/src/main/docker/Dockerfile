FROM openjdk:8-jre-alpine

EXPOSE 8000

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/product/product.jar", \
     "--spring.config.location=/etc/product/config/application.properties"]
