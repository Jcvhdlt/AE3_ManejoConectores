package model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Coche {
    private int id;
    private String modelo;
    private int plazas;
}
