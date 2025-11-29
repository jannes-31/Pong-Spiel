import sas.*;
import java.awt.Color;

public class Spiel
{
    View fenster;
    Rectangle barriereOben, barriereUnten, barriereLinks, barriereRechts, schlaeger1, schlaeger2;
    Circle ball;
    Text startText, punkte1, punkte2, punkteStand1, punkteStand2;

    Spiel() {
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

        schlaeger1 = new Rectangle(20, 360, 10, 80);
        schlaeger1.setColor(Color.blue);

        schlaeger2 = new Rectangle(1370, 360, 10, 80);
        schlaeger2.setColor(Color.red);

        ball = new Circle(690, 390, 10);
        ball.setColor(Color.yellow);

        startText = new Text(265, 200, "DRUECKE ENTER UM ZU STARTEN");
        startText.setFontSansSerif(true, 50);

        punkte1 = new Text(250, 300, "");
        punkte1.setFontSansSerif(true,100);

        punkte2 = new Text(950, 300, "");
        punkte2.setFontSansSerif(true, 100);

        while (true) {
            if (fenster.keyEnterPressed()) break;

            startText.setHidden(false);
            fenster.wait(300);

            if (fenster.keyEnterPressed()) break;

            startText.setHidden(true);
            fenster.wait(300);

            if (fenster.keyEnterPressed()) break;
        }

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
        this.startPunkt();
        this.spielFunktion();

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


    void startPunkt()
    {
        ball.moveTo(690,390);
        int winkel = Tools.randomNumber(1, 360);

        while (winkel == 90 || winkel == 180 || winkel == 270 || winkel == 360) {
            winkel = Tools.randomNumber(1, 360);
        }

        ball.setDirection(winkel * 10);
    }

    void spielFunktion()
    {
        int dX = 1;
        int dY = 1;

        int p1 = 0;
        int p2 = 0;

        while(true)
        {

            if (fenster.keyPressed('w') && schlaeger1.getShapeY() > 11)
            {
                schlaeger1.move(0,-1);
            }

            if (fenster.keyPressed('s') && schlaeger1.getShapeY() < 709)
            {
                schlaeger1.move(0,1);
            }

            if (fenster.keyUpPressed() && schlaeger2.getShapeY() > 11)
            {
                schlaeger2.move(0,-1);
            }

            if (fenster.keyDownPressed() && schlaeger2.getShapeY() < 709)
            {
                schlaeger2.move(0,1);
            }

            fenster.wait(2);

            ball.move(dX, dY);

            if (ball.intersects(schlaeger1) || ball.intersects(schlaeger2))
            {
                dX = - dX;
            }

            if (ball.intersects(barriereOben) || ball.intersects(barriereUnten))
            {
                dY = - dY;
            }

            fenster.wait(2);

                if(barriereLinks.intersects(ball))
                {
                    p1++;
                }

                if (barriereLinks.intersects(ball) && p1 < 10)
                {
                    punkte2.setHidden(false);
                    punkte2.setText("" + p1);
                    fenster.wait(1000);
                    punkte2.setHidden(true);
                    this.startPunkt();

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

                if(barriereRechts.intersects(ball))
                {
                    p2++;
                }
                if(barriereRechts.intersects(ball) && p2 < 10)
                {
                    punkte1.setHidden(false);
                    punkte1.setText("" + p2);
                    fenster.wait(1000);
                    punkte1.setHidden(true);
                    this.startPunkt();

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

            if (p1 > 10 || p2 > 10)
            {
                startText.setHidden(false);
                startText.moveTo(330,160);
                startText.setText("Game Over!");
                startText.setFontSansSerif(true,70);
                fenster.wait(1000);
                startText.setHidden(true);

                break;
            }
        }
    }

    public static void main(String[] args)
    {
        new Spiel();
    }
}
