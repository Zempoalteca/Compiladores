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
        int pos = A1.ALexico(G1, T1);
        preanalisis[0] = T1.Obtener_Lexema(pos);
        preanalisis[1] = T1.Obtener_Token(pos);
        Encabezado();
        //Secuencia();
        Enunc_comp();
        Parea(G1.HECHO);
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
            /*case "identificador":
                Asignacion();
                break;*/
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
                Asignacion();
                /*if(preanalisis[1].equals(G1.ID)){
                    Asignacion();
                }else{
                    if(preanalisis[1].equals(G1.ASIGNACION)){
                        
                    }
                    Parea(";");
                }*/
            break;
        }
    }

    private void Asignacion() throws IOException {
        Parea(G1.ID);
        Parea(G1.ASG);
        Expresion();
        Parea(";");
    }

    private void Enunc_impresion() throws IOException {
        int primeraLinea = UAMI.linea;
        int lineaActual;
        Parea(G1.IMPRIME);
        Parea("(");
        Parea(G1.CADENA);
        lineaActual=UAMI.linea;
        while (!preanalisis[0].equals(")") && lineaActual==primeraLinea) {
            Parea(",");
            Expresion();
            lineaActual=UAMI.linea;
        }
        Parea(")");
        Parea(";");
    }

    private void Enunc_para() throws IOException {
        Parea(G1.PARA);
        Asignacion();
        Parea(G1.A);
        Expresion();
        Parea(G1.HAZ);
        Enunc_comp();
    }

    private void Enunc_condicional() throws IOException {
        Parea(G1.SI);
        Expresion();
        Parea(G1.ENTONCES);
        Enunciado();
        if (preanalisis[1].equals(G1.OTRO)) {
            Parea(G1.OTRO);
            Enunciado();
        }
    }

    private void Enunc_mientras() throws IOException {
        Parea(G1.MIENTRAS);
        Expresion();
        Parea(G1.HAZ);
        Enunc_comp();
    }

    private void Enunc_repite() throws IOException {
        Parea(G1.REPITE);
        Enunc_comp();
        Parea(G1.HASTA);
        Expresion();
        Parea(";");
    }

    private void Expresion() throws IOException {
        Exp_simple();
        if (preanalisis[1].equals(G1.RELOP)) {
            Parea(G1.RELOP);
            Exp_simple();
        } else {
            if (preanalisis[1].equals(G1.LOGOP)) {
                Parea(G1.LOGOP);
                Exp_simple();
            }
        }
    }

    private void Exp_simple() throws IOException {
        Termino();
        while (preanalisis[1].equals(G1.ADDOP)) {
            Parea(G1.ADDOP);
            Termino();
        }
    }

    private void Termino() throws IOException {
        Factor();
        while (preanalisis[1].equals(G1.MULOP)) {
            Parea(G1.MULOP);
            Termino();
        }
    }

    private void Factor() throws IOException {
        if(preanalisis[0].equals("(")){
            Parea("(");
                Expresion();
                Parea(")");
        }else{
            if(preanalisis[1].equals(G1.NUM_ENT)){
                Parea(G1.NUM_ENT);
            }else{
                Parea(G1.ID);
            }
        }
        /*switch (preanalisis[0]) {                   //No es preanalisis de 1???
            case "(":
                Parea("(");
                Expresion();
                Parea(")");
                break;
            case "entero":
                Parea(G1.NUM_ENT);
                break;
            default:
                Parea(G1.ID);
                break;
        }*/
    }

    /*private void Secuencia() throws IOException {
     Parea(G1.COMIENZA);
     while (!preanalisis[1].equals(G1.HECHO) && !preanalisis[0].equals(G1.TERMINA)) {
     Asignacion();
     }
     Parea(G1.TERMINA);
     }*/
    public /*boolean*/void Parea(String se_espera) throws IOException {
        if (preanalisis[0].equals(se_espera) || preanalisis[1].equals(se_espera)) { //preanalisis[0].equals(se_espera)
            int pos = A1.ALexico(G1, T1);
            preanalisis[0] = T1.Obtener_Lexema(pos);
            preanalisis[1] = T1.Obtener_Token(pos);
            /*System.out.println("Preanalisis [0]:" + preanalisis[0] + "\n"
                    + "Preanalisis[1]:" + preanalisis[1] + "\n");*/
            
            //return true;
        } else {
            UAMI.errores++;
            UAMI.wr2.append("Error "+UAMI.errores+" en la linea" + UAMI.linea + "; Se esperaba un: " + se_espera +" tipo de error: "
                    + G1.ERROR_S +  "\n");
           
            //return false;
        }
    }
}