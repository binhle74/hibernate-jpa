package com.binhle.hjpa.model.collections.components.mapofembeddables;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class MapOfEmbeddablesTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testMapOfEmbeddables() {
        Item item = new Item();
        for (int i = 1; i < 5; i++) {
            Filename filename = new Filename();
            filename.setName(String.format("abc-%s", i));
            filename.setExtension("jpg");
            Image image = new Image();
            image.setTitle(String.format("ABC-%s", i));
            image.setWidth(i);
            image.setHeight(i);
            item.getImages().put(filename, image);
        }

        Comparator<Filename> nameComp = Comparator.comparing(Filename::getName);
        String expectedImages = item.getImages().keySet().stream().sorted(nameComp.reversed())
                .map(image -> image.getName()).reduce("", this::concat);

        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Query saved item
        Item savedItem = this.find(Item.class, item.getId());
        String savedImages = savedItem.getImages().keySet().stream().map(image -> image.getName()).reduce("",
                this::concat);

        // Verify
        assertEquals(expectedImages, savedImages);

    }

}
