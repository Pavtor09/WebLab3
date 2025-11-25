# Используем официальный образ WildFly
FROM jboss/wildfly:25.0.0.Final

# Копируем ваш WAR файл в директорию развертывания WildFly
COPY build/libs/JSFExample.war /opt/jboss/wildfly/standalone/deployments/

# Открываем порт WildFly
EXPOSE 8080

# Запускаем WildFly, биндим на все интерфейсы для доступа из контейнера
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
