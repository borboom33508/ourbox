package ourbox.myfile.service;

import ourbox.service.ServiceList;
import ourbox.service.stock.service.model.Document;
import ourbox.service.stock.service.model.Mail;


import java.io.*;

public class DocumentFileDataSource implements MailDataSource{
    private String fileDirectoryName;
    private String fileName;
    private ServiceList serviceList;

    public DocumentFileDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Document document = new Document(data[0].trim(), data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim(),data[6].trim());
            serviceList.addService(document);
        }
        reader.close();
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

    @Override
    public ServiceList getAccountsData() {
        try {
            serviceList = new ServiceList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return serviceList;
    }

    @Override
    public void setAccountData(ServiceList documents) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Mail document: documents.toList()) {
                String line = document.getReceiverName() + ","
                        + document.getIdRoom() +","
                        + document.getSenderName()+ ","
                        + document.getSize()+","
                        + document.getImage() +","
                        + ((Document)document).getPrioritize()+","
                        + document.getAddTime();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }
}
