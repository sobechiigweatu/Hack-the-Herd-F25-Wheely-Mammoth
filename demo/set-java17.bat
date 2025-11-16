@echo off
REM Simple Java 17 finder - avoids parsing issues
echo ========================================
echo Java 17 Setup Helper
echo ========================================
echo.

echo Searching for Java 17...
echo.

REM Try PowerShell script instead
powershell -ExecutionPolicy Bypass -File "%~dp0set-java17.ps1"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Java 17 has been set for this session.
    echo You can now run: gradlew.bat bootRun
    echo.
) else (
    echo.
    echo Java 17 not found.
    echo Please install Java 17 from: https://adoptium.net/temurin/releases/
    echo.
)

pause
