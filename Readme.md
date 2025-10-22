# AKF Simulator

[![Java](https://img.shields.io/badge/Java-18+-orange.svg)](https://www.oracle.com/java/)
[![License: Educational](https://img.shields.io/badge/License-Educational-blue.svg)](#lizenz)
[![IHK Project](https://img.shields.io/badge/IHK-Aachen%20Abschluss-red.svg)](#über-das-projekt)
[![Educational Use](https://img.shields.io/badge/Use-Educational%20Only-green.svg)](#lizenz)
[![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20macOS%20%7C%20Linux-lightgrey.svg)](#systemanforderungen)
[![JAR](https://img.shields.io/badge/JAR-Ready-green.svg)](#installation-und-ausführung)

Ein **Java-basierter Autokorrelationsfunktions-Simulator** mit fortgeschrittenen mathematischen Algorithmen für die präzise Analyse von Messdaten. Das System nutzt Multi-Threading-Architekturen für optimale Performance und wird über das interaktive Batch-Skript `starte_AKF_Simulation.bat` gestartet.

**Entwickelt als Abschlussprüfung für die IHK Aachen.**

## 🎓 Über das Projekt

### IHK-Abschlussprüfung
Dieses Projekt wurde als **praktische Abschlussprüfung** im Rahmen der Ausbildung bei der **IHK Aachen** entwickelt und demonstriert umfassende Kenntnisse in der Java-Programmierung und Softwareentwicklung.

### Prüfungsanforderungen
- ✅ **Objektorientierte Programmierung** in Java
- ✅ **Multi-Threading-Implementierung** für parallele Datenverarbeitung
- ✅ **Datei-I/O und Batch-Verarbeitung** für große Datenmengen
- ✅ **Mathematische Algorithmen-Implementierung** (Autokorrelationsfunktion)
- ✅ **Benutzerfreundlichkeit** durch interaktive Batch-Skripte
- ✅ **Vollständige Dokumentation** (README, JavaDoc)
- ✅ **Fehlerbehandlung und Robustheit** für Produktionsreife
- ✅ **JAR-Distribution** für einfache Deployment

### Bewertungskriterien
- **Code-Qualität**: Saubere Struktur, Kommentierung, Best Practices
- **Funktionalität**: Korrekte Implementierung aller Anforderungen
- **Benutzerfreundlichkeit**: Intuitive Bedienung für Endanwender
- **Dokumentation**: Umfassende technische Dokumentation
- **Testing**: Robustheit bei verschiedenen Eingabeszenarien
- **Performance**: Optimierte Multi-Threading-Architektur

## 🎯 Überblick

Der AKF Simulator automatisiert komplexe Signalanalyseverfahren und führt präzise Messungen für wissenschaftliche Anwendungen in Physik, Elektrotechnik und Messtechnik durch.

### ✨ Hauptfunktionen

- **📊 X-Wert Glättung**: Konvertierung und Glättung von Eingabewerten mit Pikosekunden-Präzision
- **📈 Y-Wert Normierung**: Normalisierung der Messwerte auf das Intervall (0,1]
- **🔄 Hüllkurven-Bestimmung**: Automatische Erkennung der oberen Einhüllenden
- **⚡ FWHM-Analyse**: Full Width Half Maximum Berechnung für Pulssignale
- **🚀 Multi-Threading**: Parallele Verarbeitung großer Datensätze
- **📁 Batch-Verarbeitung**: Simultane Bearbeitung mehrerer Eingabedateien
- **🗃️ JAR-Distribution**: Portable ausführbare JAR-Datei (`AKF_Simulator.jar`)
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
- Verwenden Sie die mitgelieferte `AKF_Simulator.jar`
- Oder laden Sie die aktuelle Version aus den [Releases](https://github.com/IhrUsername/AKF_Simulator/releases) herunter

**2. Mit interaktivem Batch-Skript starten:**
```cmd
# Doppelklick auf starte_AKF_Simulation.bat
starte_AKF_Simulation.bat
```

**3. Direkte JAR-Ausführung:**
```bash
# Grundlegende Ausführung
java -jar AKF_Simulator.jar "eingabe_ordner" "ausgabe_ordner"

# Mit optimierten Parametern
java -Xmx1g -jar AKF_Simulator.jar "data/input" "data/output"

# Nur Eingabeordner (Ausgabe im gleichen Verzeichnis)
java -jar AKF_Simulator.jar "data/input"
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
jar -cvfe AKF_Simulator.jar Main.Main *.class Algorithmus/*.class Ausgabe/*.class Eingabe/*.class Model/*.class Controller/*.class
```

**3. JAR-Datei testen:**
```bash
java -jar AKF_Simulator.jar "testdaten" "ergebnisse"
```

## 🖥️ Interaktives Batch-Skript: `starte_AKF_Simulation.bat`

Das Hauptfeature des Projekts ist das benutzerfreundliche Batch-Skript für die interaktive Ausführung.

### **Funktionen des Batch-Skripts:**
- ✅ Interaktive Pfadeingabe für Eingabedaten
- ✅ Optionale Ausgabepfad-Spezifikation  
- ✅ Automatische JAR-Erkennung (`AKF_Simulator.jar`)
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
├── 🗃️ AKF_Simulator.jar           # Ausführbare JAR-Datei
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
java -jar AKF_Simulator.jar "C:\Messdaten\Versuch1" "C:\Ergebnisse\Versuch1"

# Mit Performance-Optimierung
java -Xmx2g -jar AKF_Simulator.jar "./input" "./output"
```

### Szenario 3: Batch-Verarbeitung mehrerer Ordner
```bash
# Mehrere Datensätze verarbeiten (Linux/macOS)
for dir in data1 data2 data3; do
    java -jar AKF_Simulator.jar "$dir" "results_$dir"
done
```

```cmd
REM Windows Batch-Verarbeitung
for /d %%i in (Messung*) do (
    java -jar AKF_Simulator.jar "%%i" "Ergebnis_%%i"
)
```

## ⚙️ JVM-Optimierung

### Empfohlene JVM-Parameter
```bash
# Standard-Konfiguration
java -Xmx1g -Xms256m -jar AKF_Simulator.jar "input" "output"

# Für große Datensätze (>100MB)
java -Xmx4g -Xms1g -jar AKF_Simulator.jar "input" "output"

# Mit Garbage Collection Optimierung  
java -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar AKF_Simulator.jar "input" "output"

# Debug-Modus mit GC-Logging
java -verbose:gc -Xloggc:gc.log -jar AKF_Simulator.jar "input" "output"
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
jar -cvfe AKF_Simulator.jar Main.Main *.class Algorithmus/*.class Ausgabe/*.class Eingabe/*.class Model/*.class Controller/*.class

# JAR-Inhalt überprüfen
jar -tf AKF_Simulator.jar | grep Main.class
```

### Laufzeitprobleme

**Problem: OutOfMemoryError**
```bash
# Lösung: Heap-Speicher erhöhen
java -Xmx4g -jar AKF_Simulator.jar "input" "output"
```

**Problem: Batch-Skript findet JAR nicht**
```batch
REM Überprüfung: JAR im gleichen Verzeichnis?
if not exist "AKF_Simulator.jar" (
    echo FEHLER: AKF_Simulator.jar nicht gefunden!
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
java -verbose:class -jar AKF_Simulator.jar "input" "output"

# Klassenladepfad überprüfen
java -cp . -jar AKF_Simulator.jar "input" "output"

# Arbeitverzeichnis überprüfen
java -Duser.dir=%CD% -jar AKF_Simulator.jar "input" "output"
```

## 🚀 Quick-Start-Guide

### Für Endanwender:
1. **JAR-Datei (`AKF_Simulator.jar`) in gewünschtes Verzeichnis kopieren**
2. **`starte_AKF_Simulation.bat` per Doppelklick starten**
3. **Eingabepfad eingeben (z.B. `./Messdaten`)**
4. **Optional: Ausgabepfad eingeben**
5. **Simulation läuft automatisch**

### Für Prüfungsausschuss/Gutachter:
1. **JavaDoc-Dokumentation öffnen**: `JavaDoc/index.html`
2. **Quellcode-Review**: Vollständiger Quellcode im `src/` Verzeichnis
3. **Funktionstest**: Batch-Skript für einfache Demonstration
4. **Build-Prozess**: Anweisungen in `Anweisungen_jar_Erstellung_HTMLDOC_NACH_pdf.txt`

### Für Entwickler:
1. **Repository klonen**
2. **Alle Java-Dateien kompilieren (siehe Kompilierungsanweisungen)**
3. **JAR-Datei erstellen**
4. **Mit Testdaten validieren**

## 📄 Lizenz

Dieses Projekt wurde als **Abschlussprüfung der IHK Aachen** entwickelt und dient zu **Bildungs- und Demonstrationszwecken**.

### Nutzungshinweise
- ✅ **Bildungszwecke**: Freie Nutzung für Lernen und Lehre
- ✅ **Demonstrationszwecke**: Zeigen von Programmierfertigkeiten  
- ✅ **Forschung**: Akademische und wissenschaftliche Nutzung
- ✅ **Prüfungszwecke**: Bewertung durch IHK-Prüfungsausschuss
- ⚠️ **Kommerzielle Nutzung**: Nur nach Rücksprache mit dem Autor
- ⚠️ **Weiterverteilung**: Bitte Originalquelle und IHK-Prüfungskontext erwähnen

### Urheberrecht
**© 2022 Klaus Schmidt - IHK Aachen Abschlussprüfung**

Entwickelt im Rahmen der Abschlussprüfung zum mathematisch-technischen Softwareentwickler bei der IHK Aachen.

### Rechtlicher Hinweis
Dieses Projekt unterliegt den **Prüfungsordnungen der IHK Aachen** und wurde gemäß den Anforderungen der praktischen Abschlussprüfung erstellt. Die Nutzung für Lehr- und Lernzwecke ist ausdrücklich erwünscht.

## 🤝 Contributing

**Hinweis**: Da dies ein **IHK-Prüfungsprojekt** ist, sind Beiträge hauptsächlich zu **Bildungs- und Verbesserungszwecken** willkommen.

**Willkommene Beiträge:**
- 📚 **Dokumentations-Verbesserungen** für bessere Verständlichkeit
- 🐛 **Fehlerkorrekturen** und Code-Optimierungen  
- ✨ **Zusätzliche Beispiele** und realistische Testfälle
- 🎓 **Lehrpläne und Tutorials** basierend auf diesem Code
- 📈 **Performance-Optimierungen** für größere Datensätze
- 🔧 **Build-Prozess-Verbesserungen** und Automatisierung

**Entwicklungs-Workflow:**
1. Repository forken
2. Feature-Branch erstellen (`feature/verbesserung-dokumentation`)
3. Änderungen implementieren
4. Alle Java-Dateien kompilieren und testen
5. JAR neu erstellen und Funktionalität validieren
6. Pull Request mit **Bildungskontext** und Beschreibung erstellen

**Code-of-Conduct:**
- Respektvolles Feedback und konstruktive Kritik
- Fokus auf Lerneffekt und Bildungswert
- Berücksichtigung des IHK-Prüfungskontexts

## 📞 Support

- 🐛 **Issues**: Für Bug Reports und Feature Requests
- 📖 **JavaDoc**: Vollständige API-Dokumentation im `JavaDoc/` Verzeichnis
- 💬 **Discussions**: Für allgemeine Fragen und fachliche Diskussionen
- 🎓 **Educational Use**: Bei Fragen zur Nutzung in Lehrplänen

### Für Prüfungsausschuss
- 📋 **Vollständige Dokumentation** in JavaDoc-Format verfügbar
- 🔧 **Build-Anweisungen** in separater Textdatei dokumentiert
- 🧪 **Testszenarien** über Batch-Skript einfach ausführbar
- 📊 **Performance-Metriken** über JVM-Parameter konfigurierbar

---

**🎓 IHK Aachen Abschlussprüfung - Demonstriert fortgeschrittene Java-Kenntnisse**

**⭐ Gefällt Ihnen dieses Projekt? Geben Sie uns einen Stern auf GitHub!**

**🚀 Bereit zum Start? Führen Sie `starte_AKF_Simulation.bat` aus!**

**📖 Vollständige Dokumentation? Öffnen Sie `JavaDoc/index.html` in Ihrem Browser!**