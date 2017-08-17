#!/bin/bash

mvn clean package
docker build -t tmp/tls .

