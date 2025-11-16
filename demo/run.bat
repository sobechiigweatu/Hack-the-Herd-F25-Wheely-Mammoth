@echo off
cd /d "%~dp0"
echo Running Spring Boot application...
echo.
gradlew.bat bootRun
pause

