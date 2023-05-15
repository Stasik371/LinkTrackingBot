FROM openjdk:17
ARG bot_token
WORKDIR /app/bot
COPY bot/target/bot-1.0-SNAPSHOT.jar bot.jar
ENV BOT_TOKEN=$bot_token
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "bot.jar"]