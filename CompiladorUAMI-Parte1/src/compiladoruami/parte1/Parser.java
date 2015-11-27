/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami.parte1;

import java.io.IOException;

/**
 *
 * @author Cecy, Lety & Gab
 */
public class Parser {

    String[] preanalisis;
    public Alex A1;
    public Globales G1;
    public Tabla_de_Simbolos T1;

    public Parser(Alex A, Globales G, Tabla_de_Simbolos T) {
        preanalisis = new String[2];
        A1 = A;
        G1 = G;
        T1 = T;
    }

    void Inicio() throws IOException {
        int pos = 0;
        pos = A1.ALexico(G1, T1);
        preanalisis[0] = T1.Obtener_Lexema(pos);
        preanalisis[1] = T1.Obtener_Token(pos);
        Encabezado();
        Secuencia();
        Parea(G1.HECHO);
    }

    private void Encabezado() throws IOException {
        Parea(G1.PROGRAMA);
        Parea(G1.ID);
        Parea(";");
    }

    private void Secuencia() throws IOException {
        Parea(G1.COMIENZA);
        while (!preanalisis[1].equals(G1.HECHO) && !preanalisis[0].equals(G1.TERMINA)) {
            Asignacion();
        }
        Parea(G1.TERMINA);
    }

    private void Asignacion() throws IOException {
        Parea(G1.ID);
        Parea(G1.ASG);
        Parea(G1.NUM_ENT);
        Parea(";");
    }

    public boolean Parea(String se_espera) throws IOException {
        if (preanalisis[0].equals(se_espera) || preanalisis[1].equals(se_espera)) { //preanalisis[0].equals(se_espera)
            int pos = A1.ALexico(G1, T1);
            preanalisis[0] = T1.Obtener_Lexema(pos);
            preanalisis[1] = T1.Obtener_Token(pos);
            System.out.println("Preanalisis [0]:" + preanalisis[0] + "\n"
                    + "Preanalisis[1]:" + preanalisis[1] + "\n");
            return true;
        } else {
            UAMI.errores++;
            UAMI.wr2.append("Error " + UAMI.errores + " en la linea :" + UAMI.linea + ", se esperaba: \"" + se_espera + "\", tipo de error: ERROR SINTACTICO\n");
            return false;
        }
    }

}
