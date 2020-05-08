FROM java:8-jre
MAINTAINER Nicola Gaffurini

ADD ./target/vwleads.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/vwleads.jar"]

EXPOSE 8899