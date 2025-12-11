# 📦 PROJEKT-ÜBERSICHT

## Erstellte Dateien

### Java-Quelldateien (src/)
1. **Question.java**
   - Repräsentiert eine einzelne Multiple-Choice-Frage
   - Enthält: Thema, Fragetext, Antwortoptionen, korrekte Antwort, Erklärung
   - Methoden zur Validierung und Formatierung

2. **QuestionBank.java**
   - Zentrale Verwaltung aller Fragen
   - 42 Multiple-Choice-Fragen über 10 Themen
   - Methoden zum Filtern nach Themen und zufälligem Mischen

3. **Mathematik1Quiz.java**
   - Hauptprogramm mit Benutzeroberfläche
   - Interaktives Menüsystem
   - Quiz-Modi: Thema, Zufällig, Vollständig
   - Bewertungssystem und Ergebnisanzeige

### Kompilierte Dateien (automatisch erstellt)
- Question.class
- QuestionBank.class
- Mathematik1Quiz.class

### Dokumentation
1. **README.md** - Vollständige Projektdokumentation
2. **SCHNELLSTART.md** - Kurzanleitung zum Starten
3. **PROJEKT_UEBERSICHT.md** - Diese Datei

### Start-Skripte
1. **run.sh** - Bash-Skript für macOS/Linux (ausführbar)
2. **run.bat** - Batch-Skript für Windows

## 🎓 Themen und Fragen

### 1. Komplexe Zahlen (4 Fragen)
- Polarform und Betrag
- Multiplikation komplexer Zahlen
- Konjugiert komplexe Zahlen
- Lösen von Gleichungen in ℂ

### 2. Vektoren (4 Fragen)
- Skalarprodukt berechnen
- Orthogonalitätsbedingung
- Betrag eines Vektors
- Kreuzprodukt und seine Eigenschaften

### 3. Matrizen (4 Fragen)
- Determinante berechnen (2×2)
- Invertierbarkeit
- Matrixmultiplikation
- Transponierte Matrix

### 4. Lineare Gleichungssysteme (3 Fragen)
- Gauß-Elimination
- Anzahl der Lösungen
- Rang einer Matrix

### 5. Induktion (2 Fragen)
- Schritte des Induktionsbeweises
- Gauß-Summenformel

### 6. Funktionen - Grundlagen (4 Fragen)
- Definitionsmenge bestimmen
- Injektivität
- Gerade Funktionen
- Bijektivität

### 7. Grenzwert und Stetigkeit (4 Fragen)
- Grenzwerte bei x→∞
- Stetigkeitsdefinition
- Wichtiger Grenzwert: lim(x→0) sin(x)/x
- Unstetige Funktionen

### 8. Differentiation (5 Fragen)
- Potenzregel
- Kettenregel
- Ableitung von sin(x)
- Produktregel
- Extremstellen bestimmen

### 9. Integration (4 Fragen)
- Stammfunktion von x²
- Bestimmtes Integral berechnen
- Integral von cos(x)
- Hauptsatz der Differential- und Integralrechnung

### 10. Zahlentheorie (4 Fragen)
- Größter gemeinsamer Teiler (ggT)
- Kleinstes gemeinsames Vielfaches (kgV)
- Modulo-Rechnung
- Teilerfremdheit

## 🎯 Features

✅ **42 sorgfältig ausgewählte Fragen** aus allen Themenbereichen
✅ **Detaillierte Erklärungen** zu jeder Antwort
✅ **Flexible Quiz-Modi** (Thema, Zufällig, Vollständig)
✅ **Sofortiges Feedback** nach jeder Frage
✅ **Automatische Bewertung** nach österreichischem Notensystem
✅ **Übersichtliche Benutzeroberfläche** mit ASCII-Grafiken
✅ **Plattformunabhängig** (Windows, macOS, Linux)
✅ **Keine Dependencies** - nur Java Standard Library

## 📊 Statistiken

- **Gesamtzeilen Code**: ~450 Zeilen
- **Anzahl Klassen**: 3
- **Anzahl Fragen**: 42
- **Anzahl Themen**: 10
- **Durchschnittliche Fragen pro Thema**: 4.2

## 🚀 Nächste Schritte

1. **Starte das Quiz**: `./run.sh` oder `java Mathematik1Quiz`
2. **Übe gezielt**: Wähle Themen, in denen du dich verbessern möchtest
3. **Teste dich**: Mache das vollständige Quiz vor der Prüfung
4. **Erweitere**: Füge eigene Fragen in QuestionBank.java hinzu

## 💡 Erweiterungsmöglichkeiten

- Zeitlimit pro Frage hinzufügen
- Highscore-System implementieren
- Lernstatistiken speichern
- GUI mit JavaFX oder Swing erstellen
- Export der Ergebnisse als PDF
- Mehr Fragen zu jedem Thema hinzufügen

## 📝 Wartung

**Neue Fragen hinzufügen:**
1. Öffne `src/QuestionBank.java`
2. Finde die entsprechende Themen-Methode (z.B. `addComplexNumberQuestions()`)
3. Füge neue Fragen mit `addQuestion()` hinzu
4. Neu kompilieren: `javac *.java`

**Themen anpassen:**
- Ändere die Topic-Strings in den `addQuestion()` Aufrufen
- Das System passt sich automatisch an

---

**Erstellt für**: Studierende der Hochschule Campus Wien
**Kurs**: Mathematik 1 (M1VO CSDC28VZ WS2025/26)
**Letzte Aktualisierung**: Dezember 2025

🎓 **Viel Erfolg bei der Prüfung!**
