package edu.ezip.ing1.pds;

import java.util.*;

public class KnownDisplays {

    private List<String> ips = new ArrayList<>();
    private List<Integer> ports = new ArrayList<>();

    public void ajouter(String ip, int port) {
        ips.add(ip);
        ports.add(port);
    }

    public int size() {
        return ips.size();
    }

    public String getIp(int i) {
        return ips.get(i);
    }

    public int getPort(int i) {
        return ports.get(i);
    }
}
