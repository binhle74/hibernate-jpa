package com.binhle.hjpa.model.collections.components.mapofembeddables;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Embeddable
public class Image {
    private String title;
    private int width;
    private int height;
}
