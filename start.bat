@echo off

@REM Удалил билд и сделал новый
cd "D:\Lab3"
rmdir /S /Q "D:\Lab3\build"
call gradlew build

@REM копируем в папку сервера и запускаем
copy /Y "build\libs\Lab3.war" "D:\wildfly-preview-25.0.0.Final\standalone\deployments\Lab3.war"
cd  "D:\wildfly-preview-25.0.0.Final\bin"
./standalone.bat