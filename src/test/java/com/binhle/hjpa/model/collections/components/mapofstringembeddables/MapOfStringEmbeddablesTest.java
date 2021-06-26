package com.binhle.hjpa.model.collections.components.mapofstringembeddables;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class MapOfStringEmbeddablesTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testMapStringEmbeddables() {
        Item item = new Item();
        for (int i = 1; i < 5; i++) {
            Image image = new Image();
            image.setTitle(String.format("ABC-%s", i));
            image.setWidth(i);
            image.setHeight(i);
            item.getImages().put(String.format("abc-%s.jpg", i), image);
        }
        Comparator<String> comp = (String::compareTo);
        String expectedFilenames = item.getImages().keySet().stream().sorted(comp.reversed()).reduce("", this::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        Item savedItem = this.find(Item.class, item.getId());
        String savedFilenames = savedItem.getImages().keySet().stream().reduce("", this::concat);

        // Verify
        assertEquals(expectedFilenames, savedFilenames);
    }
}
