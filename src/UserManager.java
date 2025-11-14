import java.io.*;
import java.util.*;

public class UserManager {
    private static final String FILE_PATH = "users.txt";

    // Generowanie kolejnego userID w formacie 0001, 0002, ...
    public static String generateUserID() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        if (id > lastId) lastId = id;
                    } catch (NumberFormatException e) {}
                }
            }
        } catch (IOException e) {
            // Plik może nie istnieć
        }
        return String.format("%04d", lastId + 1);
    }

    // Zapis użytkownika do pliku
    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sprawdzenie, czy login już istnieje
    public static boolean isUserExists(String login) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[1].equals(login)) return true;
            }
        } catch (IOException e) {}
        return false;
    }

    // Sprawdzenie loginu i hasła, zwraca Role
    public static Role checkUser(String login, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(login) && parts[2].equals(password)) {
                    try {
                        return Role.valueOf(parts[3]);
                    } catch (IllegalArgumentException e) {
                        return Role.USER;
                    }
                }
            }
        } catch (IOException e) {}
        return null;
    }
}
