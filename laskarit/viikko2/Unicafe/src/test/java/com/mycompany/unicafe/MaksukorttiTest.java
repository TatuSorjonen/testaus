package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoKasvaaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(4);
        assertEquals("saldo: 0.6", kortti.toString());
    }
    
    @Test
    public void saldoEiMuttu() {
        kortti.otaRahaa(12);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void riittavatkoRahat() {
        kortti.otaRahaa(8);
        kortti.otaRahaa(12);
        assertTrue(kortti.saldo() >= 0);
        assertFalse(kortti.saldo() < 0);
    }
}
