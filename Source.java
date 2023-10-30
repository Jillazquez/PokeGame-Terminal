import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Random;
import java.util.Scanner;

public class pokeapi {
    public static void main(String[] args) {
        try {
            // Obtener un Pokémon aleatorio de la PokeAPI
            String apiUrl = "https://pokeapi.co/api/v2/pokemon/";
            int randomPokemonId = new Random().nextInt(898) + 1; // 898 es el número total de Pokémon
            String randomPokemonUrl = apiUrl + randomPokemonId + "/";
            String pokemonName = getPokemonName(randomPokemonUrl);

            // Inicializar el juego
            int attempts = 6; // Número de intentos permitidos
            StringBuilder guessedName = new StringBuilder("_".repeat(pokemonName.length()));
            Scanner scanner = new Scanner(System.in);

            System.out.println("¡Bienvenido al juego de adivinanza de Pokémon!");
            System.out.println("Tienes " + attempts + " intentos para adivinar el nombre del Pokémon.");

            while (attempts > 0) {
                System.out.println("\nPokémon a adivinar: " + guessedName);
                System.out.print("Ingresa una letra o intenta adivinar el nombre completo: ");
                String guess = scanner.nextLine().toLowerCase();

                if (guess.equals(pokemonName)) {
                    System.out.println("¡Felicitaciones! Has adivinado el Pokémon correctamente: " + pokemonName);
                    break;
                } else if (guess.length() == 1 && pokemonName.contains(guess)) {
                    // Si la letra es parte del nombre del Pokémon
                    for (int i = 0; i < pokemonName.length(); i++) {
                        if (pokemonName.charAt(i) == guess.charAt(0)) {
                            guessedName.setCharAt(i, guess.charAt(0));
                        }
                    }
                } else {
                    System.out.println("Letra incorrecta. Tienes " + (--attempts) + " intentos restantes.");
                }

                if (guessedName.toString().equals(pokemonName)) {
                    System.out.println("¡Felicitaciones! Has adivinado el Pokémon correctamente: " + pokemonName);
                    break;
                }
            }

            if (attempts <= 0) {
                System.out.println("¡Agotaste tus intentos! El Pokémon era: " + pokemonName);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPokemonName(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            return json.getString("name");
        } else {
            throw new Exception("Error al obtener el Pokémon. Código de respuesta: " + responseCode);
        }
    }
}
