package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void laittaaLiikaa() {
        varasto.lisaaVarastoon(15);

        // varastossa pitäisi olla 0
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaaLiikaa() {
        varasto.lisaaVarastoon(8);


        // varastossa pitäisi saada vain 8
        assertEquals(8, varasto.otaVarastosta(15), vertailuTarkkuus);
    }

    @Test
    public void haeTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }


    @Test
    public void haeSaldo() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tulosta() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0".equals(varasto.toString()), true);
    }

    @Test
    public void mahtuuPaljonko() {
        varasto.lisaaVarastoon(7);
        assertEquals(3, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void haeNeg() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }
    @Test
    public void lisaaNeg() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.otaVarastosta(1), vertailuTarkkuus);
    }

    @Test
    public void virhVarasto() {
        Varasto v = new Varasto(-1);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virhSaldVarasto() {
        Varasto v = new Varasto(10, -1);
        assertEquals(10, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virhSaldvirhVarasto() {
        Varasto v = new Varasto(-1, -1);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaSaldVarasto() {
        Varasto v = new Varasto(10, 15);
        assertEquals(0, v.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void SaldVarasto() {
        Varasto v = new Varasto(10, 5);
        assertEquals(5, v.paljonkoMahtuu(), vertailuTarkkuus);
    }



}