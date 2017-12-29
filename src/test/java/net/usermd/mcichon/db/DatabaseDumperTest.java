package net.usermd.mcichon.db;

import net.usermd.mcichon.body.products.Order;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DatabaseDumperTest {
    @Mock
    private Order order;

    private DatabaseDumper databaseDumper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(order.getOrderLines()).thenReturn(Arrays.asList());
    }

    @Test
    void testSaveToDatabase() {
        List<Document> documents = DatabaseDumper.convertToDocuments(order);
        verify(order).getOrderLines();

        assertThat(documents).hasSize(0);
    }
}