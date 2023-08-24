//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.io.IOUtils;

public class ReportUtilNative {
    private static final String DEFAULT_REPORT_DIR = "/jrxml/";
    private static final String COMPILE_REPORT_DIR = System.getProperty("user.dir") + "/printer layout/";

    public ReportUtilNative() {
    }

    public static JasperReport getReport(String reportName) {
        try {
            File file = new File(COMPILE_REPORT_DIR + reportName + ".jasper");
            return file.exists() ? getUserReport(reportName) : compileReport(reportName);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    private static JasperReport compileReport(String reportName) throws Exception {
        InputStream in = ReportUtilNative.class.getResourceAsStream("/jrxml/" + reportName + ".jrxml");
        return compileReportByInputStream(reportName, in);
    }

    private static JasperReport compileReportByInputStream(String reportName, InputStream in) throws Exception {
        InputStream outputInputStream = null;
        FileOutputStream out = null;
        File jasperFile = null;
        if (in == null) {
            return null;
        } else {
            JasperReport var6;
            try {
                File dir = new File(COMPILE_REPORT_DIR);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                jasperFile = new File(dir, reportName + ".jasper");
                out = new FileOutputStream(jasperFile);
                JasperCompileManager.compileReportToStream(in, out);
                outputInputStream = Files.newInputStream(jasperFile.toPath());
                var6 = (JasperReport)JRLoader.loadObject(outputInputStream);
            } catch (Exception var10) {
                IOUtils.closeQuietly(out);
                if (jasperFile != null) {
                    jasperFile.delete();
                }

                var10.printStackTrace();
                throw var10;
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(outputInputStream);
                IOUtils.closeQuietly(out);
            }

            return var6;
        }
    }

    public static JasperReport getUserReport(String reportName) {
        InputStream resource = null;

        Object var3;
        try {
            resource = Files.newInputStream(new File(COMPILE_REPORT_DIR + reportName + ".jasper").toPath());
            JasperReport var2 = (JasperReport)JRLoader.loadObject(resource);
            return var2;
        } catch (Exception var7) {
            var3 = null;
        } finally {
            IOUtils.closeQuietly(resource);
        }

        return (JasperReport)var3;
    }

    public static InputStream extractPrintImageAsInputStream(JasperPrint jasperPrint, int pageIndex, float zoom) {
        String extension = "jpg";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream is = null;

        try {
            BufferedImage image = (BufferedImage)JasperPrintManager.printPageToImage(jasperPrint, pageIndex, zoom);
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (JRException | IOException var7) {
            var7.printStackTrace();
        }

        return is;
    }
}
