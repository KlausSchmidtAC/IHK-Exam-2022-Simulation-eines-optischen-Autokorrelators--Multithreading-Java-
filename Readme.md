# AKF Simulator

[![Java](https://img.shields.io/badge/Java-18+-orange.svg)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20macOS%20%7C%20Linux-lightgrey.svg)](https://github.com/username/AKF_Simulator)
[![JAR](https://img.shields.io/badge/JAR-Ready-green.svg)](#installation-und-ausführung)

Ein **Java-basierter Autokorrelationsfunktions-Simulator** mit fortschrittlichen mathematischen Algorithmen für die präzise Analyse von Messdaten. Das System nutzt Multi-Threading-Architekturen für optimale Performance und wird über das interaktive Batch-Skript `starte_AKF_Simulation.bat` gestartet.

## 🎯 Überblick

Der AKF Simulator automatisiert komplexe Signalanalyseverfahren und führt präzise Messungen für wissenschaftliche Anwendungen in Physik, Elektrotechnik und Messtechnik durch.

### ✨ Hauptfunktionen

- **📊 X-Wert Glättung**: Konvertierung und Glättung von Eingabewerten mit Pikosekunden-Präzision
- **📈 Y-Wert Normierung**: Normalisierung der Messwerte auf das Intervall (0,1]
- **🔄 Hüllkurven-Bestimmung**: Automatische Erkennung der oberen Einhüllenden
- **⚡ FWHM-Analyse**: Full Width Half Maximum Berechnung für Pulssignale
- **🚀 Multi-Threading**: Parallele Verarbeitung großer Datensätze
- **📁 Batch-Verarbeitung**: Simultane Bearbeitung mehrerer Eingabedateien
- **🗃️ JAR-Distribution**: Portable ausführbare JAR-Datei (`Loesung.jar`)
- **🖥️ Interaktives Batch-Skript**: Benutzergeführte Eingabe über `starte_AKF_Simulation.bat`

## 🔧 Systemanforderungen

### Minimale Anforderungen
- **Java**: JDK 18 oder höher (für Entwicklung) / JRE 18+ (für Ausführung)
- **RAM**: 512 MB
- **Speicher**: 50 MB freier Festplattenspeicher
- **OS**: Windows 10+, macOS 10.14+, Linux (Ubuntu 18.04+)

### Empfohlene Konfiguration
- **Java**: OpenJDK 21 LTS
- **RAM**: 2 GB oder mehr
- **CPU**: Multi-Core für optimale Threading-Performance

## 🚀 Installation und Ausführung

### Option 1: Mit vorhandener JAR-Datei (Empfohlen)

**1. JAR-Datei verwenden:**
- Verwenden Sie die mitgelieferte `Loesung.jar`
- Oder laden Sie die aktuelle Version aus den [Releases](https://github.com/IhrUsername/AKF_Simulator/releases) herunter

**2. Mit interaktivem Batch-Skript starten:**
```cmd
# Doppelklick auf starte_AKF_Simulation.bat
starte_AKF_Simulation.bat
```

**3. Direkte JAR-Ausführung:**
```bash
# Grundlegende Ausführung
java -jar Loesung.jar "eingabe_ordner" "ausgabe_ordner"

# Mit optimierten Parametern
java -Xmx1g -jar Loesung.jar "data/input" "data/output"

# Nur Eingabeordner (Ausgabe im gleichen Verzeichnis)
java -jar Loesung.jar "data/input"
```

### Option 2: JAR-Datei aus Quellcode erstellen

**Wichtiger Hinweis zur Kompilierung:**
Das Kompilieren mit `javac *.java` erfasst **nicht alle** Java-Dateien in Unterordnern!

**1. Schritt-für-Schritt Kompilierung:**
```bash
# Im src/ Verzeichnis:

# 1. Hauptverzeichnis kompilieren
javac *.java

# 2. Alle Unterordner einzeln kompilieren
javac Model/DatenObjekt.java
javac Model/Exceptions/*.java
javac Algorithmus/*.java
javac Controller/*.java
javac Eingabe/*.java
javac Ausgabe/*.java
javac Main/*.java

# 3. Überprüfen ob alle .class Dateien vorhanden sind
dir /s *.class
```

**2. JAR-Archiv erstellen:**
```bash
# JAR mit allen kompilierten Klassen erstellen
jar -cvfe Loesung.jar Main.Main *.class Algorithmus/*.class Ausgabe/*.class Eingabe/*.class Model/*.class Controller/*.class
```

**3. JAR-Datei testen:**
```bash
java -jar Loesung.jar "testdaten" "ergebnisse"
```

## 🖥️ Interaktives Batch-Skript: `starte_AKF_Simulation.bat`

Das Hauptfeature des Projekts ist das benutzerfreundliche Batch-Skript für die interaktive Ausführung.

### **Funktionen des Batch-Skripts:**
- ✅ Interaktive Pfadeingabe für Eingabedaten
- ✅ Optionale Ausgabepfad-Spezifikation  
- ✅ Automatische JAR-Erkennung (`Loesung.jar`)
- ✅ Benutzerfreundliche Eingabeaufforderungen
- ✅ Unterstützung für relative und absolute Pfade

### **Verwendung:**
```cmd
# Einfach per Doppelklick starten
starte_AKF_Simulation.bat
```

### **Beispiel-Interaktion:**
```
Bitte den absoluten oder relativen Pfad zum Ordner mit den Testdatensätzen eingeben.
Standardfall: ./data
HINWEIS:
Falls der Ordner vom Pfad dieser ausgeführten Skriptdatei betrachtet in einem oder 
mehreren Unterordner liegt, die Pfadangabe bitte in der Form 
"./Unterordner1/Unterordner2/.." vornehmen.

Pfad_zum_Eingabe_Ordner: ./Messdaten

Optional: Bitte den gewünschten absoluten Pfad zur Ausgabedatei eingeben.
```

### **Unterstützte Pfadformate:**
- **Relativer Pfad**: `./data`, `data`, `./Unterordner1/Unterordner2`
- **Absoluter Pfad**: `C:\Messdaten`, `/home/user/data`
- **Netzwerkpfad**: `\\Server\Freigabe\Daten`

## 📁 Projektstruktur

```
AKF_Simulator/
├── 🗃️ Loesung.jar                 # Ausführbare JAR-Datei
├── 🚀 starte_AKF_Simulation.bat   # Interaktiver Launcher
├── 📖 README.md                   # Diese Datei
├── 📄 Anweisungen_jar_Erstellung_HTMLDOC_NACH_pdf.txt # Build-Anweisungen
├── 📚 JavaDoc/                    # API-Dokumentation
│   ├── index.html                 # Hauptdokumentation
│   ├── Main/Main.html             # Main-Klasse Dokumentation
│   ├── Algorithmus/               # Algorithmus-Dokumentation
│   ├── Controller/                # Controller-Dokumentation
│   ├── Eingabe/                   # Input-Dokumentation
│   ├── Ausgabe/                   # Output-Dokumentation
│   └── Model/                     # Datenmodell-Dokumentation
├── 🗂️ src/                        # Quellcode
│   ├── 🚪 Main/
│   │   └── Main.java              # Haupteinstiegspunkt
│   ├── 🧮 Algorithmus/
│   │   ├── AKF_ALGO.java          # Basis-Algorithmus
│   │   ├── AKF_ALGO2.java         # Erweiterte Algorithmen  
│   │   ├── AKF_Loeser.java        # Algorithm-Interface
│   │   ├── OutputDatenKollektor.java # Ausgabedaten-Container
│   │   └── RechnerThread.java     # Berechnungs-Thread
│   ├── 🎮 Controller/
│   │   └── Controler.java         # Thread-Management
│   ├── 📥 Eingabe/
│   │   ├── InputDatenKollektor.java    # Eingabedaten-Container  
│   │   ├── Leser.java             # Datei-Reader
│   │   ├── LeseThread.java        # Multi-Threading Reader
│   │   ├── leseInterface.java     # Reader-Interface
│   │   └── VermittlerThread.java  # Daten-Vermittlung
│   ├── 📤 Ausgabe/
│   │   ├── Schreiber.java         # Datei-Writer
│   │   ├── SchreiberThread.java   # Multi-Threading Writer
│   │   └── schreibInterface.java  # Writer-Interface
│   ├── 🏗️ Model/
│   │   ├── FHWM.java             # Pulsbreiten-Datenmodell
│   │   └── Exceptions/            # Custom Exceptions
│   │       ├── Ausgabe_Exception.java
│   │       ├── DatensatzInkonsistentException.java
│   │       └── EingabeException.java
│   └── 📁 META-INF/
│       └── MANIFEST.MF            # JAR-Manifest
└── 📊 data/                       # Beispieldaten (optional)
    ├── input/                     # Beispiel-Eingabedateien  
    └── output/                    # Beispiel-Ausgabedateien
```

## 📊 Eingabedaten-Format

**Beispiel-Eingabedatei:**
```
# AKF Simulator Eingabedaten
# Format: X-Wert (Zeit) [Tab/Leerzeichen] Y-Wert (Amplitude)
1.234    5.678
2.345    6.789  
3.456    7.890
4.567    8.901
5.678    9.012
```

**Format-Spezifikation:**
- **Encoding**: UTF-8 oder ASCII
- **Trenner**: Leerzeichen oder Tabulator
- **Dezimaltrennzeichen**: Punkt (.)
- **Kommentare**: Zeilen mit `#` werden ignoriert
- **Leere Zeilen**: Werden übersprungen

## 🎯 Verwendungsszenarien

### Szenario 1: Schnellstart mit Batch-Skript
```cmd
# 1. starte_AKF_Simulation.bat per Doppelklick öffnen
# 2. Eingabepfad eingeben: ./Messdaten
# 3. Ausgabepfad eingeben: ./Ergebnisse (oder Enter für Standard)
# 4. Simulation läuft automatisch
```

### Szenario 2: Kommandozeilen-Verwendung
```bash
# Direkte JAR-Ausführung mit Parametern
java -jar Loesung.jar "C:\Messdaten\Versuch1" "C:\Ergebnisse\Versuch1"

# Mit Performance-Optimierung
java -Xmx2g -jar Loesung.jar "./input" "./output"
```

### Szenario 3: Batch-Verarbeitung mehrerer Ordner
```bash
# Mehrere Datensätze verarbeiten (Linux/macOS)
for dir in data1 data2 data3; do
    java -jar Loesung.jar "$dir" "results_$dir"
done
```

```cmd
REM Windows Batch-Verarbeitung
for /d %%i in (Messung*) do (
    java -jar Loesung.jar "%%i" "Ergebnis_%%i"
)
```

## ⚙️ JVM-Optimierung

### Empfohlene JVM-Parameter
```bash
# Standard-Konfiguration
java -Xmx1g -Xms256m -jar Loesung.jar "input" "output"

# Für große Datensätze (>100MB)
java -Xmx4g -Xms1g -jar Loesung.jar "input" "output"

# Mit Garbage Collection Optimierung  
java -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar Loesung.jar "input" "output"

# Debug-Modus mit GC-Logging
java -verbose:gc -Xloggc:gc.log -jar Loesung.jar "input" "output"
```

## 📚 JavaDoc-Dokumentation

Das Projekt enthält umfassende JavaDoc-Dokumentation im `JavaDoc/` Verzeichnis.

**Zugriff auf die Dokumentation:**
```bash
# JavaDoc im Browser öffnen
start JavaDoc/index.html        # Windows
open JavaDoc/index.html         # macOS  
xdg-open JavaDoc/index.html     # Linux
```

**JavaDoc zu PDF konvertieren:**
```bash
# Alle HTML-Dateien auflisten
dir /s/b *.html > files.txt

# Mit HTMLDOC zu PDF konvertieren (wenn installiert)
htmldoc --batch files.txt --format pdf14 --outfile AKF_Simulator_Documentation.pdf
```

## 🔍 Fehlerbehebung

### Kompilierungsprobleme

**Problem: Nicht alle Java-Dateien kompiliert**
```bash
# Lösung: Alle Unterordner manuell kompilieren
javac Model/DatenObjekt.java
javac Model/Exceptions/*.java
javac Algorithmus/*.java
javac Controller/*.java
javac Eingabe/*.java
javac Ausgabe/*.java
javac Main/*.java

# Überprüfung: Alle .class Dateien listen
find . -name "*.class"
```

**Problem: "Main-Class nicht gefunden" bei JAR-Ausführung**
```bash
# Lösung: JAR mit korrekter Main-Class erstellen
jar -cvfe Loesung.jar Main.Main *.class Algorithmus/*.class Ausgabe/*.class Eingabe/*.class Model/*.class Controller/*.class

# JAR-Inhalt überprüfen
jar -tf Loesung.jar | grep Main.class
```

### Laufzeitprobleme

**Problem: OutOfMemoryError**
```bash
# Lösung: Heap-Speicher erhöhen
java -Xmx4g -jar Loesung.jar "input" "output"
```

**Problem: Batch-Skript findet JAR nicht**
```batch
REM Überprüfung: JAR im gleichen Verzeichnis?
if not exist "Loesung.jar" (
    echo FEHLER: Loesung.jar nicht gefunden!
    echo Stellen Sie sicher dass die JAR-Datei im gleichen Verzeichnis liegt.
    pause
    exit /b 1
)
```

**Problem: Eingabedateien nicht gefunden**
- Überprüfen Sie die Pfadangabe im Batch-Skript
- Verwenden Sie absolute Pfade bei Problemen mit relativen Pfaden
- Stellen Sie sicher, dass die Eingabedateien das korrekte Format haben

### Debug-Tipps
```bash
# Ausführliche Ausgaben aktivieren
java -verbose:class -jar Loesung.jar "input" "output"

# Klassenladepfad überprüfen
java -cp . -jar Loesung.jar "input" "output"

# Arbeitverzeichnis überprüfen
java -Duser.dir=%CD% -jar Loesung.jar "input" "output"
```

## 🚀 Quick-Start-Guide

### Für Endanwender:
1. **JAR-Datei (`Loesung.jar`) in gewünschtes Verzeichnis kopieren**
2. **`starte_AKF_Simulation.bat` per Doppelklick starten**
3. **Eingabepfad eingeben (z.B. `./Messdaten`)**
4. **Optional: Ausgabepfad eingeben**
5. **Simulation läuft automatisch**

### Für Entwickler:
1. **Repository klonen**
2. **Alle Java-Dateien kompilieren (siehe Kompilierungsanweisungen)**
3. **JAR-Datei erstellen**
4. **Mit Testdaten validieren**

## 📄 Lizenz

Dieses Projekt steht unter der **MIT-Lizenz**.

## 🤝 Contributing

Beiträge sind herzlich willkommen! 

**Entwicklungs-Workflow:**
1. Repository forken
2. Feature-Branch erstellen
3. Änderungen implementieren
4. Alle Java-Dateien kompilieren
5. JAR neu erstellen und testen
6. Pull Request erstellen

## 📞 Support

- 🐛 **Issues**: Für Bug Reports und Feature Requests
- 📖 **JavaDoc**: Vollständige API-Dokumentation verfügbar
- 💬 **Discussions**: Für allgemeine Fragen und Diskussionen

---

**⭐ Gefällt Ihnen dieses Projekt? Geben Sie uns einen Stern auf GitHub!**

**🚀 Bereit zum Start? Führen Sie `starte_AKF_Simulation.bat` aus!**

**📖 Benötigen Sie die API-Dokumentation? Öffnen Sie `JavaDoc/index.html` in Ihrem Browser!**