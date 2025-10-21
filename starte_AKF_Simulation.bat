@ECHO OFF
ECHO Bitte den absoluten oder relativen Pfad zum Ordner mit den Testdatensaetzen eingeben.
ECHO Standardfall: ./data
ECHO HINWEIS:
ECHO Falls der Ordner vom Pfad dieser ausgefuehrten Skriptdatei betrachtet in einem oder mehreren Unterordner liegt,
ECHO die Pfadangabe bitte in der Form "./Unterordner1/Unterordner2/.." vornehmen.

SET /p eingabeDatei="Pfad_zum_Eingabe_Ordner:"


ECHO Optional: Bitte den gewuenschten absoluten Pfad zur Ausgabedatei eingeben.
ECHO HINWEIS:
ECHO Lassen Sie das Feld leer, wird der Ordner der Eingabedateien fuer das Schreiben der Loesungsdateien verwandt. 
ECHO Sollte Ihre Pfadangabe noch nicht erstellte Ornder enthaten, werden diese erstellt. 
SET /p ausgabeDatei="Pfad_zum_Ausgabe_Ordner:"


ECHO Bitte den vollen relativen oder absoluten Pfad des AKF-Simulators (mit Dateieindung .jar) angeben:
SET /p loeser="AKF-Simulator:"

CALL java -jar %loeser% %eingabeDatei% %ausgabeDatei%

PAUSE