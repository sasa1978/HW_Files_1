import java.io.*;

public class Main {
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        File games = new File("C:\\\\Games");

        File src = mkdir(games.getPath() + "\\src");
        if (src != null) {
            File main = mkdir(src.getPath() + "\\main");
            if (main != null) {
                mkfile(main.getPath() + "\\Main.java");
                mkfile(main.getPath() + "\\Utils.java");
            }
            mkdir(src.getPath() + "\\test");
        }

        File res = mkdir(games.getPath() + "\\res");
        if (res != null) {
            mkdir(res.getPath() + "\\drawables");
            mkdir(res.getPath() + "\\vectors");
            mkdir(res.getPath() + "\\icons");
        }

        mkdir(games.getPath() + "\\savegames");

        File temp = mkdir(games.getPath() + "\\temp");
        if (temp != null) {
            try (FileWriter writer = new FileWriter(temp.getPath() + "\\temp.txt", false)) {
                result.append("Лог полностью сохранен в файл temp.txt");
                writer.write(result.toString());
                writer.flush();
                System.out.println("Результат успешно записан в файл " + temp.getPath() + "\\temp.txt");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.out.println("\n\n" + result.toString());
            }
        } else {
            System.out.println(result.toString());
        }
    }

    public static File mkdir(String path) {
        File dir = new File(path);

        if (!dir.mkdir()) {
            result.append("Ошибка создании папки ").append(path).append("\n");
            return null;
        }

        result.append("Создана папка ").append(path).append("\n");
        return dir;
    }

    public static File mkfile(String path) {
        File file = new File(path);
        Boolean resultCreate = false;
        String error = "";

        try {
            resultCreate = file.createNewFile();
        } catch (IOException ex) {
            error = ex.getMessage();
        }

        if (!resultCreate) {
            result.append("Ошибка при создании файла ").append(path).append(". ").append(error).append("\n");
            return null;
        }

        result.append("Создан файл ").append(path).append("\n");
        return file;
    }
}
