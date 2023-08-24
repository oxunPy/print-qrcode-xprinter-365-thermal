//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.print.PrinterJob;
import java.util.Map;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

public class PrintServiceByJasper {
    public PrintServiceByJasper() {
    }

    public static JasperPrint createJasperPrint(JasperReport report, Map<String, Object> properties, JRDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(report, properties, dataSource);
    }

    public static void printQuitely(JasperPrint jasperPrint, String printerName) throws JRException {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PrintService printService = printerJob.getPrintService();
        PrintService[] services = PrintServiceLookup.lookupPrintServices((DocFlavor)null, (AttributeSet)null);
        PrintService xp_365b = null;
        PrintService[] var6 = services;
        int var7 = services.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            PrintService service = var6[var8];
            if (service.getName().equals(printerName)) {
                printService = service;
                break;
            }
        }

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(new Copies(printerJob.getCopies()));
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService.getAttributes());
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
        exporter.exportReport();
    }
}
