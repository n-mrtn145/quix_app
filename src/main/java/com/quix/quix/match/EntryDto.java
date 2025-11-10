package com.quix.quix.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntryDto {

    private List<Integer> red;

    private List<Integer> yellow;

    private List<Integer> green;

    private List<Integer> blue;

    private Integer wrongTrow;

}
