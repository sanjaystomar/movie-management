package com.home.selfLearning.projectOne.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileRenameService {


    public static void main(String[] args) throws IOException {
/*

//Method to rename files
        processFileRename();
//Method to rename the directories with logic of handling multiple files in one directory >> some bug is there
        Map<String, List<FileDataToRename>> collectMap = getDirectoryRenameMap();
        System.out.println(collectMap.size());
        renameFolders(collectMap);
*/
        processDeleteFiles();
    }

    private static void processDeleteFiles() throws IOException {
        Files
                .readAllLines(Path.of("C:\\Users\\sanja\\Downloads\\MOZILLA_DOWNLOADS\\techVideos\\HOME_LEARNING\\projectOne\\src\\main\\resources\\OutputFile.csv"))
                .stream()
                .map(f -> f.split(","))
                .map(t -> FileDataToRename
                        .builder()
                        .pathToFile(t[1])
                        .build())
                .forEach(e -> deleteFiles(e));
    }

    private static void processFileRename() throws IOException {
        Files
                .readAllLines(Path.of("C:\\Users\\sanja\\Downloads\\MOZILLA_DOWNLOADS\\techVideos\\HOME_LEARNING\\projectOne\\src\\main\\resources\\MOVIE_LIST_TO_WORK.csv"))
                .stream()
                .map(f -> f.split(","))
                .map(t -> FileDataToRename
                        .builder()
                        .oldName(t[1])
                        .pathToFile(t[1].substring(0, t[1].lastIndexOf("\\") + 1))
                        .newName(t[1].substring(0, t[1].lastIndexOf("\\") + 1).concat(t[0]))
                        .build())
                .forEach(e -> renameFiles(e));
    }

    private static Map<String, List<FileDataToRename>> getDirectoryRenameMap() throws IOException {
        Map<String, List<FileDataToRename>> collectMap = Files
                .readAllLines(Path.of("C:\\Users\\sanja\\Downloads\\MOZILLA_DOWNLOADS\\techVideos\\HOME_LEARNING\\projectOne\\src\\main\\resources\\OutputFile.csv"))
                .stream()
                .map(f -> f.split(","))
                .map(k -> new String[]{k[0], k[1].substring(0, k[1].lastIndexOf("\\")), k[1].substring(k[1].lastIndexOf("\\") + 1)})
                .map(t -> FileDataToRename
                        .builder()
                        .oldName(t[2])
                        .pathToFile(t[1])
                        .newName(t[0])
                        .build())
                .collect(Collectors.groupingBy(FileDataToRename::getPathToFile));
        return collectMap;
    }

    private static void renameFolders(Map<String, List<FileDataToRename>> collectMap) {
        collectMap.entrySet()
                .stream()
                .filter(e -> e.getValue().size() == 1)
                .forEach(f -> renameFiles(f.getValue().get(0)));
    }

    private static void renameFiles(FileDataToRename fileDataToRename) {
        File oldFile = new File(fileDataToRename.getPathToFile());
        File newFile = new File(fileDataToRename.getPathToFile().substring(0, fileDataToRename.getPathToFile().lastIndexOf("\\") + 1).concat(fileDataToRename.getNewName().substring(0, fileDataToRename.getNewName().lastIndexOf("."))));
        System.out.println("================");
        System.out.println("oldFile : " + oldFile);
        System.out.println("newFile : " + newFile);
//        boolean renameStatus = oldFile.renameTo(newFile);
//        System.out.println(oldFile + " rename status is : " + renameStatus);
    }

    private static void deleteFiles(FileDataToRename fileDataToRename) {
        File oldFile = new File(fileDataToRename.getPathToFile());
        System.out.println("================");
        System.out.println("oldFile : " + oldFile);
        boolean deleteStatus =oldFile.delete();
        System.out.println(oldFile + " rename status is : " + deleteStatus);
    }






   /* public static void main(String[] args) {
        List<String> rawDataForRename = new ArrayList<>();

        List<String> pathToExploreList = Arrays.asList("C:\\Users\\sanja\\Desktop");
        FileCrawler fileCrawler = new FileCrawler();
        pathToExploreList
                .stream()
                .forEach(e -> {
                    Path startPath = Paths.get(e);
                    System.out.println(startPath);
                    try {
//                        List<String> fileDetailsToRename =
                                Files.walk(startPath, 1)
                                .filter(fn -> fn.getFileName().toString().contains("."))

                                        .peek(p -> p.toFile().getCanonicalFile())
                                .filter(f -> "txt".equals(f.getFileName().toString().substring(f.getFileName().toString().lastIndexOf(".") + 1)))
                                .map(f -> f.toFile().getName() + "," + f + "," + f.toFile().length() / (1024))
//                                .collect(Collectors.toList());
//                        rawDataForRename.addAll(fileDetailsToRename);


                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

        rawDataForRename.stream()
                .map(e -> e.substring(0, e.indexOf(',', 40)))
                .map(f -> FileDataToRename.builder()
                        .newName("1_".concat(f.split(",")[0]))
                        .oldName(f.split(",")[1].substring(f.split(",")[1].lastIndexOf("\\")+1))
                        .pathToFile((f.split(",")[1]).substring(0,f.split(",")[1].lastIndexOf("\\")+1))
                        .build())
                .forEach( e -> FileRenameService.renameFiles(e));
    }
    private static void renameFiles(FileDataToRename fileDataToRename) {
        File oldFile = new File(fileDataToRename.getPathToFile().concat(fileDataToRename.getOldName()));
        File newFile = new File(fileDataToRename.getPathToFile().concat(fileDataToRename.getNewName()));
        System.out.println("================");
        boolean renameStatus = oldFile.renameTo(newFile);
        System.out.println(oldFile +"rename status is : " +renameStatus);
    }
   */


}

@Getter
@Setter
@Builder
@ToString
class FileDataToRename {
    private String oldName;
    private String newName;
    private String pathToFile;
}
