package com.takisjoeapp.laundryaja.database.virtual;

import android.content.Context;


import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class VirtualDatabase {
    private final File file;

    private final List<VirtualDataModel> list = new ArrayList<>();

    public VirtualDatabase(Context context) {
        this.file = new File(context.getFilesDir(), "virtual-data.txt");
    }

    public void create(Map<String,Object> map) {
        list.clear();
        list.addAll(read());

        VirtualDataModel model = new VirtualDataModel();
        model.setId("ID-" + list.size());
        model.setText(null);
        model.setMap(map);

        list.add(model);
        writeToFile(machineWrite());
    }

    public List<VirtualDataModel> read() {
        return machineRead();
    }

    public void update(String id, Map<String,Object> map) {
        list.clear();
        list.addAll(read());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().contains(id)) {
                VirtualDataModel model = new VirtualDataModel();
                model.setId(list.get(i).getId());
                model.setMap(map);
                list.set(i, model);
            }
        }

        writeToFile("");
        writeToFile(machineWrite());
    }

    public void delete(String id) {
        list.clear();
        list.addAll(read());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().contains(id)) {
                list.remove(i);
            }
        }

        writeToFile("");
        writeToFile(machineWrite());
    }

    public void removeAll() {
        list.clear();
        writeToFile("");
    }

    private String machineWrite() {
        Gson gson = new Gson();
        String delim = "+";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i).getId()).append(delim);
            builder.append(list.get(i).getText()).append(delim);
            builder.append(gson.toJson(list.get(i).getObject()));
            builder.append("|");
        }

        return builder.toString();
    }

    private List<VirtualDataModel> machineRead() {
        Gson gson = new Gson();

        List<VirtualDataModel> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(readFromFile(), "|");
        while (tokenizer.hasMoreTokens()) {
            StringTokenizer tokenizer1 = new StringTokenizer(tokenizer.nextToken(), "+");
            VirtualDataModel model = new VirtualDataModel();
            do {
                model.setId(tokenizer1.nextToken());
                model.setText(tokenizer1.nextToken());
                Map<String, Object> map = gson.fromJson(tokenizer1.nextToken(), Map.class);
                model.setMap(map);
            } while (tokenizer1.hasMoreTokens());
            list.add(model);
        }

        return list;
    }

    private void writeToFile(String data) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Gagal : " + e.getMessage());
        }

    }

    private String readFromFile() {
        String content = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            content = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return content;
    }
}
