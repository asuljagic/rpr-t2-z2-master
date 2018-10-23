package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocTacka = 0, krajTacka = 0;
    private boolean pPripada, kPripada;

    public Interval(double pocTacka, double krajTacka, boolean pPripada, boolean kPripada) {
        if(pocTacka > krajTacka) throw new IllegalArgumentException("Greska! Pocetna i krajnja tacka su iste");
        this.pocTacka = pocTacka;
        this.krajTacka = krajTacka;
        this.pPripada = pPripada;
        this.kPripada = kPripada;
    }

    public Interval() {
        pocTacka = 0;
        krajTacka = 0;
        pPripada = false;
        kPripada = false;
    }

    public boolean isNull(){
      return  (pocTacka == 0 && krajTacka == 0 && pPripada == false && kPripada == false) ? true : false;
    }

    public boolean isIn (double t){
        if(pPripada == false && kPripada == false)
            return (t > pocTacka && t < krajTacka);
        if(pPripada == true && kPripada == true)
            return (t >= pocTacka && t <= krajTacka);
        if(pPripada == true && kPripada == false)
            return(t >= pocTacka && t < krajTacka);
        if(pPripada == false && kPripada == true)
            return(t > pocTacka && t <=krajTacka);
        return false;
    }
    //prvo klasicna public metoda
    public Interval intersect(Interval p){
        //prvi slucaj ako pripadaju obje pocetne tacke
        if(this.pocTacka < p.pocTacka && this.krajTacka < p.krajTacka)
            if(this.pPripada && p.pPripada)
                if(p.kPripada && !this.kPripada)
                    this.pocTacka = p.pocTacka;
        //drugi slucaj ako pripada jedna a druga ne
        if(this.pocTacka < p.pocTacka && this.krajTacka < p.krajTacka)
            if(this.pPripada && !p.pPripada){
                this.pocTacka = p.pocTacka;
                this.pPripada = p.pPripada;
            }
        // treci slucaj ako je krajnja tacka veca od krajnje primljenog parametra
        if(this.pocTacka < p.pocTacka && this.krajTacka > p.krajTacka)
            if(this.pPripada && p.pPripada)
                if(p.kPripada && ! this.kPripada){
                    this.pocTacka = p.pocTacka;
                    this.krajTacka = p.krajTacka;
                    this.kPripada = p.kPripada;
                }
        return this;
    }

    public static Interval intersect(Interval prvi, Interval drugi){
        Interval n = new Interval();
        if (prvi.pocTacka < drugi.pocTacka && prvi.krajTacka < drugi.krajTacka)
            if (prvi.pPripada && drugi.pPripada)
                if (!prvi.kPripada && drugi.kPripada) {
                    n.pocTacka = drugi.pocTacka;
                    n.krajTacka = prvi.krajTacka;
                    n.pPripada = prvi.pPripada;
                    n.kPripada = prvi.kPripada;
                }
        if (prvi.pocTacka < drugi.pocTacka && prvi.krajTacka > drugi.krajTacka)
            if (prvi.pPripada && drugi.pPripada)
                if (!prvi.kPripada && drugi.kPripada) {
                    n.pocTacka = drugi.pocTacka;
                    n.krajTacka = drugi.krajTacka;
                    n.pPripada = prvi.pPripada;
                    n.kPripada = drugi.kPripada;
                }
        if (prvi.pocTacka < drugi.pocTacka && prvi.krajTacka < drugi.krajTacka)
            if (prvi.pPripada && !drugi.pPripada)
                if (!prvi.kPripada && drugi.kPripada) {
                    n.pocTacka = drugi.pocTacka;
                    n.krajTacka = prvi.krajTacka;
                    n.pPripada = drugi.pPripada;
                    n.kPripada = prvi.kPripada;
                }
        return n;
    }

    @Override
    public String toString() {
        if (pocTacka == 0 && krajTacka == 0) return "()";
        if (pPripada == true && kPripada == true) return "[" + pocTacka + "," + krajTacka + "]";
        if (pPripada == true && kPripada == false) return "[" + pocTacka + "," + krajTacka + ")";
        if (pPripada == false && kPripada == true) return "(" + pocTacka + "," + krajTacka + "]";
        if (pPripada == false && kPripada == false) return "(" + pocTacka + "," + krajTacka + ")";
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Interval)) return false;
        Interval interval = (Interval) o;
        if(this.pocTacka == interval.pocTacka && this.krajTacka == interval.krajTacka && this.pPripada == interval.pPripada && this.kPripada == interval.kPripada)
            return true;
        return false;
    }


}
