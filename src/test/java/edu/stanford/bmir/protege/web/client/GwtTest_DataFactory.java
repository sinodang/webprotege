package edu.stanford.bmir.protege.web.client;

import com.google.common.base.Optional;
import com.google.gwt.junit.client.GWTTestCase;
import edu.stanford.bmir.protege.web.shared.DataFactory;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * @author Matthew Horridge, Stanford University, Bio-Medical Informatics Research Group, Date: 04/04/2014
 */
public class GwtTest_DataFactory extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "edu.stanford.bmir.protege.web.WebProtegeJUnit";
    }

    @Override
    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        delayTestFinish(10000);
    }

    public void test_shouldParseInteger() {
        OWLLiteral literal = DataFactory.parseLiteral("3", Optional.<String>absent());
        assertEquals(literal.getLiteral(), "3");
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_INTEGER.getIRI());
        finishTest();
    }

    public void test_shouldParseUntrimmedInteger() {
        OWLLiteral literal = DataFactory.parseLiteral(" 3 ", Optional.<String>absent());
        assertEquals(literal.getLiteral(), "3");
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_INTEGER.getIRI());
        finishTest();
    }

    public void test_shouldParseIntegerLexicalFormWithLanguageTagAsRDFPlainLiteral() {
        OWLLiteral literal = DataFactory.parseLiteral("3", Optional.of("en"));
        assertEquals(literal.getLiteral(), "3");
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.RDF_PLAIN_LITERAL.getIRI());
        finishTest();
    }

    public void test_shouldParseDecimal() {
        OWLLiteral literal = DataFactory.parseLiteral("3.3", Optional.<String>absent());
        assertEquals("3.3", literal.getLiteral());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_DECIMAL.getIRI());
        finishTest();
    }

    public void test_shouldParseUntrimmedDecimal() {
        OWLLiteral literal = DataFactory.parseLiteral(" 3.3 ", Optional.<String>absent());
        assertEquals("3.3", literal.getLiteral());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_DECIMAL.getIRI());
        finishTest();
    }

    public void test_shouldParseDecimalLexicalFormWithLanguageTagAsRDFPlainLiteral() {
        OWLLiteral literal = DataFactory.parseLiteral("3.3", Optional.of("en"));
        assertEquals("3.3", literal.getLiteral());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.RDF_PLAIN_LITERAL.getIRI());
        finishTest();
    }

    public void test_shouldParseFloat() {
        OWLLiteral literal = DataFactory.parseLiteral("3.3f", Optional.<String>absent());
        // The OWL API strips off the f, apparently
        assertEquals("3.3", literal.getLiteral());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_FLOAT.getIRI());
        finishTest();
    }

    public void test_shouldParseUntrimmedFloat() {
        OWLLiteral literal = DataFactory.parseLiteral(" 3.3f ", Optional.<String>absent());
        // The OWL API strips off the f, apparently
        assertEquals("3.3", literal.getLiteral());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_FLOAT.getIRI());
        finishTest();
    }

    public void test_shouldParseFloatLexicalFormWithLanguageTagAsRDFPlainLiteral() {
        OWLLiteral literal = DataFactory.parseLiteral("3.3f", Optional.of("en"));
        assertEquals("3.3f", literal.getLiteral());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.RDF_PLAIN_LITERAL.getIRI());
        finishTest();
    }

    public void test_shouldParseYYYYMMDDAsDateTime() {
        OWLLiteral literal = DataFactory.parseLiteral("2014-04-30", Optional.<String>absent());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_DATE_TIME.getIRI());
        finishTest();
    }

    public void test_shouldParseYYYYMMDDTHHColonMMAsDateTime() {
        OWLLiteral literal = DataFactory.parseLiteral("2014-04-30T12:15:00", Optional.<String>absent());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_DATE_TIME.getIRI());
        finishTest();
    }

    public void test_shouldParseYYYYMMDDSpaceHHColonMMAsDateTime() {
        OWLLiteral literal = DataFactory.parseLiteral("2014-04-30 12:15:00", Optional.<String>absent());
        assertEquals(literal.getDatatype().getIRI(), OWL2Datatype.XSD_DATE_TIME.getIRI());
        finishTest();
    }


    public void test_getFreshEntityReturnsFreshEntity() {
        OWLClass cls = DataFactory.getFreshOWLEntity(EntityType.CLASS, "X");
        assertTrue(DataFactory.isFreshEntity(cls));
        assertEquals("X", DataFactory.getFreshEntityShortName(cls));
        finishTest();
    }
}
