package com.javarush.redis;

import com.javarush.domain.Continent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;
}
