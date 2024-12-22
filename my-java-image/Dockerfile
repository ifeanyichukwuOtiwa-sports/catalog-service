FROM ubuntu:22.04

RUN apt-get update  \
    && apt-get install -y default-jre  \
    && apt-get clean

ENTRYPOINT ["java", "--version"]