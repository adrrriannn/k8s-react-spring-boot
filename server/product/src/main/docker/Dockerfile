FROM jre-alpine:8

EXPOSE 8000

COPY assets/ /

CMD ["/usr/bin/java", \
     "-jar", \
     "/usr/k8s-spring-boot/k8s-spring-boot.jar", \
     "--spring.config.location=/etc/k8s-spring-boot/config/application.properties"]
