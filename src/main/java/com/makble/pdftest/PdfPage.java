package com.makble.pdftest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfPage {

	public static void main ( String [] args ) {
		PdfPage instance = new PdfPage();
		String filename = "doc.pdf";
		instance.getPageText(filename);
	}
	
	public void getPageText( String filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			PDFParser parser = new PDFParser( fis );
			parser.parse();
			PDDocument pdfDocument = parser.getPDDocument();			
			PDDocumentCatalog dc = pdfDocument.getDocumentCatalog();			
			int numPages = dc.getAllPages().size();
			
			System.out.println("num of pages: " + numPages );
			
			PDFTextStripper stripper = new PDFTextStripper ();
						
			// get text of a certain page
			stripper.setStartPage(1);
			stripper.setEndPage(1);
			String content = stripper.getText(pdfDocument);
			System.out.println(content);
			
			// loop through a range of pages
			for ( int i = 1 ; i < 3 ; i++ ) {
				stripper.setStartPage(i);
				stripper.setEndPage(i);
				content = stripper.getText(pdfDocument);
				System.out.println("Page : " + i);
				System.out.println(content);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
