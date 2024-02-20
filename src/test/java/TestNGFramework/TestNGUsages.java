package TestNGFramework;

import org.testng.annotations.Test;

public class TestNGUsages {
    @Test(description = "Test numero 1")
    public void testUno() {
        System.out.println("TEST UNO");
    }
    @Test(description = "Test numero 2", dependsOnMethods = "testTres")
    public void testDos() {
        System.out.println("TEST DOS");
    }
    @Test(description = "Test numero 3", priority = 1)
    public void testTres() {
        System.out.println("TEST TRES");
    }
}
