package service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class HitChecker {
    private final HitChecker checker = new HitChecker();

    @Test
    public void testHit(){
        assertTrue(checker.check());
    }
}
