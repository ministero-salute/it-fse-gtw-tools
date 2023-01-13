package org.apache.pdfbox.signature;

import java.util.Map;
import java.util.Hashtable;
import org.bouncycastle.asn1.cms.AttributeTable;
//BC
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;

public class PAdESSignedAttributeTableGenerator extends DefaultSignedAttributeTableGenerator {

    public PAdESSignedAttributeTableGenerator(
            AttributeTable attributeTable) {
        super(attributeTable);
    }

    /**
     * Remove SigningTime Attribute from standard creation
     *
     * @param parameters
     * @return
     */
    @Override
    protected Hashtable createStandardAttributeTable(Map parameters) {
        Hashtable result = super.createStandardAttributeTable(parameters);
        result.remove(CMSAttributes.signingTime);
        // Per qualche validatore
        result.remove(CMSAttributes.cmsAlgorithmProtect);
        return result;
    }
}
