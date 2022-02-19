package ourbox.myfile.account;

import ourbox.account.account.model.SystemAdminAccount;
import ourbox.account.SystemAdminLoginLegit;

import java.io.*;

public class SystemAdminFileDataSource implements SystemAdminDataSource{
    private String fileDirectoryName;
    private String fileName;
    private SystemAdminLoginLegit systemAdminLegit;

    public SystemAdminFileDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            SystemAdminAccount systemAdmin = new SystemAdminAccount(data[0].trim(), data[1].trim());
            systemAdminLegit.addSystemAdminAccount(systemAdmin);
        }
        reader.close();
    }

    @Override
    public SystemAdminLoginLegit getAccountsData() {
        try {
            systemAdminLegit = new SystemAdminLoginLegit();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return systemAdminLegit;
    }

    @Override
    public void setAccountData(SystemAdminLoginLegit systemAdminAccounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (SystemAdminAccount systemAdmin: systemAdminAccounts.toList()) {
                String line = systemAdmin.getSystemAdminUser() + ","
                        + systemAdmin.getSystemAdminPass() +",";
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
