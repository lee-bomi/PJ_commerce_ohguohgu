FROM openjdk:17-jdk
COPY build/libs/ohguohgu.jar ohguohgu.jar
ENTRYPOINT ["java", "-DSpring.profiles.active=prod", "-jar", "ohguohgu.jar"]
