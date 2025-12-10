import sas.*;
import java.awt.Color;

public class Spiel
{
    View fenster;
    Rectangle barriereOben, barriereUnten, barriereLinks, barriereRechts, schlaeger1, schlaeger2, punkteAnzeige1, punkteAnzeige2, teamFarbe1, teamFarbe2;
    Circle ball;
    Text startText, punkte1, punkte2, punkteStand1, punkteStand2;

// Deklarierung der Objekte für das Spiel

    Spiel()
    {
        fenster = new View(1400, 800, "Pong");
        fenster.setBackgroundColor(Color.lightGray);

        this.mittelpunktErstellen();

        barriereOben = new Rectangle(0, 0, 1400, 10);
        barriereOben.setColor(Color.darkGray);

        barriereUnten = new Rectangle(0, 790, 1400, 10);
        barriereUnten.setColor(Color.darkGray);

        barriereLinks = new Rectangle(0, 0, 10, 800);
        barriereLinks.setColor(Color.darkGray);

        barriereRechts = new Rectangle(1390, 0, 10, 800);
        barriereRechts.setColor(Color.darkGray);

        schlaeger1 = new Rectangle(20, 340, 10, 100);
        schlaeger1.setColor(Color.red);

        schlaeger2 = new Rectangle(1370, 340, 10, 100);
        schlaeger2.setColor(Color.blue);

        ball = new Circle(690, 390, 10);
        ball.setColor(Color.yellow);

        startText = new Text(265, 200, "DRÜCKE ENTER UM ZU STARTEN");
        startText.setFontSansSerif(true, 50);

        punkteAnzeige1 = new Rectangle(20,20,130,40);
        punkteAnzeige1.setColor(Color.gray);
        teamFarbe1 = new Rectangle(21,21,38,38);
        teamFarbe1.setColor(Color.red);
        punkteStand1 = new Text(61,21,"Rot:");
        punkteStand1.setFontSansSerif(true,30);

        punkteAnzeige2 = new Rectangle(1240,20,140,40);
        punkteAnzeige2.setColor(Color.gray);
        teamFarbe2 = new Rectangle(1241,21,38,38);
        teamFarbe2.setColor(Color.blue);
        punkteStand2 = new Text(1280,21,"Blau:");
        punkteStand2.setFontSansSerif(true,30);

        punkte1 = new Text(950, 300, "");
        punkte1.setFontSansSerif(true, 100);

        punkte2 = new Text(250, 300, "");
        punkte2.setFontSansSerif(true,100);

// Initialisierung der Objekte

        while (true) {
            if (fenster.keyEnterPressed()) break;

            startText.setHidden(false);
            fenster.wait(300);

            if (fenster.keyEnterPressed()) break;

            startText.setHidden(true);
            fenster.wait(300);

            if (fenster.keyEnterPressed()) break;
        }

// While true schleife für den Ablauf des Starttextes der direkt am Anfang erscheint

        this.startText();
        this.startPunkt();
        this.spielFunktion();

// Aufruf der Methoden StartText, Startpunkt und Spielfunktion
    }

    void mittelpunktErstellen()
    {
        Rectangle mittelBarriere;
        for(int zeile = 0; zeile < 10; zeile++)
        {
            mittelBarriere = new Rectangle(696,zeile*80+15,8,50);
            mittelBarriere.setTransparency(0.2f);
        }
    }

// Methode zur bestimmung des Mittelpunktes des Feldes

    void startText()
    {
        startText.setHidden(false);
        startText.setFontSansSerif(true,200);
        startText.moveTo(650,200);
        startText.setText("3");
        fenster.wait(1000);
        startText.setText("2");
        fenster.wait(1000);
        startText.setText("1");
        fenster.wait(1000);
        startText.setFontSansSerif(true,100);
        startText.moveTo(550,250);
        startText.setText("LOS!!!");
        fenster.wait(500);
        startText.setHidden(true);
    }

// Methode zur erstellung des Startextes zum Start von jeder Runde und am Anfang

