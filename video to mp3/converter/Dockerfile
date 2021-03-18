FROM openjdk:8
RUN apt-get update && \
      apt-get -y install sudo
RUN sudo curl -L https://yt-dl.org/downloads/latest/youtube-dl -o /usr/local/bin/youtube-dl 
RUN sudo chmod a+rx /usr/local/bin/youtube-dl
COPY ./target/converter-0.0.1-SNAPSHOT.jar converter-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","converter-0.0.1-SNAPSHOT.jar"]