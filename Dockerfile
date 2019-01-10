FROM maven:3.5-jdk-8-alpine


# Install cURL
RUN echo -e "https://mirror.tuna.tsinghua.edu.cn/alpine/edge/main\n\
https://mirror.tuna.tsinghua.edu.cn/alpine/edge/community" > /etc/apk/repositories

EXPOSE 9997

ADD target/traveltool-0.0.1-SNAPSHOT.jar /root/


WORKDIR /root


ENTRYPOINT ["java","-Duser.timezone=GMT+08","-jar", "traveltool-0.0.1-SNAPSHOT.jar"]