@echo off
echo Testing Gradle Wrapper...
echo.

cd /d "%~dp0"
echo Current directory: %CD%
echo.

if exist "gradle\wrapper\gradle-wrapper.jar" (
    echo ✅ gradle-wrapper.jar found!
    dir "gradle\wrapper\gradle-wrapper.jar"
    echo.
    echo Attempting to run Gradle...
    echo.
    gradlew.bat --version
) else (
    echo ❌ gradle-wrapper.jar NOT FOUND!
    echo Expected location: %CD%\gradle\wrapper\gradle-wrapper.jar
    pause
    exit /b 1
)

pause

