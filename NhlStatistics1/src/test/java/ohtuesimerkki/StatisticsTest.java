/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bisi
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void loydaPelaaja() {
        Player semenko = stats.search("Semenko");
        assertEquals(semenko.getName().equals("Semenko"), true);
    }
    
    @Test
    public void eiLoydaPelaajaa() {
        assertEquals(stats.search("Selanne"), null);
    }
    
    @Test
    public void haeJoukkue() {
        List<Player> joukkue = stats.team("PIT");
        assertEquals(joukkue.get(0).getName(), "Lemieux");
    }
    @Test
    public void parhaatPistemiehet() {
        List<Player> parhaat = stats.topScorers(3);
        assertEquals(parhaat.get(0).getName(), "Gretzky");
        assertEquals(parhaat.get(1).getName(), "Lemieux");
        assertEquals(parhaat.get(2).getName(), "Yzerman");
        
    }
}
