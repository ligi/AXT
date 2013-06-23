package org.ligi.androidhelper.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLHelper {

    private final URL url;

    public URLHelper(URL url) {
        this.url=url;
    }

     public String downloadToString() {

                URLConnection con;
                InputStream in = null;
                try {
                        con = url.openConnection();
                        in = con.getInputStream();
                } catch (IOException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                }

                BufferedReader reader = null;
                try {
                        reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                } catch (Exception e3) {
                        e3.printStackTrace();
                        return null;
                }
                StringBuilder sb = new StringBuilder();
                String line = null;

                try {
                        while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                        }
                } catch (IOException e3) {
                }

                return sb.toString();

        }

}
