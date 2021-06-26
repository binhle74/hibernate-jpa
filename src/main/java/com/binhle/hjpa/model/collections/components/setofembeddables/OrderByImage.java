package com.binhle.hjpa.model.collections.components.setofembeddables;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@Embeddable
public class OrderByImage {
    private String filename;

    private String title;

    private int width;

    private int height;
}
