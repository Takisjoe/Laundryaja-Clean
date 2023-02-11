package com.takisjoeapp.laundryaja.util.address;

import android.app.Application;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class AddressUtils {
    private final Application application;

    private final List<Address> provinsiList = new ArrayList<>();
    private final List<Address> kotaList = new ArrayList<>();
    private final List<Address> kecamatanList = new ArrayList<>();
    private final List<Address> kelurahanList = new ArrayList<>();


    private  Address addressProvinsi = new Address("11", null);
    private  Address addressKota = new Address("1101", null);
    private  Address addressKecamatan = new Address("110101", null);
    private  Address addressKelurahan = new Address("1101012001", null);
    private  String detailAddress = "";

    public AddressUtils(Application application) {
        this.application = application;
    }

    //Membaca file Json
    @Nullable
    private  String LoadJsonFromAsset(String namaFileJson) {
        String jsonBuffer;
        try {
            InputStream json = application.getAssets().open(namaFileJson + ".json");
            int size = json.available();
            byte[] buffer = new byte[size];
            json.read(buffer);
            json.close();
            jsonBuffer = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonBuffer;
    }

    public  List<Address> getAllProvinsi() {

        try {
            provinsiList.clear();
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(LoadJsonFromAsset("provinsi")));
            JSONArray jsonArray = jsonObject.getJSONArray("provinsi");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Address address = new Address();
                address.setId(object.getString("id"));
                address.setNama(object.getString("nama"));
                provinsiList.add(address);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return provinsiList;
    }

    public  List<Address> getAllKota() {

        try {
            kotaList.clear();
            JSONArray jsonArray = new JSONArray(LoadJsonFromAsset("kabupaten/" + addressProvinsi.getId()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Address address = new Address();
                address.setId(object.getString("id"));
                address.setNama(object.getString("nama"));
                kotaList.add(address);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kotaList;
    }

    public  List<Address> getAllKecamatan() {
        try {
            kecamatanList.clear();
            JSONArray jsonArray = new JSONArray(LoadJsonFromAsset("kecamatan/" + addressKota.getId()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Address address = new Address();
                address.setId(object.getString("id"));
                address.setNama(object.getString("nama"));
                kecamatanList.add(address);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kecamatanList;
    }

    public  List<Address> getAllKelurahan() {

        try {
            kelurahanList.clear();
            JSONArray jsonArray = new JSONArray(LoadJsonFromAsset("kelurahan/" + addressKecamatan.getId()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Address address = new Address();
                address.setId(object.getString("id"));
                address.setNama(object.getString("nama"));
                kelurahanList.add(address);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kelurahanList;
    }

    public static String parseText(Application app, String idAddress) {
        if (idAddress.isEmpty()) {
            return "";
        }
        AddressUtils utils = new AddressUtils(app);
        StringTokenizer tokenizer = new StringTokenizer(idAddress, "-");
        ArrayList<Address> list = new ArrayList<>();

        do {
            Address address = new Address(tokenizer.nextToken(), null);
            list.add(address);

        } while (tokenizer.hasMoreTokens());

        utils.addressProvinsi.setId(list.get(0).getId());
        for (int i = 0; i < utils.getAllProvinsi().size(); i++) {
            if (utils.getAllProvinsi().get(i).getId().contains(list.get(0).getId())) {
                list.get(0).setNama(utils.getAllProvinsi().get(i).getNama());
            }
        }
        utils.addressKota.setId(list.get(1).getId());
        for (int i = 0; i < utils.getAllKota().size(); i++) {
            if (utils.getAllKota().get(i).getId().contains(list.get(1).getId())) {
                list.get(1).setNama(utils.getAllKota().get(i).getNama());
            }
        }

        utils.addressKecamatan.setId(list.get(2).getId());
        for (int i = 0; i < utils.getAllKecamatan().size(); i++) {
            if (utils.getAllKecamatan().get(i).getId().contains(list.get(2).getId())) {
                list.get(2).setNama(utils.getAllKecamatan().get(i).getNama());
            }
        }

        utils.addressKelurahan.setId(list.get(3).getId());
        for (int i = 0; i < utils.getAllKelurahan().size(); i++) {
            if (utils.getAllKelurahan().get(i).getId().contains(list.get(3).getId())) {
                list.get(3).setNama(utils.getAllKelurahan().get(i).getNama());
            }
        }


        return list.get(0).getNama() + " - " + list.get(1).getNama() + " - " + list.get(2).getNama() + " - " + list.get(3).getNama() + " - " + list.get(4).getId().replace("#", "");
//        return x.addressProvinsi.getNama() + " - " + x.addressKota.getNama() + " - " + x.addressKecamatan.getNama() + " - " + x.addressKelurahan.getNama() + " - " + x.detailAddress;
    }

    public void setAddressProvinsi(Address addressProvinsi) {
        this.addressProvinsi = addressProvinsi;
    }

    public void setAddressKota(Address addressKota) {
        this.addressKota = addressKota;
    }

    public void setAddressKecamatan(Address addressKecamatan) {
        this.addressKecamatan = addressKecamatan;
    }

    public void setAddressKelurahan(Address addressKelurahan) {
        this.addressKelurahan = addressKelurahan;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Address getAddressProvinsi() {
        return addressProvinsi;
    }

    public Address getAddressKota() {
        return addressKota;
    }

    public Address getAddressKecamatan() {
        return addressKecamatan;
    }

    public Address getAddressKelurahan() {
        return addressKelurahan;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public String getIdSellected() {
        return addressProvinsi.getId() + "-" +
                addressKota.getId() + "-" +
                addressKecamatan.getId() + "-" +
                addressKelurahan.getId() + "-#" +
                detailAddress;
    }

    public String getAddress() {
        return addressProvinsi.getNama() + " - " + addressKota.getNama() + " - " + addressKecamatan.getNama() + " - " + addressKelurahan.getNama() + " - " + detailAddress;
    }

}
