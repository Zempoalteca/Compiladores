/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiladoruami.Final;

/**
 *
 * @author Lety, Cecy & Gab
 */
public class Generador_Codigo_Intermedio {
 
    public void Emite(String Valor_Token,String Lexema ){
        Globales G1 = new Globales();
        switch(Valor_Token){
            
            case "halt":
                UAMI.wr3.append(G1.HALT + "\n");
                break;
            case "lvalue":
                UAMI.wr3.append(G1.VALOR_I + " " + Lexema + "\n");
                break;
            case "rvalue":
                UAMI.wr3.append(G1.VALOR_D + " " + Lexema + "\n");
                break;
            case "label":
                UAMI.wr3.append(G1.ETIQUETA + " " + Lexema + "\n");
                break;
            case "push":
                UAMI.wr3.append(G1.PUSH + " " + Lexema + "\n");
                break;
            case "gofalse":
                UAMI.wr3.append(G1.SI_FALSO_VE_A + " " + Lexema + "\n");
                break;
            case "goto":
                UAMI.wr3.append(G1.VE_A + " " + Lexema + "\n");
                break;
            case "write":
                UAMI.wr3.append(G1.ELIMINA + "\n");
                break;
            case "print":
                UAMI.wr3.append(G1.COPIA + " " + Lexema + "\n");
                break;
            case ":=":
                UAMI.wr3.append(G1.ASIGN + " " + Lexema + "\n");
                break;
            //default:
            case "OPERADOR RELACIONAL":
            case "OPERADOR LOGICO":
            case "OPERADOR DE SUMA O RESTA":
            case "OPERADOR DE MULTIPLICACION O DIVISION":
                switch(Lexema){
                    /*OPERADOR RELACIONAL*/
                    case "<":
                        UAMI.wr3.append(G1.LT + "\n");
                        break;
                    case "<=":
                        UAMI.wr3.append(G1.LE + "\n");
                        break;
                    case "==":
                        UAMI.wr3.append(G1.EQ + "\n");
                        break;
                    case "=":
                        UAMI.wr3.append(G1.ASG + "\n");
                        break;
                    case ">":
                        UAMI.wr3.append(G1.GT + "\n");
                        break;
                    case ">=":
                        UAMI.wr3.append(G1.GE + "\n");
                        break;
                    case "!=":
                        UAMI.wr3.append(G1.NE + "\n");
                        break;
                   /*OPERADORES ARITMETICOS*/
                    case "+":
                        UAMI.wr3.append(G1.MAS + "\n");
                        break;
                    case "-":
                        UAMI.wr3.append(G1.MENOS + "\n");
                        break;
                    case "*":
                        UAMI.wr3.append(G1.MULT + "\n");
                        break;
                    case "/":
                        UAMI.wr3.append(G1.DIV + "\n");
                        break;
                    case "%":
                        UAMI.wr3.append(G1.MODULO + "\n");
                        break;
                    /*OPERADOR LOGICO*/
                    case "&&":
                        UAMI.wr3.append(G1.AND + "\n");
                        break;
                    case "||":
                        UAMI.wr3.append(G1.OR + "\n");
                        break;
                }
        }

}
    
}
