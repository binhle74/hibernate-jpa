package com.binhle.hjpa.model.collections.basictypes.setofstringsorderby;

import static org.junit.Assert.assertEquals;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SetJpaOrderByTest extends BaseTest {

    @Before
    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(JpaOrderByItem.class);
    }

    @Test
    public void testJpaOrderByImages() {
        JpaOrderByItem item = new JpaOrderByItem();
        item.setName("JPA OrderBy Item");
        for (int i = 5; i > 0; i--) {
            item.getImages().add(String.format("abc-%s.jpg", i));
        }
        String expected = item.getImages().stream().sorted().reduce("", this::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        JpaOrderByItem savedItem = this.find(JpaOrderByItem.class, item.getId());
        String actual = savedItem.getImages().stream().reduce("", this::concat);
        assertEquals(expected, actual);
    }
}
