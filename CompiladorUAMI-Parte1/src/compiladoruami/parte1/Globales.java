/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami.parte1;

/**
 *
 * @author Cecy, Lety & Gab
 */
public class Globales {
    
    
    /************************************************/
    /***********Palabras reservadas******************/
    /************************************************/
    public final String PROGRAMA = "programa";
    public final String COMIENZA = "comienza";
    public final String TERMINA = "termina";
    public final String ESCRIBE = "escribe";
    public final String IMPRIME = "imprime";
    public final String READ = "read";
    public final String SI = "si";
    public final String ENTONCES = "entonces";
    public final String OTRO = "otro";
    public final String MIENTRAS = "mientras";
    public final String HAZ = "haz";
    public final String DUMP = "dump";
    public final String HALT = "halt";
    public final String REPITE = "repìte";
    public final String HASTA = "hasta";
    public final String PARA = "para";
    public final String A = "a";
    
    /**************************************************/
    /***********Operadores Logicos*********************/
    /**************************************************/
    public final String NOT = "!";
    public final String OR = "||";
    public final String AND = "&&";
    
    /**************************************************/
    /***********Operadores Relacionales****************/
    /**************************************************/
    public final String LT = "<";
    public final String LE = "<=";
    public final String EQ = "==";
    public final String ASG = "=";
    public final String GE = ">=";
    public final String GT = ">";
    public final String NE = "!=";  
    
    
    /**************************************************/
    /************Operadores Aritmeticos****************/
    /**************************************************/
    public final String MAS = "+";
    public final String MENOS = "-";
    public final String MULT = "*";
    public final String DIV = "/";
    public final String MODULO = "%";
    
    
    
    
    
    public final String EOS = "EOS";
    public final String ASIGNACION = "ASIGNACION";
    public final String ID = "IDENTIFICADOR";
    public final String NUM_ENT = "ENTERO";
    public final String CADENA = "CADENA";
    public final String HECHO = "FIN DE ARCHIVO";
    public final String COMENTARIO = "COMENTARIO";
    public final String RELOP = "OPERADOR RELACIONAL";
    public final String LOGOP = "OPERADOR LOGICO";
    public final String ADDOP = "OPERADOR DE SUMA O RESTA";
    public final String MULOP = "OPERADOR DE MULTIPLICACION O DIVISION";
    public final String STRINGS = "CADENA DE CARACTERES";
    public final String ERROR = "ERROR LEXICOGRAFICO";
    public final String TOKEN_INV = "TOKEN INVALIDO";
    public final String RESTO_MUNDO = "RESTO DEL MUNDO";
    public final String ERROR_S = "ERROR SINTÁCTICO";
    public final String ENUNC_VALIDO = "ENUNCIADO VALIDO";
    public final String EXP_VALIDA = "EXPRESION VALIDA";
    
    
    /*********************************************************************/
    /**************Tokens para el generador de codigo*********************/
    /*********************************************************************/
     public final String VALOR_I = "lvalue";
     public final String VALOR_D = "rvalue";
     public final String PUSH = "push";
     public final String ASIGN = ":=";
     public final String ETIQUETA = "label";
     public final String VE_A = "goto";
     public final String SI_FALSO_VE_A = "gofalse" ;
     public final String ELIMINA ="write";
     public final String COPIA = "print";
}
