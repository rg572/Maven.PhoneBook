package com.zipcodewilmington.phonebook;

import java.util.*;

/**
 * Created by leon on 1/23/18.
 */
public class PhoneBook {

    private Map<String, List<String>> phoneMap;

    public PhoneBook(Map<String, List<String>> map) {
        this.phoneMap = new LinkedHashMap<>();
        this.phoneMap.putAll(map);
    }

    public PhoneBook() {
        this.phoneMap = new LinkedHashMap<>();
    }

    public void add(String name, String phoneNumber) {
        List<String> newNumber = new ArrayList<>();
        newNumber.add(phoneNumber);
        phoneMap.merge(name, newNumber, (v1, v2)-> {v1.addAll(newNumber); return v1;});
    }

    public void addAll(String name, String... phoneNumbers) {
        for(String number : phoneNumbers){
            add(name, number);
        }
    }

    public void remove(String name) {
        phoneMap.remove(name);
    }

    public Boolean hasEntry(String number) {
        for(Map.Entry<String, List<String>> entry : phoneMap.entrySet()){
            if(entry.getValue().contains(number)){
                return true;
            }
        }
        return false;
    }

    public List<String> lookup(String name) {
        return phoneMap.get(name);
    }

    public String reverseLookup(String phoneNumber)  {
        for(Map.Entry<String, List<String>> entry : phoneMap.entrySet()){
            for(String number : entry.getValue()){
                if(number.equals(phoneNumber)){
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public List<String> getAllContactNames() {
        List<String> contacts = new ArrayList<>(phoneMap.keySet());
        return contacts;
    }

    public Map<String, List<String>> getMap() {
        Map<String, List<String>> returnMap = new HashMap<>();
        returnMap.putAll(phoneMap);
        return returnMap;
    }
}
