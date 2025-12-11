@echo off
REM Mathematik1 Quiz - Windows Compile and Run Script

echo ╔══════════════════════════════════════════════════════════╗
echo ║    MATHEMATIK 1 QUIZ - Compilation and Startup           ║
echo ╚══════════════════════════════════════════════════════════╝
echo.

REM Navigate to source directory
cd /d "%~dp0\src"

REM Compile
echo 📦 Kompiliere Java-Dateien...
javac *.java

if %errorlevel% equ 0 (
    echo ✓ Kompilierung erfolgreich!
    echo.
    echo 🚀 Starte Quiz...
    echo ════════════════════════════════════════════════════════════
    echo.
    
    REM Run
    java Mathematik1Quiz
) else (
    echo ✗ Kompilierung fehlgeschlagen!
    echo Bitte überprüfe die Fehlermeldungen oben.
    pause
    exit /b 1
)

pause
