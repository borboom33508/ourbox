package ourbox.myfile.account;

import ourbox.account.account.model.VillagerAccount;
import ourbox.account.VillagerLoginLegit;

import java.io.*;

public class VillagerFileDataSource implements VillagerDataSource {
    private String fileDirectoryName;
    private String fileName;
    private VillagerLoginLegit villagerLegit;

    public VillagerFileDataSource(String fileDirectoryName, String fileName) {
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
            VillagerAccount villagerAcc = new VillagerAccount(data[0].trim(), data[1].trim(),data[2].trim(),data[3].trim(),
                    data[4].trim(),data[5].trim(),data[6].trim());
            villagerLegit.addVillagerAccount(villagerAcc);
        }
        reader.close();
    }

    @Override
    public VillagerLoginLegit getAccountsData() {
        try {
            villagerLegit = new VillagerLoginLegit();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return villagerLegit;
    }

    @Override
    public void setAccountData(VillagerLoginLegit villagerAccounts) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (VillagerAccount villagerAccount: villagerAccounts.toList()) {
                String line = villagerAccount.getVillagerName() + ","
                        + villagerAccount.getIdRoom() +","
                        + villagerAccount.getFloor() + ","
                        + villagerAccount.getTypeOfRoom() + ","
                        + villagerAccount.getVillagerUser() + ","
                        + villagerAccount.getVillagerPass() + ","
                        + villagerAccount.getRoomStatus();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
