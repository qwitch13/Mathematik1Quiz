@echo off
REM Mathematik1 Quiz - Windows Compile and Run Script

echo ╔══════════════════════════════════════════════════════════╗
echo ║    MATHEMATIK 1 QUIZ - Compilation and Startup           ║
echo ╚══════════════════════════════════════════════════════════╝
echo.

REM Navigate to project directory
cd /d "%~dp0"

REM Create bin directory if it doesn't exist
if not exist "bin" mkdir bin

REM Compile
echo 📦 Kompiliere Java-Dateien...
javac -d bin src\*.java

if %errorlevel% equ 0 (
    echo ✓ Kompilierung erfolgreich!
    echo.
    echo 🚀 Starte Quiz...
    echo ════════════════════════════════════════════════════════════
    echo.

    REM Run
    java -cp bin QuizApp
) else (
    echo ✗ Kompilierung fehlgeschlagen!
    echo Bitte überprüfe die Fehlermeldungen oben.
    pause
    exit /b 1
)

pause
