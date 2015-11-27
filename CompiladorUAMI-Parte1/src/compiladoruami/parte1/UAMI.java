/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami.parte1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextArea;

/**
 *
 * @author Gabriel Zempoalteca Garrido
 */
public class UAMI {

    public static File archivo_tpl, archivo_err;
    public static PrintWriter wr1, wr2;
    public static BufferedWriter bw1, bw2;
    public static FileReader Archivo_fte;
    public static int linea = 1;
    public static String tokenval = null;
    public static int errores;
    JTextArea panelCompilacion;
    Alex A1;
    Tabla_de_Simbolos T1;

    private void crearArchivos(String Nombre_Archivo) {
        int tamaño_N = Nombre_Archivo.length();
        String Nombre_sin_Extension = Nombre_Archivo.substring(0, (tamaño_N - 3));
        try {
            archivo_tpl = new File(Nombre_sin_Extension + "tpl");
            archivo_err = new File(Nombre_sin_Extension + "err");
            if (!archivo_tpl.exists() && !archivo_err.exists()) {
                archivo_tpl.createNewFile();
                archivo_err.createNewFile();
                panelCompilacion.append("Se han creado los achivos *.tpl y *.err con exito\n\n");
                FileWriter w1 = new FileWriter(archivo_tpl);
                bw1 = new BufferedWriter(w1);
                wr1 = new PrintWriter(bw1);
                FileWriter w2 = new FileWriter(archivo_err);
                bw2 = new BufferedWriter(w2);
                wr2 = new PrintWriter(bw2);
                //wr1.append("  En este archivo se encuentran los Lexemas reconocidos por el Analizador Lexicografico\n\n");
                //wr1.append("         \tLexema\tToken\n");
                wr2.append("* Archivo error *\n");
                wr2.append("Muestra los errores que se presentaron en el proceso de compilación:\n");
            } else {
                archivo_tpl.delete();
                archivo_err.delete();
                crearArchivos(Nombre_Archivo);
            }
        } catch (IOException e) {
            panelCompilacion.append("No se pudieron crear los archivos *.tpl y *.err, Error: " + e + "\n");
        }
    }

    private void cierraArchivo() throws IOException {
        wr2.close();
        bw2.close();
        wr1.close();
        bw1.close();
    }

    public void compilador(String ruta_ArchFte, JTextArea panelResComp) throws IOException {
        panelCompilacion = panelResComp;
        panelCompilacion.append("Realizando el Análisis Lexicográgico de:\n" + ruta_ArchFte + "\n");
        panelCompilacion.append("\nEspere un momento por favor...\n\n");
        crearArchivos(ruta_ArchFte);
        errores = 0;
        linea = 1;
        tokenval = "";

        Archivo_fte = new FileReader(ruta_ArchFte);
        
        //Inicializa las palabras reservadas
        T1 = new Tabla_de_Simbolos();
        T1.Inicializa_Palabras_Reservadas();
        
        //LLena el buffer por primera vez
        A1 = new Alex(Archivo_fte);
        A1.Llena_Buffer();
        
        wr1.write("* Archivo Tupla *\n  En este archivo se encuentran los Lexemas reconocidos por el Analizador Lexicografico\n\n");
        wr1.append("\n\n                    Lexema");
        
        //Llamada al analizador lexicografico
        Globales G = new Globales();
        Parser P = new Parser();
        P.Inicio(A1, G, T1);
        
        
        
        
        /*
        T1 = new Tabla_de_Simbolos();
        T1.Inicializa_Palabras_Reservadas();
        A1 = new Alex(Archivo_fte);
        Object tipo;

        Globales G = new Globales();
        A1.Llena_Buffer();

        do {
            tipo = A1.ALexico(G, T1);
            //tipo.getClass().toString();
            String tipo_Int = "class java.lang.Integer";
            if (tipo != G.ERROR && tipo != G.TOKEN_INV) {
                if (!(tipo.getClass().toString().equals(tipo_Int))) {
                    wr1.append("Linea: " + UAMI.linea + "\t(" + tokenval + ",\t" + tipo + ")\n");
                } else {
                    wr1.append("Linea: " + UAMI.linea + "\t(" + T1.Obtener_Lexema((int) tipo) + ",\t" + T1.Obtener_Token((int) tipo) + ")\n");
                }
            } else {
                panelResComp.setText(panelResComp.getText() + "<<<<<ERROR DE COMPILACION>>>>>\n");
            }
        } while (!tipo.equals(G.HECHO));
        */
        panelResComp.append("\nLineas analizadas: " + (linea - 1));
        wr1.append("\nLineas analizadas: " + (linea - 1)+"\n");
        panelResComp.append("\nErrores encontrados: " + errores);
        T1.Imprimir_Tabla();
        cierraArchivo();

    }
}
