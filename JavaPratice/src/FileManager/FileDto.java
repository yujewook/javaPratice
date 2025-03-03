package FileManager;

import java.util.ArrayList;
import java.util.List;

public class FileDto {
    private String inputFilePath;
    private String outputDirPath;
    private String directory;
    private ArrayList<String> txtData;
    private List<String> sortFields;
    private List<Boolean> isAscending;
    private List<String[]> excelData;

    // Getter and Setter methods

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputDirPath() {
        return outputDirPath;
    }

    public void setOutputDirPath(String outputDirPath) {
        this.outputDirPath = outputDirPath;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public ArrayList<String> getTxtData() {
        return txtData;
    }

    public void setTxtData(ArrayList<String> txtData) {
        this.txtData = txtData;
    }

    public List<String> getSortFields() {
        return sortFields;
    }

    public void setSortFields(List<String> sortFields) {
        this.sortFields = sortFields;
    }

    public List<Boolean> getIsAscending() {
        return isAscending;
    }

    public void setIsAscending(List<Boolean> isAscending) {
        this.isAscending = isAscending;
    }

    public List<String[]> getExcelData() {
        return excelData;
    }

    public void setExcelData(List<String[]> excelData) {
        this.excelData = excelData;
    }
}
