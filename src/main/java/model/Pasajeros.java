package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Pasajeros {
    private int id;
    private String nombre;
    private int edad;
    private double peso;
}
