/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami_final;

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
    public Generador_Codigo_Intermedio GI1;
    int etiq=0;

    public Parser(Alex A, Globales G, Tabla_de_Simbolos T, Generador_Codigo_Intermedio GI) {
        preanalisis = new String[2];
        A1 = A;
        G1 = G;
        T1 = T;
        GI1 = GI;
    }

    void Inicio() throws IOException {
        int pos = A1.ALexico(G1, T1);
        preanalisis[0] = T1.Obtener_Lexema(pos);
        preanalisis[1] = T1.Obtener_Token(pos);
        Encabezado();
        Enunc_comp();
        Parea(G1.HECHO);
        GI1.Emite(G1.HALT, null);
    }

    private void Encabezado() throws IOException {
        Parea(G1.PROGRAMA);
        Parea(G1.ID);
        Parea(";");
    }

    private void Enunc_comp() throws IOException {
        Parea(G1.COMIENZA);
        while (!preanalisis[1].equals(G1.HECHO) && !preanalisis[0].equals(G1.TERMINA)) {
            Enunciado();
        }
        Parea(G1.TERMINA);
    }

    private void Enunciado() throws IOException {

        switch (preanalisis[0]) {
            case "comienza":
                Enunc_comp();
                break;
            case "si":
                Enunc_condicional();
                break;
            case "mientras":
                Enunc_mientras();
                break;
            case "para":
                Enunc_para();
                break;
            case "imprime":
                Enunc_impresion();
                break;
            case "repite":
                Enunc_repite();
                break;
            case ";":
                Parea(";");
            default:
                if (preanalisis[1].equals(G1.ID)) {
                    Asignacion();
                } else {
                    int posicion;
                    posicion = A1.ALexico(G1, T1);
                    preanalisis[0] = T1.Obtener_Lexema(posicion);
                    preanalisis[1] = T1.Obtener_Token(posicion);
                    if (!(preanalisis[1].equals(G1.ERROR)) && !(preanalisis[1].equals(G1.TOKEN_INV))) {
                        Parea(G1.ENUNC_VALIDO);
                    }             
                    break;
                }
        }
    }

    private void Asignacion() throws IOException {
        GI1.Emite(G1.VALOR_I, preanalisis[0]);
        Parea(G1.ID);
        Parea(G1.ASG);
        Expresion();
        GI1.Emite(G1.ASIGN, null);
        Parea(";");
    }

    private void Enunc_impresion() throws IOException {
        int primeraLinea = UAMI.linea;
        int lineaActual;
        Parea(G1.IMPRIME);
        Parea("(");
        GI1.Emite(null, null);
        Parea(G1.CADENA);
        lineaActual = UAMI.linea;
        while (!preanalisis[0].equals(")") && lineaActual == primeraLinea) {
            Parea(",");
            Expresion();
            lineaActual = UAMI.linea;
        }
        GI1.Emite(G1.COPIA, null);
        Parea(")");
        Parea(";");
    }

    private void Enunc_para() throws IOException {
        String c;
        int sal;
        int entrada = 0;
        Parea(G1.PARA);
        GI1.Emite(G1.VALOR_I, String.valueOf(c=preanalisis[0]));
        Parea(G1.ID);
        Parea(G1.ASG);
        Exp_simple();
        GI1.Emite(G1.ASIGN, null);
        Parea(G1.A);
        Expresion();
        GI1.Emite(G1.VALOR_D, c);
        GI1.Emite(G1.RELOP, G1.LE);
        GI1.Emite(G1.SI_FALSO_VE_A, String.valueOf(sal=etiq++));
        GI1.Emite(G1.VALOR_I, c);
        GI1.Emite(G1.VALOR_D, c);
        GI1.Emite(G1.PUSH, String.valueOf(1));
        GI1.Emite(G1.ADDOP, G1.MAS);
        GI1.Emite(G1.ASIGN, null);
        Parea(G1.HAZ);
        Enunciado();
        GI1.Emite(G1.VE_A, String.valueOf(entrada));
        GI1.Emite(G1.ETIQUETA, String.valueOf(sal));
    }

    private void Enunc_condicional() throws IOException {
        int cond, sal;
        Parea(G1.SI);
        Expresion();
        GI1.Emite(G1.SI_FALSO_VE_A, String.valueOf(cond=etiq++));
        Parea(G1.ENTONCES);
        Enunciado();
        GI1.Emite(G1.VE_A, String.valueOf(sal=etiq++));
        if (preanalisis[1].equals(G1.OTRO)) {
            Parea(G1.OTRO);
            GI1.Emite(G1.ETIQUETA, String.valueOf(cond));
            Enunciado();
            GI1.Emite(G1.ETIQUETA, String.valueOf(sal));
        }
        GI1.Emite(G1.ETIQUETA, String.valueOf(cond));
    }

    private void Enunc_mientras() throws IOException {
        int cond, sal;
        Parea(G1.MIENTRAS);
        GI1.Emite(G1.ETIQUETA, String.valueOf(cond=etiq++));
        Expresion();
        GI1.Emite(G1.SI_FALSO_VE_A, String.valueOf(sal=etiq++));
        Parea(G1.HAZ);
        Enunc_comp();
        GI1.Emite(G1.VE_A, String.valueOf(cond));
        GI1.Emite(G1.ETIQUETA, String.valueOf(sal));
    }

    private void Enunc_repite() throws IOException {
        int ciclo;
        Parea(G1.REPITE);
        GI1.Emite(G1.ETIQUETA, String.valueOf(ciclo=etiq++));
        Enunc_comp();
        Parea(G1.HASTA);
        Expresion();
        GI1.Emite(G1.SI_FALSO_VE_A, String.valueOf(ciclo));
        Parea(";");
    }

    private void Expresion() throws IOException {
        Exp_simple();
        String aux;
        aux=preanalisis[0];
        if (preanalisis[1].equals(G1.RELOP)) {
            Parea(G1.RELOP);
            Exp_simple();
            GI1.Emite(G1.RELOP, aux);
        } else {
            if (preanalisis[1].equals(G1.LOGOP)) {
                Parea(G1.LOGOP);
                Exp_simple();
                GI1.Emite(G1.LOGOP, aux);
            }
        }
    }

    private void Exp_simple() throws IOException {
        Termino();
        String aux;
        aux=preanalisis[0];
        while (preanalisis[1].equals(G1.ADDOP)) {
            Parea(G1.ADDOP);
            Termino();
            GI1.Emite(G1.ADDOP, aux);
        }
    }

    private void Termino() throws IOException {
        Factor();
        String aux;
        aux=preanalisis[0];
        while (preanalisis[1].equals(G1.MULOP)) {
            Parea(G1.MULOP);
            Termino();
            GI1.Emite(G1.MULOP, aux);
        }
    }

    private void Factor() throws IOException {
        if (preanalisis[0].equals("(")) {
            Parea("(");
            Expresion();
            Parea(")");
        } else {
            if (preanalisis[1].equals(G1.NUM_ENT)) {
                GI1.Emite(G1.PUSH, preanalisis[0]);
                Parea(G1.NUM_ENT);
            } else {
                if(preanalisis[1].equals(G1.ID)){
                    Parea(G1.ID);
                    GI1.Emite(G1.VALOR_D, preanalisis[0]);
                }else{
                    Parea(G1.EXP_VALIDA);
                }
                
            }
        }
        
    }

    public  void Parea(String se_espera) throws IOException {
        if (preanalisis[0].equals(se_espera) || preanalisis[1].equals(se_espera)) {
            int pos = A1.ALexico(G1, T1);
            preanalisis[0] = T1.Obtener_Lexema(pos);
            preanalisis[1] = T1.Obtener_Token(pos);
        } else {
            UAMI.errores++;
            UAMI.wr2.append("Error " + UAMI.errores + " en la linea" + UAMI.linea + "; Se esperaba un: " + se_espera + " tipo de error: "
                    + G1.ERROR_S + "\n");
        }
    }
}
