package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test class for {@link PetTypeFormatter}
 *
 * @author Colin But
 */
@ExtendWith(SpringExtension.class)
public class PetTypeFormatterTests {

    @Mock
    private ClinicService clinicService;

    private PetTypeFormatter petTypeFormatter;

    @BeforeEach
    public void setup() {
        petTypeFormatter = new PetTypeFormatter(clinicService);
    }

    @Test
    public void testPrint() {
        PetType petType = new PetType();
        petType.setName("Hamster");
        String petTypeName = petTypeFormatter.print(petType, Locale.ENGLISH);
        assertEquals("Hamster", petTypeName);
    }

    @Test
    public void shouldParse() throws ParseException {
        Mockito.when(clinicService.findPetTypes()).thenReturn(makePetTypes());
        PetType petType = petTypeFormatter.parse("Bird", Locale.ENGLISH);
        assertEquals("Bird", petType.getName());
    }

    @Test
    public void shouldThrowParseException() {
        Mockito.when(clinicService.findPetTypes()).thenReturn(makePetTypes());
        assertThrows(ParseException.class,() -> {
            petTypeFormatter.parse("Fish", Locale.ENGLISH);

        });
    }

    /**
     * Helper method to produce some sample pet types just for test purpose
     *
     * @return {@link Collection} of {@link PetType}
     */
    private Collection<PetType> makePetTypes() {
        Collection<PetType> petTypes = new ArrayList<>();
        petTypes.add(new PetType(){
            {
                setName("Dog");
            }
        });
        petTypes.add(new PetType(){
            {
                setName("Bird");
            }
        });
        return petTypes;
    }

}
