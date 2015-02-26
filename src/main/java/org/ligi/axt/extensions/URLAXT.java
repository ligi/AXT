package org.ligi.axt.extensions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLAXT {

    private final URL url;

    public URLAXT(URL url) {
        this.url = url;
    }

    public String downloadToString() {
        try {
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            try {
                return readToStringAsUtf8(in);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            return null;
        }
    }

    private String readToStringAsUtf8(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        try {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();
        } finally {
            reader.close();
        }
    }

}
