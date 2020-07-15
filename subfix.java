package com.bsodsoftware.subfix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class subfix {
    public static void main(String[] args) {
        List<String> timecodes = new ArrayList<>();
        int segundosASumar = 30;
        try {
            File subfile = new File("/home/m4v3r1cx/Videos/The.Orville.S01E10.HDTV.x264-SVA[eztv].srt");
            FileInputStream fis = new FileInputStream(subfile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains("-->")) {
                    timecodes.add(line);
                    line = bufferedReader.readLine();
                }
            }

            if (!timecodes.isEmpty()) {
                for (String l : timecodes) {
                    String[] partes = l.split("-->");
                    if (partes.length > 0) {
                        String sdesde = partes[0].trim();
                        String shasta = partes[1].trim();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss,SSS");
                        Date desde = sdf.parse(sdesde);
                        Date hasta = sdf.parse(shasta);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(desde);
                        cal.add(Calendar.SECOND, segundosASumar);
                        desde = cal.getTime();
                        sdesde = sdf.format(desde);
                        // Lo mismo con hasta y después reconstruis el archivo de subtitulo
                    } else {
                        System.out.println("La cagaste, no sirve separar por esa wea");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al tratar de arreglar la wea: ");
            ex.printStackTrace();
        }
    }
}
