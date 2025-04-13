package FileConVert;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

public class PdfSplitter {
    public void split(String inputPdf, String outputPdf, int startPage, int endPage) throws Exception {
        try (PDDocument sourceDoc = PDDocument.load(new File(inputPdf));
             PDDocument targetDoc = new PDDocument()) {

            int totalPages = sourceDoc.getNumberOfPages();
            startPage = Math.max(1, startPage);
            endPage = Math.min(endPage, totalPages);

            for (int i = startPage - 1; i < endPage; i++) {
                targetDoc.addPage(sourceDoc.getPage(i));
            }

            targetDoc.save(outputPdf);
        }
    }
}
