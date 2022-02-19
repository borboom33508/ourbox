package ourbox.myfile.account;

import ourbox.account.account.model.AdminAccount;
import ourbox.account.AdminLoginLegit;

import java.io.*;

public class AccountFileDataSource implements AccountDataSource {
    private String fileDirectoryName;
    private String fileName;
    private AdminLoginLegit loginLegit;

    public AccountFileDataSource(String fileDirectoryName, String fileName) {
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
            AdminAccount admin = new AdminAccount(data[0].trim(), data[1].trim() ,data[2].trim());
            admin.setTime(data[3].trim());
            loginLegit.addAccount(admin);
        }
        reader.close();
    }

    @Override
    public AdminLoginLegit getAccountsData() {
        try {
            loginLegit = new AdminLoginLegit();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return loginLegit;
    }

    @Override
    public void setAccountData(AdminLoginLegit accounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (AdminAccount admin: accounts.toList()) {
                String line = admin.getFirstName() + ","
                        + admin.getUser() + ","
                        + admin.getPass() +","
                        + admin.getTime();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }

    }
}
