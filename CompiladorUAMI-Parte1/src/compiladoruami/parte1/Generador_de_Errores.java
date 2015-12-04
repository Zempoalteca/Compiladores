/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiladoruami.parte1;

/**
 *
 * @author Leticia
 */
public class Generador_de_Errores {
    
    public void Errores(String se_esperaba){
        Globales G1 = new Globales();
        switch (se_esperaba){
            
            case "$":
            case "#":
            case "@":
            case "¡":
            case "¿":
            case "?":
            case "-":
            case "^":
                UAMI.errores++;
                UAMI.wr2.append("Tipo de Error:"+ G1.ERROR + ", en la Línea:"+
                UAMI.linea +": " + G1.TOKEN_INV +": " + se_esperaba + "\n");
                break;
            default:
                //Errores sintácticos
               
                
      
            
        }
        
    }
    
}
