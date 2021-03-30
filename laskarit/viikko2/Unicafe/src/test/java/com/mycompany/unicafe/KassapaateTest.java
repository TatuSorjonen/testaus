/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sorjonet
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void onkoKassapaateJaLounaatOlemassa() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() + kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void toimiikoKateisellaOstaminen() {
        kassapaate.syoEdullisesti(240);
        assertEquals(kassapaate.kassassaRahaa(), 100240);
        kassapaate.syoMaukkaasti(400);
        assertEquals(kassapaate.kassassaRahaa(), 100640);
        kassapaate.syoEdullisesti(250);
        assertEquals(kassapaate.kassassaRahaa(), 100880);
        kassapaate.syoMaukkaasti(410);
        assertEquals(kassapaate.kassassaRahaa(), 101280);
    }
    
    @Test
    public void onkoLounaidenMaaraOikein() {
        kassapaate.syoEdullisesti(240);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(),1);
        kassapaate.syoMaukkaasti(400);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(),1);
    }
    
    @Test
    public void maksuEiOleRiittava() {
        kassapaate.syoEdullisesti(200);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
        kassapaate.syoMaukkaasti(350);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kortillaMaksaminenToimii() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortillaLounaidenMaaraOikein() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void kortinSaldoEiOleRiittava() {
        kortti.otaRahaa(800);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertEquals(kortti.saldo(), 200);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
        kortti.otaRahaa(100);
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertEquals(kortti.saldo(), 100);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kortinJaKassapaatteenRahamaaraMuuttuu() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(kassapaate.kassassaRahaa(), 101000);
        assertEquals(kortti.saldo(), 2000);
        kassapaate.lataaRahaaKortille(kortti, -1000);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//     @Test
//     public void hello() {}
}
