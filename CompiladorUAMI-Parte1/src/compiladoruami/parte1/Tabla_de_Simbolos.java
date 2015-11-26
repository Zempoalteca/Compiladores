/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami.parte1;

/**
 *
 * @author Cecy, Lety y Gab
 */
public class Tabla_de_Simbolos {

    public static String[][] TABLA_DE_SIMBOLOS;
    public static int ultimo_entrar;
    public static int SYMMAX = 1000;

    public Tabla_de_Simbolos() {
        ultimo_entrar = 0;
        TABLA_DE_SIMBOLOS = new String[SYMMAX][2];

    }

    public int Buscar_Simbolo(String Lexema) {
        int i=0;
        for(i=ultimo_entrar; i>=0; i--){
            if(Lexema.equals(TABLA_DE_SIMBOLOS[i][0])){
                return i;
            }
        }
        return -1;
    }

    public String Obtener_Token(int i) {
        return TABLA_DE_SIMBOLOS[i][1].toString();

    }

    public String Obtener_Lexema(int i) {
        return TABLA_DE_SIMBOLOS[i][0].toString();

    }

    public int Inserta_en_Tabla_Simbolos(String Lexema, String Token) {
        if (ultimo_entrar < SYMMAX) {

            TABLA_DE_SIMBOLOS[ultimo_entrar][0] = Lexema;
            TABLA_DE_SIMBOLOS[ultimo_entrar][1] = Token;
            ultimo_entrar++;
            return ultimo_entrar - 1;
        } else {
            return -1;
        }

    }

    public void Imprimir_Tabla() {
       int i;
        for(i=0; i<ultimo_entrar; i++){
            UAMI.wr1.append(+i+" " +" ["+TABLA_DE_SIMBOLOS[i][0]+ "]\t["+TABLA_DE_SIMBOLOS[i][1]+"]\n");
        }
    }

    public void Inicializa_Palabras_Reservadas() {
        TABLA_DE_SIMBOLOS[0][0] = "programa";//
        TABLA_DE_SIMBOLOS[1][0] = "comienza";
        TABLA_DE_SIMBOLOS[2][0] = "termina";
        TABLA_DE_SIMBOLOS[3][0] = "escribe";
        TABLA_DE_SIMBOLOS[4][0] = "imprime";
        TABLA_DE_SIMBOLOS[5][0] = "read";
        TABLA_DE_SIMBOLOS[6][0] = "si";
        TABLA_DE_SIMBOLOS[7][0] = "entonces";
        TABLA_DE_SIMBOLOS[8][0] = "otro";
        TABLA_DE_SIMBOLOS[9][0] = "mientras";
        TABLA_DE_SIMBOLOS[10][0] = "halt";
        TABLA_DE_SIMBOLOS[11][0] = "dump";
        TABLA_DE_SIMBOLOS[12][0] = "haz";
        TABLA_DE_SIMBOLOS[13][0] = "repìte";
        TABLA_DE_SIMBOLOS[14][0] = "hasta";
        TABLA_DE_SIMBOLOS[15][0] = "para";
        TABLA_DE_SIMBOLOS[16][0] = "a";
       
        for(ultimo_entrar = 0;  ultimo_entrar <17;ultimo_entrar ++){
            TABLA_DE_SIMBOLOS[ultimo_entrar][1] = "PALABRA RESERVADA";
        }
    
    }

}
