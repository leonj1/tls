FROM anapsix/alpine-java

MAINTAINER Jose Leon

ADD target/tls-1.0-SNAPSHOT.jar /app/
ADD src/main/resources/keystore.jks /secrets/
ADD bootstrap.sh /

ENTRYPOINT ["/bootstrap.sh"]

