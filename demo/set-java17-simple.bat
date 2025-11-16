@echo off
REM Ultra-simple Java 17 finder - no complex parsing
echo Searching for Java 17...
echo.

REM Check if Adoptium folder exists
if exist "C:\Program Files\Eclipse Adoptium\" (
    echo Checking C:\Program Files\Eclipse Adoptium\...
    dir /b /ad "C:\Program Files\Eclipse Adoptium" | findstr /i "jdk-17" >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo Found Java 17 folder!
        echo.
        echo Please manually set JAVA_HOME:
        echo   set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot
        echo   set PATH=%%JAVA_HOME%%\bin;%%PATH%%
        echo.
        echo Replace "jdk-17.x.x-hotspot" with the actual folder name.
        echo.
        pause
        exit /b
    )
)

echo Java 17 not found automatically.
echo.
echo Please install Java 17 from: https://adoptium.net/temurin/releases/
echo.
echo Or if already installed, manually set JAVA_HOME:
echo   set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot
echo   set PATH=%%JAVA_HOME%%\bin;%%PATH%%
echo.
pause

