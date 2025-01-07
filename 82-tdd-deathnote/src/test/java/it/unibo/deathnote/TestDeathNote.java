package it.unibo.deathnote;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static it.unibo.deathnote.api.DeathNote.RULES;
import it.unibo.deathnote.impl.DeathNoteImplementation;

import org.junit.jupiter.api.BeforeEach;
import static java.lang.Thread.sleep;

class TestDeathNote {


    private static String Chandler_Bing = "Chandler Bing";
    private static final String LIGHT_YAGAMI = "Light Yagami";
    private DeathNoteImplementation death;
    private static final int INVALID_CAUSE_TIME = 100;
    private static final int INVALID_DETAILS_TIME = 6000 + INVALID_CAUSE_TIME;
    
    
        @BeforeEach
        void callOnDeathNote(){
            death = new DeathNoteImplementation();
        }
    
    
    
        @Test
        void numberRules(){
            assertNull(death.getRule(0));
            assertNull(death.getRule(-1));
        }
    
        @Test
        void emptyRules(){
    
            for(int i = 0; i < RULES.size(); i++){
                assertNotNull(RULES.get(i));
                assertFalse(RULES.get(i).isEmpty());
            }
            }
    
    
        @Test
        void nameWritten(){
            assertFalse(death.isNameWritten(Chandler_Bing));
            death.writeName(Chandler_Bing);
            assertTrue(death.isNameWritten(Chandler_Bing));
            assertFalse(death.isNameWritten(LIGHT_YAGAMI));
            assertFalse(death.isNameWritten(""));
    
        }
    
        @Test
        void deathSpeed() throws InterruptedException{
            
            assertThrows(IllegalStateException.class,
            () -> death.writeDeathCause("Heart Attack")
        );

        death.writeName(LIGHT_YAGAMI);
        assertEquals("heart attack", death.getDeathCause(LIGHT_YAGAMI));
        death.writeName(Chandler_Bing);
        assertTrue(death.writeDeathCause("karting accident"));
        // Assuming the method can be executed in less than 40ms
        assertEquals("karting accident", death.getDeathCause(Chandler_Bing));
        // Wait for more than 40 ms
        sleep(INVALID_CAUSE_TIME);
        assertFalse(death.writeDeathCause("Spontaneous human combustion"));
        assertEquals("karting accident", death.getDeathCause(Chandler_Bing));


        }
        
        @Test
        void deathDetails() throws InterruptedException{

            assertThrows(IllegalStateException.class, () -> death.writeDeathCause("Cuz niggas be crazy"));

            death.writeName(Chandler_Bing);
            assertTrue(death.writeDetails("Ran too long"));
            death.writeName(LIGHT_YAGAMI);
            sleep(INVALID_DETAILS_TIME);
            assertFalse(death.writeDetails("wrote many tests before dying"));


        }
        

    }

    

