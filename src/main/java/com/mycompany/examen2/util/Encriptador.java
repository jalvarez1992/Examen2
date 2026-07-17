package com.mycompany.examen2.util;

import java.util.HashMap;
import java.util.Map;

public class Encriptador {

    //Metodo interno para convertir el archivo BLOB (byte[]) en un mapa de caracteres.
    private static Map<Character, Character> obtenerMapa(byte[] configBytes, boolean esParaEncriptar) {
        Map<Character, Character> mapa = new HashMap<>();
        
        if (configBytes == null || configBytes.length == 0) {
            return mapa; // Retorna mapa vacío si no hay configuración
        }

        // Convertimos los bytes de nuevo a un String de texto
        String textoConfig = new String(configBytes);
        
        // Separamos el texto línea por línea
        String[] lineas = textoConfig.split("\n");

        for (String linea : lineas) {
            linea = linea.trim(); // Limpiamos espacios en blanco o saltos de carro extra (\r)
            
            // Validamos que la línea tenga el formato correcto "x,y"
            if (linea.length() >= 3 && linea.contains(",")) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    char original = partes[0].charAt(0);
                    char reemplazo = partes[1].charAt(0);

                    // Si es para encriptar guardamos (original = reemplazo)
                    // Si es para desencriptar guardamos (reemplazo = original)
                    if (esParaEncriptar) {
                        mapa.put(original, reemplazo);
                    } else {
                        mapa.put(reemplazo, original);
                    }
                }
            }
        }
        return mapa;
    }

    //Encripta una frase utilizando el archivo de configuracion en formato BLOB.
    public static String encriptar(String textoOriginal, byte[] configBytes) {
        Map<Character, Character> mapa = obtenerMapa(configBytes, true);
        StringBuilder resultado = new StringBuilder();

        // Recorremos letra por letra la frase del usuario
        for (char c : textoOriginal.toCharArray()) {
            // getOrDefault busca 'c' en el mapa. Si no existe (ej. un espacio), deja 'c' intacto.
            resultado.append(mapa.getOrDefault(c, c));
        }

        return resultado.toString();
    }

    //Desencripta una frase utilizando la configuracion almacenada en la base de datos.
    public static String desencriptar(String textoEncriptado, byte[] configBytes) {
        Map<Character, Character> mapaInverso = obtenerMapa(configBytes, false);
        StringBuilder resultado = new StringBuilder();

        for (char c : textoEncriptado.toCharArray()) {
            // Ahora busca el caracter encriptado y devuelve el original
            resultado.append(mapaInverso.getOrDefault(c, c));
        }

        return resultado.toString();
    }
}