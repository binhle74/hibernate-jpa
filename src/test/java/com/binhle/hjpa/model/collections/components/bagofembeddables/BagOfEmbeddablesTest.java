package com.binhle.hjpa.model.collections.components.bagofembeddables;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class BagOfEmbeddablesTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(Item.class);
    }

    @Test
    public void testBagOfEmbeddablesOrder() {
        Item item = new Item();
        for (int i = 3; i > 0; i--) {
            for (int j = 1; j < 3; j++) {
                Image image = new Image();
                image.setFilename(String.format("abc-%s", i));
                image.setTitle(String.format("ABC-%s", i));
                image.setWidth(j);
                image.setHeight(j);
                item.getImages().add(image);
            }
        }

        Comparator<Image> widthOrder = Comparator.comparing(Image::getWidth).reversed();
        Comparator<Image> imageOrder = Comparator.comparing(Image::getFilename).thenComparing(widthOrder);
        String expectedImages = item.getImages().stream().sorted(imageOrder)
                .map(image -> image.getFilename() + " = " + image.getWidth()).reduce("", this::concat);
        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();

        // Find item by id
        Item savedItem = this.find(Item.class, item.getId());
        String savedImages = savedItem.getImages().stream().map(image -> image.getFilename() + " = " + image.getWidth())
                .reduce("", this::concat);

        // Verify
        assertEquals(expectedImages, savedImages);
    }

}
