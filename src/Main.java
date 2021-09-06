import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\nikolskaya\\Desktop\\Games";
        StringBuilder log = new StringBuilder();

        //src, res, savegames, temp
        File src = createDirectory("src", folderPath, log);
        File res = createDirectory("res", folderPath, log);
        createDirectory("savegames", folderPath, log);
        File temp = createDirectory("temp", folderPath, log);

        //main and utils for src
        File main = createDirectory("main", src.getAbsolutePath(), log);
        createDirectory("utils", src.getAbsolutePath(), log);

        //2 files for main;
        createFile("Main.java", main.getAbsolutePath(), log);
        createFile("Utils.java", main.getAbsolutePath(), log);

        //3 directories for res;
        createDirectory("drawables", res.getAbsolutePath(), log);
        createDirectory("vectors", res.getAbsolutePath(), log);
        createDirectory("icons", res.getAbsolutePath(), log);

        //for temp
        File tempTxt = createFile("temp.txt", temp.getAbsolutePath(), log);

        //writing in temp.txt
        try (FileWriter fileWriter = new FileWriter(tempTxt, false)) {
            fileWriter.write(log.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createDirectory(String dirName, String folderPath, StringBuilder log) {
        String finalPath = folderPath + "\\" + dirName;
        File file = new File(finalPath);
        if (file.mkdir()) {
            log.append("Директория ").append(file.getAbsoluteFile()).append(" успешно создана\n");
        } else {
            log.append("Create directory failed!\n");
        }
        return file;
    }

    public static File createFile(String fileName, String folderPath, StringBuilder log) {
        String finalPath = folderPath + "\\" + fileName;
        File file = new File(finalPath);
        try {
            if (file.createNewFile()) {
                log.append("Файл ").append(file.getAbsolutePath()).append(" успешно создан\n");
            } else {
                log.append("Create file failed!\n");
            }
        } catch (IOException e) {
            log.append("Something goes really wrong!\n");
        }
        return file;
    }
}
