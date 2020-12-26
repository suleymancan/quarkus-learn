package org.acme.panache.repository_pattern;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;

// resource: https://quarkus.io/guides/hibernate-orm-panache#using-the-repository-pattern
@QuarkusTest
class RepositoryPatternTest {

    @InjectMock
    FruitRepository fruitRepository;

    @Test
    void testPanacheRepositoryMocking() throws Throwable {
        // Mocked classes always return a default value
        Assertions.assertEquals(0, fruitRepository.count());

        // Now let's specify the return value
        Mockito.when(fruitRepository.count()).thenReturn(23L);
        Assertions.assertEquals(23, fruitRepository.count());

        // Now let's change the return value
        Mockito.when(fruitRepository.count()).thenReturn(42L);
        Assertions.assertEquals(42, fruitRepository.count());

        // Now let's call the original method
        Mockito.when(fruitRepository.count()).thenCallRealMethod();
        Assertions.assertEquals(0, fruitRepository.count());

        // Check that we called it 4 times
        Mockito.verify(fruitRepository, Mockito.times(4)).count();

        // Mock only with specific parameters
        Fruit f = new Fruit("test", "season-1");
        Mockito.when(fruitRepository.findById(12L)).thenReturn(f);
        Assertions.assertSame(f, fruitRepository.findById(12L));
        Assertions.assertNull(fruitRepository.findById(42L));

        // Mock throwing
        Mockito.when(fruitRepository.findById(12L)).thenThrow(new NotFoundException());
        Assertions.assertThrows(NotFoundException.class, () -> fruitRepository.findById(12L));


        // We can even mock your custom methods
        Mockito.verify(fruitRepository, Mockito.atLeastOnce()).findById(Mockito.any());
        Mockito.verifyNoMoreInteractions(fruitRepository);
    }
}
