package repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ResultObject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseServiceTest {

    private DatabaseService dbService;

    @BeforeEach
    public void setUp() {
        dbService = new DatabaseService();
        // Очищаем таблицу перед каждым тестом, чтобы тесты были независимы
        try {
            dbService.clear();
        } catch (Exception e) {
            // если БД недоступна, тесты просто упадут
        }
    }

    @Test
    public void testSaveAndLoad() {
        ResultObject result = new ResultObject(1.0, 2.0, 3.0, true, "12:00", 100L);
        dbService.save(result);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertEquals(1, results.size());

        ResultObject loaded = results.get(0);
        assertEquals(1.0, loaded.getX());
        assertEquals(2.0, loaded.getY());
        assertEquals(3.0, loaded.getR());
        assertTrue(loaded.isHit());
        assertEquals("12:00", loaded.getCurrentTime());
        assertEquals(100L, loaded.getExecutionTime());
    }

    @Test
    public void testSaveMultipleResults() {
        ResultObject r1 = new ResultObject(1.0, 2.0, 3.0, true, "12:00", 100L);
        ResultObject r2 = new ResultObject(4.0, 5.0, 6.0, false, "12:01", 200L);

        dbService.save(r1);
        dbService.save(r2);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertEquals(2, results.size());
    }

    @Test
    public void testSaveWithHitFalse() {
        ResultObject result = new ResultObject(0.0, 0.0, 1.0, false, "10:30", 50L);
        dbService.save(result);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertEquals(1, results.size());
        assertFalse(results.get(0).isHit());
    }

    @Test
    public void testSaveWithNegativeCoordinates() {
        ResultObject result = new ResultObject(-3.0, -2.5, 5.0, true, "15:45", 300L);
        dbService.save(result);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertEquals(1, results.size());
        assertEquals(-3.0, results.get(0).getX());
        assertEquals(-2.5, results.get(0).getY());
    }

    @Test
    public void testClearRemovesAll() {
        ResultObject r1 = new ResultObject(1.0, 2.0, 3.0, true, "12:00", 100L);
        ResultObject r2 = new ResultObject(4.0, 5.0, 6.0, false, "12:01", 200L);

        dbService.save(r1);
        dbService.save(r2);
        assertEquals(2, ((List<ResultObject>) dbService.load()).size());

        dbService.clear();
        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertTrue(results.isEmpty());
    }

    @Test
    public void testLoadEmptyDatabase() {
        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSavePreservesAllFields() {
        ResultObject result = new ResultObject(
                1.5, 2.5, 3.5,
                true,
                "23:59",
                999999L
        );
        dbService.save(result);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        ResultObject loaded = results.get(0);

        assertEquals(1.5, loaded.getX());
        assertEquals(2.5, loaded.getY());
        assertEquals(3.5, loaded.getR());
        assertTrue(loaded.isHit());
        assertEquals("23:59", loaded.getCurrentTime());
        assertEquals(999999L, loaded.getExecutionTime());
    }

    @Test
    public void testSaveWithZeroExecutionTime() {
        ResultObject result = new ResultObject(0.0, 0.0, 1.0, false, "00:00", 0L);
        dbService.save(result);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertEquals(0L, results.get(0).getExecutionTime());
    }

    @Test
    public void testClearThenSaveAgain() {
        ResultObject r1 = new ResultObject(1.0, 2.0, 3.0, true, "12:00", 100L);
        dbService.save(r1);
        dbService.clear();

        ResultObject r2 = new ResultObject(4.0, 5.0, 6.0, false, "12:01", 200L);
        dbService.save(r2);

        List<ResultObject> results = (List<ResultObject>) dbService.load();
        assertEquals(1, results.size());
        assertEquals(4.0, results.get(0).getX());
    }
}