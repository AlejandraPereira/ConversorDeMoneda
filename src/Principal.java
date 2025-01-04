import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir datos al usuario
        System.out.println("Ingrese la moneda base (por ejemplo, USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese la moneda a convertir (por ejemplo, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        // Crear instancias de las clases
        ApiClient apiClient = new ApiClient();
        CurrencyConverter converter = new CurrencyConverter();

        try {
            // Obtener los datos de la API
            String jsonResponse = apiClient.getExchangeRates(baseCurrency);

            // Realizar la conversión usando Gson
            double convertedAmount = converter.convert(jsonResponse, targetCurrency, amount);

            // Mostrar el resultado
            System.out.printf("%.2f %s equivale a %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
