package com.binhle.hjpa.model.collections.components.setofembeddables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SetOfEmbeddablesTest extends BaseTest {

    @Before
    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testSetOfImages() {
        Item item = new Item();
        for (int i = 1; i <= 5; i++) {
            Image image = new Image();
            image.setFilename(String.format("abc-%s.jpg", i));
            image.setTitle(String.format("Title %s", i));
            image.setWidth(i * 5);
            image.setHeight(i * 2);
            item.getImages().add(image);

            Image image1 = new Image();
            image1.setFilename(String.format("abc-%s.jpg", i));
            image1.setTitle(String.format("Title %s", i));
            image1.setWidth(i * 5);
            image1.setHeight(i * 2);
            item.getImages().add(image1);
        }
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        assertNotNull(item);
        assertEquals(5, item.getImages().size());
    }
}
