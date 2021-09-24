FROM eclipse-temurin:11
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD ./ $HOME
RUN dos2unix mvnw && ./mvnw dependency:go-offline