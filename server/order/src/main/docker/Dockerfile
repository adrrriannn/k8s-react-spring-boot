FROM openjdk:8-jre-alpine

EXPOSE 8000

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/order/order.jar", \
     "--spring.config.location=/etc/order/config/application.properties"]
