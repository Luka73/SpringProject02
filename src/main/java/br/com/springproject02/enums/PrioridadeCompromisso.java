package br.com.springproject02.enums;

public enum PrioridadeCompromisso {
    Baixa(1), //o padrão é 0, 1, 2 etc. Mas pode mapear.
    Media(2),
    Alta(3);

    PrioridadeCompromisso(int i) {
    }
}
