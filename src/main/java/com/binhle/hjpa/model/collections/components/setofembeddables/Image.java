package com.binhle.hjpa.model.collections.components.setofembeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Embeddable
public class Image {
    @Getter
    @Setter
    @Column(nullable = false)
    private String filename;

    @Getter
    @Setter
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    private int width;
}