    void startPunkt()
    {
        ball.moveTo(690,390);
        int winkel = Tools.randomNumber(1, 360);

        while (winkel%90 == 0)
        {
            winkel = Tools.randomNumber(1, 360);
        }

        ball.setDirection(winkel * 10);
    }

// Methode zur Bewegung des Balles zum Mittelpunkt am Anfang jeder Runde und zufällige Winkelbestimmung, die aber aufgrund von dX / dY nicht funktioniert

    void spielFunktion()
    {
        int dX = 3;
        int dY = 3;

        int sY = 6;

        int p1 = 0;
        int p2 = 0;

        long startZeit = Tools.getStartTime();

// Deklarierung der Int für die Geschwindigkeiten, die Punktzahl und des longs für die Zeit die pro runde vergeht

        while(true)
        {
            if (fenster.keyPressed('w') && schlaeger1.getShapeY() > 11)
            {
                schlaeger1.move(0,-sY);
            }

            if (fenster.keyPressed('s') && schlaeger1.getShapeY() < 709)
            {
                schlaeger1.move(0,sY);
            }

            if (fenster.keyUpPressed() && schlaeger2.getShapeY() > 11)
            {
                schlaeger2.move(0,-sY);
            }

            if (fenster.keyDownPressed() && schlaeger2.getShapeY() < 709)
            {
                schlaeger2.move(0,sY);
            }

// Steuerung der Schläger mit den Tasten S und W und Pfeiltaste Hoch und Pfeiltaste runter

            fenster.wait(2);

            ball.move(dX, dY);

// Bewegung des Balles mit der Geschwindigkeit dX, dY

            if (Tools.getElapsedTime(startZeit) >= 10)
            {
                dX *= 1;
                dY *= 1;
                startZeit = Tools.getStartTime();
            }

            fenster.wait(5);

            if (ball.intersects(schlaeger1) || ball.intersects(schlaeger2))
            {
                dX = - dX;
                dX *= 1.4;
                dY *= 1.4;
            }

            if (ball.intersects(barriereOben) || ball.intersects(barriereUnten))
            {
                dY = - dY;
                dX *= 1.1;
                dY *= 1.1;
            }

            fenster.wait(2);

// Verschiedene Arten der Geschwindigkeitssteigerung des Balles

            if(ball.intersects(barriereLinks))
            {
                p1++;
                punkteStand1.setText("Rot: " + p1);
            }

            if (ball.intersects(barriereLinks) && p1 < 10)
            {
                punkte2.setHidden(false);
                punkte2.setText("" + p1);
                fenster.wait(1000);
                punkte2.setHidden(true);

                this.startPunkt();
                this.startText();

                dX = 4;
                dY = 4;
                startZeit = Tools.getStartTime();
            }

// Punktestand des roten Teams und zurücksetzung der Ausgangsposition und Geschwindigkeit

            if(ball.intersects(barriereRechts))
            {
                p2++;
                punkteStand2.setText("Blau: " + p2);
            }

            if(ball.intersects(barriereRechts) && p2 < 10)
            {
                punkte1.setHidden(false);
                punkte1.setText("" + p2);
                fenster.wait(1000);
                punkte1.setHidden(true);

                this.startPunkt();
                this.startText();

                dX = 4;
                dY = 4;
                startZeit = Tools.getStartTime();
            }

// Punktestand des blauen Teams und zurücksetzung der Ausgangsposition und Geschwindigkeit

            if (p1 > 10 || p2 > 10)
            {
                startText.setHidden(false);
                startText.moveTo(630,160);
                startText.setText("Game Over!" + "");
                startText.setFontSansSerif(true,70);
                fenster.wait(5000);
                startText.setHidden(true);

                break;
            }

// if - Verzweigung für das Spielende sollte eines der beiden Teams 10 Punkte erlangen
        }
    }


    public static void main(String[] args)
    {
        new Spiel();
    }
}
