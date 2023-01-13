package org.apache.pdfbox.signature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;

 
public class CreateSignaturePades extends CreateSignatureBasePades {

    public CreateSignaturePades(KeyStore keystore, char[] pin)
            throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, CertificateException, IOException
    {
        super(keystore, pin);
    }
 
    public void signDetached(File file) throws IOException
    {
        signDetached(file, file, null);
    }

     
    public void signDetached(File inFile, File outFile) throws IOException {
        signDetached(inFile, outFile, null);
    }
 
    public void signDetached(File inFile, File outFile, String tsaUrl) throws IOException {
        if (inFile == null || !inFile.exists())
        {
            throw new FileNotFoundException("Document for signing does not exist");
        }
        
        setTsaUrl(tsaUrl);

        // sign
        try (FileOutputStream fos = new FileOutputStream(outFile);
                PDDocument doc = PDDocument.load(inFile)) {
            signDetached(doc, fos);
        }
    }

    public void signDetached(PDDocument document, OutputStream output) throws IOException {
        int accessPermissions = SigUtils.getMDPPermission(document);
        if (accessPermissions == 1)
        {
            throw new IllegalStateException("No changes to the document are permitted due to DocMDP transform parameters dictionary");
        }     

        // create signature dictionary
        PDSignature signature = new PDSignature();
        signature.setFilter(PDSignature.FILTER_ADOBE_PPKLITE);
        signature.setSubFilter(PDSignature.SUBFILTER_ETSI_CADES_DETACHED);
        signature.setName("PNDGC");
        signature.setLocation("Roma, IT");
        signature.setReason("Firma Digitale");

        // the signing date, needed for valid signature
        signature.setSignDate(Calendar.getInstance());

        if (isExternalSigning()) {
            document.addSignature(signature);
            ExternalSigningSupport externalSigning = document.saveIncrementalForExternalSigning(output);
            // invoke external signature service
            byte[] cmsSignature = sign(externalSigning.getContent());
            // set signature bytes received from the service
            externalSigning.setSignature(cmsSignature);
        } else {
            SignatureOptions signatureOptions = new SignatureOptions();
            // Size can vary, but should be enough for purpose.
            signatureOptions.setPreferredSignatureSize(SignatureOptions.DEFAULT_SIGNATURE_SIZE * 2);
            // register signature dictionary and sign interface
            document.addSignature(signature, this, signatureOptions);

            // write incremental (only for signing purpose)
            document.saveIncremental(output);
        }
    }
 
}
