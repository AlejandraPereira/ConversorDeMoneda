import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CurrencyConverter {
    private final Gson gson = new Gson();

    public double convert(String jsonResponse, String targetCurrency, double amount) {
        // Parsear la respuesta JSON usando Gson
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

        // Verificar si la moneda  está en las tasas de conversión
        if (rates.has(targetCurrency)) {
            double rate = rates.get(targetCurrency).getAsDouble();
            return amount * rate;
        } else {
            throw new IllegalArgumentException("Moneda no encontrada en la API.");
        }
    }
}

