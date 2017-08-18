#!/bin/bash

java ${JAVA_OPTS} -Dlog4j.configuration=log4j2.xml -jar /app/tls-1.0-SNAPSHOT.jar --enable.ssl=true --http.server.port=443

