package com.binhle.hjpa.model.collections.components.mapofembeddables;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@Embeddable
@EqualsAndHashCode
public class Filename {
    private String name;
    private String extension;
}
