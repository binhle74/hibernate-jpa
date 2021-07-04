package com.binhle.hjpa.model.associations.onetomany.cascade.orphan.component;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class Image {
    private String filename;

    private String title;
}
