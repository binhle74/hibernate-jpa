package com.binhle.hjpa.model.collections.components.setofembeddables;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import com.binhle.hjpa.BaseTest;

import org.junit.After;
import org.junit.Test;

public class SetOfEmbeddablesOrderByTest extends BaseTest {

    @Override
    public void before() {

    }

    @After
    @Override
    public void after() {
        this.cleanData(OrderByItem.class);
    }

    @Test
    public void testImagesOrderBy() {
        OrderByItem item = new OrderByItem();
        for (int i = 3; i > 0; i--) {
            for (int j = 1; j < 3; j++) {
                OrderByImage image = new OrderByImage();
                image.setFilename(String.format("abc-%s.jpg", i));
                image.setTitle(String.format("ABC %s", i));
                image.setWidth(j);
                image.setHeight(j);
                item.getImages().add(image);
            }
        }
        Comparator<OrderByImage> orderByFilename = Comparator.comparing(OrderByImage::getFilename);
        Comparator<OrderByImage> orderByWidth = Comparator.comparing(OrderByImage::getWidth).reversed();
        Comparator<OrderByImage> orderByImage = orderByFilename.thenComparing(orderByWidth);

        String expectedImages = item.getImages().stream().sorted(orderByImage)
                .map(image -> image.getFilename() + "=" + image.getWidth()).reduce("", this::concat);

        this.beginTransaction().persist(item).commitTransaction().closeEntityManager();
        OrderByItem savedItem = this.find(OrderByItem.class, item.getId());
        String actualImages = savedItem.getImages().stream().map(image -> image.getFilename() + "=" + image.getWidth())
                .reduce("", this::concat);
        assertEquals(expectedImages, actualImages);
    }
}
