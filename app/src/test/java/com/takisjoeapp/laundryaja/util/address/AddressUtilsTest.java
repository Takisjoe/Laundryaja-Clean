package com.takisjoeapp.laundryaja.util.address;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class AddressUtilsTest extends TestCase {

    private List<Address> listProvinsi() {
        List<Address> listAddress = new ArrayList<>();
        System.out.println("----- Daftar Provinsi -----");
        for (int i = 11; i < 15; i++) {
            Address address = new Address();
            address.setId(String.valueOf(i));
            address.setNama("NamaProvinsi-" + i);
            listAddress.add(address);
            System.out.println((i + 1) + ". " + address.getId() + " | " + address.getNama());
        }

        return listAddress;
    }

    private List<Address> listKota(String idProvinsi) {
        List<Address> listAddress = new ArrayList<>();
        System.out.println("----- Daftar Kota -----");
        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setId(idProvinsi + i);
            address.setNama("NamaKota-" + (i + 1));
            listAddress.add(address);
            System.out.println((i + 1) + ". " + address.getId() + " | " + address.getNama());
        }

        return listAddress;
    }

    private List<Address> listKecamatan(String idKota) {
        List<Address> listAddress = new ArrayList<>();
        System.out.println("----- Daftar Kota -----");
        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setId(idKota + i);
            address.setNama("NamaKota-" + (i + 1));
            listAddress.add(address);
            System.out.println((i + 1) + ". " + address.getId() + " | " + address.getNama());
        }
        return listAddress;
    }

    private List<Address> listKelurahan(String idKecamatan) {
        List<Address> listAddress = new ArrayList<>();
        System.out.println("----- Daftar Kota -----");
        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setId(idKecamatan + i);
            address.setNama("NamaKota-" + (i + 1));
            listAddress.add(address);
            System.out.println((i + 1) + ". " + address.getId() + " | " + address.getNama());
        }
        return listAddress;
    }

//    public Address getProvinsi(String idProvinsi) {
//
//    }

    public void testCreateData() {
//      11-01022001 prov
//      1101-022001 kota
//      110102-2001 kec
//      1101022001 kel

        String idProvinsi = "110";
        String idKota = "11050";
        String idKecamatan = "11050200";
        String idKelurahan = "";

        listProvinsi();
        listKota("110");
        listKecamatan("11050");
        listKelurahan("11050200");

        for (int i = 0; i < listProvinsi().size(); i++) {
            if (listProvinsi().get(i).getId().contains("11")) {
                System.out.println(listProvinsi().get(i).getNama());
            }
        }

    }
}