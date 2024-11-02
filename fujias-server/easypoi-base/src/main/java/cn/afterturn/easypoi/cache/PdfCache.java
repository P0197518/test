package cn.afterturn.easypoi.cache;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.kernel.pdf.PdfReader;

import cn.afterturn.easypoi.cache.manager.POICacheManager;

/**
 * @author jueyue on 20-8-22.
 */
public class PdfCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfCache.class);

    public static PdfReader getDocument(String url) {
        InputStream is = null;
        try {
            is = POICacheManager.getFile(url);
            PdfReader pdfReader = new PdfReader(is);
            return pdfReader;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
