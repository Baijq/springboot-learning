package com.biubiu.base.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.util.HashMap;

public class QRCodeUtil {

    private final static int WIDTH = 300;
    private final static int HEIGHT = 300;
    private final static String FORMAT = "png";
    private final static String CONTEXT = "https://www.cnblogs.com/baijinqiang";
    private final static String PATH = "./qrcode.png";

    /**
     * generalQrCode
     * @param context of the QR_CODE (default https://www.cnblogs.com/baijinqiang)
     * @param width the width of the QR_CODE (default 300)
     * @param height the height of the QR_CODE (default 300)
     * @param path generalQrCode save on the path (default ./qrcode.png)
     */
    public static void generalQrCode(String context, int width, int height, String path) {
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, new File(path).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * generalQrCode
     * @param context of the QR_CODE (default https://www.cnblogs.com/baijinqiang)
     * @param path generalQrCode save on the path (default ./qrcode.png)
     */
    public static void generalQrCode(String context, String path) {
        generalQrCode(context, WIDTH, HEIGHT, path);
    }

    /**
     * generalQrCode
     * @param context of the QR_CODE (default https://www.cnblogs.com/baijinqiang)
     */
    public static void generalQrCode(String context) {
        generalQrCode(context, PATH);
    }

    /** generalQrCode **/
    public static void generalQrCode() {
        generalQrCode(CONTEXT);
    }

}