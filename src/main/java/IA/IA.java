/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IA;

import java.net.http.HttpClient;

/**
 *
 * @author MI PC
 */
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IA {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("API_KEY");

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro:generateContent";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public String createContent(String message) {
    JsonObject requestBody = new JsonObject();
JsonObject content = new JsonObject();
JsonObject part = new JsonObject();
part.addProperty("text", message);

JsonArray partsArray = new JsonArray();
partsArray.add(part);

content.add("parts", partsArray);

JsonArray contentsArray = new JsonArray();
contentsArray.add(content);

requestBody.add("contents", contentsArray);

    try {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObj = new JSONObject(response.body());

        if (!jsonObj.has("candidates")) {
            return "Error: La respuesta de la IA no contiene datos válidos.";
        }

        String respuesta = jsonObj.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");

        // 🚀 Verificar si el usuario pidió una tabla específica
        if (respuesta.toLowerCase().contains("historial")) {
            return obtenerDatosTabla("historial");
        } else if (respuesta.toLowerCase().contains("users")) {
            return obtenerDatosTabla("users");
        } else if (respuesta.toLowerCase().contains("amigos")) {
            return obtenerDatosTabla("amigos");
        } else if (respuesta.toLowerCase().contains("juego")) {
            return obtenerDatosTabla("juego");
        } else if (respuesta.toLowerCase().contains("lista_deseados")) {
            return obtenerDatosTabla("lista_deseados");
        } else if (respuesta.toLowerCase().contains("pedidos")) {
            return obtenerDatosTabla("pedidos");
        } else if (respuesta.toLowerCase().contains("pedido_juegos")) {
            return obtenerDatosTabla("pedido_juegos");
        }

        return respuesta;

    } catch (IOException | InterruptedException e) {
        return "Error al crear contenido: " + e.getMessage();
    } catch (JSONException e) {
        return "Error en el formato de la respuesta de la IA: " + e.getMessage();
    }
}

   public String obtenerDatosTabla(String tabla) {
    String url = "jdbc:mysql://localhost:3307/proyectoFinal";
    String usuario = "root";
    String contraseña = "Kurumi107911";

    StringBuilder resultado = new StringBuilder("📊 Datos de la tabla " + tabla + ":\n");

    String sql = "SELECT * FROM " + tabla + " LIMIT 10"; // 🔹 Solo los primeros 10 registros

    try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        ResultSetMetaData metaData = rs.getMetaData();
        int columnas = metaData.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnas; i++) {
                resultado.append(metaData.getColumnName(i)).append(": ").append(rs.getString(i)).append(" | ");
            }
            resultado.append("\n");
        }

    } catch (SQLException e) {
        return "Error al obtener datos de la tabla " + tabla + ": " + e.getMessage();
    }

    return resultado.toString();
}
   
   
   ///AAAAAA///

}
