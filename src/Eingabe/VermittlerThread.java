package Eingabe;

import Controller.Controler;

import java.util.concurrent.TimeUnit;

public class VermittlerThread extends Thread {
    private final Controler ct;

    public VermittlerThread(Controler _ct) {
        this.ct = _ct;
    }


    @Override
    public void run() {
        while (true) {
            if (ct.alleDatenImVorstopper.get()) {
                InputDatenKollektor inPut = this.ct.getVorstopperQueue().poll();
                if (inPut != null) {
                    while (true) {
                        System.out.println("Vermittler liefert neuen Datensatz");
                        try {
                            if (this.ct.getDetektordatenQueue().offer(inPut, 20, TimeUnit.MILLISECONDS)) { // Ablehn-Fall aufgrund der Queeu-Size gar nicht möglich
                                System.out.println("Vermittler hat neuen Datensatz geliefert");
                                break;
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        System.out.println("Datenvermittler wartet 50ms!");
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    if (!this.ct.alleDatenGeliefert.get()) {
                        this.ct.alleDatenGeliefert.set(true);
                        System.out.println("Vermitller hat alle Daten Jeliefert!!!");
                        while (true) {
                            try {
                                if (this.ct.getDetektordatenQueue().offer(new InputDatenKollektor(true), 5, TimeUnit.MILLISECONDS))
                                    break;
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        while (true) {
                            try {
                                if (this.ct.getDetektordatenQueue().offer(new InputDatenKollektor(true), 5, TimeUnit.MILLISECONDS))
                                    break;
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }   return;
                    }
                }
            }
        }
    }
}
