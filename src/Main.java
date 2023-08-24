import javafx.print.Printer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.*;

public class Main {

    private static List<InputStreamWrapper> fillQRCodeTicketsToPrint() {
        InputStreamWrapper obj = new InputStreamWrapper();
        obj.setImgInputStream(GenerateQrCode.generateQRCode("12345678"));
        obj.setSn("34ASDL30ASLPE38");
        return Collections.singletonList(obj);
    }

    public static void main(String[] args) throws JRException {
        System.out.println("Hello world!");

        Map<String, Object> map = new HashMap<>();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fillQRCodeTicketsToPrint());


        PrintServiceByJasper.printQuitely(PrintServiceByJasper.createJasperPrint(ReportUtilNative.getReport("qrcode"), map, dataSource), Printer.getDefaultPrinter().getName());
    }
}