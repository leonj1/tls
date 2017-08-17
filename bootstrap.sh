#!/bin/bash

java ${JAVA_OPTS} -jar /app/tls-1.0-SNAPSHOT.jar --enable.ssl=true --http.server.port=443

