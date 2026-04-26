FROM eclipse-temurin:25-jre
ENV JAR_NAME=ordering.jar
COPY build/libs/$JAR_NAME $JAR_NAME
CMD java $JAVA_OPTS -jar $JAR_NAME